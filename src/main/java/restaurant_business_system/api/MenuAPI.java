package restaurant_business_system.api;

import java.util.List;

import io.dropwizard.auth.Auth;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import restaurant_business_system.core.User;
import restaurant_business_system.db.menu.Menu;
import restaurant_business_system.db.menu.MenuDAO;
import restaurant_business_system.db.menu.MenuDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents a REST API endpoint for menu management.
 * This class is responsible for handling incoming HTTP requests related to the
 * menu entity.
 */
/**
 * This class represents the API endpoints for managing menus in a restaurant business system.
 */
@Path("/menu")
public class MenuAPI {
    private final MenuDAO dao;
    private final Logger LOGGER = LoggerFactory.getLogger(MenuAPI.class);

    /**
     * Constructs a new MenuAPI object with the specified MenuDAO.
     *
     * @param dao the MenuDAO used for interacting with the menu data.
     */
    public MenuAPI(MenuDAO dao) {
        this.dao = dao;
    }

    /**
     * Adds a new menu to the system.
     *
     * @param menu the Menu object representing the menu to be added.
     * @return a Response object indicating the status of the operation.
     */
    @POST
    @Path("/create")
    public Response addMenu(@Auth User u, Menu menu) {
        Menu m = new Menu(menu.getIdRestaurant(), menu.getName());
        if (dao.create(m) != null) {
            return Response.ok("OK").build();
        } else {
            return Response.serverError().build();
        }
    }

    /**
     * Retrieves the menu for a specific restaurant.
     *
     * @param idRestaurant the ID of the restaurant.
     * @return a Response object containing the menu for the specified restaurant.
     */
    @GET
    @Path("/get")
    @Produces("application/json")
    public Response getMenu(@QueryParam("idRestaurant") String idRestaurant){
        List<MenuDTO> menu = dao.get(idRestaurant);
        if (menu != null) {
            return Response.ok(menu).build();
        } else {
            return Response.serverError().build();
        }
    }

    /**
        * Deletes a menu based on the provided menu ID.
        *
        * @param u       the authenticated user
        * @param idMenu  the ID of the menu to be deleted
        * @return a Response object indicating the status of the deletion operation
        */
    @GET
    @Path("/delete")
    public Response deleteMenu(@Auth User u, @QueryParam("idMenu") String idMenu) {
        if(u != null) {
            LOGGER.info("Deleting menu id: " + idMenu);
            LOGGER.info("User id: " + u.getId());
            dao.delete(idMenu, u.getId());
            return Response.ok("OK").build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    /**
     * Updates the name of a menu.
     * 
     * @param u The User object representing the user making the request.
     * @param menu The Menu object representing the menu to be updated.
     */
    @POST
    @Path("/update-name")
    public Response updateMenu(@Auth User u, Menu menu) {
        if(u != null) {
            LOGGER.info("Updating menu: " + menu.getIdMenu());
            if(menu.getIdMenu() == null || menu.getName() == null || menu.getIdRestaurant() == null){
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
            dao.update(menu, u.getId());
            return Response.ok("OK").build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
}
