package com.portfolio.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolio.entity.Product;
import com.portfolio.entity.ProductData;
import com.portfolio.entity.ProductDataPrice;
import com.portfolio.entity.ProductPrice;
import com.portfolio.repository.ProductPriceRepository;
import com.portfolio.repository.ProductRepository;

@Service
public class ProductDataService {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private ProductPriceRepository productPriceRepository;

	// 創建或更新商品
	public ProductData saveProduct(ProductData productData) {
		try {
			Product product = new Product(productData);
			productRepository.save(product);

			Integer id = productData.getId();
			List<ProductDataPrice> lists = productData.getDatas();
			List<ProductPrice> productPrices = new ArrayList<>();
			for (ProductDataPrice list : lists) {
				ProductPrice productPrice = new ProductPrice(id, list);
				productPrices.add(productPrice);
			}
			productPriceRepository.saveAll(productPrices);
		} catch (Exception e) {
		}
		return null;
	}

	public ProductData getProductDataById(Integer id) {
		Optional<Product> product = productRepository.findById(id);

		List<ProductPrice> productPrice = productPriceRepository.findByProductid(id);

		List<ProductDataPrice> productDataPrices = new ArrayList<>();
		for (ProductPrice p : productPrice) {
			ProductDataPrice productDataPrice = new ProductDataPrice(p);
			productDataPrices.add(productDataPrice);
		}

		ProductData productData = new ProductData(product.get(), productDataPrices);

		return productData;
	}

	public List<ProductData> getAllProductData() {
		List<Product> products = productRepository.findAll();

		List<ProductData> productDataList = new ArrayList<>();

		for (Product product : products) {
			List<ProductPrice> productPrices = productPriceRepository.findByProductid(product.getId());

			List<ProductDataPrice> productDataPrices = new ArrayList<>();
			for (ProductPrice p : productPrices) {
				productDataPrices.add(new ProductDataPrice(p));
			}

			ProductData productData = new ProductData(product, productDataPrices);
			productDataList.add(productData);
		}

		return productDataList;
	}

	@Transactional
	public void deleteProductDataById(Integer id) {
		productPriceRepository.deleteByProductid(id);
		productRepository.deleteById(id);
	}

	public Double getProductPriceOnDate(Integer productId, String dateString) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}

		Optional<ProductPrice> productPrice = productPriceRepository.findByProductidAndDate(productId, date);

		if (productPrice.isPresent()) {
			return productPrice.get().getPrice();
		} else {
			return null;
		}
	}

	public String updateProductPrice(Integer productId, String dateString, Double newPrice) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		try {
			date = sdf.parse(dateString);
		} catch (Exception e) {
			return "Invalid date format. Please use yyyy-MM-dd.";
		}

		Optional<ProductPrice> productPriceOptional = productPriceRepository.findByProductidAndDate(productId, date);

		if (productPriceOptional.isPresent()) {
			ProductPrice productPrice = productPriceOptional.get();
			productPrice.setPrice(newPrice);
			productPriceRepository.save(productPrice);
			return "Price updated successfully.";
		} else {
			return "Product price for the given date not found.";
		}
	}

	public String addProductPrice(Integer productId, String dateString, Double price) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		try {
			date = sdf.parse(dateString);
		} catch (Exception e) {
			return "Invalid date format. Please use yyyy-MM-dd.";
		}
		if (productPriceRepository.findByProductidAndDate(productId, date).isPresent()) {
			return "Price for the given product and date already exists.";
		}
		ProductPrice newProductPrice = new ProductPrice();
		newProductPrice.setProductid(productId);
		newProductPrice.setDate(date);
		newProductPrice.setPrice(price);
		productPriceRepository.save(newProductPrice);

		return "Price added successfully.";
	}

	public String deleteProductPrice(Integer productId, String dateString) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		try {
			date = sdf.parse(dateString);
		} catch (Exception e) {
			return "Invalid date format. Please use yyyy-MM-dd.";
		}

		Optional<ProductPrice> productPriceOptional = productPriceRepository.findByProductidAndDate(productId, date);

		if (productPriceOptional.isPresent()) {
			ProductPrice productPrice = productPriceOptional.get();
			productPriceRepository.delete(productPrice);
			return "Price deleted successfully.";
		} else {
			return "Price for the given product and date not found.";
		}
	}

}
