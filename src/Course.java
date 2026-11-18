

public class Course implements Identifiable,Registrable{
 private String id; private String name; private String code; private int creditHours; private Department department; private Teacher teacher;
 public Course(String id,String name,String code,int creditHours,Department department,Teacher teacher){this.id=id;this.name=name;this.code=code;this.creditHours=creditHours;this.department=department;this.teacher=teacher;}
 public String getId(){return id;} public void setId(String v){id=v;} public String getName(){return name;} public void setName(String v){name=v;}
 public String getCode(){return code;} public void setCode(String v){code=v;} public int getCreditHours(){return creditHours;} public void setCreditHours(int v){creditHours=v;}
 public Department getDepartment(){return department;} public void setDepartment(Department v){department=v;} public Teacher getTeacher(){return teacher;} public void setTeacher(Teacher v){teacher=v;}
 public String getDisplayName(){return name;} public boolean isActive(){return true;}
 public void validate() throws ValidationException{if(id==null||id.trim().isEmpty())throw new ValidationException("Course ID is required.");if(name==null||name.trim().length()<2)throw new ValidationException("Course name is required.");if(code==null||code.trim().isEmpty())throw new ValidationException("Course code is required.");if(creditHours<1||creditHours>6)throw new ValidationException("Credit hours must be 1 to 6.");if(department==null)throw new ValidationException("Department is required.");if(teacher==null)throw new ValidationException("Teacher is required.");}
 public void printDetails(){System.out.println(getRegistrationSummary());} public String getRegistrationSummary(){return "["+id+"] "+code+" "+name+" ("+creditHours+" credits) - "+department.getName()+", "+teacher.getName();}
 public String toString(){return getRegistrationSummary();}
}
