package com.example.project.mainbackend;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Invoice {

	private ArrayList<Product> items;
	private LocalDateTime date;

	public Invoice() {
		items = new ArrayList<Product>();
		date = LocalDateTime.now();
	}

	public String getLocalDateTime() {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedDate = date.format(format);
		return formattedDate;
	}

	public void addProduct(Product product) {
		items.add(product);
		StockableProduct sp = (StockableProduct)product;
		sp.removeStock(1);
	}

	public void removeProduct(Product product) {
		items.remove(product);
		StockableProduct sp = (StockableProduct)product;
		sp.addStock(1);
	}

	public double calculatePriceWithoutDiscount() {
		double totalPrice = 0.0;
		for(Product product : items) {
			totalPrice = totalPrice + product.getPrice();
		}
		return totalPrice;
	}

	public boolean isFullHouseDiscountAvailable() {
		int gameCount = 0;
		int	musicCount = 0;
		int movieCount = 0;
		
		for(Product product : items) {
			if(product instanceof Game) {
				gameCount++;
			}
			else if (product instanceof Music) {
				musicCount++;
			}
			else {
				movieCount++;
			}
		}
		
		if(gameCount >= 2 && musicCount >=2 && movieCount >= 2) {
			return true;
		}
		return false;
	}
	
	public double calculateDiscountedPrice() {
		double priceWithoutFullHouseDiscount = 0.0;
		double priceWithFullHouseDiscount = 0.0;
		
		for(Product product : items) {
			priceWithoutFullHouseDiscount = priceWithoutFullHouseDiscount + (product.getPrice() - (product.getPrice() * (product.getDiscount() / 100)));
		}
		
		if(isFullHouseDiscountAvailable()) {
			priceWithFullHouseDiscount = calculatePriceWithoutDiscount() - (calculateDiscountedPrice() - (calculatePriceWithoutDiscount() * 0.5));
			return Math.min(priceWithoutFullHouseDiscount, priceWithFullHouseDiscount);
		}
		return priceWithoutFullHouseDiscount;
	}
	
	public String getInvoice() {
		String str = getLocalDateTime() + "\n";
		
		for(Product product : items) {
			str = str + "Name: " + product.getName() + ", Price: " + product.getPrice() + "\n";
		}
		return str + "Total Price: " + calculatePriceWithoutDiscount() + "\n" + "Price After Discount: " + calculateDiscountedPrice();
	}
}
