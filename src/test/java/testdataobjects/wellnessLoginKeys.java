package testdataobjects;

public class wellnessLoginKeys {

    String employeeRole;
    String loginKey;
    String fullname;

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmployeeRole() {
        return employeeRole;
    }

    public void setEmployeeRole(String employeeRole) {
        this.employeeRole = employeeRole;
    }

    public String getLoginKey() {
        return loginKey;
    }

    public void setLoginKey(String loginKey) {
        this.loginKey = loginKey;
    }

    @Override
    public String toString() {
        return "wellnessLoginKeys{" +
                "siteName='" + employeeRole + '\'' +
                ", loginKey='" + loginKey + '\'' +
                '}';
    }


}