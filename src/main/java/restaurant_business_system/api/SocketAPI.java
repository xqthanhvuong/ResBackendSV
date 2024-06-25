package restaurant_business_system.api;

import java.io.IOException;

import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import restaurant_business_system.db.message.MessageSocket;

@Path("/socket")
public class SocketAPI {
    private final Logger LOGGER = LoggerFactory.getLogger(SocketAPI.class);

    public SocketAPI() {
    }

    @POST
    @Path("/test")
    public Response sendMessage(MessageSocket m) {
        String clientId = "0UDUVYE7SM";
        String message = m.getMessage();
        try {
            int statusCode = sendMessageToSocketServer(clientId, message);
            if (statusCode == HttpStatus.SC_OK) {
                return Response.ok("Message sent to server socket").build();
            } else if (statusCode == HttpStatus.SC_NOT_FOUND) {
                return Response.status(Response.Status.NOT_FOUND).entity("Client not connected").build();
            } else {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to send message").build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to send message").build();
        }
    }

    private int sendMessageToSocketServer(String clientId, String message) throws IOException {
        String url = "http://localhost:3000/api/v1/socket/send-message";
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        post.setHeader("Content-Type", "application/json");

        String json = String.format("{\"clientId\":\"%s\", \"message\":\"%s\"}", clientId, message);
        StringEntity entity = new StringEntity(json);
        post.setEntity(entity);

        CloseableHttpResponse response = client.execute(post);
        int statusCode = response.getCode();
        LOGGER.info("Response code: " + statusCode);
        EntityUtils.consume(response.getEntity());
        client.close();

        return statusCode;
    }
}
