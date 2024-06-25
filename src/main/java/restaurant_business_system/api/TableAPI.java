package restaurant_business_system.api;

import io.dropwizard.auth.Auth;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import restaurant_business_system.core.User;
import restaurant_business_system.db.table.Table;
import restaurant_business_system.db.table.TableDAO;
import restaurant_business_system.response.ApiResponse;

@Path("/table")
@Produces(MediaType.APPLICATION_JSON)
public class TableAPI {
    private final TableDAO dao;

    public TableAPI(TableDAO dao) {
        this.dao = dao;
    }

    @POST
    @Path("/create")
    public Response createTable(@Auth User u, Table t) {
        if (u != null) {
            Table tb = new Table(t.getIdRestaurant(), t.getTableName());
            if (dao.create(tb, u.getId()) != null) {
                ApiResponse apiResponse = new ApiResponse(true, "Operation succeeded");
                return Response.ok(apiResponse).build();
            }
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    @GET
    @Path("/delete")
    public Response deleteTable(@Auth User u, @QueryParam("idTable") String t) {
        if (u != null) {
            dao.delete(t, u.getId());
            return Response.ok("OK").build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    @GET
    @Path("/get")
    public Response getTable(@Auth User u, @QueryParam("idRestaurant") String r) {
        if (r == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        if (u != null) {
            return Response.ok(dao.get(r, u.getId())).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }
}
