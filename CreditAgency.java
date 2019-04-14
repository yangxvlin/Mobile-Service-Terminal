public interface CreditAgency {

	/**
	 * 
	 * @param cardNumber
	 */
	boolean hasEnoughCreditCardRemaining(float cardNumber, double amount);

	/**
	 * 
	 * @param cardNumber
	 * @param amount
	 */
	void makePayment(float cardNumber, double amount);

}