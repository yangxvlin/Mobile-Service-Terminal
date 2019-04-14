public abstract class Staff extends Person {

	private Store workingStore;
	private Sale processingSale;

	public Staff(int id, String name, Store workingStore) {
		super(id, name);
		this.processingSale = null;
		this.workingStore = workingStore;
	}

	public void cancelSale() {
		this.processingSale = null;
	}

	public void newSale() {
		this.processingSale = new Sale(this);
	}

	/**
	 * 
	 * @param item
	 */
	public void addNewItem(Item item) {
		this.workingStore.getInventory().addItem(item);
	}

	/**
	 * 
	 * @param item
	 */
	public void enterSaleItem(Item item) {
		this.processingSale.addItem(item);
	}

	/**
	 * 
	 * @param payMethod
	 */
	public void createTransaction(PaymentMethod payMethod) {
		this.processingSale.createTransaction(payMethod);
	}

	public Store getWorkingStore() {
		return this.workingStore;
	}

	public Sale getProcessingSale() {
		return this.processingSale;
	}

	public int getId() {
		return super.getId();
	}

	public String getName() {
		return super.getName();
	}

}