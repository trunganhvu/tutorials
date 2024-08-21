package Builder.Mutable;

/**
 * Object như bình thường (thuộc tính, get, set, all argu constructor)
 */
public class BankAccountMutable {
    private String name; // required
    private String accountNumber; // required
    private String address;
    private String email;
    private boolean newsletter;
    private boolean mobileBanking;

    public BankAccountMutable(String name, String accountNumber, String address, String email, boolean newsletter, boolean mobileBanking) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.address = address;
        this.email = email;
        this.newsletter = newsletter;
        this.mobileBanking = mobileBanking;
    }

    public String getName() {
        return name;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public boolean isNewsletter() {
        return newsletter;
    }

    public boolean isMobileBanking() {
        return mobileBanking;
    }

    @Override
    public String toString() {
        return "BankAccount [name=" + name + ", accountNumber=" + accountNumber + ", address=" + address + ", email="
                + email + ", newsletter=" + newsletter + ", mobileBanking=" + mobileBanking + "]";
    }
}
