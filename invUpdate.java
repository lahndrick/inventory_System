package Inventory_System;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class InvUpdate{
    
    Inventory inv;
    File file;
    InvShow invs;   
    
    //only constructor for class
    public InvUpdate(InvShow invs) {
        this.invs = invs;
        this.inv = this.invs.inventory;
        this.file = this.invs.file;
    }
    
    //populate inventory with txt file information
    public void update() {
        try {
            //file reader
            BufferedReader br = new BufferedReader(new FileReader(this.invs.file));
            //null String for passing each txt file line
            String line;
            //ArrayList used to temporarily store each line before converting to object information
            ArrayList<String> convert = new ArrayList<>();
            //this String[8] will be used for splitting txt line inputs into 8 seperate pieces of information
            String[] itemInformation = new String[9];
            
            for(int x = 0; x < 9;x++) {
                itemInformation[x] = "no data";
            }

            //loop to add each txt file line
            for (int x = 0; ((line = br.readLine()) != null); x++) {
                convert.add(line);
                itemInformation = convert.get(x).split(" ");

                this.invs.inventory.add(new Item());

                //adds the relevant information into each object field
                this.invs.inventory.get(x).setSkuCode(itemInformation[2]);
                this.invs.inventory.get(x).setBarcode(itemInformation[4]);
                this.invs.inventory.get(x).setQuantity(Integer.valueOf(itemInformation[6]));
                this.invs.inventory.get(x).setLocation(itemInformation[8]);
            }

            //clears all array slots to ensure no overlap with next update
            for (int x = 0; x < this.invs.inventory.size(); x++) {
                this.invs.inventory.get(x).clearAll();
            }

            br.close();

        //catching possible errors
        } catch (FileNotFoundException ex) {
            System.out.println("Inventory could not be updated, file not found.");
        } catch (IOException ex) {
            System.out.println("Inventory could not be updated, input/output exception.");
        } catch (ArrayIndexOutOfBoundsException ex) {
        }
    }
}
