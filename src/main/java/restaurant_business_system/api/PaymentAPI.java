package restaurant_business_system.api;

import io.dropwizard.auth.Auth;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import restaurant_business_system.core.User;
import restaurant_business_system.db.payment.Payment;
import restaurant_business_system.db.payment.PaymentDAO;
import restaurant_business_system.db.payment.PaymentDTO;

@Path("payment")
@Produces(MediaType.APPLICATION_JSON)
public class PaymentAPI {
    private PaymentDAO dao;

    public PaymentAPI(PaymentDAO dao) {
        this.dao = dao;
    }

    @POST
    @Path("/create-payment")
    public Response createPayment(@Auth User u, Payment p) {
        dao.createPayment(new Payment(p.getIdRestaurant(), p.getPartnerCode(), p.getAccessKey(), p.getSecretKey()));
        return Response.ok("OK").build();
    }

    @GET
    @Path("/get-payment")
    public Response getPayment(@Auth User u) {
        PaymentDTO p = dao.getPayment(u.getId());
        return Response.ok(p).build();
    }

    @POST
    @Path("/update-payment")
    public Response updatePayment(@Auth User u, Payment p) {
        dao.updatePayment(new PaymentDTO(p.getIdPayment(), p.getIdRestaurant(), p.getPartnerCode(), p.getAccessKey(), p.getSecretKey()));
        return Response.ok("OK").build();
    }
}
