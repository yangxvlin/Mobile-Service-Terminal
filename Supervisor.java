public class Supervisor extends Staff {

	public Supervisor(int id, String name, Store workingStore) {
	    super(id, name, workingStore);
    }

	public void getInventorySummary() {
        Inventory inventory = super.getWorkingStore().getInventory();
        System.out.println(inventory.toString());
	}

}