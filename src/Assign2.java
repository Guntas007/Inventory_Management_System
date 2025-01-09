import java.util.*;

public class Assign2 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Inventory inventory = new Inventory();

		int choice = 0;

		while (choice != 8) {
			displayMenu();
			if (scanner.hasNextInt()) {
				choice = scanner.nextInt();
				scanner.nextLine(); // Consume the newline character
				switch (choice) {
				case 1:
					inventory.addItem(scanner, false);
					break;
				case 2:
					inventory.displayInventory();
					break;
				case 3:
					updateQuantity(scanner, inventory, true);
					break;
				case 4:
					updateQuantity(scanner, inventory, false);
					break;
				case 5:
					inventory.searchForItem(scanner);
					break;
				case 6:
					inventory.saveToFile(scanner);
					break;
				case 7:
					inventory.readFromFile(scanner);
					break;
				case 8:
					System.out.println("Exiting...");
					break;
				default:
					System.out.println("Invalid choice. Please select a number between 1 and 8.");
				}
			} else {
				System.out.println("Invalid input. Please enter a number.");
				scanner.nextLine(); // Consume the newline character
			}
		}

		scanner.close();
	}

	/**
	 * Displays the main menu for the application.
	 */
	private static void displayMenu() {
		System.out.println("Please select one of the following:");
		System.out.println("1: Add Item to Inventory");
		System.out.println("2: Display Current Inventory");
		System.out.println("3: Buy Item(s)");
		System.out.println("4: Sell Item(s)");
		System.out.println("5: Search for Item");
		System.out.println("6: Save Inventory to File");
		System.out.println("7: Read Inventory from File");
		System.out.println("8: To Exit");
		System.out.print("> ");
	}

	/**
	 * Updates the quantity of an item in the inventory (buy or sell).
	 *
	 * @param scanner   The Scanner to take input from.
	 * @param inventory The Inventory instance to update.
	 * @param buy       A boolean indicating whether to buy (true) or sell (false)
	 *                  the item.
	 */
	private static void updateQuantity(Scanner scanner, Inventory inventory, boolean buy) {
		System.out.print("Enter the item code: ");
		if (scanner.hasNextInt()) {
			int itemCode = scanner.nextInt();
			scanner.nextLine(); // Consume the newline character

			System.out.print("Enter the quantity: ");
			if (scanner.hasNextInt()) {
				int quantity = scanner.nextInt();
				scanner.nextLine(); // Consume the newline character

				boolean success;
				if (buy) {
					success = inventory.updateQuantity(itemCode, quantity, true);
				} else {
					success = inventory.updateQuantity(itemCode, quantity, false);
				}

				if (success) {
					System.out.println("Inventory updated successfully.");
				}
			} else {
				System.out.println("Invalid input for quantity.");
			}
		} else {
			System.out.println("Invalid input for item code.");
			scanner.nextLine(); // Consume the newline character
		}
	}
}