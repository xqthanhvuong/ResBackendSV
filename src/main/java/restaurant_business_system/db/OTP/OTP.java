package restaurant_business_system.db.OTP;

public class OTP {
    private int otp_id;
    private String phone;
    private String otp_code;
    private boolean used;
    public int getOtp_id() {
        return otp_id;
    }
    public void setOtp_id(int otp_id) {
        this.otp_id = otp_id;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getOtp_code() {
        return otp_code;
    }
    public void setOtp_code(String otp_code) {
        this.otp_code = otp_code;
    }
    public boolean isUsed() {
        return used;
    }
    public void setUsed(boolean used) {
        this.used = used;
    }
    public OTP(String phone, String otp_code) {
        this.phone = phone;
        this.otp_code = otp_code;
        this.used = false;
    }
    public OTP(int otp_id, String phone, String otp_code, boolean used) {
        this.otp_id = otp_id;
        this.phone = phone;
        this.otp_code = otp_code;
        this.used = used;
    }
    public OTP() {
    }
}