

import java.time.LocalDate;

public class Administrator extends Person {
    private String administratorNumber; private String departmentName; private String position; private LocalDate hireDate; private boolean fullTime; private String accessLevel;
    public Administrator(String id,String name,String email,String phone,LocalDate dateOfBirth,boolean active,
                         String administratorNumber,String departmentName,String position,LocalDate hireDate,boolean fullTime,String accessLevel){
        super(id,name,email,phone,dateOfBirth,active);
        this.administratorNumber=administratorNumber; this.departmentName=departmentName; this.position=position; this.hireDate=hireDate; this.fullTime=fullTime; this.accessLevel=accessLevel;
    }
    public String getAdministratorNumber(){return administratorNumber;} public void setAdministratorNumber(String v){administratorNumber=v;}
    public String getDepartmentName(){return departmentName;} public void setDepartmentName(String v){departmentName=v;}
    public String getPosition(){return position;} public void setPosition(String v){position=v;}
    public LocalDate getHireDate(){return hireDate;} public void setHireDate(LocalDate v){hireDate=v;}
    public boolean isFullTime(){return fullTime;} public void setFullTime(boolean v){fullTime=v;}
    public String getAccessLevel(){return accessLevel;} public void setAccessLevel(String v){accessLevel=v;}
    public String getRoleDescription(){return "Administrator";}
    public void validate() throws ValidationException {
        super.validate();
        if(administratorNumber==null||administratorNumber.trim().isEmpty()) throw new ValidationException("Administrator number is required.");
        if(departmentName==null||departmentName.trim().isEmpty()) throw new ValidationException("Department name is required.");
        if(position==null||position.trim().isEmpty()) throw new ValidationException("Position is required.");
        if(hireDate==null||hireDate.isAfter(LocalDate.now())) throw new ValidationException("Hire date must not be in the future.");
        if(accessLevel==null||!accessLevel.matches("(?i)LOW|MEDIUM|HIGH|ADMIN")) throw new ValidationException("Access level must be LOW, MEDIUM, HIGH, or ADMIN.");
    }
    public String getRegistrationSummary(){return "["+getId()+"] "+getName()+" | Administrator "+administratorNumber+" | "+position;}
    public String toString(){return getRegistrationSummary();}
}
