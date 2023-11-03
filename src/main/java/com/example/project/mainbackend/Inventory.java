package com.example.project.mainbackend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Inventory implements Iterable<StockableProduct>{
	
	public static Inventory instance;
	private ArrayList<StockableProduct> items = new ArrayList<StockableProduct>();
	
	public Inventory() {
		
	}
	
	public static Inventory getInstance() {
		if(instance == null) {
			instance = new Inventory();
		}
		return instance;
	}
	
	public void addItem(StockableProduct product) {
		items.add(product);
	}
	
	public void removeItem(int productId) {
		Iterator<StockableProduct> itr = items.iterator();
		while(itr.hasNext()) {
			if(itr.next().getProductId() == productId) {
				itr.remove();
				break;
			}
		}
	}
	
	public Product getItem(int productId) {
		for (StockableProduct sp : items) {
			if(sp.getProductId() == productId) {
				sp.removeStock(1);
				return sp;
			}
		}
		return null;
	}
	
	public void addProductStock(int productId, int numberOfNewStock) {
		for(StockableProduct sp : items) {
			if(sp.getProductId() == productId) {
				sp.addStock(numberOfNewStock);
				return ;
			}
		}
	}
	
	public void sortByPrice() {
		Collections.sort(items, StockableProduct.priceComparator);
	}
	
	public void sortByAvailableStock() {
		Collections.sort(items, StockableProduct.availableStockComparator);
	}

	@Override
	public Iterator<StockableProduct> iterator() {
		return items.iterator();
	}
	
}
