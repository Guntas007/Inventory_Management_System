import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Inventory {
	private ArrayList<FoodItem> inventoryList;

	public Inventory() {
		inventoryList = new ArrayList<>();
	}

	/**
	 * Adds a new item to the inventory, taking input from the provided Scanner.
	 *
	 * @param scanner  The Scanner to take input from.
	 * @param fromFile A boolean indicating if the input is from a file.
	 * @return true if the item is successfully added, false otherwise.
	 */
	public boolean addItem(Scanner scanner, boolean fromFile) {
		FoodItem item;
		String type = "";

		if (!fromFile) {
			System.out.print("Enter the type [Fruit(f), Vegetable(v), or Preserve(p)]: ");
			type = scanner.nextLine().toLowerCase();
		}

		if (type.equals("f") || (fromFile && type.equals("f"))) {
			item = new Fruit();
		} else if (type.equals("v") || (fromFile && type.equals("v"))) {
			item = new Vegetable();
		} else if (type.equals("p") || (fromFile && type.equals("p"))) {
			item = new Preserve();
		} else {
			System.out.println("Invalid type. Please enter Fruit, Vegetable, or Preserve.");
			return false;
		}

		if (item.addItem(scanner, fromFile)) {
			for (FoodItem i : inventoryList) {
				if (i.getItemCode() == item.getItemCode()) {
					System.out.println("Item code already exists in inventory.");
					return false;
				}
			}
			inventoryList.add(item);
			Collections.sort(inventoryList, new FoodItemComparator());
			return true;
		}
		return false;
	}

	public void searchForItem(Scanner scanner) {
		System.out.print("Enter the item code: ");
		if (scanner.hasNextInt()) {
			int itemCode = scanner.nextInt();
			int index = binarySearch(itemCode);
			if (index != -1) {
				System.out.println(inventoryList.get(index).toString());
			} else {
				System.out.println("Item code not found in inventory.");
			}
		} else {
			System.out.println("Invalid input for item code.");
			scanner.nextLine(); // Consume the newline character
		}
	}

	public int alreadyExists(int itemCode) {
		for (int i = 0; i < inventoryList.size(); i++) {
			if (inventoryList.get(i).getItemCode() == itemCode) {
				return i;
			}
		}
		return -1; // Item not found in inventory
	}

	public boolean updateQuantity(int itemCode, int quantity, boolean buyOrSell) {
		int index = alreadyExists(itemCode);
		if (index == -1) {
			System.out.println("Item code not found in inventory.");
			return false;
		}

		FoodItem item = inventoryList.get(index);
		if (buyOrSell) {
			item.updateItem(quantity);
			System.out.println("Quantity updated successfully.");
			return true;
		} else {
			if (item.updateItem(-quantity)) {
				System.out.println("Quantity updated successfully.");
				return true;
			} else {
				System.out.println("Insufficient stock in inventory.");
				return false;
			}
		}
	}

	public void saveToFile(Scanner scanner) {
		System.out.print("Enter the filename to save to: ");
		String fileName = scanner.next();

		try (PrintWriter writer = new PrintWriter(new File(fileName))) {
			for (FoodItem item : inventoryList) {
				String itemTypeAbbreviation = getItemTypeAbbreviation(item);
				writer.println(itemTypeAbbreviation);

				writer.println(item.getItemCode());
				writer.println(item.getName());
				writer.println(item.getItemQuantityInStock());
				writer.printf("%.2f%n", item.getSalesPrice());
				writer.printf("%.2f%n", item.getCost());

				if (item instanceof Fruit) {
					writer.println(((Fruit) item).getOrchardName());
				} else if (item instanceof Vegetable) {
					writer.println(((Vegetable) item).getFarmName());
				} else if (item instanceof Preserve) {
					writer.println(((Preserve) item).getJarSizeInMl());
				}
			}

			System.out.println("Inventory successfully saved to file.");
		} catch (IOException e) {
			System.out.println("Could not write to file: " + e.getMessage());
		}
	}

	public void readFromFile(Scanner scanner) {
		System.out.print("Enter the filename to read from: ");
		String fileName = scanner.next();

		try (Scanner fileScanner = new Scanner(new File(fileName))) {
			while (fileScanner.hasNextLine()) {
				String itemType = fileScanner.nextLine().trim().toLowerCase();

				FoodItem item = createFoodItemFromType(itemType);
				if (item != null) {
					item.setItemCode(Integer.parseInt(fileScanner.nextLine().trim()));
					item.setName(fileScanner.nextLine().trim());
					item.setItemQuantityInStock(Integer.parseInt(fileScanner.nextLine().trim()));
					item.setSalesPrice(Float.parseFloat(fileScanner.nextLine().trim()));
					item.setCost(Float.parseFloat(fileScanner.nextLine().trim()));

					if (item instanceof Fruit) {
						((Fruit) item).setOrchardName(fileScanner.nextLine().trim());
					} else if (item instanceof Vegetable) {
						((Vegetable) item).setFarmName(fileScanner.nextLine().trim());
					} else if (item instanceof Preserve) {
						((Preserve) item).setJarSizeInMl(fileScanner.nextLine().trim());
					}

					inventoryList.add(item);
				}
			}

			System.out.println("Inventory successfully read from file.");
		} catch (IOException e) {
			System.out.println("Error encountered while reading the file: " + e.getMessage());
		}
	}

	private String getItemTypeAbbreviation(FoodItem item) {
		if (item instanceof Fruit) {
			return "f";
		} else if (item instanceof Vegetable) {
			return "v";
		} else if (item instanceof Preserve) {
			return "p";
		}
		return "";
	}

	private FoodItem createFoodItemFromType(String itemType) {
		switch (itemType) {
		case "f":
			return new Fruit();
		case "v":
			return new Vegetable();
		case "p":
			return new Preserve();
		default:
			System.out.println("Invalid item type: " + itemType);
			return null;
		}
	}

	public void displayInventory() {
		System.out.println("Inventory:");

		for (FoodItem item : inventoryList) {
			System.out.println(item.toString()); // Use the toString method of FoodItem
		}
	}

	/**
	 * Binary search for a FoodItem by item code.
	 *
	 * @param itemCode The item code to search for.
	 * @return The index of the found item in the inventory or -1 if not found.
	 */
	private int binarySearch(int itemCode) {
		int low = 0;
		int high = inventoryList.size() - 1;

		while (low <= high) {
			int mid = (low + high) / 2;
			int currentCode = inventoryList.get(mid).getItemCode();

			if (currentCode == itemCode) {
				return mid;
			} else if (currentCode < itemCode) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}

		return -1; // Item not found
	}

	private static class FoodItemComparator implements Comparator<FoodItem> {
		@Override
		public int compare(FoodItem item1, FoodItem item2) {
			return item1.getItemCode() - item2.getItemCode();
		}
	}
}
