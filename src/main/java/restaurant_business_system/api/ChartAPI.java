package restaurant_business_system.api;

import java.util.Collections;

import io.dropwizard.auth.Auth;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import restaurant_business_system.core.User;
import restaurant_business_system.db.chart.ChartDAO;
import restaurant_business_system.db.chart.ChartRequestMonth;
import restaurant_business_system.exception.AccountException;
import restaurant_business_system.exception.PhoneNumberException;
import restaurant_business_system.response.ApiResponse;

@Path("/chart")
@Produces(MediaType.APPLICATION_JSON)
public class ChartAPI {
    private ChartDAO dao;

    public ChartAPI(ChartDAO dao) {
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
    @GET
    @Path("/getMonthlyChart")
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(@Auth User u ,@QueryParam("idRestaurant") String idRestaurant,
    @QueryParam("month") int month,
    @QueryParam("year") int year) {
    if(u!=null){
        
        ChartRequestMonth chartRequest = new ChartRequestMonth(idRestaurant, month, year);
        try {
            System.out.println(dao.getMonthlyChart(chartRequest));
            return Response.ok(dao.getMonthlyChart(chartRequest)).build();
        } catch (AccountException | PhoneNumberException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ApiResponse(false, "Errol"))
                    .build();
        }
    }else {
        return Response.status(Response.Status.UNAUTHORIZED)
               .entity(Collections.singletonMap("error", "You are not logged in."))
               .type(MediaType.APPLICATION_JSON)
               .build();
    }
    }

    @GET
    @Path("/getWeekChartTable")
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(@Auth User u ,@QueryParam("idRestaurant") String idRestaurant) {
    if(u!=null){
        try {
            System.out.println(dao.getWeekChartForTable(idRestaurant));
            return Response.ok(dao.getWeekChartForTable(idRestaurant)).build();
        } catch (AccountException | PhoneNumberException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ApiResponse(false, "Errol"))
                    .build();
        }
    }else {
        return Response.status(Response.Status.UNAUTHORIZED)
               .entity(Collections.singletonMap("error", "You are not logged in."))
               .type(MediaType.APPLICATION_JSON)
               .build();
    }
    }

}