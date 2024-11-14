package com.portfolio.service;

import java.util.Date;

public interface PriceChangeCalculator {

	/** 涨跌 */
	Double calculatePriceChange(Integer productId, Date startTime, Date endTime);

	/** 前收 */
	Double getOpeningPrice(Integer productId, Date startTime);

	/** 后收 */
	Double getClosingPrice(Integer productId, Date endTime);

	/** 涨跌幅 */
	Double calculatePriceChangePercentage(Integer productId, Date startTime, Date endTime);
}
