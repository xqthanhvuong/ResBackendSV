package restaurant_business_system.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotiClient {

    public static void sendMessgae(String deviceToken, String message) {
        // Prepare list of target device tokens
        List<String> deviceTokens = new ArrayList<>();

        // Add your device tokens here
        deviceTokens.add(deviceToken);

        // Convert to String[] array
        String[] to = deviceTokens.toArray(new String[deviceTokens.size()]);

        // Optionally, send to a publish/subscribe topic instead
        // String to = '/topics/news';

        // Set payload (any object, it will be serialized to JSON)
        Map<String, String> payload = new HashMap<>();

        // Add "message" parameter to payload
        payload.put("message", message);

        // iOS notification fields
        Map<String, Object> notification = new HashMap<>();

        notification.put("badge", 1);
        notification.put("sound", "ping.aiff");
        notification.put("title", "Thông báo từ nhà hàng");
        notification.put("body", message);

        // Prepare the push request
        NotiClientConfig.PushyPushRequest push = new NotiClientConfig.PushyPushRequest(payload, to, notification);

        try {
            // Try sending the push notification
            NotiClientConfig.sendPush(push);
        } catch (Exception exc) {
            // Error, print to console
            System.out.println(exc.toString());
        }
    }
}
