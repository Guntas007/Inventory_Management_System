import java.util.Scanner;
import java.util.Formatter;

public class Fruit extends FoodItem {
	private String orchardName;

	public Fruit() {
		// Default constructor
	}

	public Fruit(int itemCode, String name, int itemQuantityInStock, double cost, double salesPrice,
			String orchardName) {
		super(itemCode, name, itemQuantityInStock, cost, salesPrice);
		this.orchardName = orchardName;
	}

	public String getOrchardName() {
		return orchardName;
	}

	public void setOrchardName(String orchardName) {
		this.orchardName = orchardName;
	}

	/**
	 * Adds a fruit item, taking input from the provided Scanner.
	 *
	 * @param scanner  The Scanner to take input from.
	 * @param fromFile A boolean indicating if the input is from a file.
	 * @return true if the item is successfully added, false otherwise.
	 */
	@Override
	public boolean addItem(Scanner scanner, boolean fromFile) {
		if (super.addItem(scanner, fromFile)) {
			System.out.print("Enter the orchard name: ");
			orchardName = scanner.nextLine();
			return true;
		}
		return false;
	}

	@Override
	public void outputItem(Formatter writer) {
		super.outputItem(writer);
		writer.format("%s\n", orchardName);
	}

	/**
	 * Returns a string representation of the Fruit item.
	 *
	 * @return A string containing the item code, name, quantity in stock, sales
	 *         price, cost, and orchard name.
	 */
	@Override
	public String toString() {
		return String.format("Item: %d %s %d price: $%.2f cost: $%.2f orchard supplier: %s", getItemCode(), getName(),
				getItemQuantityInStock(), getSalesPrice(), getCost(), orchardName);
	}

}
