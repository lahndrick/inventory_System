import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/*****************************************************
 * 			   Master (master sku) Class			 *
 ****************************************************/

public class Master
{
	//an ArrayList consisting of ArrayList's 
	private ArrayList<Inventory> master;
	
	//default constructor
	public Master()
	{
		this.master = new ArrayList<>();
	}
	
	//gets a specific inventory
	public Inventory getInventory(int index) 
	{
		return master.get(index);
	}
	
	//returns a string of all inventories in master inventory
	public String showInventory(int index)
	{
		System.out.println(master.get(index));
		return "";
	}
	
	//returns back the master stock
	public ArrayList<Inventory> getMaster() 
	{
		return master;
	}
	
	//returns back the size of indexed inventory size/a specific inventory
	public int InventorySize(int index)
	{
		return this.getInventory(index).size();
	}
	
	//adds an inventory to mastery inventory
	public void addInventory(Inventory inventory)
	{
		master.add(inventory);
	}
	
	//returns the size of master/the amount of inventories in master
	public int size()
	{
		return master.size();
	}
	
	//returns a string of all inventories
	public String showAll()
	{
		for(int x = 0;x <master.size();x++)
		{
			for(int y = 0; y < this.getInventory(x).size();y++)
			{
				System.out.println("Region: " + (x + 1) + ", Stock: " + this.getInventory(x).getItem(y));
			}
		}
		
		return "";
	}
	
	//returns the total quantity of a specific sku in all inventories
	public int skuTotal(int skuCode)
	{
		int total = 0;
		
		for(int x = 0;x <master.size();x++)
		{
			for(int y = 0; y < this.getInventory(x).size();y++)
			{
				if(this.getInventory(x).getItem(y).getSkuCode() == skuCode)
					total += this.getInventory(x).getItem(y).getQuantity();
			}
		}
		
		System.out.print("\nTotal quantity for sku code " + skuCode + ": ");
		return total;
	}
	
	//returns all Item objects across all inventories
	public String showSku(int skuCode)
	{
		System.out.println("Products with sku code: " + skuCode);
		
		for(int x = 0;x <master.size();x++)
		{
			for(int y = 0; y < this.getInventory(x).size();y++)
			{
				if(this.getInventory(x).getItem(y).getSkuCode() == skuCode && this.getInventory(x).getItem(y).getQuantity() > 0)
					System.out.println("Region: " + (x + 1) + ", Stock: " + this.getInventory(x).getItem(y));
			}
		}
		
		return "";
	}
}

/*****************************************************
 * 			   		Item Class						 *
 ****************************************************/

class Item
{

	//location is a string to simplify setting location to anything
	private int barcode;
	private String location;
	private int quantity;
	private int skuCode;
	
	//basic 4 variable constructor
	public Item(int skuCode, int barcode, String location, int quantity)
	{
		this.skuCode = skuCode;
		this.barcode = barcode;
		this.location = location;
		this.quantity = quantity;
	}

	//returns barcode
	public int getBarcode() 
	{
		return barcode;
	}

	//sets barcode
	public void setBarcode(int barcode)
	{
		this.barcode = barcode;
	}

	//returns sku code
	public int getSkuCode() 
	{
		return skuCode;
	}

	//sets sku code
	public void setSkuCode(int skuCode) 
	{
		this.skuCode = skuCode;
	}

	//returns location
	public String getLocation()
	{
		return location;
	}

	//sets location
	public void setLocation(String location) 
	{
		this.location = location;
	}

	//returns quantity
	public int getQuantity()
	{
		return quantity;
	}

	//sets quantity
	public void setQuantity(int quantity) 
	{
		this.quantity = quantity;
	}
	
	//simple toString returns the details of an Item object
	public String toString()
	{
		return "(Sku: " + this.getSkuCode() + ") Barcode: " + this.getBarcode() + ", Quantity: " + this.getQuantity() + ", Location: " + this.getLocation();
	}
	
	//boolean to check if 2 Item locations match
	public boolean matchLocation(String input)
	{
		if(this.getLocation().equalsIgnoreCase(input))
		{
			return true;
		}
			
		return false;
	}
	
	//boolean to check if 2 Item sku codes match
	public boolean matchSkuCode(int input)
	{
		if(this.getSkuCode() == input)
		{
			return true;
		}
		
		return false;
	}
}



/*****************************************************
 * 					Inventory Class	     			 *
 ****************************************************/
class Inventory extends Master
{
	//arraylist of items
	private ArrayList<Item> inventory;
	
	//constructor
	public Inventory()
	{
		this.inventory = new ArrayList<Item>();
	}
	
	//adds to inventory
	public void add(Item input)
	{
		inventory.add(input);
	}
	
