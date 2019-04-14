public class Controller {
	private Store store;
	private Staff operatingStaff;

	public Controller(Store store) {
        this.store = store;
        this.operatingStaff = null;
    }

    public Staff getOperatingStaff() {return operatingStaff;}

    public void logout() {
	    operatingStaff = null;
    }

	/**
	 * 
	 * @param id
	 * @param name
	 */
	public boolean login(int id, String name) {
		if (store.isCorrectStaff(id, name)) {
            this.operatingStaff = store.getStaff(id, name);
            return true;
        }
        return false;
	}

	public void createSale() {
		this.operatingStaff.newSale();
	}

	/**
	 * 
	 * @param item
	 */
	public void enterSaleItem(Item item) {
		this.operatingStaff.enterSaleItem(item);
	}

	public double calculateSalePrince() {
        return this.operatingStaff.getProcessingSale()
                                    .calculateItemPrice();
	}

	/**
	 * 
	 * @param paymentMethod
	 */
	public void createTransaction(PaymentMethod paymentMethod) {
		this.operatingStaff.createTransaction(paymentMethod);
	}

	public boolean validatePayment() {
		return this.operatingStaff.getProcessingSale()
                                    .getTransaction()
                                    .validatePayment(this.store);
	}

	public void makePayment() {
        this.operatingStaff.getProcessingSale()
                            .getTransaction()
                            .makePayment(this.store);

        store.sellItems(this.operatingStaff
                                .getProcessingSale()
                                .getItems());
	}

	/**
	 * 
	 * @param item
	 */
	public void addNewItem(Item item) {
		store.getInventory().addItem(item);
	}

	public void inventoryLookUp() {
	    if (this.operatingStaff instanceof Supervisor) {
            ((Supervisor)(this.operatingStaff)).getInventorySummary();
        } else {
	        System.out.println("Unauthorised lookup.");
        }

	}

	public void cancelSale() {
        operatingStaff.cancelSale();
    }

    public Item getItem(String barCode) {
        return this.operatingStaff.getWorkingStore()
                                    .getInventory()
                                    .getItem(barCode);
    }



}