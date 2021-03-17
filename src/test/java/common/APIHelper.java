package common;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class APIHelper {

    public String getLoginUserTimeZone(String wsendpoint, String username, String password){

        String timeZone = "";
        RestAssured.baseURI = wsendpoint;
        RequestSpecification request = RestAssured.given().relaxedHTTPSValidation();

        JSONObject requestParams = new JSONObject();
        requestParams.put("username", username);
        requestParams.put("password", password);
        request.body(requestParams.toString());
        request.header("Content-Type","application/json");
        Response response = request.post("/api/User/UserAuthenticate");

        int statusCode = response.getStatusCode();

        if(statusCode == 200) {
            JsonPath jsonPathEvaluator = response.jsonPath();
            timeZone = jsonPathEvaluator.get("User.TimeZone");
        }
        return timeZone;
       }


    public Integer selectAllActivityForApprovalGivenEmployeeNo(String wsendpoint, String employeeNo, String token){

        Integer ticketId = 0;
        RestAssured.baseURI = wsendpoint;
        RequestSpecification request = RestAssured.given().relaxedHTTPSValidation();

        JSONObject requestParams = new JSONObject();
        requestParams.put("draw", 1);

        ArrayList<JSONObject> reqArray= new ArrayList<JSONObject>();
        JSONObject json= new JSONObject();
        json = createColumnJSONObject(JSONObject.NULL, "", false, false, "", false);
        reqArray.add(json);
        json = createColumnJSONObject("Content", "", true, false, "", false);
        reqArray.add(json);
        json = createColumnJSONObject("ID", "", true, true, "", false);
        reqArray.add(json);
        json = createColumnJSONObject("EmployeeName", "", true, true, "", false);
        reqArray.add(json);
        json = createColumnJSONObject("EmployeeCampaign", "", true, true, "", false);
        reqArray.add(json);
        json = createColumnJSONObject("EmployeeLob", "", true, true, "", false);
        reqArray.add(json);
        json = createColumnJSONObject("ApproverName", "", true, true, "", false);
        reqArray.add(json);
        json = createColumnJSONObject("ApproverSubName", "", true, true, "", false);
        reqArray.add(json);
        json = createColumnJSONObject("TicketStatus", "", true, true, "", false);
        reqArray.add(json);
        json = createColumnJSONObject("CreatedDate", "", true, true, "", false);
        reqArray.add(json);
        json = createColumnJSONObject("UpdatedDate", "", true, true, "", false);
        reqArray.add(json);
        String printjsonarray= reqArray.toString();
        requestParams.put("columns", reqArray);

        ArrayList<JSONObject> orderReqArray= new ArrayList<JSONObject>();
        JSONObject order = new JSONObject();
        order.put("column", 9);
        order.put("dir", "desc");
        orderReqArray.add(order);
        requestParams.put("order", orderReqArray.toString());

        requestParams.put("start", 0);
        requestParams.put("length", 10);

        JSONObject search = new JSONObject();
        search.put("value", "");
        search.put("regex", false);
        requestParams.put("search", search);

        requestParams.put("filterType", "For Supervisor Approval");
        requestParams.put("employeeID", employeeNo);
        requestParams.put("ticketID", JSONObject.NULL);
        requestParams.put("searchColumn", JSONObject.NULL);
        requestParams.put("searchValue", JSONObject.NULL);
        requestParams.put("showOption", JSONObject.NULL);

        System.out.println("requestParams: [" + requestParams + "]");
        request.body(requestParams.toString());

        request.header("Content-Type","application/json");
        request.header("Authorization", "Bearer " + token);

        Response response = request.post("/api/Ticket/TicketSelect");

        int statusCode = response.getStatusCode();
        System.out.println("statusCode: " + statusCode);
        if(statusCode == 200){
            JsonPath jsonPathEvaluator = response.jsonPath();
            ticketId = jsonPathEvaluator.get("data[0].ID");
        }

        System.out.println("ticketId: " + ticketId);
        return ticketId;
    }

    private JSONObject createColumnJSONObject(Object data, String name, boolean searchable, boolean orderable,
                                              String searchValue, boolean searchRegex){
        JSONObject json= new JSONObject();
        JSONObject searchJSON = new JSONObject();
        searchJSON.put("value", searchValue);
        searchJSON.put("regex", searchRegex);
        json.put("data", data);
        json.put("name", name);
        json.put("searchable", searchable);
        json.put("orderable", orderable);
        json.put("search", searchJSON);

        return json;

    }

    public void approveSubmittedStartShiftActivity(String wsendpoint, String employeeNo, String token, Integer ticketid,
                                                String approver, String approver2, String startTime, String campaign,
                                                   String timeZone, String timeZoneOffset, String userIdentity,
                                                   String createdDate){

        RestAssured.baseURI = wsendpoint;
        RequestSpecification request = RestAssured.given().relaxedHTTPSValidation();

        JSONObject requestParams = new JSONObject();
        requestParams.put("ticketID", ticketid);
        requestParams.put("employeeID", employeeNo);
         requestParams.put("approver", approver);
        requestParams.put("approverSub", approver2);
        requestParams.put("ticketStatus", 1);
        requestParams.put("updatedBy", approver);

        ArrayList<JSONObject> ticketReqArray= new ArrayList<JSONObject>();
        JSONObject ticketActivityLogs = new JSONObject();
        ticketActivityLogs.put("ID", JSONObject.NULL);
        ticketActivityLogs.put("TicketID", ticketid);
        ticketActivityLogs.put("ActionType", "Insert");
        ticketActivityLogs.put("ActivityLogID", JSONObject.NULL);
        ticketActivityLogs.put("EmployeeID", employeeNo);
        ticketActivityLogs.put("ActivityTypeID", 3);
        ticketActivityLogs.put("StartTime", startTime);
        ticketActivityLogs.put("EndTime", JSONObject.NULL);
        ticketActivityLogs.put("Campaign", campaign);
        ticketActivityLogs.put("Lob", "");
        ticketActivityLogs.put("IS1", approver);
        ticketActivityLogs.put("IS2", approver2);
        ticketActivityLogs.put("IS3", 0);
        ticketActivityLogs.put("IS4", 0);
        ticketActivityLogs.put("TimeZone", timeZone);
        ticketActivityLogs.put("TimeZoneOffset", timeZoneOffset);
        ticketActivityLogs.put("Remark", "");
        ticketActivityLogs.put("CreatedBy", userIdentity);
        ticketActivityLogs.put("CreatedDate", createdDate);
        ticketActivityLogs.put("UpdatedBy", "");
        ticketActivityLogs.put("UpdatedDate", JSONObject.NULL);
        ticketActivityLogs.put("MachineName", JSONObject.NULL);

        ticketReqArray.add(ticketActivityLogs);
        requestParams.put("ticketActivityLogs", ticketReqArray.toString());

        request.body(requestParams.toString());
        request.header("Content-Type","application/json");
        request.header("Authorization", "Bearer " + token);
        Response response = request.post("/api/Ticket/TicketApprove");

        int statusCode = response.getStatusCode();
        System.out.println("TicketApprove statusCode: " + statusCode);

        if(statusCode == 200) {
            JsonPath jsonPathEvaluator = response.jsonPath();
        }
    }

    public HashMap<String, String> getActivityLogSelectForEdit(String wsendpoint, String employeeID,
                                                       String fromDate, String timeZone, String timeZoneIANA,
                                                       String toDate, String token){

        HashMap<String, String> activityLogs = new HashMap<String, String>();
        String activitytype = null;
        String startTime = null;
        String endTime = null;
        String duration = null;
        String timeZoneResponse = null;
        String allString = null;
        LocalDateTime localDateTime = null;
        ZonedDateTime zonedDateTime = null;
        String startTimeKey = null;

        RestAssured.baseURI = wsendpoint;
        RequestSpecification request = RestAssured.given().relaxedHTTPSValidation();

        request.param("employeeID", employeeID);
        request.param("from", fromDate);
        request.param("timeZone", timeZone);
        request.param("to", toDate);

        request.header("Authorization", "Bearer " + token);
        Response response = request.get("/api/ActivityLog/ActivityLogSelectForEdit");
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a");
        DateTimeFormatter formatter2 = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");

        int statusCode = response.getStatusCode();
        System.out.println("getActivityLogSelectForEdit statusCode: " + statusCode);
        ZoneId zoneId = ZoneId.of(timeZoneIANA);

        if(statusCode == 200) {
            JsonPath jsonPathEvaluator = response.jsonPath();
            List<String> jsonResponse = response.jsonPath().getList("$");

            for(int i=0; i<jsonResponse.size();i++){
                activitytype = jsonPathEvaluator.get("ActivityType[" + i + "]");
                startTime = jsonPathEvaluator.get("StartTime[" + i + "]");

                localDateTime = LocalDateTime.parse(startTime, formatter2);
                zonedDateTime = localDateTime.atZone(zoneId);
                startTime = formatter1.format(zonedDateTime);
                startTimeKey = formatter3.format(zonedDateTime);

                endTime = jsonPathEvaluator.get("EndTime[" + i + "]");
                if(endTime!=null){
                    localDateTime = LocalDateTime.parse(endTime, formatter2);
                    zonedDateTime = localDateTime.atZone(zoneId);
                    endTime = formatter1.format(zonedDateTime);
                }

                duration = jsonPathEvaluator.get("Duration["+ i + "]");
                timeZoneResponse = jsonPathEvaluator.get("TimeZone["+ i + "]");

                allString = "Activity Type : " + activitytype + "Start Time : " + startTime +
                        "End Time : " + endTime + "Duration : " + duration +
                        "TimeZone : " + timeZoneResponse + "Action Type : No Changes";

                activityLogs.put(activitytype.trim() + startTimeKey.trim(), allString);
            }

         }
        return activityLogs;
    }

    public List<String> getActivityLogSelectForEditForScreenLogsCompare(String wsendpoint, String employeeID,
                                                               String fromDate, String timeZone, String timeZoneIANA,
                                                               String toDate, String token){

        List<String> activityLogs = new ArrayList<String>();
        String activitytype = null;
        String startTime = null;
        String endTime = null;
        String duration = null;
        String timeZoneResponse = null;
        String allString = null;
        LocalDateTime localDateTime = null;
        ZonedDateTime zonedDateTime = null;
        String startTimeKey = null;

        RestAssured.baseURI = wsendpoint;
        RequestSpecification request = RestAssured.given().relaxedHTTPSValidation();

        request.param("employeeID", employeeID);
        request.param("from", fromDate);
        request.param("timeZone", timeZone);
        request.param("to", toDate);

        request.header("Authorization", "Bearer " + token);
        Response response = request.get("/api/ActivityLog/ActivityLogSelectForEdit");
        DateTimeFormatter formatter2 = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");

        int statusCode = response.getStatusCode();
        System.out.println("getActivityLogSelectForEdit statusCode: " + statusCode);
        ZoneId zoneId = ZoneId.of(timeZoneIANA);

        if(statusCode == 200) {
            JsonPath jsonPathEvaluator = response.jsonPath();
            List<String> jsonResponse = response.jsonPath().getList("$");

            for(int i=0; i<jsonResponse.size();i++){
                activitytype = jsonPathEvaluator.get("ActivityType[" + i + "]");
                startTime = jsonPathEvaluator.get("StartTime[" + i + "]");

                localDateTime = LocalDateTime.parse(startTime, formatter2);
                zonedDateTime = localDateTime.atZone(zoneId);
                startTime = formatter3.format(zonedDateTime);

                endTime = jsonPathEvaluator.get("EndTime[" + i + "]");
                localDateTime = LocalDateTime.parse(endTime, formatter2);
                zonedDateTime = localDateTime.atZone(zoneId);
                endTime = formatter3.format(zonedDateTime);

               allString = startTime + "|" + endTime + "|" + activitytype;

                activityLogs.add(allString);
            }

        }
        return activityLogs;
    }

    public List<String> getActivitiesByCampaign(String wsendpoint, String campaign, String token) {
        List<String> activitiesByCampaign = new ArrayList<String>();
        String code = null;
        //String id = null;
        String disabled = null;

        RestAssured.baseURI = wsendpoint;
        RequestSpecification request = RestAssured.given().relaxedHTTPSValidation();

        request.param("campaign", campaign);

        request.header("Authorization", "Bearer " + token);
        Response response = request.get("/api/ActivityType/ActivityTypeSelectByCampaign");

        int statusCode = response.getStatusCode();
        System.out.println("getActivitiesByCampaign statusCode: " + statusCode);

        if (statusCode == 200) {
            JsonPath jsonPathEvaluator = response.jsonPath();
            List<String> jsonResponse = response.jsonPath().getList("$");
            for(int i=0; i<jsonResponse.size();i++) {
               code = jsonPathEvaluator.get("Code[" + i + "]");
                disabled = jsonPathEvaluator.get("StartTime[" + i + "]");
                if(disabled == null){
                    activitiesByCampaign.add(code);
                }
            }
        }

        return activitiesByCampaign;

    }

    public void selectAndApproveTicket(String wsendpoint, String ticketid, String token){

        String starTime = null;
        String endTime = null;

        RestAssured.baseURI = wsendpoint;
        RequestSpecification request = RestAssured.given().relaxedHTTPSValidation();
        RequestSpecification request2 = RestAssured.given().relaxedHTTPSValidation();

        request.param("id", ticketid);

        request.header("Authorization", "Bearer " + token);
        Response response = request.get("/api/Ticket/TicketSelectID");
        int statusCode = response.getStatusCode();
        System.out.println("TicketSelectID statusCode: " + statusCode);

        ArrayList<JSONObject> ticketReqArray= new ArrayList<JSONObject>();
        JSONObject ticketActivityLogs = new JSONObject();
        JSONObject requestParams = new JSONObject();

        if(statusCode == 200) {

            JsonPath jsonPathEvaluator = response.jsonPath();
            requestParams.put("ticketID", Integer.parseInt(ticketid));
            requestParams.put("employeeID", jsonPathEvaluator.get("Ticket.EmployeeID").toString());
            requestParams.put("approver" , jsonPathEvaluator.get("Ticket.ApproverEID").toString());
            requestParams.put("approverSub",  jsonPathEvaluator.get("Ticket.ApproverSubEID").toString());
            requestParams.put("ticketStatus", 1);
            requestParams.put("updatedBy", jsonPathEvaluator.get("Ticket.ApproverEID").toString());

            List<String> jsonResponse = response.jsonPath().getList("TicketActivityLogs");
            String start = null;

            for (int i = 0; i < jsonResponse.size(); i++) {
                start = "TicketActivityLogs[" + i + "].";
                Integer Id = jsonPathEvaluator.get("TicketActivityLogs[" + i + "].ID");
                Integer ticketID = jsonPathEvaluator.get(start + "TicketID");
                Integer actionReasonId = jsonPathEvaluator.get(start + "ActionReasonID");
                String actionType = jsonPathEvaluator.get(start + "ActionType");
                Integer activityLogId = jsonPathEvaluator.get(start + "ActivityLogID");
                String employeeId = jsonPathEvaluator.get(start + "EmployeeID");
                Integer activitytypeId =  jsonPathEvaluator.get(start + "ActivityTypeID");

                ticketActivityLogs.put("ID", Id);
                ticketActivityLogs.put("TicketID", ticketID);
                ticketActivityLogs.put("ActionReasonID", actionReasonId);
                ticketActivityLogs.put("ActionType", actionType);
                ticketActivityLogs.put("ActivityLogID", activityLogId);
                ticketActivityLogs.put("EmployeeID", employeeId);
                ticketActivityLogs.put("ActivityTypeID", activitytypeId);

                starTime = jsonPathEvaluator.get(start + "StartTime").toString();
                //starTime = TimeCalendar.formatStringFromISOFormatToAnother("yyyy-MM-dd HH:mm:ss", starTime);
                endTime = jsonPathEvaluator.get(start + "EndTime").toString();
                //endTime = TimeCalendar.formatStringFromISOFormatToAnother("yyyy-MM-dd HH:mm:ss", endTime);

                ticketActivityLogs.put("StartTime", starTime);
                ticketActivityLogs.put("EndTime", endTime);

                String campaign = jsonPathEvaluator.get(start + "Campaign");
                String lob = jsonPathEvaluator.get(start + "Lob");
                String is1 = jsonPathEvaluator.get(start + "IS1");
                String is2 = jsonPathEvaluator.get(start + "IS2");
                String is3 = jsonPathEvaluator.get(start + "IS3");
                String is4 = jsonPathEvaluator.get(start + "IS4");
                String timezone = jsonPathEvaluator.get(start + "TimeZone");
                String timezonOffset = jsonPathEvaluator.get(start + "TimeZoneOffset");
                String remark = jsonPathEvaluator.get(start + "Remark");
                String createdBy = jsonPathEvaluator.get(start + "CreatedBy");
                String createdDate = jsonPathEvaluator.get(start + "CreatedDate");
                String updatedBy = jsonPathEvaluator.get(start + "CreatedBy");
                String updatedDate = jsonPathEvaluator.get(start + "CreatedDate");


                ticketActivityLogs.put("Campaign", campaign);
                ticketActivityLogs.put("Lob", lob);
                ticketActivityLogs.put("IS1", is1);
                ticketActivityLogs.put("IS1", is2);
                ticketActivityLogs.put("IS3", is3);
                ticketActivityLogs.put("IS4", is4);
                ticketActivityLogs.put("TimeZone", timezone);
                ticketActivityLogs.put("TimeZoneOffset", timezonOffset);
                ticketActivityLogs.put("Remark", remark);
                ticketActivityLogs.put("CreatedBy", createdBy);
                ticketActivityLogs.put("CreatedDate", createdDate);
                ticketActivityLogs.put("UpdatedBy", updatedBy);
                ticketActivityLogs.put("UpdatedDate", updatedDate);
                ticketActivityLogs.put("MachineName", JSONObject.NULL);

                ticketReqArray.add(ticketActivityLogs);
            }

            String ticketEmployeeID = jsonPathEvaluator.get("Ticket.EmployeeID");
            requestParams.put("createdBy", ticketEmployeeID);
            requestParams.put("ticketActivityLogs", ticketReqArray.toString());
            System.out.println("requestParams: [" + requestParams + "]");

            request2.body(requestParams.toString());
            request2.header("Content-Type","application/json");
            request2.header("Authorization", "Bearer " + token);
            response = request2.post("/api/Ticket/TicketApprove");

            statusCode = response.getStatusCode();
            System.out.println("TicketApprove statusCode: " + statusCode);

        }

    }

    public List<String> getActionReasonSelect(String wsendpoint, String token) {
        List<String> actionReasonSelect = new ArrayList<String>();
        String code = null;
        boolean status;

        RestAssured.baseURI = wsendpoint;
        RequestSpecification request = RestAssured.given().relaxedHTTPSValidation();

        request.header("Authorization", "Bearer " + token);
        Response response = request.get("/api/ActionReason/ActionReasonSelect");

        int statusCode = response.getStatusCode();

        if (statusCode == 200) {
            JsonPath jsonPathEvaluator = response.jsonPath();
            List<String> jsonResponse = response.jsonPath().getList("$");

            for(int i=0; i<jsonResponse.size();i++) {
                code = jsonPathEvaluator.get("Code[" + i + "]");
                status = jsonPathEvaluator.get("Status[" + i + "]");
                if(status){ actionReasonSelect.add(code.trim()); }
            }
        }

        return actionReasonSelect;

    }

    public List<Map<Object, String>> getEmployeesUnderASupervisor(String wsendpoint, String token, String employeeId) {
        List<Map<Object, String>> employeeList = new ArrayList<Map<Object, String>>();
        Map<Object, String> rowData = new HashMap<>();
        String employeeID;
        String fullname;
        //String iS1;
        String campaign;
        String lob;

        RestAssured.baseURI = wsendpoint;
        RequestSpecification request = RestAssured.given().relaxedHTTPSValidation();

        request.param("employeeID", employeeId);
        request.header("Content-Type","application/json");
        request.header("Authorization", "Bearer " + token);
        Response response = request.get("/api/Employee/EmployeeSelectByIS");

        int statusCode = response.getStatusCode();

        System.out.println("statusCode: " + statusCode);

        if (statusCode == 200) {
            JsonPath jsonPathEvaluator = response.jsonPath();
            List<String> jsonResponse = response.jsonPath().getList("$");

            for(int i=0; i<jsonResponse.size();i++) {
                employeeID = jsonPathEvaluator.get("EmployeeID[" + i + "]");
                fullname = jsonPathEvaluator.get("FullName[" + i + "]");
                //iS1 = jsonPathEvaluator.get("IS1[" + i + "]");
                campaign = jsonPathEvaluator.get("Campaign[" + i + "]");
                lob = jsonPathEvaluator.get("LOB[" + i + "]");

                rowData = new HashMap<>();
                for(int j=1; j<6; j++){
                    if(j == 1)  rowData.put(j, "Edit Logs");
                    if(j == 2)  rowData.put(j, employeeID);
                    if(j == 3)  rowData.put(j, fullname);
                    if(j == 4)  rowData.put(j, campaign);
                    if(j == 5)  rowData.put(j, lob);
                }

                employeeList.add(rowData);
            }
        }

       Collections.sort(employeeList, new Comparator<Map<Object, String>>() {
            @Override
            public int compare(Map<Object, String> map1, Map<Object, String> map2) {
                return map1.get(2).compareTo(map2.get(2));
            }
        });

        return employeeList;

    }

        public List<String> getTicketDetailsGivenTicketNumber(String wsendpoint, String ticketid, String token){

            String starTime = null;
            String endTime = null;
            List<String> logEntries = new ArrayList<>();

            RestAssured.baseURI = wsendpoint;
            RequestSpecification request = RestAssured.given().relaxedHTTPSValidation();
            RequestSpecification request2 = RestAssured.given().relaxedHTTPSValidation();

            request.param("id", ticketid);

            request.header("Authorization", "Bearer " + token);
            Response response = request.get("/api/Ticket/TicketSelectID");
            int statusCode = response.getStatusCode();
            System.out.println("TicketSelectID statusCode: " + statusCode);


            if(statusCode == 200) {

                JsonPath jsonPathEvaluator = response.jsonPath();
                List<String> jsonResponse = response.jsonPath().getList("TicketActivityLogs");
                String start = "";

                for (int i = 0; i < jsonResponse.size(); i++) {
                    start = "TicketActivityLogs[" + i + "].";
                    String activitytype =  jsonPathEvaluator.get(start + "ActivityType");

                    starTime = jsonPathEvaluator.get(start + "StartTime").toString();
                    starTime = TimeCalendar.formatStringFromISOFormatToAnother("yyyy-MM-dd hh:mm a", starTime);
                    endTime = jsonPathEvaluator.get(start + "EndTime").toString();
                    endTime = TimeCalendar.formatStringFromISOFormatToAnother("yyyy-MM-dd hh:mm a", endTime);

                    String finalTxt = starTime + "|" + endTime + "|" + activitytype;
                    logEntries.add(finalTxt);

                }
            }
            return logEntries;


        }

    public static void main(String[] args) throws Exception {
        APIHelper helper = new APIHelper();
        /*UserAuthenticate approverInfo = helper.getLoginUserInfo("http://phlbl02dev01:8090/editv2",
                "ISBI9900263", "Qwerty!@#$1234");
        helper.getEmployeesUnderASupervisor("http://phlbl02dev01:8090/editv2",
                approverInfo.getAuthenticationToken(), "9900263");*/

    }
}
