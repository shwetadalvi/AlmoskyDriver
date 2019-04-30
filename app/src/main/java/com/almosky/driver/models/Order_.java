
package com.almosky.driver.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Order_ {

    @SerializedName("orderNo")
    @Expose
    private String orderNo;
    @SerializedName("price")
    @Expose
    private Double price;
    @SerializedName("orderType")
    @Expose
    private Integer orderType;
    @SerializedName("remarks")
    @Expose
    private String remarks;
    private Double vat;
    private Double exlusivePrice;
    @SerializedName("deliveryType")
    @Expose
    private Integer deliveryType;
    @SerializedName("service")
    @Expose
    private List<Service> service = null;

    @SerializedName("pickupDriver")
    @Expose
    private String pickupDriver;
    @SerializedName("deliveryDriver")
    @Expose
    private String deliveryDriver;
    @SerializedName("customerDisc")
    @Expose
    private double customerDiscount;
    @SerializedName("nasabDiscAmt")
    @Expose
    private double nasabDiscount;
    @SerializedName("nasabDiscPerc")
    @Expose
    private double nasab_disc_perc;
    @SerializedName("customerDiscPerc")
    @Expose
    private double customer_disc_perc;
    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(Integer deliveryType) {
        this.deliveryType = deliveryType;
    }

    public List<Service> getService() {
        return service;
    }

    public void setService(List<Service> service) {
        this.service = service;
    }

    public Double getVat() {
        return vat;
    }

    public void setVat(Double vat) {
        this.vat = vat;
    }

    public Double getExlusivePrice() {
        return exlusivePrice;
    }

    public void setExlusivePrice(Double exlusivePrice) {
        this.exlusivePrice = exlusivePrice;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getPickupDriver() {
        return pickupDriver;
    }

    public void setPickupDriver(String pickupDriver) {
        this.pickupDriver = pickupDriver;
    }

    public String getDeliveryDriver() {
        return deliveryDriver;
    }

    public void setDeliveryDriver(String deliveryDriver) {
        this.deliveryDriver = deliveryDriver;
    }

    public double getCustomerDiscount() {
        return customerDiscount;
    }

    public void setCustomerDiscount(double customerDiscount) {
        this.customerDiscount = customerDiscount;
    }

    public double getNasabDiscount() {
        return nasabDiscount;
    }

    public void setNasabDiscount(double nasabDiscount) {
        this.nasabDiscount = nasabDiscount;
    }

    public double getNasab_disc_perc() {
        return nasab_disc_perc;
    }

    public void setNasab_disc_perc(double nasab_disc_perc) {
        this.nasab_disc_perc = nasab_disc_perc;
    }

    public double getCustomer_disc_perc() {
        return customer_disc_perc;
    }

    public void setCustomer_disc_perc(double customer_disc_perc) {
        this.customer_disc_perc = customer_disc_perc;
    }
}
