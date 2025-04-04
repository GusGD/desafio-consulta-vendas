package com.devsuperior.dsmeta.dto;


public class SaleSummaryDTO {
    private String sellerName;
    private Double totalAmount;

    public SaleSummaryDTO(String sellerName, Double totalAmount) {
        this.sellerName = sellerName;
        this.totalAmount = totalAmount;
    }

    public SaleSummaryDTO(){}

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }


}