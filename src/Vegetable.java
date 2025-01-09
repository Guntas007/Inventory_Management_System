import java.util.Scanner;
import java.util.Formatter;

public class Vegetable extends FoodItem {
	private String farmName;

	public Vegetable() {
		// Default constructor
	}

	public Vegetable(int itemCode, String name, int itemQuantityInStock, double cost, double salesPrice,
			String farmName) {
		super(itemCode, name, itemQuantityInStock, cost, salesPrice);
		this.farmName = farmName;
	}

	public String getFarmName() {
		return farmName;
	}

	public void setFarmName(String farmName) {
		this.farmName = farmName;
	}

	/**
	 * Adds a vegetable item, taking input from the provided Scanner.
	 *
	 * @param scanner  The Scanner to take input from.
	 * @param fromFile A boolean indicating if the input is from a file.
	 * @return true if the item is successfully added, false otherwise.
	 */
	@Override
	public boolean addItem(Scanner scanner, boolean fromFile) {
		if (super.addItem(scanner, fromFile)) {
			System.out.print("Enter the farm name: ");
			farmName = scanner.nextLine();
			return true;
		}
		return false;
	}

	@Override
	public void outputItem(Formatter writer) {
		super.outputItem(writer);
		writer.format("%s\n", farmName);
	}

	/**
	 * Returns a string representation of the Vegetable item.
	 *
	 * @return A string containing the item code, name, quantity in stock, sales
	 *         price, cost, and farm name.
	 */
	@Override
	public String toString() {
		return String.format("Item: %d %s %d price: $%.2f cost: $%.2f farm supplier: %s", getItemCode(), getName(),
				getItemQuantityInStock(), getSalesPrice(), getCost(), farmName);
	}

}
