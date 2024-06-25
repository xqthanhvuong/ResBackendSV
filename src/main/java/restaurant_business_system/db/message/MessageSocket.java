package restaurant_business_system.db.message;

public class MessageSocket {
    private String clientID;
    private String message;
    
    public MessageSocket() {
    }

    public MessageSocket(String clientID, String message) {
        this.clientID = clientID;
        this.message = message;
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
