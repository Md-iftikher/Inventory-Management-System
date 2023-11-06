package com.example.project.mainbackend;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Invoice {

	private ArrayList<Product> items;
	private LocalDateTime date;
	private int gameCount;
	private int musicCount;
	private int movieCount;

	public Invoice() {
		items = new ArrayList<>();
		date = LocalDateTime.now();
		gameCount = 0;
		musicCount = 0;
		movieCount = 0;
	}
	public void resetInvoice() {
		items.clear();
		gameCount = 0;
		musicCount = 0;
		movieCount = 0;
	}

	public String getLocalDateTime() {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedDate = date.format(format);
		return formattedDate;
	}

	public void addProduct(Product product) {
		items.add(product);
		StockableProduct sp = (StockableProduct) product;
		sp.removeStock(1);

		if (product instanceof Game) {
			gameCount++;
		} else if (product instanceof Music) {
			musicCount++;
		} else if (product instanceof Movie) {
			movieCount++;
		}
	}

	public void removeProduct(Product product) {
		for (Product item : items) {
			if ((item.getProductId() == product.getProductId()) && (item.getName()==product.getName())) {
				items.remove(item);
				productCounts(item);
				break;
			}
			else{
				System.out.println("product did not removed");
			}
		}
	}

	private void productCounts(Product removedProduct) {
		if (removedProduct instanceof Game) {
			gameCount--;
		} else if (removedProduct instanceof Music) {
			musicCount--;
		} else if (removedProduct instanceof Movie) {
			movieCount--;
		}
	}


	public double calculatePriceWithoutDiscount() {
		double totalPrice = 0.0;
		for (Product product : items) {
			totalPrice = totalPrice + product.getPrice();
		}
		return totalPrice;
	}




	public boolean isFullHouseDiscountAvailable() {
		return gameCount >= 2 && musicCount >= 2 && movieCount >= 2;
	}

	public double calculateDiscountedPrice() {
		double priceWithoutFullHouseDiscount = 0.0;

		for (Product product : items) {
			priceWithoutFullHouseDiscount += (product.getPrice() - (product.getPrice() * (product.getDiscount() / 100)));
		}

		if (isFullHouseDiscountAvailable()) {
			double fullHouseDiscount = calculatePriceWithoutDiscount() * 0.5;
			return Math.min(priceWithoutFullHouseDiscount, fullHouseDiscount);
		}

		return priceWithoutFullHouseDiscount;
	}
	public ArrayList<Product> getItems() {
		return items;
	}

	public String getInvoice() {
		String str = getLocalDateTime() + "\n";

		for (Product product : items) {
			str = str + "Name: " + product.getName() + ", Price: " + product.getPrice() + "\n";
		}
		return str + "Total Price: " + calculatePriceWithoutDiscount() + "\n" + "Price After Discount: " + calculateDiscountedPrice();
	}
}
