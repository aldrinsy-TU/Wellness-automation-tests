package common;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class StringFormatters {

    public String formatTimeFromGrid(String given){

        String ret = given;
        if(given.length()<8){
            ret = "0" + given;
        }
        return ret;
    }

    public List<String> transformDateDisplayedOnCtrOfMyLogsToDateStrings(String headerDisplayCtr){
        List<String> dates = new ArrayList<>();
        String[] splitTxt = headerDisplayCtr.split(" ");
        String strSplit = splitTxt[0] + " " + splitTxt[1];
        String[] splitTxt2 = headerDisplayCtr.split(",");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("MMM d yyyy");
        LocalDate startDate = LocalDate.parse(strSplit + " " + splitTxt2[1].trim(), formatter2);
        String start = formatter.format(startDate);
        //System.out.println("start: " + start);

        LocalDate endDate = startDate.plusDays(6);
        String end = formatter.format(endDate);
        //System.out.println("end: " + end);

        dates.add(start);
        dates.add(end);
        return dates;
    }

    public static void main(String[] args) throws Exception {
        StringFormatters formatters = new StringFormatters();
        formatters.transformDateDisplayedOnCtrOfMyLogsToDateStrings("Jan 18 - 24, 2020");
    }
}
