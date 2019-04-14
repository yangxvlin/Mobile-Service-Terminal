public class StoreDebitCard {

	private float number;
	private double remainingBalance;
	private static double discountRate = 0.1;

	public StoreDebitCard(float number, double remainingBalance) {
		this.number = number;
		this.remainingBalance = remainingBalance;
	}

	/**
	 * 
	 * @param totalPrice
	 */
	public boolean hasEnoughRemaining(double totalPrice) {
		return totalPrice <= remainingBalance;
	}

	/**
	 * 
	 * @param price
	 */
	public void makePayment(double price) {
		this.remainingBalance -= (1-discountRate) * price;
		System.out.print("Card remaining: $");
		System.out.println(Double.toString(this.remainingBalance));
	}

}