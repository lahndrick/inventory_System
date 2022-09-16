package Inventory_System;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.io.File;

public class InvShow extends Inventory {

    Inventory inventory;
    File file;
    
    //constructor
    public InvShow(Inventory inventory, File file) {
        this.inventory = inventory;
        this.file = new File("./src/Inventory_System/FileSystem.txt");
    }

    //returns a string showing all items in the inventory (more accurately the FileSystem.txt file)
    //string is filled by reading each line the a txt file
    public String show() {
        String showInventory = "";
        String line = "";

        try {
            //file reader
            BufferedReader br = new BufferedReader(new FileReader(file));
                       
            for (int x = 0; ((line = br.readLine()) != null); x++) {
                showInventory += (x + 1) + ". " + line + "\n";
            }

            br.close();

        } catch (FileNotFoundException ex) {
            System.out.println("Inventory could not be shown, file not found.");
        } catch (IOException ex) {
            System.out.println("Inventory could not be shown, input/output exception.");
        }

        return showInventory;
    }
}
