package restaurant_business_system.db.payment;

public class PaymentDTO {
    private String idPayment;
    private String idRestaurant;
    private String partnerCode;
    private String accessKey;
    private String secretKey;

    public PaymentDTO(String idPayment, String idRestaurant, String partnerCode, String accessKey, String secretKey) {
        this.idPayment = idPayment;
        this.idRestaurant = idRestaurant;
        this.partnerCode = partnerCode;
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    public String getIdRestaurant() {
        return idRestaurant;
    }

    public String getPartnerCode() {
        return partnerCode;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public String getIdPayment() {
        return idPayment;
    }
}
