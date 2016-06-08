package hr.tvz.rome.utilities;

import hirondelle.date4j.DateTime;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Math.toIntExact;

/**
 * Created by mlado on 30.5.2015..
 */
public class DateTimeBuilder {

    final private DateTime initialDateTime;

    private DateTime dateTime;

    private DateTimeBuilder() {
        this(DateTime.now(TimeZone.getDefault()));
    }

    private DateTimeBuilder(DateTime dateTime) {
        this.initialDateTime = dateTime;
        this.dateTime = dateTime;
    }

    private DateTimeBuilder(Date date) {
        final Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        this.initialDateTime = DateTime.forInstant(date.getTime(), TimeZone.getDefault());
        this.dateTime = DateTime.forInstant(date.getTime(), TimeZone.getDefault());
    }

    public String buildDateTimeString() {
        return dateTime.format("YYYYMMDDhhmmss");
    }

    public String buildDateTimeStringExtended() {
        return dateTime.format("YYYYMMDDhhmmss.fff");
    }

    public Date buildDate() {
        final Calendar instance = Calendar.getInstance();
        instance.set(dateTime.getYear(), dateTime.getMonth() - 1, dateTime.getDay(), dateTime.getHour(),
                dateTime.getMinute(), dateTime.getSecond());
        if (dateTime.getNanoseconds() != null) {
            instance.set(Calendar.MILLISECOND,
                    toIntExact(TimeUnit.MILLISECONDS.convert(dateTime.getNanoseconds(), TimeUnit.NANOSECONDS)));
        }
        return instance.getTime();
    }

    public DateTime buildDateTime() {
        return dateTime;
    }

    public static DateTimeBuilder now() {
        return new DateTimeBuilder();
    }

    public static DateTimeBuilder fromDate(Date date) {
        return new DateTimeBuilder(date);
    }

    public static DateTimeBuilder fromDateTime(DateTime dateTime) {
        return new DateTimeBuilder(dateTime);
    }

    public static DateTimeBuilder fromDateTimeString(String dateTimeString) {
        return new DateTimeBuilder(convertFromDateTimeString(dateTimeString));
    }

    public static boolean isParseable(String dateTimeString) {
        return convertFromDateTimeString(dateTimeString) != null;
    }

    private static DateTime convertFromDateTimeString(String dateTimeString) {
        Pattern pattern = Pattern.compile("([0-9]{4})([0-9]{2})([0-9]{2})([0-9]{2})([0-9]{2})([0-9]{2})((\\.)([0-9]{1,3}))?$");

        Matcher m = pattern.matcher(dateTimeString);
        DateTime startDateTime = null;
        if (m.matches()) {
            startDateTime = new DateTime(String.format("%s-%s-%s %s:%s:%s%s",
                    m.group(1), m.group(2), m.group(3),
                    m.group(4), m.group(5), m.group(6), (m.group(7) != null) ? m.group(7) : ""));
        }
        if (startDateTime != null) {
            return startDateTime;
        } else {
            return DateTime.now(TimeZone.getDefault());
        }
    }

    public DateTimeBuilder nextMonth() {
        dateTime = dateTime.getEndOfMonth().plus(0, 0, 0, 1, 0, 0, 0, DateTime.DayOverflow.Spillover).getStartOfMonth();
        return this;
    }

    public DateTimeBuilder tomorrow() {
        dateTime = dateTime.plus(0, 0, 1, 0, 0, 0, 0, DateTime.DayOverflow.Spillover);
        return this;
    }

    public DateTimeBuilder nextHour() {
        dateTime = dateTime.plus(0, 0, 0, 1, 0, 0, 0, DateTime.DayOverflow.Spillover);
        return this;
    }

    public DateTimeBuilder startOfMonth() {
        dateTime = dateTime.getStartOfMonth();
        return this;
    }

    public DateTimeBuilder endOfMonth() {
        dateTime = dateTime.getEndOfMonth();
        return this;
    }

    public DateTimeBuilder addMinutes(int minutes) {
        dateTime = dateTime.plus(0, 0, 0, 0, minutes, 0, 0, DateTime.DayOverflow.Spillover);
        return this;
    }

    public DateTimeBuilder addYear(int years) {
        dateTime = dateTime.plus(years, 0, 0, 0, 0, 0, 0, DateTime.DayOverflow.Spillover);
        return this;
    }

    public DateTimeBuilder addMonth(int months) {
        dateTime = dateTime.plus(0, months, 0, 0, 0, 0, 0, DateTime.DayOverflow.Spillover);
        return this;
    }

    public DateTimeBuilder subNanoseconds(int nanoseconds) {
        dateTime = dateTime.minus(0, 0, 0, 0, 0, 0, nanoseconds, DateTime.DayOverflow.Spillover);
        return this;
    }

    public DateTimeBuilder subMinutes(int minutes) {
        dateTime = dateTime.minus(0, 0, 0, 0, minutes, 0, 0, DateTime.DayOverflow.Spillover);
        return this;
    }

    public DateTimeBuilder subSeconds(int seconds) {
        dateTime = dateTime.minus(0, 0, 0, 0, 0, seconds, 0, DateTime.DayOverflow.Spillover);
        return this;
    }

    public DateTimeBuilder subDays(int days) {
        dateTime = dateTime.minus(0, 0, days, 0, 0, 0, 0, DateTime.DayOverflow.Spillover);
        return this;
    }

    public DateTimeBuilder addHours(int hours) {
        dateTime = dateTime.plus(0, 0, 0, hours, 0, 0, 0, DateTime.DayOverflow.Spillover);
        return this;
    }

    public DateTimeBuilder nextWeek() {
        dateTime = dateTime.plus(0, 0, 7, 0, 0, 0, 0, DateTime.DayOverflow.Spillover);
        return this;
    }


}
