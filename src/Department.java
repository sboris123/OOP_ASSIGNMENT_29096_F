

public class Department implements Identifiable, Registrable {
    private String id; private String name; private String code; private String location; private Teacher head; private boolean active;
    public Department(String id, String name, String code, String location, Teacher head, boolean active) {
        this.id=id; this.name=name; this.code=code; this.location=location; this.head=head; this.active=active;
    }
    public String getId(){return id;} public void setId(String id){this.id=id;}
    public String getName(){return name;} public void setName(String name){this.name=name;}
    public String getCode(){return code;} public void setCode(String code){this.code=code;}
    public String getLocation(){return location;} public void setLocation(String location){this.location=location;}
    public Teacher getHead(){return head;} public void setHead(Teacher head){this.head=head;}
    public boolean isActive(){return active;} public void setActive(boolean active){this.active=active;}
    public String getDisplayName(){return name;}
    public void validate() throws ValidationException {
        if(id==null||id.trim().isEmpty()) throw new ValidationException("Department ID is required.");
        if(name==null||name.trim().length()<2||name.trim().length()>60) throw new ValidationException("Department name must be 2 to 60 characters.");
        if(code==null||code.trim().isEmpty()) throw new ValidationException("Department code is required.");
        if(location==null||location.trim().isEmpty()) throw new ValidationException("Location is required.");
    }
    public void printDetails(){System.out.println(getRegistrationSummary());}
    public String getRegistrationSummary(){return "["+id+"] "+name+" ("+code+") - "+location+(head==null?"":" - Head: "+head.getName());}
    public String toString(){return getRegistrationSummary();}
}
