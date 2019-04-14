import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {

	private String date;
	public static int totalTransaction = 0;
	private int id;
	private PaymentMethod paymentMethod;
	private Sale sale;
	private static CreditAgency creditAgency = new CreditCardAgency();

	public Transaction(PaymentMethod paymentMethod, Sale sale) {
		this.sale = sale;
		this.id = totalTransaction;
		totalTransaction += 1;
		this.paymentMethod = paymentMethod;

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		this.date = dateFormat.format(date);
	}

	/**
	 * 
	 * @param store
	 */
	public boolean validatePayment(Store store) {
		if (this.paymentMethod.isCreditCard()) {
			return creditAgency.hasEnoughCreditCardRemaining(
					this.paymentMethod.getCardNumber(), getSale().getPrice());
		/* store debit card */
		} else {
			StoreDebitCard card = store.getStoreDebitCard(this.paymentMethod.getCardNumber());

			if (card == null) {
				return false;
			}

			return card.hasEnoughRemaining(this.sale.getPrice());
		}
	}

	public void makePayment(Store store) {
		if (this.paymentMethod.isCreditCard()) {
			creditAgency.makePayment(this.paymentMethod.getCardNumber(),
										this.sale.getPrice());
		/* store debit card */
		} else {
			store.getStoreDebitCard(this.paymentMethod.getCardNumber())
					.makePayment(this.sale.getPrice());
		}
		store.addTransaction(this);
	}

	public Sale getSale() {
		return this.sale;
	}

	public int getId() {return this.id;}

}