package com.portfolio.component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.portfolio.entity.ApiResponse;
import com.portfolio.entity.Product;
import com.portfolio.entity.ProductPrice;
import com.portfolio.repository.ProductPriceRepository;
import com.portfolio.repository.ProductRepository;
import com.portfolio.service.ProductService;

@Component
public class ScheduleData {

	private static final Logger logger = LoggerFactory.getLogger(ScheduleData.class);

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ProductPriceRepository productPriceRepository;
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductService productService;

	/** 所提供API連結 */
	private static final String API_URL = "https://www.cathaybk.com.tw/cathaybk/service/newwealth/fund/chartservice.asmx/GetFundNavChart";

	private static final String requestBodyJSON = "{\"req\":{\"Keys\":[\"10480016\"],\"From\":\"2023/03/10\",\"To\":\"2024/03/10\"}}";

	@SuppressWarnings("unchecked")
	@Scheduled(initialDelay = 5000, fixedRate = Long.MAX_VALUE) // 啟用5秒，自動寫入資料
	public void getData() {
		String responseString = "";
		LocalDateTime now = LocalDateTime.now();
		String formattedDateTime = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		logger.info("getData method triggered at: {}", formattedDateTime);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("Content-Type", "application/json");
			HttpEntity<String> entity = new HttpEntity<>(requestBodyJSON, headers);

			ResponseEntity<byte[]> response = restTemplate.exchange(API_URL, HttpMethod.POST, entity, byte[].class);
			byte[] responseBody = response.getBody();

			if (responseBody != null && responseBody.length > 0) {
				responseString = new String(responseBody);

			} else {
				logger.warn("No data received from API");
			}
			try {

				ObjectMapper objectMapper = new ObjectMapper();
				Map<String, Object> responseMap = objectMapper.readValue(responseString, Map.class);

				if (responseMap.containsKey("Data")) {
					List<Map<String, Object>> productsList = (List<Map<String, Object>>) responseMap.get("Data");

					if (productService.getAll().size() <= 0) {// 判別DB是否有資料
						productsList.forEach(productData -> {
							Integer id = parseId(productData.get("id"));
							String name = (String) productData.get("name");
							String shortName = (String) productData.get("shortName");

							Boolean dataGrouping = parseDataGrouping(productData.get("dataGrouping"));

							Product product = new Product(id, name, shortName, dataGrouping);
							productRepository.save(product);
						});

						for (Map<String, Object> product : productsList) {
							Integer ids = 0;
							for (int i = 0; i < productsList.size(); i++) {
								Map<String, Object> productData = productsList.get(i);
								ids = parseId(productData.get("id"));
							}
							List<List<Object>> data = (List<List<Object>>) product.get("data");

							List<ProductPrice> productPriceList = new ArrayList<>();

							for (List<Object> pair : data) {

								Long timestamp = ((Number) pair.get(0)).longValue();
								Double value = ((Number) pair.get(1)).doubleValue();

								ProductPrice productPrice = new ProductPrice(ids, new Date(timestamp), value);
								productPriceList.add(productPrice);
							}

							productPriceRepository.saveAll(productPriceList);
						}
					}
				}
			} catch (Exception e) {
				logger.error("Error parsing or saving product data", e);
			}
		} catch (Exception e) {
			logger.error("Error occurred while processing API data", e);
		}
	}

	private Integer parseId(Object idObject) {
		if (idObject instanceof Integer) {
			return (Integer) idObject;
		} else if (idObject instanceof String) {
			return Integer.parseInt((String) idObject);
		}
		return null;
	}

	private boolean parseDataGrouping(Object dataGroupingObj) {
		if (dataGroupingObj instanceof Boolean) {
			return (Boolean) dataGroupingObj;
		} else if (dataGroupingObj instanceof String) {
			return "true".equalsIgnoreCase((String) dataGroupingObj);
		}
		return false;
	}
}