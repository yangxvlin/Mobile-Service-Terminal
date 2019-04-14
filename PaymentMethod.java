public class PaymentMethod {

	private boolean isStoreCard;
	private float cardNumber;

	public PaymentMethod(boolean isStoreCard, float cardNumber) {
		this.isStoreCard = isStoreCard;
		this.cardNumber = cardNumber;
	}

	public boolean isCreditCard() {
		return !this.isStoreCard;
	}


	public float getCardNumber() {
		return this.cardNumber;
	}

}