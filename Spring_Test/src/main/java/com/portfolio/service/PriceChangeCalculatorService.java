package com.portfolio.service;

import com.portfolio.entity.ProductPrice;
import com.portfolio.repository.ProductPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class PriceChangeCalculatorService implements PriceChangeCalculator {

	@Autowired
	private ProductPriceRepository productPriceRepository;

	@Override
	public Double calculatePriceChange(Integer productId, Date startTime, Date endTime) {
		Double startPrice = getOpeningPrice(productId, startTime);
		Double endPrice = getClosingPrice(productId, endTime);

		if (startPrice != null && endPrice != null) {
			return endPrice - startPrice;
		}
		return null;
	}

	@Override
	public Double getOpeningPrice(Integer productId, Date startTime) {
		Optional<ProductPrice> productPrice = productPriceRepository
				.findTopByProductidAndDateBeforeOrderByDateDesc(productId, startTime);
		return productPrice.map(ProductPrice::getPrice).orElse(null);
	}

	@Override
	public Double getClosingPrice(Integer productId, Date endTime) {
		Optional<ProductPrice> productPrice = productPriceRepository
				.findTopByProductidAndDateBeforeOrderByDateDesc(productId, endTime);
		return productPrice.map(ProductPrice::getPrice).orElse(null);
	}

	@Override
	public Double calculatePriceChangePercentage(Integer productId, Date startTime, Date endTime) {
		Double startPrice = getOpeningPrice(productId, startTime);
		Double endPrice = getClosingPrice(productId, endTime);

		if (startPrice != null && endPrice != null && startPrice != 0) {
			return ((endPrice - startPrice) / startPrice) * 100;
		}
		return null;
	}
}
