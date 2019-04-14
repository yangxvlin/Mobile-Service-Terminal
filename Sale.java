import java.util.ArrayList;
import java.util.List;

public class Sale {

	private double totalPrice = 0.0;
	private List<Item> items;
	private Transaction transaction;
	private Staff operatedStaff;

	public Sale(Staff operatedStaff) {
        this.items = new ArrayList<>();
        this.transaction = null;
        this.operatedStaff = operatedStaff;
    }

	/**
	 * 
	 * @param item
	 */
	public void addItem(Item item) {
		this.items.add(item);
	}

	public double calculateItemPrice() {
		for (Item item: items) {
		    this.totalPrice += item.getPrice();
        }

        return this.totalPrice;
	}

	/**
	 * 
	 * @param paymentMethod
	 */
	public void createTransaction(PaymentMethod paymentMethod) {
		this.transaction = new Transaction(paymentMethod, this);
	}

	public double getPrice() { return this.totalPrice; }

	public Transaction getTransaction() {
		return transaction;
	}

	public List<Item> getItems() {
		return items;
	}

}