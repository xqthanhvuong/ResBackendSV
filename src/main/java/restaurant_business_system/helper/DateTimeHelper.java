package restaurant_business_system.helper;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeHelper {
    // Múi giờ cho Việt Nam
    private static final ZoneId VIETNAM_ZONE_ID = ZoneId.of("Asia/Ho_Chi_Minh");

    // Định dạng ngày tháng
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // Phương thức để lấy ngày hiện tại
    public static int getCurrentDay() {
        ZonedDateTime nowInVietnam = ZonedDateTime.now(VIETNAM_ZONE_ID);
        return nowInVietnam.getDayOfMonth();
    }

    // Phương thức để lấy tháng hiện tại
    public static int getCurrentMonth() {
        ZonedDateTime nowInVietnam = ZonedDateTime.now(VIETNAM_ZONE_ID);
        return nowInVietnam.getMonthValue();
    }

    // Phương thức để lấy năm hiện tại
    public static int getCurrentYear() {
        ZonedDateTime nowInVietnam = ZonedDateTime.now(VIETNAM_ZONE_ID);
        return nowInVietnam.getYear();
    }

    // Phương thức để lấy số ngày trong tháng
    public static int getDaysOfMonth(int month, int year) {
        YearMonth yearMonth = YearMonth.of(year, month);
        return yearMonth.lengthOfMonth();
    }

    // Phương thức để lấy ngày chủ nhật tuần này
    public static String getSundayOfTwoWeekAgo() {
        LocalDate nowInVietnam = LocalDate.now(VIETNAM_ZONE_ID);
        LocalDate sunday = nowInVietnam.minusWeeks(2).with(DayOfWeek.SUNDAY);
        return sunday.format(DATE_FORMATTER);
    }

    // Phương thức để lấy ngày chủ nhật tuần trước
    public static String getSundayOfLastWeek() {
        LocalDate nowInVietnam = LocalDate.now(VIETNAM_ZONE_ID);
        LocalDate sundayLastWeek = nowInVietnam.minusWeeks(1).with(DayOfWeek.SUNDAY);
        return sundayLastWeek.format(DATE_FORMATTER);
    }

        // Phương thức để lấy ngày chủ nhật tuần trước
        public static String getSundayOfWeek() {
            LocalDate nowInVietnam = LocalDate.now(VIETNAM_ZONE_ID);
            LocalDate sundayLastWeek = nowInVietnam.with(DayOfWeek.SUNDAY);
            return sundayLastWeek.format(DATE_FORMATTER);
        }
}

