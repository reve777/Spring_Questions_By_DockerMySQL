package com.portfolio.entity;

import java.text.DecimalFormat;

public class PriceChangeResponse {

    private Double priceChange;
    private Double priceChangePercentage;
    private Double openingPrice;
    private Double closingPrice;

    // DecimalFormat 实例，用于将数字保留三位小数
    private static final DecimalFormat df = new DecimalFormat("#.###");

    public PriceChangeResponse(Double priceChange, Double priceChangePercentage, Double openingPrice, Double closingPrice) {
        this.priceChange = formatDecimal(priceChange);
        this.priceChangePercentage = formatDecimal(priceChangePercentage);
        this.openingPrice = formatDecimal(openingPrice);
        this.closingPrice = formatDecimal(closingPrice);
    }

    // 辅助方法：格式化为小数点后三位
    private Double formatDecimal(Double value) {
        if (value == null) return null;
        return Double.valueOf(df.format(value));
    }

    public Double getPriceChange() {
        return priceChange;
    }

    public void setPriceChange(Double priceChange) {
        this.priceChange = formatDecimal(priceChange);
    }

    public Double getPriceChangePercentage() {
        return priceChangePercentage;
    }

    public void setPriceChangePercentage(Double priceChangePercentage) {
        this.priceChangePercentage = formatDecimal(priceChangePercentage);
    }

    public Double getOpeningPrice() {
        return openingPrice;
    }

    public void setOpeningPrice(Double openingPrice) {
        this.openingPrice = formatDecimal(openingPrice);
    }

    public Double getClosingPrice() {
        return closingPrice;
    }

    public void setClosingPrice(Double closingPrice) {
        this.closingPrice = formatDecimal(closingPrice);
    }
}
