package common;


import au.com.bytecode.opencsv.CSVWriter;
import testdataobjects.*;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CSVReader {

    public static HashMap<String, EmployeeProfile> readAndLoadCSVData(){

        String csvFile = "src/test/resources/data/employee_profile.csv";
        String line = "";
        String cvsSplitBy = ",";
        HashMap<String, EmployeeProfile> returnList = new  HashMap<String, EmployeeProfile>();
        EmployeeProfile employeeProfile;

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {
                employeeProfile = new EmployeeProfile();

                // use comma as separator
                String[] record = line.split(cvsSplitBy);
                employeeProfile.setEmpno(record[0]);
                employeeProfile.setEmail(record[1]);
                employeeProfile.setUsername(record[2]);
                employeeProfile.setPassword(record[3]);
                employeeProfile.setLastName(record[4]);
                employeeProfile.setFirstName(record[5]);
                employeeProfile.setPosition(record[6]);
                returnList.put(record[6], employeeProfile);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        //System.out.println("End reading csv file: " + returnList.size());

        return returnList;

    }

    public static HashMap<String, wellnessLoginKeys> readAndLoadCSVDataForWellness(){

        String csvFile = "src/test/resources/data/wellness_credentials.csv";
        String line = "";
        String cvsSplitBy = ",";
        HashMap<String, wellnessLoginKeys> returnList = new  HashMap<String, wellnessLoginKeys>();
        wellnessLoginKeys WellnessLoginKeys;

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {
                WellnessLoginKeys = new wellnessLoginKeys();

                // use comma as separator
                String[] record = line.split(cvsSplitBy);
                WellnessLoginKeys.setEmployeeRole(record[0]);
                WellnessLoginKeys.setLoginKey(record[1]);
                WellnessLoginKeys.setFullname(record[2].replace(";",","));
                returnList.put(record[0], WellnessLoginKeys);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return returnList;

    }

    public static void saveCSVDataForSessionRequest(String status, String coach, Boolean updateDate) {
        RecentRequestSession recentRequestSession = readCSVDataForSessionRequest();
        String DateStr;
        String csvFile = "src/test/resources/data/recentlyCreatedSessionRequest.csv";
        Date date = new Date();
        String[] newlist = new String[6];
        SimpleDateFormat dtFormat = new SimpleDateFormat("dd-MM-yy");
        dtFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        if(updateDate){
            DateStr = dtFormat.format(date);
        }
        else{
            DateStr = recentRequestSession.getRegistrationDate().equals(null) ? dtFormat.format(date) : recentRequestSession.getRegistrationDate();
        }

            try{
                newlist[0] = "Sy Aldrin";
                newlist[1] = "Chateau Ridiculous";
                newlist[2] = "LBTI-Corporate Applications";
                newlist[3] = DateStr;
                newlist[4] = status;
                newlist[5] = coach.replace(",","");
                List<String[]> listStringArr = new ArrayList<>();
                listStringArr.add(newlist);
                CSVWriter writer = new CSVWriter(new FileWriter(csvFile)) ;
                writer.writeAll(listStringArr);
                writer.close();
            }catch (IOException e){
                e.printStackTrace();
            }

    }

    public static RecentRequestSession readCSVDataForSessionRequest(){

        String csvFile = "src/test/resources/data/recentlyCreatedSessionRequest.csv";
        String line = "";
        String cvsSplitBy = ",";
        RecentRequestSession recentRequestSession = new RecentRequestSession();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {

                String[] record = line.split(cvsSplitBy);
                recentRequestSession.setCoacheeName(record[0].replace("\"",""));
                recentRequestSession.setSite(record[1].replace("\"",""));
                recentRequestSession.setCampaign(record[2].replace("\"",""));
                recentRequestSession.setRegistrationDate(record[3].replace("\"",""));
                recentRequestSession.setStatus(record[4].replace("\"",""));
                recentRequestSession.setCoach(record[5].replace("\"",""));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return recentRequestSession;

    }

    public static void saveCSVDataForIndividualSessionForm(Boolean updateDate) {
        RecentIndividualSession recentIndividualSession = readCSVDataForIndividualSessionForm();
        String DateStr;
        String csvFile = "src/test/resources/data/recentlyCreatedIndividualSessionForm.csv";
        Date date = new Date();
        String[] newlist = new String[11];
        SimpleDateFormat dtFormat = new SimpleDateFormat("dd-MM-yy");
        dtFormat.setTimeZone(TimeZone.getTimeZone("GMT+5:30"));
        if(updateDate){
            DateStr = dtFormat.format(date);
        }
        else{
            DateStr = recentIndividualSession.getLastSessionDate().equals(null) ? dtFormat.format(date) : recentIndividualSession.getLastSessionDate();
        }

        try{
            String DateTodayStr;
            SimpleDateFormat dtFormat2 = new SimpleDateFormat("M/dd/yyyy");
            DateTodayStr = dtFormat2.format(new Date());
            newlist[0] = "Individual Session Form";
            newlist[1] = "1st";
            newlist[2] = "Sy Aldrin";
            newlist[3] =  DateStr;
            newlist[4] = "N/A";
            newlist[5] = "Chateau Ridiculous";
            newlist[6] = "LBTI-Corporate Applications";
            newlist[7] = "Emergent - Safety Concerns";
            newlist[8] = "Concerned about the ability to remain safe";
            newlist[9] = "test";
            newlist[10] = DateTodayStr;

            List<String[]> listStringArr = new ArrayList<>();
            listStringArr.add(newlist);
            CSVWriter writer = new CSVWriter(new FileWriter(csvFile)) ;
            writer.writeAll(listStringArr);
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public static RecentIndividualSession readCSVDataForIndividualSessionForm(){

        String csvFile = "src/test/resources/data/recentlyCreatedIndividualSessionForm.csv";
        String line = "";
        String cvsSplitBy = ",";
        RecentIndividualSession recentIndividualSession = new RecentIndividualSession();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {

                String[] record = line.split(cvsSplitBy);
                recentIndividualSession.setSessionType(record[0].replace("\"",""));
                recentIndividualSession.setSessionNumber(record[1].replace("\"",""));
                recentIndividualSession.setCoacheeName(record[2].replace("\"",""));
                recentIndividualSession.setLastSessionDate(record[3].replace("\"",""));
                recentIndividualSession.setNextSessionDate(record[4].replace("\"",""));
                recentIndividualSession.setSite(record[5].replace("\"",""));
                recentIndividualSession.setCampaign(record[6].replace("\"",""));
                recentIndividualSession.setAspect(record[7].replace("\"",""));
                recentIndividualSession.setAspect_checkbox(record[8].replace("\"",""));
                recentIndividualSession.setActionItems(record[9].replace("\"",""));
                recentIndividualSession.setActionItemsDate(record[10].replace("\"",""));

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return recentIndividualSession;

    }
}
