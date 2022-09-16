package Inventory_System;

import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;

public class userInterface {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Inventory inv = new Inventory();
        File file = new File("./src/Inventory_System/FileSystem.txt");
        InvShow invShow = new InvShow(inv, file);
        TxtUpdate txtUp = new TxtUpdate(inv);
        InvUpdate invUp = new InvUpdate(invShow);

        int intInput;
        int skuCode;
        int switchInput = 0;
        int barcode = 0;
        int quantity = 0;
        boolean stop = false;
        String enterClear;
        int numberFromList = 0;

        while (!stop) {
            //update inventory every loop
            invUp.update();
            //main menu
            System.out.println();
            System.out.println("1. View inventory");
            System.out.println("2. Manage inventory");
            System.out.println("3. Exit");

            try {
                switchInput = scan.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Incorrect input detected.");
                scan.nextLine();
                continue;
            }

            switch (switchInput) {

                //view inventory (option 1)
                case 1:
                    System.out.println();
                    System.out.println("1. View all items");
                    System.out.println("2. Search specific sku");
                    System.out.println("3. main menu");
                    System.out.println("4. exit");

                    try {
                        intInput = scan.nextInt();
                        enterClear = scan.nextLine();

                        switch (intInput) {
                            //show inventory (option 1)
                            case 1:
                                System.out.println();
                                System.out.println(invShow.show());
                                break;

                            //search specific sku (option 2)
                            case 2:
                                System.out.println();
                                System.out.println("Sku code: ");
                                skuCode = scan.nextInt();

                                System.out.println(inv.findSku(String.valueOf(skuCode)));
                                break;

                            case 3:
                                break;

                            case 4:
                                System.exit(0);
                            default:
                                System.out.println();
                                System.out.println("Incorrect input detected.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Incorrect input detected.");
                        scan.nextLine();
                        continue;
                    }

                    break;

                //manage inventory (option 2)
                case 2:
                    System.out.println();
                    System.out.println("1. Adjust quantity");
                    System.out.println("2. Add new item");
                    System.out.println("3. Remove item.");

                    try {
                        intInput = scan.nextInt();

                        //options for adjusting quantity (option 1)
                        switch (intInput) {

                            //adjust quantity (option 1)
                            case 1:
                                //lists all items in inventory and asks user which number on the list to adjust
                                System.out.println(invShow.show());
                                System.out.println("Which item number would you like to adjust?");

                                try {
                                    numberFromList = scan.nextInt();
                                } catch (InputMismatchException e) {
                                    System.out.println("Incorrect input detected.");
                                }

                                //if barcode not found
                                if (inv.findBarcode(numberFromList) == null) {
                                    System.out.println("Barcode not found.");
                                    continue;
                                }

                                //when adjusting quantity, a negative number decreases and a positive number increases quantity
                                System.out.println();
                                System.out.println("How much would you like to adjust " + inv.findBarcode(numberFromList) + " by (use \"-\" to decrease): ");

                                try {
                                    quantity = scan.nextInt();
                                } catch (InputMismatchException e) {
                                    System.out.println("Incorrect input detected.");
                                }

                                //show user new quantity
                                System.out.println();
                                inv.findBarcode(barcode).setQuantity(inv.findBarcode(barcode).getQuantity() + quantity);
                                System.out.println("new quantity: " + inv.findBarcode(barcode).getQuantity());
                                txtUp.updateTxtFile();
                                continue;

                            //adding item requirements
                            case 2:
                                enterClear = scan.nextLine();
                                System.out.print("Sku code: ");
                                String newSkuCode = scan.nextLine();

                                System.out.print("Barcode: ");
                                String newBarcode = scan.nextLine();

                                System.out.print("Quantity: ");
                                int newQuantity = scan.nextInt();
                                enterClear = scan.nextLine();

                                System.out.print("Location: ");
                                String newLocation = scan.nextLine();
                                
                                inv.add(new Item(newSkuCode, newBarcode, newLocation, newQuantity));
                                txtUp.updateTxtFile();
                                continue;

                            //remove item option
                            case 3:

                                //if no items in the inventory
                                if (inv.size() < 1) {
                                    System.out.println("There are no items in the inventory.");
                                }

                                System.out.println();
                                System.out.println(invShow.show());
                                System.out.println("Which item would you like to remove? ");
                                int removeNumber = scan.nextInt() - 1;

                                //if negative number given or a number is given that is greater than the size of the inventory
                                if (removeNumber < 0 || removeNumber > inv.size()) {
                                    System.out.println("Number chosen is not on the list.");
                                    continue;
                                }

                                inv.remove(removeNumber);
                                invShow.show();
                                continue;

                            //when an option is invalid
                            default:
                                System.out.println("Incorrect input detected.");
                                continue;
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Incorrect input detected.");
                    }

                //exit (option 3)
                case 3:
                    System.exit(0);

                //when an option is invalid    
                default:
                    System.out.println();
                    System.out.println("Incorrect input detected.");
            }
        }
    }
}
