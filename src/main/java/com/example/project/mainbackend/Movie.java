package com.example.project.mainbackend;

public class Movie extends StockableProduct{
	
	private String director;
	
	public Movie(String name, int productId, double price, String genre, int yearPublished, double discount, int numberOfItemsStocked, String director) {
		super(name, productId, price, genre, yearPublished, discount, numberOfItemsStocked);
		this.director = director;
	}

    public Movie() {

    }

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	@Override
	public void addStock(int num) {
		int currentStock = getNumberOfItemsStocked();
		int newStock = currentStock + num;
		setNumberOfItemsStocked(newStock);
	}

	@Override
	public void removeStock(int num) {
		int currentStock = getNumberOfItemsStocked();
		int newStock = currentStock - num;
		setNumberOfItemsStocked(newStock);
	}

	@Override
	public void editStock(int num) {
		int newStock = num;
		setNumberOfItemsStocked(newStock);
	}
	
	@Override
	public String toString() {
		return super.toString() + ", Director = " + director;
	}

	@Override
	public String getInfo() {
		return toString();
	}
	
	
}
