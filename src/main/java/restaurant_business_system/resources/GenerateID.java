package restaurant_business_system.resources;

/**
 * The generateID class provides a method to generate a unique ID.
 */
public class GenerateID {
    /**
     * Generates a unique ID consisting of alphanumeric characters.
     *
     * @return a unique ID
     */
    public static String generateUniqueID() {
        String characString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            int index = (int) (characString.length() * Math.random());
            sb.append(characString.charAt(index));
        }
        return sb.toString();
    }
}
