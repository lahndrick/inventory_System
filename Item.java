package Inventory_System;

public class Item {

    private String barcode;
    private String location;
    private int quantity;
    private String skuCode;

    //four variable constructor
    public Item(String skuCode, String barcode, String location, int quantity) {
        this.barcode = barcode;
        this.location = location;
        this.quantity = quantity;
        this.skuCode = skuCode;
    }

    /*
    *Three variable constructor if no barcode allocated
     */
    public Item(String skuCode, String location, int quantity) {
        this.skuCode = skuCode;
        this.location = location;
        this.quantity = quantity;
        this.skuCode = "Unallocated";
    }

    //default constructor for InventoryManager to add new information into
    public Item() {
        this.barcode = "";
        this.location = "";
        this.quantity = 0;
        this.skuCode = "";
    }

    //simple toString method
    @Override
    public String toString() {
        return "Sku code: " + getSkuCode() + " Barcode: " + getBarcode() + " Quantity: " + getQuantity() + " Location: " + getLocation();
    }

    //get and set methods
    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    //boolean to check if 2 barcodes are the same
    public boolean matchBarcode(String input) {
        return this.getBarcode().equalsIgnoreCase(input);
    }
    
    //used in invUpdate class to clear arraylist after updating
    public void clearAll(){
        this.barcode = null;
        this.location = null;
        this.skuCode = null;
        this.quantity = 0;
    }
}
