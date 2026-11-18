

import java.math.BigDecimal;
import java.time.LocalDate;

public class Teacher extends AcademicMember {
    private String employeeNumber; private String qualification; private BigDecimal salary; private LocalDate hireDate; private boolean fullTime; private String specialization;
    public Teacher(String id,String name,String email,String phone,LocalDate dateOfBirth,boolean active,Department department,
                   String academicCode,LocalDate admissionDate,String status,int studyYear,String officeRoom,
                   String employeeNumber,String qualification,BigDecimal salary,LocalDate hireDate,boolean fullTime,String specialization){
        super(id,name,email,phone,dateOfBirth,active,department,academicCode,admissionDate,status,studyYear,officeRoom);
        this.employeeNumber=employeeNumber; this.qualification=qualification; this.salary=salary; this.hireDate=hireDate; this.fullTime=fullTime; this.specialization=specialization;
    }
    public String getEmployeeNumber(){return employeeNumber;} public void setEmployeeNumber(String v){employeeNumber=v;}
    public String getQualification(){return qualification;} public void setQualification(String v){qualification=v;}
    public BigDecimal getSalary(){return salary;} public void setSalary(BigDecimal v){salary=v;}
    public LocalDate getHireDate(){return hireDate;} public void setHireDate(LocalDate v){hireDate=v;}
    public boolean isFullTime(){return fullTime;} public void setFullTime(boolean v){fullTime=v;}
    public String getSpecialization(){return specialization;} public void setSpecialization(String v){specialization=v;}
    public String getRoleDescription(){return "Teacher";} public String getAcademicCategory(){return "Faculty";}
    public void validate() throws ValidationException {
        super.validate();
        if(employeeNumber==null||employeeNumber.trim().isEmpty()) throw new ValidationException("Employee number is required.");
        if(qualification==null||qualification.trim().length()<2) throw new ValidationException("Qualification is required.");
        if(salary==null||salary.compareTo(BigDecimal.ZERO)<=0) throw new ValidationException("Salary must be strictly positive.");
        if(hireDate==null||hireDate.isAfter(LocalDate.now())) throw new ValidationException("Hire date must not be in the future.");
        if(specialization==null||specialization.trim().isEmpty()) throw new ValidationException("Specialization is required.");
    }
    public String getRegistrationSummary(){return "["+getId()+"] "+getName()+" | Employee "+employeeNumber+" | "+specialization+" | "+getDepartment().getName();}
    public String toString(){return getRegistrationSummary();}
}
