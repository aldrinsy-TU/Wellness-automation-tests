package common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TimeCalendar {

    public  static String getPreviousMonthYear(){
        LocalDate now = LocalDate.now();
        LocalDate earlier = now.minusMonths(1);
        Month monthval = earlier.getMonth();
        int yearval  = earlier.getYear();
        return monthval.toString() + " " + String.valueOf(yearval);

    }

    public static String getNextMonthYear(){
        LocalDate now = LocalDate.now();
        LocalDate future = now.plusMonths(1);
        Month monthVal = future.getMonth();
        int yearVal  = future.getYear();
        return monthVal.toString() + " " + String.valueOf(yearVal);
    }

    public static String getToDaysMonthYear()
    {
        LocalDate now = LocalDate.now();
        Month monthval = now.getMonth();
        int yearval  = now.getYear();
        return monthval.toString() + " " + yearval;
   }

   public static String getYesterDaysDayValue(String timeZoneIANA)
   {
       ZoneId id = ZoneId.of(timeZoneIANA);
       ZonedDateTime today = ZonedDateTime.now(id);
       ZonedDateTime earlier = today.minusDays(1);

       int dayVal = earlier.getDayOfMonth();
       return String.valueOf(dayVal);
   }

   public static String getTomorrowsDateInEEEMddFormat(String timeZoneIANA){
       ZoneId id = ZoneId.of(timeZoneIANA);
       ZonedDateTime today = ZonedDateTime.now(id);
       ZonedDateTime earlier = today.minusDays(1);
       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE M/d");
       String tmr = formatter.format(earlier);
       return tmr;
    }

    public static String getTodaysDayValue(String timeZoneIANA){
        ZoneId id = ZoneId.of(timeZoneIANA);
        ZonedDateTime today = ZonedDateTime.now(id);
        int dayVal = today.getDayOfMonth();
        return String.valueOf(dayVal);
    }

    public static String getWeekTextGivenMonthDayYear(String month, String day, String year){
        String allString = null;
      try {
          SimpleDateFormat startFormat = new SimpleDateFormat("MMM dd");
          SimpleDateFormat endFormat = new SimpleDateFormat("dd, yyyy");
          SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd yyyy");

          String strStartDate = month + " " + day + " " + year;
          System.out.println("strStartDate: " + strStartDate);
          Date dStartDate = sdf.parse(strStartDate);
          System.out.println("dStartDate: " + dStartDate);
          String startText = startFormat.format(dStartDate);

          Date endDate = addDays(dStartDate,6);
          String endText = endFormat.format(endDate);

          allString = startText + " - " + endText;

     }catch(Exception e){
          e.printStackTrace();
      }
        return allString;

    }

    private static Date addDays(Date date, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }

    public static String getFourtyFirstDayPreviousToday(String timeZoneIANA){
        ZoneId id = ZoneId.of(timeZoneIANA);
        ZonedDateTime today = ZonedDateTime.now(id);
        ZonedDateTime earlier = today.minusDays(42);
        return earlier.getMonth() + " " + earlier.getDayOfMonth() + " " + earlier.getYear();
    }

    public static String whatShouldBeTheDefaultDateDisplayUponLoginToMyLogs(){
        LocalDate now = LocalDate.now();
        LocalDate start = now.minusDays(3);
        LocalDate end = start.plusDays(6);

        String startFormat = start.format(DateTimeFormatter.ofPattern("MMM d"));
        String endFormat = end.format(DateTimeFormatter.ofPattern("d, yyyy "));
        return startFormat + " - " + endFormat;
    }



    /*
      Format of dates passed to this method should be yyyy-mm-dd
     */
    public static List<String> getListOfDatesBetweenTwoDates(String start, String end){
        java.time.LocalDate s = java.time.LocalDate.parse(start);
        java.time.LocalDate e = java.time.LocalDate.parse(end);
        List<String> totalDates = new ArrayList<>();
        while (!s.isAfter(e)) {
            totalDates.add(s.toString());
            s = s.plusDays(1);
        }
        return totalDates;

    }

    public static List<String> getListOfDateFromTodayAndFourtyDaysPrior(String timeZoneIANA){
        ZoneId id = ZoneId.of(timeZoneIANA);
        ZonedDateTime today = ZonedDateTime.now(id);
        ZonedDateTime earlier = today.minusDays(1);
        String yesterday = earlier.getMonth() + " " + earlier.getDayOfMonth() + " " + earlier.getYear();
        String start = getFourtyFirstDayPreviousToday(timeZoneIANA);
        SimpleDateFormat startFormat = new SimpleDateFormat("MMMM dd yyyy");
        SimpleDateFormat endFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dFormat = null;
        try {
            dFormat = startFormat.parse(start);
            start = endFormat.format(dFormat);
            dFormat = startFormat.parse(yesterday);
            yesterday = endFormat.format(dFormat);

        }catch(ParseException pe){
            pe.printStackTrace();}

        return getListOfDatesBetweenTwoDates(start, yesterday);
    }

     public static String formatStringDateToAnother(String pattern1, String pattern2, String givendate){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern1);
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern(pattern2);

        LocalDateTime localDateTime = LocalDateTime.parse(givendate, formatter);
        String strTime = formatter2.format(localDateTime);

        return strTime;

    }

    public static String formatStringFromISOFormatToAnother(String pattern2, String givendate){

        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern(pattern2);

        LocalDateTime localDateTime = LocalDateTime.parse(givendate, formatter);
        String strTime = formatter2.format(localDateTime);

        return strTime;

    }

    public static String getSevenDaysFromToday(){
        LocalDate now = LocalDate.now();
        LocalDate earlier = now.minusDays(7);
        int dayVal = earlier.getDayOfMonth();
        return String.valueOf(dayVal);
    }

    public static String getDurationBetweenTwoDates(String startTime, String endTime){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a");
        LocalDateTime start = LocalDateTime.parse(startTime, formatter);
        LocalDateTime end = LocalDateTime.parse(endTime, formatter);
        Duration duration = Duration.between(start, end);

        long seconds = duration.getSeconds();
        long absSeconds = Math.abs(seconds);
        String positive = String.format(
                "%02d:%02d:%02d",
                absSeconds / 3600,
                (absSeconds % 3600) / 60,
                absSeconds % 60);
        return seconds < 0 ? "-" + positive : positive;
    }

    public static String getYesterdaysDateInEEEMddFormat(String timezoneIANA){
        ZoneId id = ZoneId.of(timezoneIANA);
        ZonedDateTime today = ZonedDateTime.now(id);
       ZonedDateTime earlier = today.minusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE M/d");
        String ystrday = formatter.format(earlier);
        return ystrday;
    }

    public static String getTodaysDateInEEEMddFormat(String timezoneIANA){
        ZoneId id = ZoneId.of(timezoneIANA);
        ZonedDateTime today = ZonedDateTime.now(id);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE M/d");
        String todayInEE = formatter.format(today);
        return todayInEE;
    }

    public static String getMyLogsCenterTextGivenDate(String startDate){
        String allString = null;
        String endOfWeek = null;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("MMM d");
        DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("d, yyyy");
        DateTimeFormatter formatter4 = DateTimeFormatter.ofPattern("MMM d, yyyy");

        LocalDate startDateTime = LocalDate.parse(startDate, formatter);
        LocalDate sixDaysFuture = startDateTime.plusDays(6);

        if(!startDateTime.getMonth().toString().equalsIgnoreCase(sixDaysFuture.getMonth().toString())){
            endOfWeek = formatter4.format(sixDaysFuture);
        }else{
            endOfWeek = formatter3.format(sixDaysFuture);
        }

        String startWeek = formatter2.format(startDateTime);
        allString = startWeek + " - " + endOfWeek;

        return allString;
    }

    public static long getDurationBetweenTwoTimeInMinutes(String startTime, String endTime){

        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("ha");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("h:mma");
        DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("hha");

        LocalTime start = null;
        LocalTime end = null;

        if(startTime.indexOf(":") == -1){
                 start = LocalTime.parse(startTime, formatter1);
        }else{
            start = LocalTime.parse(startTime, formatter2);
        }
        if(endTime.indexOf(":") == -1){
            end = LocalTime.parse(endTime, formatter1);
        }else{
            end = LocalTime.parse(endTime, formatter2);
        }

       Duration duration = Duration.between(start, end);

        long seconds = duration.getSeconds();
        if(seconds > 60){
            seconds = seconds/60;
        }
        return seconds;
    }

    public static String getStartTimeValueForTodayAndTimeParam(String givenhour, String timeZoneIANA){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        ZoneId id = ZoneId.of(timeZoneIANA);
        ZonedDateTime today = ZonedDateTime.now(id);

        String sDate = formatter.format(today);
        return sDate + " " + givenhour;
    }

    public static String getStartTimeValueForTodayAndNineHoursFromNow(String timeZoneIANA){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        ZoneId id = ZoneId.of(timeZoneIANA);
        ZonedDateTime today = ZonedDateTime.now(id).minusHours(9);
        String sDate = formatter.format(today);

        return sDate;
    }

    /*public static String getStartTimeValueGivenTimeAndMinusOrAddHours(String sDate, long minusOrAdd,
                                                                      String hoursOrMinsOrSec){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(sDate, formatter);
        System.out.println(dateTime);
        return dateTime.toString();

    }*/


    public static String getTimeValueGivenTimeAndMinusOrAddHours(String sDate, long time, String minusOrAdd,
                                                                  String hoursOrMinsOrSec, String pattern){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime zDate = LocalDateTime.parse(sDate, formatter);

        String retDate = null;
        if("add".equalsIgnoreCase(minusOrAdd)){
            if(hoursOrMinsOrSec.equalsIgnoreCase("Hour")){
                zDate = zDate.plusHours(time);
            }
            if(hoursOrMinsOrSec.equalsIgnoreCase("Minute")){
                zDate = zDate.plusMinutes(time);
            }
            if(hoursOrMinsOrSec.equalsIgnoreCase("Second")){
                zDate = zDate.plusSeconds(time);
            }
        }
        else if("minus".equalsIgnoreCase(minusOrAdd))
        {
            if(hoursOrMinsOrSec.equalsIgnoreCase("Hour")){
                zDate = zDate.minusHours(time);
            }
            if(hoursOrMinsOrSec.equalsIgnoreCase("Minute")){
                zDate = zDate.minusMinutes(time);
            }
            if(hoursOrMinsOrSec.equalsIgnoreCase("Second")){
                zDate = zDate.minusSeconds(time);
            }
        }

        retDate = formatter.format(zDate);
        return retDate;
    }

    public static LocalDate getFirstDayInLocalDateGivenTextDisplayedonCenterOnMyLogs(String textDisplayOnCenter){
        String[] split = textDisplayOnCenter.split(" ");
        String[] split2 = textDisplayOnCenter.split(",");
        String firstDay = split[0] + " " + split[1] + " " + split2[1].trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        LocalDate startDate = LocalDate.parse(firstDay, formatter);

        return startDate;
   }

    public static String getFirstDayGivenTextDisplayedonCenterOnMyLogs(String textDisplayOnCenter){
        String[] split = textDisplayOnCenter.split(" ");
        String[] split2 = textDisplayOnCenter.split(",");
        String firstDay = split[0] + " " + split[1] + " " + split2[1].trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        LocalDate startDate = LocalDate.parse(firstDay, formatter);
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("EEE M/d");
        firstDay = formatter2.format(startDate);

        return firstDay;
    }

   public static LocalDate getLocalDateOfTodaysDateGivenZoneId(String zoneId){
       ZoneId id = ZoneId.of(zoneId);
       ZonedDateTime today = ZonedDateTime.now(id);
       LocalDate localDate = today.toLocalDate();
       return localDate;
   }

    public static String getDateFourtyEightHoursFromNow(String timeZoneIANA){
        ZoneId id = ZoneId.of(timeZoneIANA);
        ZonedDateTime now = ZonedDateTime.now(id);
        ZonedDateTime seventyTwoHoursFromNow = now.minusHours(48);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedString = seventyTwoHoursFromNow.format(formatter);
        return formattedString;
    }

    public static String getDateFourtyEightHoursFromNowInyyyyMMddhhmmssaFormat(String timeZoneIANA){
        ZoneId id = ZoneId.of(timeZoneIANA);
        ZonedDateTime now = ZonedDateTime.now(id);
        ZonedDateTime seventyTwoHoursFromNow = now.minusHours(48);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a");
        String formattedString = seventyTwoHoursFromNow.format(formatter);
        return formattedString;
    }

    public static String getDateNowInyyyMMddhhmmssaFromat(String timeZoneIANA){
        ZoneId id = ZoneId.of(timeZoneIANA);
        ZonedDateTime now = ZonedDateTime.now(id);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a");
        String formattedString = now.format(formatter);
        return formattedString;
    }

    public static String getDateTimeNow(String timeZoneIANA){
        ZoneId id = ZoneId.of(timeZoneIANA);
        ZonedDateTime now = ZonedDateTime.now(id);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedString = now.format(formatter);
        return formattedString;
    }

    public static String getDateNinetySixFromNowInyyyyMMddhhmmssaFormat(String timeZoneIANA){
        ZoneId id = ZoneId.of(timeZoneIANA);
        ZonedDateTime now = ZonedDateTime.now(id);
        ZonedDateTime seventyTwoHoursFromNow = now.minusHours(96);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a");
        String formattedString = seventyTwoHoursFromNow.format(formatter);
        return formattedString;
    }

    public static String getDateSeventyTwoHoursFromNowInyyyyMMddhhmmssaFormat(String timeZoneIANA){
        ZoneId id = ZoneId.of(timeZoneIANA);
        ZonedDateTime now = ZonedDateTime.now(id);
        ZonedDateTime seventyTwoHoursFromNow = now.minusHours(72);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a");
        String formattedString = seventyTwoHoursFromNow.format(formatter);
        return formattedString;
    }

    public static String getDateNinetySixHoursFromNow(String timeZoneIANA){
        ZoneId id = ZoneId.of(timeZoneIANA);
        ZonedDateTime now = ZonedDateTime.now(id);
        ZonedDateTime ninetySixHoursBeforeNow = now.minusHours(96);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedString = ninetySixHoursBeforeNow.format(formatter);
        return formattedString;
    }

    public static String getDateNineHoursBeforeNowInyyyyMMddhhmmssaFormat(String timeZoneIANA){
        ZoneId id = ZoneId.of(timeZoneIANA);
        ZonedDateTime now = ZonedDateTime.now(id);
        ZonedDateTime nineHoursBeforeNow = now.minusHours(9);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a");
        String formattedString = nineHoursBeforeNow.format(formatter);
        return formattedString;
    }

    public static String getDateOneHourBeforeGiveDateInyyyyMMddhhmmssaFormat(String givendate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a");
        LocalDateTime localDateTime = LocalDateTime.parse(givendate, formatter);
        LocalDateTime oneHourAgo = localDateTime.minusHours(1);
        String formattedString = oneHourAgo.format(formatter);
        return formattedString;
    }

    public static String getDateOneHourAfterGiveDateInyyyyMMddhhmmssaFormat(String givendate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a");
        LocalDateTime localDateTime = LocalDateTime.parse(givendate, formatter);
        LocalDateTime oneHourAfter = localDateTime.plusHours(1);
        String formattedString = oneHourAfter.format(formatter);
        return formattedString;
    }

    public static String getDateSeventyTwoHoursBeforeNow(String timeZoneIANA){
        ZoneId id = ZoneId.of(timeZoneIANA);
        ZonedDateTime now = ZonedDateTime.now(id);
        ZonedDateTime seventyTwoHoursFromNow = now.minusHours(72);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedString = seventyTwoHoursFromNow.format(formatter);
        return formattedString;
    }

    public static String getFirstDayForCalendarDateSelectionGivenTextDisplayedonCenterOnMyLogs
            (String textDisplayOnCenter){
        LocalDate date;
        String[] split = textDisplayOnCenter.split(" ");
        String[] split2 = textDisplayOnCenter.split(",");
        String firstDay = split[0] + "/" + split[1] + "/" + split2[1].trim();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM/dd/yyyy");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("MMM/d/yyyy");

        try {
            date = LocalDate.parse(firstDay, formatter);
        }catch(DateTimeParseException de){
            date = LocalDate.parse(firstDay, formatter3);
        }

        String sDate = formatter2.format(date);
        return sDate;
    }

    public static long getDurationBetweenGivenDateAndToday(String givendate, String timeZoneIANA){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a");
        LocalDateTime lGivenDate = LocalDateTime.parse(givendate, formatter);

        ZoneId id = ZoneId.of(timeZoneIANA);
        ZonedDateTime today = ZonedDateTime.now(id);
        ZonedDateTime ZGivenDate = lGivenDate.atZone( id );

        //calculating number of days in between
        long noOfDaysBetween = ChronoUnit.DAYS.between(ZGivenDate, today);
        return noOfDaysBetween;
    }

     public static void main(String[] args) {
        long daysBetween = TimeCalendar.getDurationBetweenGivenDateAndToday("2020-04-05 01:31:49 PM", "Asia/Singapore");
        System.out.println("daysBetween: " + daysBetween);
     }


}
