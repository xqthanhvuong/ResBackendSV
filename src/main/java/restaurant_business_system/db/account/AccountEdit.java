package restaurant_business_system.db.account;

public class AccountEdit {
    private String idAccount;
    private String name;
    private String birthDate;
    private String avt;

    public AccountEdit (String idAccount, String name, String avt, String birthDate) {
        this.idAccount = idAccount;
        this.name =  name;
        this.birthDate = birthDate;
        this.avt = avt;
    }
    public AccountEdit () {
    }

    public String getName () {
        return this.name;
    }

    public String getAvt () {
        return this.avt;
    }

    public String getBirthDate () {
        return this.birthDate;
    }

    public String getIdAccount () {
        return idAccount;
    }
}
