package com.example.project.mainbackend;

public class Music extends StockableProduct{
	
	private String artistName;
	
	public Music(String name, int productId, double price, String genre, int yearPublished, double discount, int numberOfItemsStocked, String artistName) {
		super(name, productId, price, genre, yearPublished, discount, numberOfItemsStocked);
		this.artistName = artistName;
	}

    public Music() {

    }

    public String getArtistName() {
		return artistName;
	}

	public void setArtistName(String artistName) {
		this.artistName = artistName;
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
		return super.toString() + ", Artist Name = " + artistName;
	}
	
	@Override
	public String getInfo() {
		return toString();
	}
	
	
}
