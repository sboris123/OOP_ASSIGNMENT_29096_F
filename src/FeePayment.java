

import java.math.BigDecimal; import java.time.LocalDate;
public class FeePayment implements Identifiable,Registrable{
 private String id; private Student student; private BigDecimal amount; private LocalDate paymentDate; private String paymentMethod; private String receiptNumber;
 public FeePayment(String id,Student student,BigDecimal amount,LocalDate paymentDate,String paymentMethod,String receiptNumber){this.id=id;this.student=student;this.amount=amount;this.paymentDate=paymentDate;this.paymentMethod=paymentMethod;this.receiptNumber=receiptNumber;}
 public String getId(){return id;} public void setId(String v){id=v;} public Student getStudent(){return student;} public void setStudent(Student v){student=v;}
 public BigDecimal getAmount(){return amount;} public void setAmount(BigDecimal v){amount=v;} public LocalDate getPaymentDate(){return paymentDate;} public void setPaymentDate(LocalDate v){paymentDate=v;}
 public String getPaymentMethod(){return paymentMethod;} public void setPaymentMethod(String v){paymentMethod=v;} public String getReceiptNumber(){return receiptNumber;} public void setReceiptNumber(String v){receiptNumber=v;}
 public String getDisplayName(){return receiptNumber+" - "+student.getName();} public boolean isActive(){return true;}
 public void validate() throws ValidationException{if(id==null||id.trim().isEmpty())throw new ValidationException("Payment ID is required.");if(student==null)throw new ValidationException("Student is required.");if(amount==null||amount.compareTo(BigDecimal.ZERO)<=0)throw new ValidationException("Payment amount must be strictly positive.");if(paymentDate==null||paymentDate.isAfter(LocalDate.now()))throw new ValidationException("Payment date must not be in the future.");if(paymentMethod==null||!paymentMethod.matches("(?i)CASH|CARD|MOBILE_MONEY|BANK_TRANSFER"))throw new ValidationException("Payment method is invalid.");if(receiptNumber==null||receiptNumber.trim().isEmpty())throw new ValidationException("Receipt number is required.");}
 public void printDetails(){System.out.println(getRegistrationSummary());} public String getRegistrationSummary(){return "["+id+"] "+student.getName()+" paid "+amount+" via "+paymentMethod+" - receipt "+receiptNumber;}
 public String toString(){return getRegistrationSummary();}
}
