
/*
 * author : Satyapriya Raut
 * email : satyapriyaraut99@gmail.com
 */

import java.util.Scanner;

class Operations {
	public static void OwnerOperations(RestaurantOwner owner) {
		Scanner sc = new Scanner(System.in);
		boolean ownerLoggedIn = true;
		while (ownerLoggedIn) {
			System.out.println("\n----OPERATIONS----");
			System.out.println("Enter choice");
			System.out.println("1) Add Food Item.\n" + "2) Remove Food Item \n" + "3) Update Food Item \n"
					+ "4) Display Menu\n" + "5) Exit");
			byte choice = sc.nextByte();
			String foodItem = "";
			float price = 0F;
			switch (choice) {
			case 1:
				System.out.println("Enter number of food items to add in menu.");
				int noOfItems = sc.nextInt();
				for (int i = 0; i < noOfItems; i++) {
					System.out.println("Enter food item " + (i + 1));
					foodItem = sc.next();
					System.out.println("Enter its price.");
					price = sc.nextFloat();
					if (owner.addItem(foodItem, price))
						System.out.println(foodItem + " added in menu.");
					else
						System.out.println(foodItem + " already in menu.");
					owner.addItem(foodItem, price);
				}

				break;
			case 2:
				System.out.println("Enter food item to remove.");
				foodItem = sc.next();
				if (owner.deleteItem(foodItem))
					System.out.println(foodItem + " removed from menu.");
				else
					System.out.println(foodItem + " does not exist in menu.");
				break;
			case 3:
				System.out.println("Enter food item to update its price.");
				foodItem = sc.next();
				System.out.println("Enter its price.");
				price = sc.nextFloat();
				if (owner.update(foodItem, price))
					System.out.println("Menu updated.");
				else
					System.out.println("Failed to update menu.");
				break;
			case 4:
				owner.displayMenu();
				break;
			case 5:
				return;
			default:
				System.out.println("Exiting from Owner Module.");
				System.out.println("-------------------------------------");
				ownerLoggedIn = false;
			}
		}
		sc.close();
	}

	public static void CustomerOpeartions(Customer customer) {
		Scanner sc = new Scanner(System.in);
		boolean customerLoggedIn = true;
		while (customerLoggedIn) {
			System.out.println("----Please select your choice----");
			System.out.println("1) Display Menu \n" + "2) Order Food Item \n" + "3) Cancel Food Item \n"
					+ "4) Update Food Item Quantity \n" + "5) Display Order\n" + "6) Pay Bill\n" + "7) Exit\n");
			String foodItem = "";
			int quantity = 0;
			byte choice = sc.nextByte();
			switch (choice) {
			case 1:
				System.out.println("----MENU----");
				customer.displayMenu();
				break;
			case 2:
				System.out.println("Enter food item to add.");
				foodItem = sc.next();
				System.out.println("Enter " + foodItem + "\'s quantity.");
				quantity = sc.nextInt();
				if (customer.orderFood(foodItem, quantity))
					System.out.println(quantity + " " + foodItem + " added in order.");
				else
					System.out.println(foodItem + " does not exist in menu.");
				break;
			case 3:
				System.out.println("Enter food item to remove.");
				foodItem = sc.next();
				if (customer.removeFood(foodItem))
					System.out.println(foodItem + " removed from order");
				else
					System.out.println("Failed to remove " + foodItem);
				break;
			case 4:
				System.out.println("Enter food to update quantity.");
				foodItem = sc.next();
				if (customer.update(foodItem, quantity))
					System.out.println(quantity + " " + foodItem + " added in order.");
				else
					System.out.println("Failed to update quantity for " + foodItem);
				break;
			case 5:
				System.out.println("Your order is ");
				customer.displayOrder();
				break;
			case 6:
				System.out.println("Your total amount for following order ");
				System.out.println("-------------------------------------");
				System.out.println("FOOD \t QUANTITY");
				System.out.println("-------------------------------------");
				customer.displayOrder();
				System.out.println("Total Amount(including GST) = " + customer.totalBill());
				System.out.println("-------------------------------------\n");
				break;
			case 7:
				return;
			default:
				System.out.println("Exiting from Customer Module.");
				System.out.println("-------------------------------------");
				customerLoggedIn = false;
			}
		}
		sc.close();
	}
}

public class RestaurantApplication {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestaurantOwner owner = new RestaurantOwner();
		Customer customer = new Customer();
		Scanner sc = new Scanner(System.in);
		boolean wantToContinue = true;
		while (wantToContinue) {
			System.out.println("\n------WELCOME TO RESTAURANT------\n");
			System.out.println("Login as,(Enter 1 for Owner and 2 for Customer ) ");
			System.out.println("1) Owner");
			System.out.println("2) Customer");

			byte loginAs = sc.nextByte();
			switch (loginAs) {
			case 1:
				System.out.println("------AUTHENTICATION-------");
				System.out.println("Enter username.");
				String uname = sc.next();
				System.out.println("Enter password.");
				String password = sc.next();
				if (owner.authorize(uname, password)) {
					Operations.OwnerOperations(owner);
				} else {
					System.out.println("Invalid credentials.");
				}
				break;
			case 2:
				Operations.CustomerOpeartions(customer);
				break;
			default:
				System.out.println("Invalid Choice!! " + "Terminating program.");
				wantToContinue = false;
			}

		}
		sc.close();
	}
}
