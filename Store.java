import java.util.HashMap;
import java.util.List;

public class Store {

	private String address;
	private HashMap<String, Staff> staffDB;
	private Inventory inventory;
	private HashMap<Float, StoreDebitCard> storeDebitCardDB;
	private HashMap<Integer, Transaction> transactionDB;

	public Store(String address) {
	    this.address = address;
	    this.staffDB = new HashMap<>();
	    this.inventory = new Inventory();
	    this.storeDebitCardDB = new HashMap<>();
	    this.transactionDB = new HashMap<>();

	    Store.addSampleStoreDebitCard(this);
    }

	public void registerStaff(int id, String name, String type) {
        String key = name + Integer.toString(id);

        if (!this.isInStaffDB(key)) {
            if (type.equals(NormalStaff.class.getName())) {
                staffDB.put(key, new NormalStaff(id, name, this));
            } else if (type.equals(Supervisor.class.getName())) {
                staffDB.put(key, new Supervisor(id, name, this));
            } else {
                System.out.println("Unsupported staff type");
            }
        } else {
            System.out.println("Already has the staff");
        }

    }

    private boolean isInStaffDB(String key) {
	    return staffDB.get(key) != null;
    }


	/**
	 * 
	 * @param id
	 * @param name
	 */
	public boolean isCorrectStaff(int id, String name) {
        String key = name + Integer.toString(id);
        return this.isInStaffDB(key);
	}

	/**
	 * 
	 * @param id
	 * @param name
	 */
	public Staff getStaff(int id, String name) {
        String key = name + Integer.toString(id);
        return staffDB.get(key);
	}

	public Inventory getInventory() {
		return this.inventory;
	}

	/**
	 * 
	 * @param cardNumber
	 */
	public StoreDebitCard getStoreDebitCard(float cardNumber) {
		return this.storeDebitCardDB.get(cardNumber);
	}

	/**
	 * 
	 * @param items
	 */
	public void sellItems(List<Item> items) {
		for (Item item:items) {
            this.inventory.sellItem(item);
        }
	}

	public void addTransaction(Transaction transaction) {
	    this.transactionDB.put(transaction.getId(), transaction);
    }

	public static void addSampleStoreDebitCard(Store store) {
        store.storeDebitCardDB.put(0f, new StoreDebitCard(0f, 1000.0));
        store.storeDebitCardDB.put(1f, new StoreDebitCard(1f, 1000.0));
        store.storeDebitCardDB.put(2f, new StoreDebitCard(2f, 1000.0));
    }
}