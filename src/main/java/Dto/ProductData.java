package Dto;

import Entities.ProductType;

public class ProductData {
    private int productId;
    private String productName;
    private double weight;
    private int quantityInStock;
    private double costPrice;
    private double sellingPrice;
    private ProductType productType;

    public ProductData(int productId, String productName, double weight, int quantityInStock, double costPrice,
                       double sellingPrice, ProductType productType) {
        this.productId = productId;
        this.productName = productName;
        this.weight = weight;
        this.quantityInStock = quantityInStock;
        this.costPrice = costPrice;
        this.sellingPrice = sellingPrice;
        this.productType = productType;
    }

    public ProductData(String productName, double weight, int quantityInStock, double costPrice, double sellingPrice, ProductType productType) {
        this.productName = productName;
        this.weight = weight;
        this.quantityInStock = quantityInStock;
        this.costPrice = costPrice;
        this.sellingPrice = sellingPrice;
        this.productType = productType;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    @Override
    public String toString() {
        return "ProductData{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", weight=" + weight +
                ", quantityInStock=" + quantityInStock +
                ", costPrice=" + costPrice +
                ", sellingPrice=" + sellingPrice +
                ", productType=" + productType +
                '}';
    }
}