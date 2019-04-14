import java.util.HashMap;

/**
 * Xulin Yang, 904904
 *
 * @create 2019-04-14 17:13
 * description:
 **/

public class CreditCardAgency implements CreditAgency{
    private HashMap<Float, Double> creditCardDB;

    public CreditCardAgency() {
        this.creditCardDB = new HashMap<Float, Double>();

        CreditCardAgency.addSampleCreditCard(this);
    }

    public boolean hasEnoughCreditCardRemaining(float cardNumber, double amount) {
        if (!creditCardDB.containsKey(cardNumber)) {
            return false;
        }
        return creditCardDB.get(cardNumber) >= amount;
    }

    public void makePayment(float cardNumber, double amount) {
        Double remainingAmount = creditCardDB.get(cardNumber);
        creditCardDB.put(cardNumber, remainingAmount-amount);
        System.out.print("Card remaining: $");
        System.out.println(Double.toString(creditCardDB.get(cardNumber)));
    }

    public static void addSampleCreditCard(CreditCardAgency creditCardAgency) {
        creditCardAgency.creditCardDB.put(0f, 1000.0);
        creditCardAgency.creditCardDB.put(1f, 1000.0);
    }
}
