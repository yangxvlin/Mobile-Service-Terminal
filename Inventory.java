import java.util.HashMap;

public class Inventory {

    /* {item type: {barcode: item}, ...} */
	private HashMap<String, HashMap<String, Item>> itemInventory;

	public Inventory() {
	    itemInventory = new HashMap<>();
    }

	/**
	 * 
	 * @param itemType
	 */
	public boolean hasAvailable(String itemType) {
		return this.getQuantity(itemType) > 0;
	}

	/**
	 * 
	 * @param item
	 */
	public void addItem(Item item) {
		itemInventory.putIfAbsent(item.getItemType(), new HashMap<>());
		HashMap<String, Item> itemMap = itemInventory.get(item.getItemType());
		itemMap.put(item.getBarCode(), item);
	}

	public Item getItem(String barcode) {
        for (HashMap<String, Item> items: itemInventory.values()) {
            if (items.containsKey(barcode)) {
                return items.get(barcode);
            }
        }

        System.out.println("Unknown barcode");
        return null;
    }

	/**
	 * 
	 * @param item
	 */
	public void sellItem(Item item) {
		itemInventory.get(item.getItemType()).remove(item.getBarCode());
	}

	public int getQuantity(String itemType) {
		return itemInventory.get(itemType).size();
	}

	@Override
	public String toString() {
	    HashMap<String, Integer> quantity = new HashMap<>();

	    for (String itemType: itemInventory.keySet()) {
            quantity.put(itemType, this.getQuantity(itemType));
        }

        return quantity.toString();
    }
}