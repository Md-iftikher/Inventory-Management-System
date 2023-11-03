package com.example.project;

public class ProductDataForTableView {
    private Integer id;
    private String productName;
    private Double price;
    private String genre;
    private Integer numOfItemStocked;
    private Double discount;
    private String developerArtistDirector;
    private Integer Year_of_Publish;


    public ProductDataForTableView(Integer id, String productName, String genre, String developerArtistDirector,Integer Year_of_Publish, double price, double discount, int numOfItemStocked) {
        this.id = id;
        this.productName = productName;
        this.price =price;
        this.genre = genre;
        this.numOfItemStocked =numOfItemStocked;
        this.discount = discount;
        this.developerArtistDirector =developerArtistDirector;
        this.Year_of_Publish=Year_of_Publish;
    }

    public ProductDataForTableView() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getYear_of_Publish() {
        return Year_of_Publish;
    }

    public void setYear_of_Publish(Integer year_of_Publish) {
        Year_of_Publish = year_of_Publish;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setNumOfItemStocked(Integer numOfItemStocked) {
        this.numOfItemStocked = numOfItemStocked;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public void setDeveloperArtistDirector(String developerArtistDirector) {
        this.developerArtistDirector = developerArtistDirector;
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
