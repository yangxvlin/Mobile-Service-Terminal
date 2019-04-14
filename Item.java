public class Item {

	private String itemType;
	private String barCode;
	private float price;
	private float discountRate;
	private static int totalItem = 0;
	private int stockNumber;

	public Item(String itemType, String barCode, float price, float discountRate) {
		this.itemType = itemType;
		this.barCode = barCode;
		this.price = price;
		this.discountRate = discountRate;
		totalItem += 1;
		this.stockNumber = totalItem;
	}

	public float getPrice() {
		return this.price;
	}

	public String getItemType() {return this.itemType;}

	public int getStockNumber() {return this.stockNumber;}


	public boolean equals(Item other) {
		return this.barCode.equals(other.barCode);
	}

	public String getBarCode(){return this.barCode;}
}