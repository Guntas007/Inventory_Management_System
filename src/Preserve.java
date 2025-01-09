import java.util.Scanner;
import java.util.Formatter;

public class Preserve extends FoodItem {
	private String jarSizeInMl;

	public Preserve() {
		// Default constructor
	}

	public Preserve(int itemCode, String name, int itemQuantityInStock, double cost, double salesPrice,
			String jarSizeInMl) {
		super(itemCode, name, itemQuantityInStock, cost, salesPrice);
		this.jarSizeInMl = jarSizeInMl;
	}

	public String getJarSizeInMl() {
		return jarSizeInMl;
	}

	public void setJarSizeInMl(String string) {
		this.jarSizeInMl = string;
	}

	/**
	 * Adds a preserve item, taking input from the provided Scanner.
	 *
	 * @param scanner  The Scanner to take input from.
	 * @param fromFile A boolean indicating if the input is from a file.
	 * @return true if the item is successfully added, false otherwise.
	 */
	@Override
	public boolean addItem(Scanner scanner, boolean fromFile) {
		if (super.addItem(scanner, fromFile)) {
			System.out.print("Enter the jar size in milliliters: ");
			if (scanner.hasNextInt()) {
				jarSizeInMl = scanner.next();
				scanner.nextLine(); // Consume the newline character
				return true;
			} else {
				System.out.println("Invalid input for jar size.");
				return false;
			}
		}
		return false;
	}

	@Override
	public void outputItem(Formatter writer) {
		super.outputItem(writer);
		writer.format("%d\n", jarSizeInMl);
	}

	/**
	 * Returns a string representation of the Preserve item.
	 *
	 * @return A string containing the item code, name, quantity in stock, sales
	 *         price, cost, and jar size in milliliters.
	 */
	@Override
	public String toString() {
		return String.format("Item: %d %s %d price: $%.2f cost: $%.2f size: %d mL", getItemCode(), getName(),
				getItemQuantityInStock(), getSalesPrice(), getCost(), jarSizeInMl);
	}

}