	//removes from inventory
	public void remove(int input)
	{
		inventory.remove(input);
	}
	
	//returns size of inventory
	public int size()
	{
		return inventory.size();
	}
	
	//returns an Item object
	public Item getItem(int index)
	{
		return inventory.get(index);
	}
	
	//finds/returns first Item object in inventory using barcode
	public Item find(int barcode)
	{
		for(int x = 0; x < this.size();x++)
		{
			if(this.getItem(x).getBarcode() == barcode)
			{
				return this.getItem(x);
			}
		}
		
		//if no barcode is found, return is null and user is information
		System.out.print("No barcodes found matching.");
		return null;
	}
	
	//returns barcode
	public int getBarcode(int barcode)
	{
		return this.find(barcode).getBarcode();
	}
	
	//increase the quantity of an Item object in inventory
	public void increaseStock(int barcode, int amount)
	{
		this.find(barcode).setQuantity(this.find(barcode).getQuantity() + amount);;
	}
	
	//decrease the quantity of an Item object in inventory
	public void decreaseStock(int barcode, int amount)
	{
		this.find(barcode).setQuantity(this.find(barcode).getQuantity() - amount);;
	}
	
	//transfers a specified quantity of an Item object to another, using barcodes
	public void transfer(int barcode1, int barcode2, int amount)
	{
		if(barcode1 != barcode2)
		{
			this.decreaseStock(barcode1, amount);
			this.increaseStock(barcode2, amount);
		}
	}
	
	//simple toString return for all Item objects in inventory
	public String toString()
	{
		for(int x = 0; x < inventory.size();x++)
		{
			System.out.println(this.getItem(x));
		}
		
		return "";
	}
	
	//takes the first Item object returned from using find method (with one barcode)
	//merges that into the first Item object returned using find method (with second barcode) 
	public void consolidate(int barcode1, int barcode2)
	{
		if(this.find(barcode1).getSkuCode() == this.find(barcode2).getSkuCode())
		{
			int amount = this.find(barcode1).getQuantity();
			this.find(barcode1).setQuantity(0);
			
			this.find(barcode2).setQuantity(this.find(barcode2).getQuantity() + amount);
			
			System.out.println("New quantity for " + barcode1 + ": " 
								+ this.find(barcode1).getQuantity());
			
			System.out.println("New quantity for " + barcode2 + ": " 
								+ this.find(barcode2).getQuantity());
		}
		//consolidation will not occur if sku codes are not the same
		//to ensure different kinds of stock are not merged into one
		else
		{
			System.out.println("Unable to proceed, sku codes are not matching.");
		}
	}
}

/*****************************************************
 * 			   		 Client Class					 *
 ****************************************************/

class Client 
{
	private Master client;
	
	//constructor
	public Client(Master client)
	{
		this.client = client;
	}
	
	//shows the total quantity for a sku
	public int skuTotal(int skuCode)
	{
		int total = 0;
		
		for(int y = 0;y <client.size();y++)
		{
			for(int x = 0; x < client.getInventory(y).size();y++)
			{
				total = client.getInventory(y).getItem(x).getQuantity();
			}
		}
		
		return total;
	}
	
