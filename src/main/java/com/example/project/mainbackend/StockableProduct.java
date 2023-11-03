package com.example.project.mainbackend;

import java.util.Comparator;

public abstract class StockableProduct extends Product implements Stockable{
	
	private int numberOfItemsStocked;

	
	public StockableProduct(String name, int productId, double price, String genre, int yearPublished, double discount, int numberOfItemsStocked) {
		super(name, productId, price, genre, yearPublished, discount);
		this.numberOfItemsStocked = numberOfItemsStocked;
	}

	public StockableProduct() {
        super();
    }



	public int getNumberOfItemsStocked() {
		return numberOfItemsStocked;
	}


	public void setNumberOfItemsStocked(int numberOfItemsStocked) {
		this.numberOfItemsStocked = numberOfItemsStocked;
	}

	@Override
	public String toString() {
		return super.toString() + ", Number of Items Stocked = " + numberOfItemsStocked;
	}
	
	public static Comparator<StockableProduct> priceComparator = new Comparator<StockableProduct>() {
		@Override
		public int compare(StockableProduct o1, StockableProduct o2) {
			return (o1.getPrice() < o2.getPrice()) ? -1 : (o1.getPrice() > o2.getPrice()) ? 1 : 0;
		}
	};
	
	public static Comparator<StockableProduct> availableStockComparator = new Comparator<StockableProduct>() {
		@Override
		public int compare(StockableProduct o1, StockableProduct o2) {
			return o1.getNumberOfItemsStocked() - o2.getNumberOfItemsStocked();
		}
	};
}
