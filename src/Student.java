

import java.time.LocalDate;

public class Student extends AcademicMember {
    private String studentNumber; private String program; private String major; private double gpa; private LocalDate enrollmentDate; private boolean scholarship;
    public Student(String id,String name,String email,String phone,LocalDate dateOfBirth,boolean active,Department department,
                   String academicCode,LocalDate admissionDate,String status,int studyYear,String officeRoom,
                   String studentNumber,String program,String major,double gpa,LocalDate enrollmentDate,boolean scholarship){
        super(id,name,email,phone,dateOfBirth,active,department,academicCode,admissionDate,status,studyYear,officeRoom);
        this.studentNumber=studentNumber; this.program=program; this.major=major; this.gpa=gpa; this.enrollmentDate=enrollmentDate; this.scholarship=scholarship;
    }
    public String getStudentNumber(){return studentNumber;} public void setStudentNumber(String v){studentNumber=v;}
    public String getProgram(){return program;} public void setProgram(String v){program=v;}
    public String getMajor(){return major;} public void setMajor(String v){major=v;}
    public double getGpa(){return gpa;} public void setGpa(double v){gpa=v;}
    public LocalDate getEnrollmentDate(){return enrollmentDate;} public void setEnrollmentDate(LocalDate v){enrollmentDate=v;}
    public boolean hasScholarship(){return scholarship;} public void setScholarship(boolean v){scholarship=v;}
    public String getRoleDescription(){return "Student";} public String getAcademicCategory(){return "Undergraduate";}
    public void validate() throws ValidationException {
        super.validate();
        if(studentNumber==null||studentNumber.trim().isEmpty()) throw new ValidationException("Student number is required.");
        if(program==null||program.trim().length()<2) throw new ValidationException("Program is required.");
        if(major==null||major.trim().length()<2) throw new ValidationException("Major is required.");
        if(gpa<0.0||gpa>4.0) throw new ValidationException("GPA must be from 0.0 to 4.0.");
        if(enrollmentDate==null||enrollmentDate.isAfter(LocalDate.now())) throw new ValidationException("Enrollment date must not be in the future.");
    }
    public String getRegistrationSummary(){return "["+getId()+"] "+getName()+" | Student "+studentNumber+" | "+program+" | "+getDepartment().getName();}
    public String toString(){return getRegistrationSummary();}
}
