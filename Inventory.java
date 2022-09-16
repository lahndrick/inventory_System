package Inventory_System;

import java.util.ArrayList;

public class Inventory {

    private ArrayList<Item> inventory;

    //default constructor
    public Inventory() {
        inventory = new ArrayList<>();
    }

    //return size of array
    public int size() {
        return inventory.size();
    }

    //returns a specific item from list of items
    public Item get(int n) {
        return inventory.get(n);
    }

    //add item to array and update text file
    public void add(Item item) {
        inventory.add(item);
    }

    //remove item from array
    public void remove(int n) {
        inventory.remove(n);
    }

    //this function find the first item with the specified barcode
    public Item findBarcode(int barcode) {
        for (int x = 0; x < inventory.size(); x++) {
            if (inventory.get(x).getBarcode().equals(String.valueOf(barcode))) {
                return inventory.get(x);
            }
        }

        return null;
    }

    //returns a string of all skus matching given code
    public String findSku(String skuCode) {
        String sku = "";

        for (int x = 0; x < inventory.size(); x++) {
            if (inventory.get(x).getSkuCode().equalsIgnoreCase(skuCode)) {
                sku += (String) inventory.get(x).toString() + "\n";
            }
        }

        //if user does not give a sku code
        if (sku.equals("")) {
            return "Sku not found.";
        }

        return sku;
    }

    //updates the quantity of an item
    public void updateQuantity(int itemNum, int amount) {
        this.get(itemNum).setQuantity(this.get(itemNum).getQuantity() + amount);
    }
}
