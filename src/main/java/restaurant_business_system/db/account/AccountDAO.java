package restaurant_business_system.db.account;

import org.mindrot.jbcrypt.BCrypt;
import java.util.Map;

import org.jdbi.v3.core.Jdbi;

import restaurant_business_system.exception.AccountException;
import restaurant_business_system.exception.PhoneNumberException;
import restaurant_business_system.helper.PhoneNumberHelper;

/**
 * The AccountDAO class is responsible for performing database operations
 * related to the Account entity.
 * It extends the AbstractDAO class and provides methods for creating, finding,
 * updating, and querying Account objects.
 */
public class AccountDAO {

    private final Jdbi jdbi;

    public AccountDAO(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    /**
     * Creates a new Account object in the database.
     * 
     * @param account The Account object to be created.
     * @return The Account object that was created.
     */
    public Account create(Account account) {
        jdbi.useHandle(handle -> {
            // Check if the account already exists => throw exception 409
            boolean existingAccount = accountIsExist(account.getUsername());
            if (existingAccount) {
                throw new AccountException("Account already exists");
            }
            boolean existingPhoneNumber = phoneNumberExists(account.getPhone());
            if (existingPhoneNumber) {
                throw new PhoneNumberException("Phone number already exists");
            }
            if (!PhoneNumberHelper.isValidPhoneNumber(account.getPhone())) {
                throw new PhoneNumberException("Phone number invalid");
            }
            String passwordHash = BCrypt.hashpw(account.getPassword(), BCrypt.gensalt(12));

            handle.createUpdate(
                    "INSERT INTO accounts (id_account, username, password, role, name, phone, status, id_restaurant) VALUES (:idAccount, :username, :password, :role, :name, :phone, :status, :idRestaurant)")
                    .bind("idAccount", account.getId())
                    .bind("username", account.getUsername())
                    .bind("password", passwordHash)
                    .bind("role", account.getRole())
                    .bind("name", account.getName())
                    .bind("phone", account.getPhone())
                    .bind("status", account.getStatus())
                    .bind("idRestaurant", account.getIdRestaurant())
                    .execute();
        });
        return account;
    }

    private boolean phoneNumberExists(String phone) {
        return jdbi.withHandle(handle -> {
            Map<String, Object> account = handle.createQuery("SELECT * FROM accounts WHERE phone = :phone")
                    .bind("phone", phone)
                    .mapToMap()
                    .findFirst()
                    .orElse(null);
            return account != null;
        });
    }

    /**
     * Finds an Account object by its username and password.
     * 
     * @param username The username of the Account.
     * @param password The password of the Account.
     * @return The Account object if found, null otherwise.
     */
    public AccountDTO findByUsernameAndPassword(String username, String password) {
        Map<String, Object> account = jdbi.withHandle(handle -> handle
                .createQuery("SELECT * FROM accounts WHERE username = :username && status = 'active'")
                .bind("username", username)
                .mapToMap()
                .findFirst()
                .orElse(null));
        if (account == null) {
            return null;
        }
        if (BCrypt.checkpw(password, (String) account.get("password"))) {
            return new AccountDTO((String) account.get("id_account"), (String) account.get("username"),
                    (String) account.get("name"), (String) account.get("phone"), (String) account.get("role"),
                    (String) account.get("status"), (String) account.get("id_restaurant"), (String) account.get("avt"), (String) account.get("birthdate"));
        } else {
            return null;
        }
    }

    // check if account exists
    public boolean accountIsExist(String username) {
        return jdbi.withHandle(handle -> {
            Map<String, Object> account = handle.createQuery("SELECT * FROM accounts WHERE username = :username")
                    .bind("username", username)
                    .mapToMap()
                    .findFirst()
                    .orElse(null);
            return account != null;
        });
    }

    public boolean activeAcount(String phone) {
        return jdbi.withHandle(handle -> {
            handle.createUpdate("UPDATE accounts SET status = 'active' WHERE phone = :phone")
                    .bind("phone", phone)
                    .execute();
            return true;
        });
    }

    public boolean updateProfile(AccountEdit a) {
        try {
            return jdbi.withHandle(h -> {
                h.createUpdate(
                        "UPDATE accounts set name = :name, avt = :avt, birthdate = :birthDate where id_account = :idAccount")
                        .bind("name", a.getName())
                        .bind("avt", a.getAvt())
                        .bind("birthDate", a.getBirthDate())
                        .bind("idAccount", a.getIdAccount())
                        .execute();
                return true;
            });
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateRestaurant(String idRes, String idAcc) {
        return jdbi.withHandle(handle -> {
            handle.createUpdate("UPDATE accounts SET id_restaurant = :idRes WHERE id_account = :idAccount")
                    .bind("idRes", idRes)
                    .bind("idAccount", idAcc)
                    .execute();
            return true;
        });
    }

    public boolean updateDeviceToken(String idAccount, String deviceToken) {
        return jdbi.withHandle(handle -> {
            handle.createUpdate("UPDATE accounts SET device_token = :deviceToken WHERE id_account = :idAccount")
                    .bind("deviceToken", deviceToken)
                    .bind("idAccount", idAccount)
                    .execute();
            return true;
        });
    }
}
