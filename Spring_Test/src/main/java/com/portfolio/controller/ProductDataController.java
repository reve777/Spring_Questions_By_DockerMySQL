package com.portfolio.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.component.ScheduleData;
import com.portfolio.entity.PriceChangeResponse;
import com.portfolio.entity.ProductData;
import com.portfolio.service.PriceChangeCalculator;
import com.portfolio.service.ProductDataService;
import com.portfolio.service.ProductService;

@RestController
@RequestMapping("/api/forex")
public class ProductDataController {

	@Autowired
	private ProductService productService;
	@Autowired
	private ProductDataService productDataService;

	@Autowired
	private ScheduleData data;

	@Autowired
	private PriceChangeCalculator priceChangeCalculator;

	@GetMapping("/create")
	public boolean get() {
		try {
			data.getData();
			System.out.println("success");
		} catch (Exception e) {

		}
		return true;
	}

	@PostMapping("/addUpdate")
	public ProductData createOrUpdateProduct2(@RequestBody ProductData productData) {
		ProductData savedProduct = productDataService.saveProduct(productData);
		return savedProduct;
	}

	@GetMapping("/getAllProductData")
	public List<ProductData> getAllProductData() {
		return productDataService.getAllProductData();
	}

	@GetMapping("/getProductData/{id}")
	public ProductData getProductDataById(@PathVariable Integer id) {
		ProductData product = productDataService.getProductDataById(id);
		return product;
	}

	@DeleteMapping("/deleteProductData/{id}")
	public String deleteProductDataById(@PathVariable Integer id) {
		productDataService.deleteProductDataById(id);
		return "ProductData with ID " + id + " has been deleted successfully.";
	}

	@GetMapping("/getProductPriceOnDate")
	public Double getProductPriceOnDate(@RequestParam Integer productId, @RequestParam String date) {
		return productDataService.getProductPriceOnDate(productId, date);
	}

	@PutMapping("/update")
	public ResponseEntity<String> updateProductPrice(@RequestParam Integer productId, @RequestParam String dateString,
			@RequestParam Double newPrice) {
		String result = productDataService.updateProductPrice(productId, dateString, newPrice);

		if ("Price updated successfully.".equals(result)) {
			return ResponseEntity.ok(result);
		} else {
			return ResponseEntity.badRequest().body(result);
		}
	}

	@PostMapping("/add")
	public ResponseEntity<String> addProductPrice(@RequestParam Integer productId, @RequestParam String dateString,
			@RequestParam Double price) {

		String result = productDataService.addProductPrice(productId, dateString, price);

		if ("Price added successfully.".equals(result)) {
			return ResponseEntity.ok(result);
		} else {
			return ResponseEntity.badRequest().body(result);
		}
	}

	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteProductPrice(@RequestParam Integer productId, @RequestParam String dateString) {

		String result = productDataService.deleteProductPrice(productId, dateString);

		if ("Price deleted successfully.".equals(result)) {
			return ResponseEntity.ok(result);
		} else {
			return ResponseEntity.badRequest().body(result);
		}
	}

	@GetMapping("/calculate")
	public ResponseEntity<PriceChangeResponse> calculatePriceChange(@RequestParam Integer productId,
			@RequestParam String startDate, @RequestParam String endDate) {

		try {
			Date startTime = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
			Date endTime = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);

			Double priceChange = priceChangeCalculator.calculatePriceChange(productId, startTime, endTime);
			Double priceChangePercentage = priceChangeCalculator.calculatePriceChangePercentage(productId, startTime,
					endTime);
			Double openingPrice = priceChangeCalculator.getOpeningPrice(productId, startTime);
			Double closingPrice = priceChangeCalculator.getClosingPrice(productId, endTime);

			PriceChangeResponse response = new PriceChangeResponse(priceChange, priceChangePercentage, openingPrice,
					closingPrice);

			return ResponseEntity.ok(response);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(null);
		}
	}
}
