package com.example.project;

public class ProductDataForTableView {
    private Integer id;
    private String productName;
    private Double price;
    private String genre;
    private Integer numOfItemStocked;
    private Double discount;
    private String developerArtistDirector;

    public ProductDataForTableView(Integer id, String productName, String genre, String developerArtistDirector, double price, double discount, int numOfItemStocked) {
        this.id = id;
        this.productName = productName;
        this.price =price;
        this.genre = genre;
        this.numOfItemStocked =numOfItemStocked;
        this.discount = discount;
        this.developerArtistDirector =developerArtistDirector;
    }
    public Integer getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public Double getPrice() {
        return price;
    }

    public String getGenre() {
        return genre;
    }

    public Integer getNumOfItemStocked() {
        return numOfItemStocked;
    }

    public Double getDiscount() {
        return discount;
    }

    public String getDeveloperArtistDirector() {
        return developerArtistDirector;
    }
}
