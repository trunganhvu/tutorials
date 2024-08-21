package Builder.Mutable;

/**
 * Defind builder per attribute and object instant
 */
public interface BankAccountMutableBuilder {
    BankAccountMutableBuilder name(String name); // required
    BankAccountMutableBuilder accountNumber(String accountNumber); // required
    BankAccountMutableBuilder address(String address);
    BankAccountMutableBuilder email(String email);
    BankAccountMutableBuilder newsletter(boolean newsletter);
    BankAccountMutableBuilder mobileBanking(boolean mobileBanking);

    BankAccountMutable builder();
}
