package restaurant_business_system.auth;

import org.checkerframework.checker.nullness.qual.Nullable;

import io.dropwizard.auth.Authorizer;
import jakarta.ws.rs.container.ContainerRequestContext;
import restaurant_business_system.core.User;

public class UserAuthorizer implements Authorizer<User> {

    public UserAuthorizer() {
        //TODO Auto-generated constructor stub
    }

    @Override
    public boolean authorize(User user, String role, @Nullable ContainerRequestContext arg2) {
        return true;
    }
}

