package BuilderPattern.Mutable;


/**
 * Implement every attribute and return this state
 * finally return new object with all this attributes
 */
public class BankAccountMutableBuilderImpl implements BankAccountMutableBuilder {

    private String name; // required
    private String accountNumber; // required
    private String address;
    private String email;
    private boolean newsletter;
    private boolean mobileBanking;

    @Override
    public BankAccountMutableBuilder name(String name) {
        this.name = name;
        return this;
    }

    @Override
    public BankAccountMutableBuilder accountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    @Override
    public BankAccountMutableBuilder address(String address) {
        this.address = address;
        return this;
    }

    @Override
    public BankAccountMutableBuilder email(String email) {
        this.email = email;
        return this;
    }

    @Override
    public BankAccountMutableBuilder newsletter(boolean newsletter) {
        this.newsletter = newsletter;
        return this;
    }

    @Override
    public BankAccountMutableBuilder mobileBanking(boolean mobileBanking) {
        this.mobileBanking = mobileBanking;
        return this;
    }

    @Override
    public BankAccountMutable builder() {
        return new BankAccountMutable(name, accountNumber, address, email, newsletter, mobileBanking);
    }
}
