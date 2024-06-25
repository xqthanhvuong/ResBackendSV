package restaurant_business_system.api;

import java.util.Collections;

import io.dropwizard.auth.Auth;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import restaurant_business_system.core.User;
import restaurant_business_system.db.account.Account;
import restaurant_business_system.db.account.AccountDAO;
import restaurant_business_system.db.account.AccountDTO;
import restaurant_business_system.db.account.AccountEdit;
import restaurant_business_system.exception.AccountException;
import restaurant_business_system.exception.PhoneNumberException;
import restaurant_business_system.response.ApiResponse;

@Path("/account")
@Produces(MediaType.APPLICATION_JSON)
public class AccountAPI {
    private AccountDAO dao;

    public AccountAPI(AccountDAO dao) {
        this.dao = dao;
    }

    /**
     * Registers a new account.
     *
     * @param account the account to be registered
     *                When user submit, the account object will be created and
     *                passed to the create method in the AccountDAO class.
     * @return the response containing the created account
     */
    @POST
    @Path("/register")
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(Account account) {
        try {
            Account a = new Account(account.getUsername(), account.getPassword(), account.getName(),
                    account.getPhone());
            dao.create(a);
            ApiResponse apiResponse = new ApiResponse(true, "Operation succeeded");
            return Response.ok(apiResponse).build();
        } catch (AccountException e) {
            // Account already exists
            return Response.status(Response.Status.CONFLICT)
                    .entity(Collections.singletonMap("error", e.getMessage()))
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        } catch (PhoneNumberException e) {
            // Phone number issues (exists or invalid)
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Collections.singletonMap("error", e.getMessage()))
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        } catch (Exception e) {
            // General error handling
            System.out.println(e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Collections.singletonMap("error", "An unexpected error occurred"))
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(Account a) {
        AccountDTO account = dao.findByUsernameAndPassword(a.getUsername(), a.getPassword());
        if (account != null) {
            return Response.ok(account).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @POST
    @Path("update")
    public Response edit(@Auth User u, AccountEdit a) {
        if (u != null) {
            boolean rs = dao.updateProfile(a);
            if (rs) {
                return Response.ok("OK").build();
            }
            return Response.status(Response.Status.EXPECTATION_FAILED).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @POST
    @Path("create-employee")
    public Response createEmployee(@Auth User u, Account a) {
        if (u != null) {
            Account an = new Account(a.getUsername(), a.getPassword(), a.getName(), a.getPhone(), "employee", a.getIdRestaurant());
            Account rs = dao.create(an);
            if (rs != null) {
                return Response.ok("OK").build();
            }
            return Response.status(Response.Status.EXPECTATION_FAILED).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @POST
    @Path("update-device-token")
    public Response updateDeviceToken(@Auth User u, Account a) {
        if (u != null) {
            boolean rs = dao.updateDeviceToken(u.getId(), a.getDeviceToken());
            if (rs) {
                return Response.ok("OK").build();
            }
            return Response.status(Response.Status.EXPECTATION_FAILED).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
}