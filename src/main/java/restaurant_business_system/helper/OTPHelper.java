package restaurant_business_system.helper;

import java.security.SecureRandom;
public class OTPHelper {
    public static String generateOTP(int length) {
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(10)); // Thêm một số ngẫu nhiên từ 0 đến 9
        }

        return sb.toString();
    }
    
    public static boolean isOTP(String otp){
        return otp.matches("[0-9]{5}");
    }
}