import java.util.Scanner;
import java.util.Formatter;

public class FoodItem implements Comparable<FoodItem> {
	private int itemCode;
	private String name;
	private int itemQuantityInStock;
	private double cost;
	private double salesPrice;

	public FoodItem() {

	}

	public FoodItem(int itemCode, String name, int itemQuantityInStock, double cost, double salesPrice) {
		this.itemCode = itemCode;
		this.name = name;
		this.itemQuantityInStock = itemQuantityInStock;
		this.cost = cost;
		this.salesPrice = salesPrice;
	}

	public boolean addItem(Scanner scanner, boolean fromFile) {
		System.out.print("Enter the code for the item: ");
		if (scanner.hasNextInt()) {
			itemCode = scanner.nextInt();
			scanner.nextLine(); // Consume the newline character
		} else {
			System.out.println("Invalid input for item code.");
			return false;
		}

		System.out.print("Enter the name for the item: ");
		name = scanner.nextLine();

		System.out.print("Enter the quantity for the item: ");
		if (scanner.hasNextInt()) {
			itemQuantityInStock = scanner.nextInt();
			scanner.nextLine(); // Consume the newline character
		} else {
			System.out.println("Invalid input for quantity.");
			return false;
		}

		System.out.print("Enter the cost of the item: ");
		if (scanner.hasNextDouble()) {
			cost = scanner.nextDouble();
			scanner.nextLine(); // Consume the newline character
		} else {
			System.out.println("Invalid input for cost.");
			return false;
		}

		System.out.print("Enter the sales price of the item: ");
		if (scanner.hasNextDouble()) {
			salesPrice = scanner.nextDouble();
			scanner.nextLine(); // Consume the newline character
		} else {
			System.out.println("Invalid input for sales price.");
			return false;
		}

		return true;
	}

	public boolean updateItem(int amount) {
		if (itemQuantityInStock + amount >= 0) {
			itemQuantityInStock += amount;
			return true;
		} else {
			System.out.println("Quantity cannot be negative.");
			return false;
		}
	}

	public boolean inputCode(Scanner scanner, boolean fromFile) {
		if (!fromFile) {
			System.out.print("Enter the code for the item: ");
			if (scanner.hasNextInt()) {
				itemCode = scanner.nextInt();
				scanner.nextLine(); // Consume the newline character
				return true;
			} else {
				System.out.println("Invalid input for item code.");
				return false;
			}
		}
		return true;
	}

	public void setItemCode(int itemCode) {
		this.itemCode = itemCode;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setItemQuantityInStock(int itemQuantityInStock) {
		this.itemQuantityInStock = itemQuantityInStock;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public void setSalesPrice(double salesPrice) {
		this.salesPrice = salesPrice;
	}

	public int getItemCode() {
		return itemCode;
	}

	public String getName() {
		return name;
	}

	public int getItemQuantityInStock() {
		return itemQuantityInStock;
	}

	public double getCost() {
		return cost;
	}

	public double getSalesPrice() {
		return salesPrice;
	}

	public void outputItem(Formatter writer) {
		writer.format("Item: %d %s %d %.2f %.2f", itemCode, name, itemQuantityInStock, cost, salesPrice);
	}

	/**
	 * Compares this FoodItem to another FoodItem based on their item codes.
	 *
	 * @param other The other FoodItem to compare to.
	 * @return A negative integer, zero, or a positive integer as this FoodItem is
	 *         less than, equal to, or greater than the other.
	 */
	@Override
	public int compareTo(FoodItem other) {
		return this.itemCode - other.itemCode;
	}

	/**
	 * Returns a string representation of the FoodItem.
	 *
	 * @return A string containing the item code, name, quantity in stock, sales
	 *         price, and cost.
	 */
	@Override
	public String toString() {
		return String.format("Item: %d %s %d price: $%.2f cost: $%.2f", getItemCode(), getName(),
				getItemQuantityInStock(), getSalesPrice(), getCost());
	}

}
