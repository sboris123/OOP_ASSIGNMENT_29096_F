

import java.time.LocalDate;
public class Enrollment implements Identifiable,Registrable{
 private String id; private Student student; private Course course; private String semester; private LocalDate enrollmentDate; private boolean active;
 public Enrollment(String id,Student student,Course course,String semester,LocalDate enrollmentDate,boolean active){this.id=id;this.student=student;this.course=course;this.semester=semester;this.enrollmentDate=enrollmentDate;this.active=active;}
 public String getId(){return id;} public void setId(String v){id=v;} public Student getStudent(){return student;} public void setStudent(Student v){student=v;}
 public Course getCourse(){return course;} public void setCourse(Course v){course=v;} public String getSemester(){return semester;} public void setSemester(String v){semester=v;}
 public LocalDate getEnrollmentDate(){return enrollmentDate;} public void setEnrollmentDate(LocalDate v){enrollmentDate=v;} public boolean isActive(){return active;} public void setActive(boolean v){active=v;}
 public String getDisplayName(){return student.getName()+" - "+course.getName();}
 public void validate() throws ValidationException{if(id==null||id.trim().isEmpty())throw new ValidationException("Enrollment ID is required.");if(student==null)throw new ValidationException("Student is required.");if(course==null)throw new ValidationException("Course is required.");if(semester==null||semester.trim().isEmpty())throw new ValidationException("Semester is required.");if(enrollmentDate==null||enrollmentDate.isAfter(LocalDate.now()))throw new ValidationException("Enrollment date must not be in the future.");}
 public void printDetails(){System.out.println(getRegistrationSummary());} public String getRegistrationSummary(){return "["+id+"] "+student.getName()+" enrolled in "+course.getName()+" - "+semester+" - "+(active?"ACTIVE":"INACTIVE");}
 public String toString(){return getRegistrationSummary();}
}
