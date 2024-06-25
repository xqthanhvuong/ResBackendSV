package restaurant_business_system.db.OTP;

import java.util.Optional;

import org.jdbi.v3.core.Jdbi;

public class OTPDAO {
    private final Jdbi jdbi;

    public OTPDAO(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    public boolean create(OTP otp){
        try {
            // Sử dụng 'withHandle' để tự động đóng handle sau khi sử dụng
            int result = jdbi.withHandle(handle -> 
                handle.createUpdate("INSERT INTO otps(phone, otp_code) VALUES (?, ?)")
                    .bind(0, otp.getPhone())
                    .bind(1, otp.getOtp_code())
                    .execute()
            );
    
            // 'execute' trả về số hàng bị ảnh hưởng, nếu lớn hơn 0 tức là chèn thành công
            return result > 0;
        } catch (Exception e) {
            // Log lỗi để dễ dàng theo dõi và khắc phục
            System.err.println("Error inserting OTP: " + e.getMessage());
            return false;
        }
    }
    
    public String getPhoneByMail(String email){
        return jdbi.withHandle(handle -> {
            Optional<String> phone = handle.createQuery("SELECT phone FROM accounts WHERE username = :email")
                   .bind("email", email)
                   .mapTo(String.class)
                   .findOne();
            return phone.orElse(null);
        });
    }

    public boolean checkOTP(OTP otp) {
        return jdbi.withHandle(handle -> {
            int updated = handle.createUpdate("UPDATE otps SET used = 1 WHERE phone = :phone AND otp_code = :otpCode")
                    .bind("phone", otp.getPhone())
                    .bind("otpCode", otp.getOtp_code())
                    .execute();
            return updated > 0;
        });
    }
    
}
