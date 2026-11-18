

import java.time.LocalDate;
public class BookLoan implements Identifiable,Registrable{
 private String id; private Student student; private LibraryBook book; private LocalDate borrowedDate; private LocalDate dueDate; private LocalDate returnedDate;
 public BookLoan(String id,Student student,LibraryBook book,LocalDate borrowedDate,LocalDate dueDate,LocalDate returnedDate){this.id=id;this.student=student;this.book=book;this.borrowedDate=borrowedDate;this.dueDate=dueDate;this.returnedDate=returnedDate;}
 public String getId(){return id;} public void setId(String v){id=v;} public Student getStudent(){return student;} public void setStudent(Student v){student=v;}
 public LibraryBook getBook(){return book;} public void setBook(LibraryBook v){book=v;} public LocalDate getBorrowedDate(){return borrowedDate;} public void setBorrowedDate(LocalDate v){borrowedDate=v;}
 public LocalDate getDueDate(){return dueDate;} public void setDueDate(LocalDate v){dueDate=v;} public LocalDate getReturnedDate(){return returnedDate;} public void setReturnedDate(LocalDate v){returnedDate=v;}
 public String getDisplayName(){return student.getName()+" - "+book.getTitle();} public boolean isActive(){return returnedDate==null;}
 public void validate() throws ValidationException{if(id==null||id.trim().isEmpty())throw new ValidationException("Loan ID is required.");if(student==null)throw new ValidationException("Student is required.");if(book==null)throw new ValidationException("Library book is required.");if(borrowedDate==null||borrowedDate.isAfter(LocalDate.now()))throw new ValidationException("Borrowed date must not be in the future.");if(dueDate==null||!dueDate.isAfter(borrowedDate))throw new ValidationException("Due date must be after borrowed date.");if(returnedDate!=null&&returnedDate.isBefore(borrowedDate))throw new ValidationException("Returned date cannot be before borrowed date.");}
 public void printDetails(){System.out.println(getRegistrationSummary());} public String getRegistrationSummary(){return "["+id+"] "+student.getName()+" borrowed "+book.getTitle()+" - due "+dueDate+" - "+(returnedDate==null?"NOT RETURNED":"returned "+returnedDate);}
 public String toString(){return getRegistrationSummary();}
}
