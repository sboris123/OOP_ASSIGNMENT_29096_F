

public class Classroom implements Identifiable,Registrable{
 private String id; private String roomNumber; private int capacity; private int floorNumber; private String building; private boolean active;
 public Classroom(String id,String roomNumber,int capacity,int floorNumber,String building,boolean active){this.id=id;this.roomNumber=roomNumber;this.capacity=capacity;this.floorNumber=floorNumber;this.building=building;this.active=active;}
 public String getId(){return id;} public void setId(String v){id=v;} public String getRoomNumber(){return roomNumber;} public void setRoomNumber(String v){roomNumber=v;}
 public int getCapacity(){return capacity;} public void setCapacity(int v){capacity=v;} public int getFloorNumber(){return floorNumber;} public void setFloorNumber(int v){floorNumber=v;}
 public String getBuilding(){return building;} public void setBuilding(String v){building=v;} public boolean isActive(){return active;} public void setActive(boolean v){active=v;}
 public String getDisplayName(){return building+" "+roomNumber;}
 public void validate() throws ValidationException{if(id==null||id.trim().isEmpty())throw new ValidationException("Classroom ID is required.");if(roomNumber==null||roomNumber.trim().isEmpty())throw new ValidationException("Room number is required.");if(capacity<=0)throw new ValidationException("Capacity must be greater than zero.");if(floorNumber<0)throw new ValidationException("Floor number must not be negative.");if(building==null||building.trim().isEmpty())throw new ValidationException("Building is required.");}
 public void printDetails(){System.out.println(getRegistrationSummary());} public String getRegistrationSummary(){return "["+id+"] "+building+" Room "+roomNumber+" - capacity "+capacity+" - floor "+floorNumber;}
 public String toString(){return getRegistrationSummary();}
}
