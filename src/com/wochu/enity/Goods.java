package com.wochu.enity;

import java.math.BigDecimal;

public class Goods {
    private Integer goodsId;

    private String goodsName;

    private BigDecimal goodsPresentPrice;

    private  BigDecimal goodsOriginalPrice;

    private String goodsImg;
    
    private Integer goodsImgnum;

    private String goodsOrigin;

    private String goodsSpecifications;

    private String goodsShelflife;

    private String goodsStorageCondition;

    private String goodsIntroduce;

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public BigDecimal getGoodsPresentPrice() {
        return goodsPresentPrice;
    }

    public void setGoodsPresentPrice(BigDecimal goodsPresentPrice) {
        this.goodsPresentPrice = goodsPresentPrice;
    }

    public BigDecimal getGoodsOriginalPrice() {
        return goodsOriginalPrice;
    }

    public void setGoodsOriginalPrice(BigDecimal goodsOriginalPrice) {
        this.goodsOriginalPrice = goodsOriginalPrice;
    }

    public String getGoodsStorageCondition() {
        return goodsStorageCondition;
    }

    public void setGoodsStorageCondition(String goodsStorageCondition) {
        this.goodsStorageCondition = goodsStorageCondition;
    }

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg == null ? null : goodsImg.trim();
    }
    
    
    public Integer getGoodsImgnum() {
		return goodsImgnum;
	}

	public void setGoodsImgNum(Integer goodImgNum) {
		this.goodsImgnum = goodImgNum;
	}

	public String getGoodsOrigin() {
        return goodsOrigin;
    }

    public void setGoodsOrigin(String goodsOrigin) {
        this.goodsOrigin = goodsOrigin == null ? null : goodsOrigin.trim();
    }

    public String getGoodsSpecifications() {
        return goodsSpecifications;
    }

    public void setGoodsSpecifications(String goodsSpecifications) {
        this.goodsSpecifications = goodsSpecifications == null ? null : goodsSpecifications.trim();
    }

    public String getGoodsShelflife() {
        return goodsShelflife;
    }

    public void setGoodsShelflife(String goodsShelflife) {
        this.goodsShelflife = goodsShelflife == null ? null : goodsShelflife.trim();
    }

    public String getGoodsIntroduce() {
        return goodsIntroduce;
    }

    public void setGoodsIntroduce(String goodsIntroduce) {
        this.goodsIntroduce = goodsIntroduce == null ? null : goodsIntroduce.trim();
    }

	@Override
	public String toString() {
		return "Goods [goodsId=" + goodsId + ", goodsName=" + goodsName
				+ ", goodsPresentPrice=" + goodsPresentPrice
				+ ", goodsOriginalPrice=" + goodsOriginalPrice + ", goodImg="
				+ goodsImg + ", goodImgNum=" + goodsImgnum + ", goodsOrigin="
				+ goodsOrigin + ", goodsSpecifications=" + goodsSpecifications
				+ ", goodsShelflife=" + goodsShelflife
				+ ", goodsStorageCondition=" + goodsStorageCondition
				+ ", goodsIntroduce=" + goodsIntroduce + "]";
	}

    
}