package restaurant_business_system.api;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import restaurant_business_system.db.OTP.OTP;
import restaurant_business_system.db.OTP.OTPDAO;
import restaurant_business_system.db.OTP.PhoneNumberRequest;
import restaurant_business_system.db.account.AccountDAO;
import restaurant_business_system.helper.OTPHelper;
import restaurant_business_system.response.ApiResponse;
import io.github.cdimascio.dotenv.Dotenv;


@Path("/otp")
@Produces(MediaType.APPLICATION_JSON)
public class OTPAPI {
    private static final Dotenv dotenv = Dotenv.load();
    private final String ACCOUNT_SID = dotenv.get("TWILIO_ACCOUNT_SID");
    private final String AUTH_TOKEN = dotenv.get("TWILIO_AUTH_TOKEN");
    private final String PHONE = dotenv.get("TWILIO_PHONE");

    private final OTPDAO otpDao;
    private final AccountDAO accountDao;

    public OTPAPI(OTPDAO otpDao, AccountDAO accountDao) {
        this.otpDao = otpDao;
        this.accountDao = accountDao;
    }

    @POST
    @Path("/sendOTP")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response sendOTP(PhoneNumberRequest request) {
        try {
            String code_otp = OTPHelper.generateOTP(5);
            if(send(request.getPhone(), code_otp)){
                if(otpDao.create(new OTP(request.getPhone() , code_otp))){
                    ApiResponse apiResponse = new ApiResponse(true, "Operation succeeded");
                    return Response.ok(apiResponse).build();
                }
            }
            return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\":\"Failed to send OTP.\"}").build();
        } catch (Exception e) {
            System.err.println(e);
            return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\":\"Failed to send OTP.\"}").build();
        }
    }
    @POST
    @Path("/sendOTP-mail")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response sendOTPMail(PhoneNumberRequest request){
        try {
            String phone = otpDao.getPhoneByMail(request.getMail());
            String code_otp = OTPHelper.generateOTP(5);
            if(send(phone, code_otp)){
                if(otpDao.create(new OTP(phone , code_otp))){
                    ApiResponse apiResponse = new ApiResponse(true, "Operation succeeded");
                    return Response.ok(apiResponse).build();
                }
            }
            return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\":\"Failed to send OTP.\"}").build();
        } catch (Exception e) {
            System.err.println(e);
            return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\":\"Failed to send OTP.\"}").build();
        }
    }

    private boolean send(String phone, String code_otp){
        try {
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
            Message message = Message.creator(
                    new PhoneNumber(phone), // Số điện thoại nhận
                    new PhoneNumber(PHONE), // Số điện thoại gửi của Twilio
                    "Your OTP code is: " + code_otp  // Nội dung tin nhắn
            ).create();
            message.getSid();
        } catch (Exception e) {
            throw e;
        }
        return true;
    }
    
    @POST
    @Path("/activeOTP")
    @Produces(MediaType.APPLICATION_JSON)
    public Response activeOTP(OTP otp){
        try {
            if(!OTPHelper.isOTP(otp.getOtp_code())){
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Invalid OTP")
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }
            // Check OTP
            if(otpDao.checkOTP(otp)){
                if(accountDao.activeAcount(otp.getPhone())){
                    ApiResponse apiResponse = new ApiResponse(true, "Operation succeeded");
                return Response.ok(apiResponse).build();
                }
                System.out.println("12312313");
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .type(MediaType.APPLICATION_JSON)
                .build();
            }
            return Response.status(Response.Status.UNAUTHORIZED)
                            .entity("OTP is not valid")
                            .type(MediaType.APPLICATION_JSON)
                            .build();
            
        } catch (Exception e) {

            System.out.println(e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
            .type(MediaType.APPLICATION_JSON)
            .build();
        }        
    }

    @POST
    @Path("/checkOTP")
    @Produces(MediaType.APPLICATION_JSON)
    public Response checkOTP(OTP otp){
        try {
            if(otpDao.checkOTP(otp)){
                ApiResponse apiResponse = new ApiResponse(true, "Operation succeeded");
        return Response.ok(apiResponse).build();
            }
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
            .type(MediaType.APPLICATION_JSON)
            .build();
        } catch (Exception e) {
            // TODO: handle exception
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
            .type(MediaType.APPLICATION_JSON)
            .build();
        }
    }

}