

public class LibraryBook implements Identifiable,Registrable{
 private String id; private String title; private String isbn; private String author; private int totalCopies; private int availableCopies;
 public LibraryBook(String id,String title,String isbn,String author,int totalCopies,int availableCopies){this.id=id;this.title=title;this.isbn=isbn;this.author=author;this.totalCopies=totalCopies;this.availableCopies=availableCopies;}
 public String getId(){return id;} public void setId(String v){id=v;} public String getTitle(){return title;} public void setTitle(String v){title=v;}
 public String getIsbn(){return isbn;} public void setIsbn(String v){isbn=v;} public String getAuthor(){return author;} public void setAuthor(String v){author=v;}
 public int getTotalCopies(){return totalCopies;} public void setTotalCopies(int v){totalCopies=v;} public int getAvailableCopies(){return availableCopies;} public void setAvailableCopies(int v){availableCopies=v;}
 public String getDisplayName(){return title;} public boolean isActive(){return availableCopies>0;}
 public void validate() throws ValidationException{if(id==null||id.trim().isEmpty())throw new ValidationException("Book ID is required.");if(title==null||title.trim().length()<2)throw new ValidationException("Book title is required.");if(isbn==null||isbn.trim().isEmpty())throw new ValidationException("ISBN is required.");if(author==null||author.trim().length()<2)throw new ValidationException("Author is required.");if(totalCopies<0)throw new ValidationException("Total copies must not be negative.");if(availableCopies<0||availableCopies>totalCopies)throw new ValidationException("Available copies must be from 0 to total copies.");}
 public void printDetails(){System.out.println(getRegistrationSummary());} public String getRegistrationSummary(){return "["+id+"] "+title+" by "+author+" | ISBN "+isbn+" | "+availableCopies+"/"+totalCopies+" available";}
 public String toString(){return getRegistrationSummary();}
}
