package com.example.project.mainbackend;

public class Game extends StockableProduct {
	
	private String developer;
	
	public Game(String name, int productId, double price, String genre, int yearPublished, double discount, int numberOfItemsStocked, String developer) {
		super(name, productId, price, genre, yearPublished, discount, numberOfItemsStocked);
		this.developer = developer;
	}

	public String getDeveloper() {
		return developer;
	}

	public void setDeveloper(String developer) {
		this.developer = developer;
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
		return super.toString() + ", Developer = " + developer;
	}
	
	@Override
	public String getInfo() {
		return toString();
	}
	
}
