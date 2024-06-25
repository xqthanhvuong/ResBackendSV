package restaurant_business_system.helper;

public class PhoneNumberHelper {
    /**
     * Check if the string is a valid phone number.
     * Assume a valid phone number contains only digits and is between 10 and 15 characters long.
     *
     * @param s The string to check.
     * @return true if the string is a valid phone number, otherwise false.
     */
    public static boolean isValidPhoneNumber(String s) {
        // Kiểm tra xem chuỗi có null hoặc không phù hợp độ dài không
        if (s == null || s.length() < 10 || s.length() > 15) {
            return false;
        }
    
        // Bắt đầu kiểm tra từ ký tự đầu tiên
        int start = 0;
    
        // Nếu số điện thoại bắt đầu bằng dấu '+', bỏ qua ký tự đó
        if (s.startsWith("+")) {
            start = 1;
        }
    
        // Kiểm tra tất cả các ký tự còn lại phải là chữ số
        for (int i = start; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) {
                return false;
            }
        }
    
        // Nếu tất cả các ký tự là chữ số (và có thể có một dấu '+' ở đầu), trả về true
        return true;
    }
    

    public static String convertToInternationalFormat(String phoneNumber){
        if(phoneNumber.startsWith("0")){
            return "+84"+phoneNumber.substring(1);
        }else{
            return "+84"+phoneNumber;
        }
    }
}
