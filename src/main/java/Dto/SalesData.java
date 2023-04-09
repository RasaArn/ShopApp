package Dto;


import java.sql.Date;

public class SalesData {
    private int saleId;
    private Date saleDate;
    private ProductData product; // object of product data class. which will contain properties like id, productName,
    // price, etc. By using this variable can access and manipulate the properties of the ProductData object it refers to.
    private UserData user;
    private int purchasedQuantity;
    private double totalPricePerSale;



    public SalesData(int saleId, Date saleDate, ProductData product, UserData user, int purchasedQuantity, double totalPricePerSale) {
        this.saleId = saleId;
        this.saleDate = saleDate;
        this.product = product;
        this.user = user;
        this.purchasedQuantity = purchasedQuantity;
        this.totalPricePerSale = totalPricePerSale;
    }

    public SalesData() {

    }

    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public ProductData getProduct() {
        return product;
    }

    public void setProduct(ProductData product) {
        this.product = product;
    }

    public UserData getUser() {
        return user;
    }

    public void setUser(UserData user) {
        this.user = user;
    }

    public int getPurchasedQuantity() {
        return purchasedQuantity;
    }

    public void setPurchasedQuantity(int purchasedQuantity) {
        this.purchasedQuantity = purchasedQuantity;
    }

    public double getTotalPricePerSale() {
        return totalPricePerSale;
    }

    public void setTotalPricePerSale(double totalPricePerSale) {
        this.totalPricePerSale = totalPricePerSale;
    }


    public void setMonth(String month) {
    }

    public void setProductName(String productName) {
    }

    public void setCostOfGoodsSold(double costOfGoodsSold) {
    }

    public void setRevenue(double revenue) {
    }

    public void setProfit(double profit) {
    }


}
