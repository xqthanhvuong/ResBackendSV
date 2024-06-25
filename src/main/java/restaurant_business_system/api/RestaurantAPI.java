package restaurant_business_system.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.dropwizard.auth.Auth;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import restaurant_business_system.core.User;
import restaurant_business_system.db.restaurant.Restaurant;
import restaurant_business_system.db.restaurant.RestaurantDAO;


@Path("/restaurant")
@Produces(MediaType.APPLICATION_JSON)
public class RestaurantAPI {
    private RestaurantDAO dao;
    private static final Logger LOGGER = LoggerFactory.getLogger(RestaurantAPI.class);

    public RestaurantAPI(RestaurantDAO dao) {
        this.dao = dao;
    }

    /**
     * Adds a new restaurant to the system.
     *
     * @param u The authenticated user.
     * @param r The restaurant to be added.
     * @return A response indicating the success or failure of the operation.
     */
    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addRestaurant(@Auth User u, Restaurant r) {
        if (u != null) {
            Restaurant re = new Restaurant(r.getName(), u.getId());
            Restaurant rs = dao.create(re);
            if (rs != null) {
                return Response.ok(rs).build();
            }
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }


    /**
     * Updates the name of a restaurant.
     *
     * @param u The authenticated user.
     * @param r The restaurant with the updated name.
     * @return A response indicating the success or failure of the operation.
     */
    @POST
    @Path("/update-name")
    public Response updateNameRestaurant(@Auth User u, Restaurant r) {
        if (u != null) {
            LOGGER.info("ID: " + r.getIdRestaurant());
            LOGGER.info("New name: " + r.getName());
            if (dao.updateName(r) != null) {
                return Response.ok("OK").build();
            }
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    /**
     * Deletes a restaurant.
     *
     * @param u The authenticated user.
     * @param r The restaurant to be deleted.
     * @return A response indicating the success or failure of the operation.
     */
    @GET
    @Path("/delete")
    public Response deleteRestaurant(@Auth User u, @QueryParam("idRestaurant") String idRestaurant){
        if (u != null) {
            dao.delete(u.getId(), idRestaurant);
            return Response.ok("OK").build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }
}