	//main
	@SuppressWarnings({ "resource", "unused" })
	public static void main(String[] args)
	{
		//scan and random generator
		Scanner scan = new Scanner(System.in);
		Random rand = new Random();

		Item[] items = new Item[40];
		
		//creates 40 items with the same location
		for(int x = 0; x < 40;x++)
		{
			items[x] = new Item(0, 0, "aisle 1, bay 1, row 1", 0);
		}
		
		//4 different inventories
		Inventory wellington = new Inventory();
		Inventory auckland = new Inventory();
		Inventory christchurch = new Inventory();
		Inventory hastings = new Inventory();

		//new master inventory (used to work with sku codes instead of barcodes) and add 4 inventories
		Master master = new Master();
		master.addInventory(wellington);
		master.addInventory(auckland);
		master.addInventory(christchurch);
		master.addInventory(hastings);
		
		//new cleint
		Client client = new Client(master);
		
		//wellington inventory
		for(int x = 0; x < 10;x++)
		{
			wellington.add(items[x]);
		}
		//auckland inventory
		for(int x = 10; x < 20;x++)
		{
			auckland.add(items[x]);
		}
		//christchurch inventory
		for(int x = 20; x < 30;x++)
		{
			christchurch.add(items[x]);
		}
		//hastings inventory
		for(int x = 30; x < 40;x++)
		{
			hastings.add(items[x]);
		}

		//setting random quantities
		for(int x = 0;x <master.size();x++)
		{
			for(int y = 0; y < master.getInventory(x).size();y++)
			{
				int quantity = rand.nextInt(50);
				master.getInventory(x).getItem(y).setQuantity(quantity);
			}
		}
		
		//setting random sku codes
		for(int x = 0;x <master.size();x++)
		{
			for(int y = 0; y < master.getInventory(x).size();y++)
			{
				int skuCode = rand.nextInt(5);
				master.getInventory(x).getItem(y).setSkuCode(skuCode+1);
			}
		}
		
		//setting random barcode numbers
		for(int x = 0;x <master.size();x++)
		{
			for(int y = 0; y < master.getInventory(x).size();y++)
			{
				int ID = rand.nextInt(1000000000);
				ID += 1000000000;
				
				master.getInventory(x).getItem(y).setBarcode(ID+1);
			}
		}
		
		for(;;)
		{
			//simple user prompting for input
			System.out.println("1. View stock");
			System.out.println("2. Manage stock");
			System.out.println("3. Exit");
			int input = scan.nextInt();
			
			//view stock options
			if(input == 1)
			{
				System.out.println("Would you like to view all stock (1) or a specific region (2)");
				input = scan.nextInt();
				
				//show all stock
				if(input == 1)
				{
					master.showAll();
					System.out.println();
				}
				
				//view specific region
				if(input == 2)
				{
					System.out.println("Which region would you like to view? (" + master.size() 
										+ " regions)");
					
					//input is nextInt() - 1 to allow for easier user use
					input = scan.nextInt() - 1;
					
					//check integer is within range
					boolean withinRange;
					if((input < 0 || input >= master.size()))
					{
						withinRange = false;						
					}
					else 
					{
						withinRange = true;
					}
					
					//loop until input is within range
					for(;withinRange == false;)
					{
						System.out.println("This region is not recognised, please try again.");
						input = scan.nextInt() - 1;
						
						if(input > 0 || input < master.size())
						{
							withinRange = true;
						}
					}
					
					System.out.println(master.getInventory((input)).toString());
				}
			}
			
			//manage stock options
			if(input == 2)
			{
				System.out.println("1. View all stock");
				System.out.println("2. Transfer stock");
				System.out.println("3. Adjust stock quantity");
				System.out.println("4. Consolidate stock");
				input = scan.nextInt();
				
				//show all stock
				if(input == 1)
				{
					master.showAll();
					System.out.println();
				}
				
				//transfer stock
				if(input == 2)
				{
					System.out.print("Which inventory would you like to work with? ");
					int inventory = scan.nextInt() - 1;
					System.out.println(master.getInventory(inventory));
					
					System.out.print("What barcode would you like to transfer FROM? ");
					int input1 = scan.nextInt();
					System.out.print("What barcode would you like to transfer TO? ");
					int input2 = scan.nextInt();
					System.out.print("How much would you like to transfer? ");
					int amount = scan.nextInt();
					
					master.getInventory(inventory).transfer(input1, input2, amount);
				}
				
				//adjust stock quantity
				if(input == 3)
				{
					//simple prompting for user input
					System.out.print("Which inventory would you like to work with? ");
					int inventory = scan.nextInt() - 1;
					System.out.println(master.getInventory(inventory));
					
					System.out.print("What barcode would you like to adjust? ");
					int barcode = scan.nextInt();
					
					System.out.print("Increase or decrease? ");
					//without clear string, program seems to crash after user input
					String clear = scan.nextLine();
					String adjust = scan.nextLine();
					
					//increase stock
					if(adjust.toLowerCase().contains("increase"))
					{
						System.out.print("How much would you like to increase "
											+ barcode + " by? ");
						int amount = scan.nextInt();
						
						master.getInventory(inventory).increaseStock(barcode, amount);
						
						//clear input variable
						input = 0;
					}
					
					//decrease stock
					if(adjust.toLowerCase().contains("decrease"))
					{
						System.out.print("How much would you like to decrease "
											+ barcode + " by? ");
						int amount = scan.nextInt();
						
						master.getInventory(inventory).decreaseStock(barcode, amount);
						
						//clear input variable
						input = 0;
					}
				}
				
				//consolidation
				//consolidation is the act of merging one stock into another
				if(input == 4)
				{
					System.out.print("Which inventory would you like to work with? ");
					int inventory = scan.nextInt() - 1;
					System.out.println(master.getInventory(inventory));
					
					System.out.print("Which barcode would you like to consolidate from? ");
					int barcode1 = scan.nextInt();
					System.out.print("Which barcode would you like to consolidate to? ");
					int barcode2 = scan.nextInt();
					
					master.getInventory(inventory).consolidate(barcode1, barcode2);
				}
			}
			
			//exit program
			if(input == 3)
			{
				System.exit(input);
			}
		}
	}
}