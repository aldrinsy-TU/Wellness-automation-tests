package common;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DBHelper {
    public static Connection connection = null;
    public static Statement stmt = null;

    public static Connection getConnection() throws Exception {
        try {

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionUrl =
                    "jdbc:sqlserver://sgawsws00sql01.choq3dpfrets.ap-southeast-1.rds.amazonaws.com;"
                            + "database=Wellness;"
                            + "user=ASy.user;"
                            + "password=ZGhBG0UqOavSffi9W7qw;"
                            + "encrypt=false;"
                            + "trustServerCertificate=false;"
                            + "loginTimeout=30;";

            connection = DriverManager.getConnection(connectionUrl);

        }catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }

    public static ResultSet getResultSet(Connection connection, String query) throws Exception {
        ResultSet rs;
        stmt = null;
        try {
            stmt = connection.createStatement();
            rs = stmt.executeQuery(query);
        } catch (Exception e) {
            throw new Exception("DataBase error while executing the query"
                    + e.getMessage());
        }
        return rs;
    }

    public static ResultSet getSQLResultSet(Connection con, String query) throws Exception {
        ResultSet rs;
        stmt = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
        } catch (Exception e) {
            throw new Exception("DataBase error while executing the query"
                    + e.getMessage());
       }

        return rs;
    }

    public static void fireACommitQuery(String query) throws Exception {
        stmt = null;
        try {
            stmt = getConnection().createStatement();
            stmt.executeQuery(query);
            connection.commit();
            connection.close();
        } catch (Exception e) {
            connection.commit();
            connection.close();
            if(!e.getMessage().contains("The statement did not return a result set.")){
                throw new Exception("DataBase error while executing the query"
                        + e.getMessage());
            }
        }
    }

    public static void fireACommitQuery(String query, Connection cnt) throws Exception {
        stmt = null;
        try {
            stmt = cnt.createStatement();
            stmt.executeQuery(query);
            cnt.commit();
            cnt.close();
        } catch (Exception e) {
            cnt.commit();
            cnt.close();
            throw new Exception("DataBase error while executing the query"
                    + e.getMessage());
        }
    }

    public List<String> getAllLogEntriesGivenUsernameAndDates(String username, String startdate, String enddate){

        String query = "SELECT E.activity_log_id, A.activity_type, " +
                "FORMAT(DATEADD(minute,try_convert( DECIMAL(3,2), E.TimeZoneOffset)*60,  DATEADD(hour,-8, E.start_time)),'yyyy-MM-dd hh:mm tt') AS startManilaTime, " +
                "FORMAT(DATEADD(minute,try_convert( DECIMAL(3,2), E.TimeZoneOffset)*60,  DATEADD(hour,-8, E.end_time)),'yyyy-MM-dd hh:mm tt') AS endManilaTime " +
                "FROM [dbo].[tbl_activity_logs] E, [dbo].[tbl_activity_types] A " +
                "WHERE E.activity_type_id_fk = A.activity_type_id " +
                "AND E.employee_id_fk='"+ username +  "' " +
                "AND E.date>='" + startdate + "' " +
                "AND E.date<='" + enddate + "' " +
                "AND E.IsDeleted <>1";

        Connection con = null;
        List<String> dateLogs = new ArrayList<String>();
        String activity = null;
        String startTime = null;
        String endTime = null;
        String startOfDay = null;
        String endOfDay = null;

       try {
           con = DBHelper.getConnection();
           ResultSet rs = getSQLResultSet(con, query);
            while (rs.next()) {
                activity = rs.getString("activity_type");
                startTime = rs.getString("startManilaTime");
                endTime = rs.getString("endManilaTime");

                if(!isActivityStartTimeAndEndTimeOfDifferentDates(startTime, endTime)){
                    endOfDay = getEndOfDayGivenTime(startTime);
                    startOfDay = getStartOfDayGivenTime(endTime);

                    dateLogs.add(startTime + "|"+ endOfDay + "|" + activity);
                    dateLogs.add(startOfDay + "|"+ endTime + "|" + activity);
                }else{
                    dateLogs.add(startTime + "|"+ endTime + "|" + activity);
                }
           }

        }catch(Exception e){
            System.out.println(e.getMessage());
        } finally {

            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

         return dateLogs;
    }

    public boolean isActivityStartTimeAndEndTimeOfDifferentDates(String startTime, String endTime){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");
        LocalDateTime endTimeLocalDateTime = LocalDateTime.parse(endTime, formatter);
        LocalDateTime startTimeLocalDateTime = LocalDateTime.parse(startTime, formatter);

        String dateOnlyEndTime = endTimeLocalDateTime.getYear() + "-" +
                endTimeLocalDateTime.getMonth() + "-" + endTimeLocalDateTime.getDayOfMonth();
        String dateOnlyStartTime = startTimeLocalDateTime.getYear() + "-" +
                startTimeLocalDateTime.getMonth() + "-" + startTimeLocalDateTime.getDayOfMonth();

        if(!dateOnlyStartTime.equalsIgnoreCase(dateOnlyEndTime)) {return false;}else{return true;}

    }

    public String getStartOfDayGivenTime(String endDay){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");
        LocalDateTime localDateTime = LocalDateTime.parse(endDay, formatter);

        LocalDateTime dateStart= localDateTime.toLocalDate().atStartOfDay();
        String startOfDay = formatter.format(dateStart);
        return startOfDay;
    }

    public String getEndOfDayGivenTime(String startDay){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("hh:mm a");
        DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime localDateTime = LocalDateTime.parse(startDay, formatter);

        LocalDateTime dateEnd = localDateTime.toLocalDate().atStartOfDay().plusDays(1);
        String datepart = formatter3.format(localDateTime);
        String endOfDay = formatter2.format(dateEnd);
        return datepart + " " + endOfDay;
    }

    public String getActivityLogEntryGivenActivityDateStartTimeAndEndTime(String username, String date, String activity,
            String startTime, String endTime){

        Connection con = null;
        String activityString = null;

        String query = "SELECT E.activity_log_id, A.activity_type, E.TimeZone, 'No Changes' as Action_Type, " +
                "FORMAT(DATEADD(minute,try_convert( DECIMAL(3,2), E.TimeZoneOffset)*60,  DATEADD(hour,-8, E.start_time)),'yyyy-MM-dd hh:mm:ss tt') AS startManilaTime, " +
                "FORMAT(DATEADD(minute,try_convert( DECIMAL(3,2), E.TimeZoneOffset)*60,  DATEADD(hour,-8, E.end_time)),'yyyy-MM-dd hh:mm:ss tt') AS endManilaTime, " +
                "CONVERT(time,DATEADD(s, DATEDIFF(s,E.start_time,E.end_time),CAST('1900-01-01 00:00:00.0000000' as datetime2))) AS duration " +
                "FROM [dbo].[tbl_activity_logs] E, [dbo].[tbl_activity_types] A " +
                "WHERE E.activity_type_id_fk = A.activity_type_id " +
                "AND E.employee_id_fk='" + username + "' " +
                "AND E.date='" + date + "' " +
                "AND E.IsDeleted <>1 " +
                "AND A.activity_type='" + activity + "' " +
                "AND FORMAT(DATEADD(minute,try_convert( DECIMAL(3,2), E.TimeZoneOffset)*60,  DATEADD(hour,-8, E.start_time)),'yyyy-MM-dd hh:mm tt') = '" + startTime + "' " +
                "AND FORMAT(DATEADD(minute,try_convert( DECIMAL(3,2), E.TimeZoneOffset)*60,  DATEADD(hour,-8, E.end_time)),'yyyy-MM-dd hh:mm tt') = '" + endTime + "'";

        System.out.println("query: [" + query + "]");
        try {
            con = DBHelper.getConnection();
            ResultSet rs = getSQLResultSet(con, query);

          if(rs.next()){
                String activityType = rs.getString("activity_type");
                String timeZone = rs.getString("TimeZone");
                String start_time = rs.getString("startManilaTime");
                String end_time = rs.getString("endManilaTime");
                String duration =  rs.getString("duration").substring(0,8);
                String action = rs.getString("Action_Type");

              activityString =  "Activity Type : " + activityType + "Start Time : " + start_time +
                        "End Time : " + end_time + "Duration : " + duration
                        + "TimeZone : " + timeZone + "Action Type : " + action.trim();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        }

        return activityString;
    }

    public String getDBTimeDateInISO8601Format() {
        Connection con = null;
        String timeInISO = null;
        String query = "SELECT CONVERT(NVARCHAR(30), GETDATE(), 126) as TimeInISO";
        try {
            con = DBHelper.getConnection();
            ResultSet rs = getSQLResultSet(con, query);

            if (rs.next()) {
                timeInISO = rs.getString("TimeInISO");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        }
        return timeInISO;
    }

    public String getOTPGivenEID(String employeeNumber) {
        Connection con = null;
        String Otp = null;
        String query = "SELECT TOP 1 EmployeeNo, OTP, IsUsed, CreatedDate, ExpiryDate FROM [QA_automation_taskus_aux_tool].[dbo].[Otp] WHERE EmployeeNo = '" + employeeNumber + "' ORDER BY CreatedDate DESC ";
            try {
            con = DBHelper.getConnection();
            ResultSet rs = getSQLResultSet(con, query);

            if (rs.next()) {
                Otp = rs.getString("OTP");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        }
        return Otp;
    }

    public String getOTPGivenEIDForTW(String employeeNumber) {
        Connection con = null;
        String Otp = null;
        String query = "SELECT TOP 1 EmployeeNo, OTP, IsUsed, CreatedDate, ExpiryDate FROM [QA_automation_taskus_aux_tool].[dbo].[Otp] WHERE EmployeeNo = '" + employeeNumber + "' ORDER BY CreatedDate DESC ";
        try {
            con = DBHelper.getConnection();
            ResultSet rs = getSQLResultSet(con, query);

            if (rs.next()) {
                Otp = rs.getString("OTP");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        }
        return Otp;
    }

    public String getExpiredOTPGivenEID(String employeeNumber) {
        Connection con = null;
        String Otp = null;
        String query = "SELECT TOP 1 EmployeeNo, OTP, IsUsed, CreatedDate, ExpiryDate FROM [QA_automation_taskus_aux_tool].[dbo].[Otp]  WHERE EmployeeNo = '" + employeeNumber + "' ORDER BY CreatedDate ASC ";
        try {
            con = DBHelper.getConnection();
            ResultSet rs = getSQLResultSet(con, query);

            if (rs.next()) {
                Otp = rs.getString("OTP");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        }
        return Otp;
    }


    public static void main(String[] args) throws Exception {
        DBHelper helper = new DBHelper();
        helper.getOTPGivenEID("8888001");
    }

    public void truncateWellnessTBL() {
        String truncate_query = "TRUNCATE TABLE [dbo].[CheckInLog]\n" +
                "TRUNCATE TABLE [dbo].[CheckInResponses]\n" +
                "TRUNCATE TABLE [dbo].[CoachingLog]\n" +
                "TRUNCATE TABLE [dbo].[CoachingLogHistory]\n" +
                "TRUNCATE TABLE [dbo].[CoachingRegistration]\n" +
                "TRUNCATE TABLE [dbo].[CoachingRegistrationHistory]\n" +
                "TRUNCATE TABLE [dbo].[CoachingResponses]\n" +
                "TRUNCATE TABLE [dbo].[CoachingResponsesHistory]\n" +
                "TRUNCATE TABLE [dbo].[CoachingSurvey]\n" +
                "TRUNCATE TABLE [dbo].[GroupLog]\n" +
                "TRUNCATE TABLE [dbo].[GroupResponses]\n" +
                "TRUNCATE TABLE [dbo].[MeetingLog]\n" +
                "TRUNCATE TABLE [dbo].[MeetingResponses]\n" +
                "TRUNCATE TABLE [dbo].[RegistrationLog]\n" +
                "TRUNCATE TABLE [dbo].[RegistrationLogHistory]\n" +
                "TRUNCATE TABLE [dbo].[RegistrationResponses]\n" +
                "TRUNCATE TABLE [dbo].[SurveyLog]\n" +
                "TRUNCATE TABLE [dbo].[SurveyResponses]\n" +
                "TRUNCATE TABLE [dbo].[ExceptionLogs]\n" +
                "TRUNCATE TABLE [dbo].[TokenManager]";

        String reseed_query = "DBCC CHECKIDENT ('dbo.CheckInLog', RESEED, 10000);\n" +
                "DBCC CHECKIDENT ('dbo.CoachingLog', RESEED, 10000);\n" +
                "DBCC CHECKIDENT ('dbo.GroupLog', RESEED, 10000);\n" +
                "DBCC CHECKIDENT ('dbo.MeetingLog', RESEED, 10000);\n" +
                "DBCC CHECKIDENT ('dbo.RegistrationLog', RESEED, 10000);\n" +
                "DBCC CHECKIDENT ('dbo.SurveyLog', RESEED, 10000);";
        try {
            fireACommitQuery(truncate_query);
            fireACommitQuery(reseed_query);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
