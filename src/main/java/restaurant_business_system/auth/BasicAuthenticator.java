package restaurant_business_system.auth;

import java.util.Optional;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import io.dropwizard.hibernate.UnitOfWork;
import restaurant_business_system.core.User;
import restaurant_business_system.db.account.AccountDAO;
import restaurant_business_system.db.account.AccountDTO;

/**
 * This class is responsible for authenticating basic credentials and returning the corresponding user.
 */
public class BasicAuthenticator implements Authenticator<BasicCredentials, User> {
    private final AccountDAO accountDAO;

    public BasicAuthenticator(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    @Override
    @UnitOfWork
    public Optional<User> authenticate(BasicCredentials credentials) throws AuthenticationException {
        // Validate the credentials and return the user
        String pass = credentials.getPassword();
        String us = credentials.getUsername();
        AccountDTO a = accountDAO.findByUsernameAndPassword(us, pass);
        if (a != null) {
            return Optional.of(new User(us, a.getId(), true));
        }
        return Optional.empty();
    }
}
