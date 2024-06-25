package restaurant_business_system.db.payment;

import java.util.List;
import java.util.Map;

import org.jdbi.v3.core.Jdbi;

import restaurant_business_system.resources.GenerateID;

public class PaymentDAO {
    private final Jdbi jdbi;

    public PaymentDAO(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    public void createPayment(Payment payment) {
        jdbi.useHandle(handle -> {
            handle.createUpdate(
                    "INSERT INTO payments (id_payment, id_restaurant, partner_code, access_key, secret_key) VALUES (:idPayment, :idRestaurant, :partnerCode, :accessKey, :secretKey)")
                    .bind("idPayment", GenerateID.generateUniqueID())
                    .bind("idRestaurant", payment.getIdRestaurant())
                    .bind("partnerCode", payment.getPartnerCode())
                    .bind("accessKey", payment.getAccessKey())
                    .bind("secretKey", payment.getSecretKey())
                    .execute();
        });
    }

    public PaymentDTO getPayment(String idAccount) {
        return jdbi.withHandle(handle -> {
            List<Map<String, Object>> rs = handle
                    .createQuery("SELECT * FROM payments WHERE id_restaurant = (Select id_restaurant from accounts where id_account = :idAccount)")
                    .bind("idAccount", idAccount)
                    .mapToMap()
                    .list();

            if (rs.isEmpty()) {
                return null;
            }

            return new PaymentDTO((String) rs.get(0).get("id_payment"), (String) rs.get(0).get("id_restaurant"),
                    (String) rs.get(0).get("partner_code"), (String) rs.get(0).get("access_key"),
                    (String) rs.get(0).get("secret_key"));
        });
    }

    public void updatePayment(PaymentDTO payment) {
        jdbi.useHandle(handle -> {
            handle.createUpdate(
                    "UPDATE payments SET id_restaurant = :idRestaurant, partner_code = :partnerCode, access_key = :accessKey, secret_key = :secretKey WHERE id_payment = :idPayment")
                    .bind("idPayment", payment.getIdPayment())
                    .bind("idRestaurant", payment.getIdRestaurant())
                    .bind("partnerCode", payment.getPartnerCode())
                    .bind("accessKey", payment.getAccessKey())
                    .bind("secretKey", payment.getSecretKey())
                    .execute();
        });
    }
}
