package Inventory_System;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class TxtUpdate extends Inventory {
    
    File file = new File("./src/Inventory_System/FileSystem.txt");
    Inventory inventory;
    
    //constructor
    public TxtUpdate(Inventory inventory){
        this.inventory = inventory;
    }
    
    //for updating txt file containing inventory items
    //mainly used to keep txt file as updated as possible
    public void updateTxtFile() {

        String line;
        try {
            //txt file will be located in inventory_System folder
            PrintWriter pw = new PrintWriter(file);

            //loops through inventory adding an item per line into the txt file
            for (int x = 0; x < inventory.size(); x++) {
                line = this.inventory.get(x).toString();
                pw.println(line);
            }

            pw.close();

        } catch (FileNotFoundException ex) {
            System.out.println("File could not be found.");
        }

    }
}
