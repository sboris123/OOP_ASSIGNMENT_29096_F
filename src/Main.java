

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Person> people = new ArrayList<>();
    private static final List<Department> departments = new ArrayList<>();
    private static final List<Course> courses = new ArrayList<>();
    private static final List<Classroom> classrooms = new ArrayList<>();
    private static final List<Enrollment> enrollments = new ArrayList<>();
    private static final List<Assessment> assessments = new ArrayList<>();
    private static final List<FeePayment> payments = new ArrayList<>();
    private static final List<LibraryBook> books = new ArrayList<>();
    private static final List<BookLoan> loans = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("AUCA_ID School Management System");
        while (true) {
            printMenu();
            int choice = readInt("Select: ", 0, 19);
            try {
                switch (choice) {
                    case 1 -> registerDepartment();
                    case 2 -> registerStudent();
                    case 3 -> registerTeacher();
                    case 4 -> registerAdministrator();
                    case 5 -> registerCourse();
                    case 6 -> registerClassroom();
                    case 7 -> enrollStudent();
                    case 8 -> recordAssessment();
                    case 9 -> recordFeePayment();
                    case 10 -> registerLibraryBook();
                    case 11 -> borrowBook();
                    case 12 -> returnBook();
                    case 13 -> viewAllRecords();
                    case 14 -> searchById();
                    case 15 -> updateRecord();
                    case 16 -> showStudentAcademicSummary();
                    case 17 -> showPaymentHistory();
                    case 18 -> showAvailableBooks();
                    case 19 -> demonstratePolymorphism();
                    case 0 -> { System.out.println("Goodbye."); return; }
                }
            } catch (ValidationException | DuplicateRecordException | RecordNotFoundException |
                     PrerequisiteMissingException | BookUnavailableException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Error: enter a valid number.");
            } catch (Exception e) {
                System.out.println("Operation failed: " +
                        (e.getMessage() == null ? "unexpected input or missing value." : e.getMessage()));
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n=== AUCA_ID Main Menu ===");
        System.out.println("1 Register Department");
        System.out.println("2 Register Student");
        System.out.println("3 Register Teacher");
        System.out.println("4 Register Administrator");
        System.out.println("5 Register Course");
        System.out.println("6 Register Classroom");
        System.out.println("7 Enroll Student");
        System.out.println("8 Record Assessment");
        System.out.println("9 Record Fee Payment");
        System.out.println("10 Register Library Book");
        System.out.println("11 Borrow Book");
        System.out.println("12 Return Book");
        System.out.println("13 View All Records");
        System.out.println("14 Search by ID");
        System.out.println("15 Update Record");
        System.out.println("16 Show Student Academic Summary");
        System.out.println("17 Show Payment History");
        System.out.println("18 Show Available Books");
        System.out.println("19 Demonstrate Polymorphism");
        System.out.println("0 Exit");
    }

    private static String readText(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private static String requiredText(String prompt) {
        while (true) {
            String value = readText(prompt);
            if (!value.isEmpty()) return value;
            System.out.println("This field is required.");
        }
    }

    private static int readInt(String prompt, int min, int max) {
        while (true) {
            String value = readText(prompt);
            int number;
            try {
                number = Integer.parseInt(value);
            } catch (NumberFormatException e) {
                System.out.println("Enter a whole number from " + min + " to " + max + ".");
                continue;
            }
            if (number >= min && number <= max) return number;
            System.out.println("Enter a whole number from " + min + " to " + max + ".");
        }
    }

    private static double readDouble(String prompt) {
        while (true) {
            String value = readText(prompt);
            try {
                double number = Double.parseDouble(value);
                if (Double.isFinite(number)) return number;
                System.out.println("Enter a finite number.");
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid number.");
            }
        }
    }

    private static LocalDate readDate(String prompt) {
        while (true) {
            String value = readText(prompt + " (yyyy-MM-dd): ");
            try { return LocalDate.parse(value); }
            catch (DateTimeParseException e) { System.out.println("Invalid calendar date. Use yyyy-MM-dd."); }
        }
    }

    private static BigDecimal readMoney(String prompt) {
        while (true) {
            String value = readText(prompt);
            try { return new BigDecimal(value); }
            catch (NumberFormatException e) { System.out.println("Enter a valid amount."); }
        }
    }

    private static boolean confirmReview(String summary) {
        System.out.println("\n--- Review ---\n" + summary);
        int choice = readInt("1 Save, 2 Save then update later, 0 Cancel: ", 0, 2);
        if (choice == 0) { System.out.println("Cancelled. No record was created."); return false; }
        if (choice == 2) System.out.println("Saved. Use menu option 15 (Update Record) to make changes.");
        return true;
    }

    private static boolean uniqueId(String id) throws DuplicateRecordException {
        for (Department d : departments) if (d.getId().equalsIgnoreCase(id)) throw new DuplicateRecordException("Duplicate ID: " + id);
        for (Course c : courses) if (c.getId().equalsIgnoreCase(id)) throw new DuplicateRecordException("Duplicate ID: " + id);
        for (Classroom c : classrooms) if (c.getId().equalsIgnoreCase(id)) throw new DuplicateRecordException("Duplicate ID: " + id);
        for (Enrollment e : enrollments) if (e.getId().equalsIgnoreCase(id)) throw new DuplicateRecordException("Duplicate ID: " + id);
        for (Assessment a : assessments) if (a.getId().equalsIgnoreCase(id)) throw new DuplicateRecordException("Duplicate ID: " + id);
        for (FeePayment p : payments) if (p.getId().equalsIgnoreCase(id)) throw new DuplicateRecordException("Duplicate ID: " + id);
        for (LibraryBook b : books) if (b.getId().equalsIgnoreCase(id)) throw new DuplicateRecordException("Duplicate ID: " + id);
        for (BookLoan l : loans) if (l.getId().equalsIgnoreCase(id)) throw new DuplicateRecordException("Duplicate ID: " + id);
        for (Person p : people) if (p.getId().equalsIgnoreCase(id)) throw new DuplicateRecordException("Duplicate ID: " + id);
        return true;
    }

    private static void requireUniqueCode(String value, String label, List<String> existing) throws DuplicateRecordException {
        for (String code : existing) {
            if (code.equalsIgnoreCase(value)) {
                throw new DuplicateRecordException("Duplicate " + label + ": " + value);
            }
        }
    }

    private static void registerDepartment() throws Exception {
        String id = requiredText("Department ID (0 or Back to cancel): ");
        if (id.equalsIgnoreCase("0") || id.equalsIgnoreCase("back")) return;
        uniqueId(id);
        String name = requiredText("Department name: ");
        String code = requiredText("Department code: ");
        List<String> departmentCodes = new ArrayList<>();
        for (Department existing : departments) departmentCodes.add(existing.getCode());
        requireUniqueCode(code, "department code", departmentCodes);
        String location = requiredText("Location: ");
        Teacher head = null;
        if (!teachers().isEmpty()) {
            int useHead = readInt("Assign head? 1 Yes, 0 No: ", 0, 1);
            if (useHead == 1) head = selectTeacher("Select department head");
        }
        Department department = new Department(id, name, code, location, head, true);
        department.validate();
        if (confirmReview(department.getRegistrationSummary())) departments.add(department);
    }

    private static void registerStudent() throws Exception {
        Department department = ensureDepartment("Student");
        if (department == null) return;
        String id = requiredText("Student personal ID: "); uniqueId(id);
        String name = requiredText("Full name: ");
        String email = requiredText("Email: ");
        String phone = requiredText("Phone: ");
        LocalDate dob = readDate("Date of birth");
        String studentNumber = requiredText("Student number: ");
        List<String> studentCodes = new ArrayList<>();
        for (Student existing : students()) studentCodes.add(existing.getStudentNumber());
        requireUniqueCode(studentNumber, "student number", studentCodes);
        String program = requiredText("Program: ");
        String major = requiredText("Major: ");
        double gpa = readDouble("GPA (0.0-4.0): ");
        LocalDate enrollmentDate = readDate("Enrollment date");
        int year = readInt("Study year (1-6): ", 1, 6);
        String officeRoom = readText("Office room (optional): ");
        String academicCode = studentNumber;
        String status = "ACTIVE";
        boolean active = true;
        boolean scholarship = readInt("Scholarship? 1 Yes, 0 No: ", 0, 1) == 1;
        Student student = new Student(id,name,email,phone,dob,active,department,academicCode,enrollmentDate,status,year,officeRoom,
                                      studentNumber,program,major,gpa,enrollmentDate,scholarship);
        student.validate();
        if (confirmReview(student.getRegistrationSummary())) people.add(student);
    }

    private static void registerTeacher() throws Exception {
        registerTeacher(null);
    }

    private static void registerTeacher(Department requiredDepartment) throws Exception {
        Department department = requiredDepartment == null ? ensureDepartment("Teacher") : requiredDepartment;
        if (department == null) return;
        String id = requiredText("Teacher personal ID: "); uniqueId(id);
        String name = requiredText("Full name: ");
        String email = requiredText("Email: ");
        String phone = requiredText("Phone: ");
        LocalDate dob = readDate("Date of birth");
        String employeeNumber = requiredText("Employee number: ");
        List<String> employeeCodes = new ArrayList<>();
        for (Teacher existing : teachers()) employeeCodes.add(existing.getEmployeeNumber());
        requireUniqueCode(employeeNumber, "employee number", employeeCodes);
        String qualification = requiredText("Qualification: ");
        BigDecimal salary = readMoney("Salary: ");
        LocalDate hireDate = readDate("Hire date");
        boolean fullTime = readInt("Full-time? 1 Yes, 0 No: ", 0, 1) == 1;
        String specialization = requiredText("Specialization: ");
        String officeRoom = readText("Office room (optional): ");
        Teacher teacher = new Teacher(id,name,email,phone,dob,true,department,employeeNumber,hireDate,"ACTIVE",1,officeRoom,
                                      employeeNumber,qualification,salary,hireDate,fullTime,specialization);
        teacher.validate();
        if (confirmReview(teacher.getRegistrationSummary())) people.add(teacher);
    }

    private static void registerAdministrator() throws Exception {
        String id = requiredText("Administrator ID: "); uniqueId(id);
        String name = requiredText("Full name: ");
        String email = requiredText("Email: ");
        String phone = requiredText("Phone: ");
        LocalDate dob = readDate("Date of birth");
        String administratorNumber = requiredText("Administrator number: ");
        String departmentName = requiredText("Department name: ");
        String position = requiredText("Position: ");
        LocalDate hireDate = readDate("Hire date");
        boolean fullTime = readInt("Full-time? 1 Yes, 0 No: ", 0, 1) == 1;
        String accessLevel = requiredText("Access level (LOW, MEDIUM, HIGH, ADMIN): ").toUpperCase();
        Administrator administrator = new Administrator(id,name,email,phone,dob,true,administratorNumber,departmentName,position,hireDate,fullTime,accessLevel);
        administrator.validate();
        if (confirmReview(administrator.getRegistrationSummary())) people.add(administrator);
    }

    private static void registerCourse() throws Exception {
        Department department = ensureDepartment("Course");
        if (department == null) return;
        if (teachersForDepartment(department).isEmpty()) {
            System.out.println("No Teacher is registered in the selected Department.");
            int choice = readInt("1 Register Teacher now, 0 Cancel course: ", 0, 1);
            if (choice == 0) return;
            registerTeacher(department);
            if (teachersForDepartment(department).isEmpty()) return;
        }
        Teacher teacher = selectTeacher("Select course teacher", department);
        String id = requiredText("Course ID: "); uniqueId(id);
        String name = requiredText("Course name: ");
        String code = requiredText("Course code: ");
        List<String> courseCodes = new ArrayList<>();
        for (Course existing : courses) courseCodes.add(existing.getCode());
        requireUniqueCode(code, "course code", courseCodes);
        int creditHours = readInt("Credit hours (1-6): ", 1, 6);
        Course course = new Course(id,name,code,creditHours,department,teacher);
        course.validate();
        if (confirmReview(course.getRegistrationSummary())) courses.add(course);
    }

    private static void registerClassroom() throws Exception {
        String id = requiredText("Classroom ID: "); uniqueId(id);
        String roomNumber = requiredText("Room number: ");
        int capacity = readInt("Capacity: ", 1, 100000);
        int floor = readInt("Floor number: ", 0, 1000);
        String building = requiredText("Building: ");
        Classroom classroom = new Classroom(id,roomNumber,capacity,floor,building,true);
        classroom.validate();
        if (confirmReview(classroom.getRegistrationSummary())) classrooms.add(classroom);
    }

    private static void enrollStudent() throws Exception {
        if (students().isEmpty()) {
            System.out.println("No Student is registered.");
            int c = readInt("1 Register Student now, 0 Cancel enrollment: ", 0, 1);
            if (c == 1) registerStudent(); else return;
        }
        if (courses.isEmpty()) {
            System.out.println("No Course is registered.");
            int c = readInt("1 Register Course now, 0 Cancel enrollment: ", 0, 1);
            if (c == 1) registerCourse(); else return;
        }
        Student student = selectStudent("Select Student");
        Course course = selectCourse("Select Course");
        String semester = requiredText("Semester (e.g. 2026-FALL): ");
        LocalDate date = readDate("Enrollment date");
        for (Enrollment e : enrollments) if (e.isActive() && e.getStudent()==student && e.getCourse()==course && e.getSemester().equalsIgnoreCase(semester))
            throw new DuplicateRecordException("Student is already actively enrolled in this course for the semester.");
        String id = requiredText("Enrollment ID: "); uniqueId(id);
        Enrollment enrollment = new Enrollment(id,student,course,semester,date,true);
        enrollment.validate();
        if (confirmReview(enrollment.getRegistrationSummary())) enrollments.add(enrollment);
    }

    private static void recordAssessment() throws Exception {
        if (activeEnrollments().isEmpty()) throw new PrerequisiteMissingException("No active enrollment exists. Enroll a student first.");
        Enrollment enrollment = selectEnrollment("Select active enrollment");
        if (!enrollment.isActive()) throw new ValidationException("Only active enrollments can be assessed.");
        String id = requiredText("Assessment ID: "); uniqueId(id);
        String type = requiredText("Assessment type (QUIZ, ASSIGNMENT, MIDTERM, FINAL, PROJECT): ").toUpperCase();
        double maximum = readDouble("Maximum score: ");
        double obtained = readDouble("Obtained score: ");
        LocalDate date = readDate("Assessment date");
        Assessment assessment = new Assessment(id,enrollment,type,maximum,obtained,date);
        assessment.validate();
        if (confirmReview(assessment.getRegistrationSummary())) assessments.add(assessment);
    }

    private static void recordFeePayment() throws Exception {
        if (students().isEmpty()) throw new PrerequisiteMissingException("No Student is registered.");
        Student student = selectStudent("Select Student");
        String id = requiredText("Payment ID: "); uniqueId(id);
        BigDecimal amount = readMoney("Amount: ");
        LocalDate date = readDate("Payment date");
        String method = requiredText("Method (CASH, CARD, MOBILE_MONEY, BANK_TRANSFER): ").toUpperCase();
        String receipt = requiredText("Receipt number: ");
        for (FeePayment p : payments) if (p.getReceiptNumber().equalsIgnoreCase(receipt))
            throw new DuplicateRecordException("Duplicate receipt number: " + receipt);
        FeePayment payment = new FeePayment(id,student,amount,date,method,receipt);
        payment.validate();
        if (confirmReview(payment.getRegistrationSummary())) payments.add(payment);
    }

    private static void registerLibraryBook() throws Exception {
        String id = requiredText("Book ID: "); uniqueId(id);
        String title = requiredText("Title: ");
        String isbn = requiredText("ISBN: ");
        String author = requiredText("Author: ");
        int total = readInt("Total copies: ", 0, 100000);
        int available = readInt("Available copies: ", 0, total);
        LibraryBook book = new LibraryBook(id,title,isbn,author,total,available);
        book.validate();
        if (confirmReview(book.getRegistrationSummary())) books.add(book);
    }

    private static void borrowBook() throws Exception {
        if (students().isEmpty()) throw new PrerequisiteMissingException("No Student is registered.");
        if (books.isEmpty()) throw new PrerequisiteMissingException("No Library Book is registered.");
        Student student = selectStudent("Select Student");
        LibraryBook book = selectAvailableBook("Select available book");
        if (book.getAvailableCopies() <= 0) throw new BookUnavailableException("Selected book has no available copies.");
        String id = requiredText("Loan ID: "); uniqueId(id);
        LocalDate borrowed = readDate("Borrowed date");
        LocalDate due = readDate("Due date");
        BookLoan loan = new BookLoan(id,student,book,borrowed,due,null);
        loan.validate();
        if (confirmReview(loan.getRegistrationSummary())) {
            loans.add(loan);
            book.setAvailableCopies(book.getAvailableCopies() - 1);
        }
    }

    private static void returnBook() throws Exception {
        if (activeLoans().isEmpty()) throw new RecordNotFoundException("No active loan exists.");
        BookLoan active = selectActiveLoan("Select loan to return");
        LocalDate returned = readDate("Returned date");
        if (returned.isBefore(active.getBorrowedDate())) throw new ValidationException("Returned date cannot be before borrowed date.");
        active.setReturnedDate(returned);
        active.getBook().setAvailableCopies(active.getBook().getAvailableCopies() + 1);
        System.out.println("Book returned successfully.");
    }

    private static void viewAllRecords() {
        System.out.println("\n--- Departments ---"); departments.forEach(System.out::println);
        System.out.println("--- People ---"); people.forEach(System.out::println);
        System.out.println("--- Courses ---"); courses.forEach(System.out::println);
        System.out.println("--- Classrooms ---"); classrooms.forEach(System.out::println);
        System.out.println("--- Enrollments ---"); enrollments.forEach(System.out::println);
        System.out.println("--- Assessments ---"); assessments.forEach(System.out::println);
        System.out.println("--- Fee Payments ---"); payments.forEach(System.out::println);
        System.out.println("--- Library Books ---"); books.forEach(System.out::println);
        System.out.println("--- Book Loans ---"); loans.forEach(System.out::println);
    }

    private static void searchById() throws RecordNotFoundException {
        String id = requiredText("Enter ID: ");
        for (Identifiable r : allIdentifiable()) if (r.getId().equalsIgnoreCase(id)) {
            System.out.println(r.getDisplayName());
            if (r instanceof Registrable reg) reg.printDetails();
            return;
        }
        throw new RecordNotFoundException("No record found with ID " + id);
    }

    private static void updateRecord() throws RecordNotFoundException {
        String id = requiredText("Enter record ID to update: ");
        for (Person p : people) if (p.getId().equalsIgnoreCase(id)) {
            String newName = readText("New name (leave blank for unchanged): ");
            if (!newName.isBlank()) p.setName(newName);
            p.setActive(readInt("1 Active, 0 Inactive: ", 0, 1) == 1);
            System.out.println("Person updated."); return;
        }
        for (Department d : departments) if (d.getId().equalsIgnoreCase(id)) { d.setName(requiredText("New name: ")); System.out.println("Department updated."); return; }
        for (Course c : courses) if (c.getId().equalsIgnoreCase(id)) { c.setName(requiredText("New name: ")); System.out.println("Course updated."); return; }
        for (LibraryBook b : books) if (b.getId().equalsIgnoreCase(id)) { b.setTitle(requiredText("New title: ")); System.out.println("Book updated."); return; }
        throw new RecordNotFoundException("No editable record found with ID " + id);
    }

    private static void showStudentAcademicSummary() throws RecordNotFoundException {
        if (students().isEmpty()) throw new RecordNotFoundException("No Students are registered.");
        Student student = selectStudent("Select Student");
        System.out.println("\nAcademic Summary for " + student.getName());
        for (Enrollment e : enrollments) if (e.getStudent()==student) {
            System.out.println(e.getRegistrationSummary());
            for (Assessment a : assessments) if (a.getEnrollment()==e) a.printDetails();
        }
    }

    private static void showPaymentHistory() throws RecordNotFoundException {
        if (students().isEmpty()) throw new RecordNotFoundException("No Students are registered.");
        Student student = selectStudent("Select Student");
        boolean found = false;
        for (FeePayment p : payments) if (p.getStudent()==student) { p.printDetails(); found = true; }
        if (!found) System.out.println("No payment history.");
    }

    private static void showAvailableBooks() {
        for (LibraryBook b : books) if (b.getAvailableCopies()>0) b.printDetails();
    }

    private static void demonstratePolymorphism() {
        System.out.println("\nRuntime polymorphism - Person references:");
        for (Person p : people) System.out.println(p.getRoleDescription() + ": " + p.getRegistrationSummary());
        System.out.println("\nInterface polymorphism - Identifiable references:");
        for (Identifiable r : allIdentifiable()) System.out.println(r.getId()+" | "+r.getDisplayName()+" | active="+r.isActive());
    }

    private static Department ensureDepartment(String forType) throws Exception {
        if (departments.isEmpty()) {
            System.out.println("No Department is registered for " + forType + ".");
            int choice = readInt("1 Register Department now, 0 Cancel: ", 0, 1);
            if (choice == 1) registerDepartment();
            if (departments.isEmpty()) return null;
        }
        return selectDepartment("Select Department");
    }

    private static List<Student> students() { List<Student> result=new ArrayList<>(); for(Person p:people) if(p instanceof Student s) result.add(s); return result; }
    private static List<Teacher> teachers() { List<Teacher> result=new ArrayList<>(); for(Person p:people) if(p instanceof Teacher t) result.add(t); return result; }
    private static List<Teacher> teachersForDepartment(Department department) { List<Teacher> result=new ArrayList<>(); for(Teacher teacher:teachers()) if(teacher.getDepartment()==department) result.add(teacher); return result; }
    private static List<Enrollment> activeEnrollments() { List<Enrollment> result=new ArrayList<>(); for(Enrollment e:enrollments) if(e.isActive()) result.add(e); return result; }
    private static List<BookLoan> activeLoans() { List<BookLoan> result=new ArrayList<>(); for(BookLoan loan:loans) if(loan.isActive()) result.add(loan); return result; }

    private static Student selectStudent(String prompt) throws RecordNotFoundException { List<Student> list=students(); if(list.isEmpty())throw new RecordNotFoundException("No Students."); System.out.println(prompt+":"); for(int i=0;i<list.size();i++)System.out.println((i+1)+". "+list.get(i).getRegistrationSummary()); int n=readInt("Select: ",1,list.size()); return list.get(n-1); }
    private static Teacher selectTeacher(String prompt) throws RecordNotFoundException { return selectTeacher(prompt, null); }
    private static Teacher selectTeacher(String prompt, Department department) throws RecordNotFoundException { List<Teacher> list=department==null?teachers():teachersForDepartment(department); if(list.isEmpty())throw new RecordNotFoundException(department==null?"No Teachers.":"No Teachers belong to the selected Department."); System.out.println(prompt+":"); for(int i=0;i<list.size();i++)System.out.println((i+1)+". "+list.get(i).getRegistrationSummary()); int n=readInt("Select: ",1,list.size()); return list.get(n-1); }
    private static Department selectDepartment(String prompt) throws RecordNotFoundException { if(departments.isEmpty())throw new RecordNotFoundException("No Departments."); System.out.println(prompt+":"); for(int i=0;i<departments.size();i++)System.out.println((i+1)+". "+departments.get(i).getRegistrationSummary()); int n=readInt("Select: ",1,departments.size()); return departments.get(n-1); }
    private static Course selectCourse(String prompt) throws RecordNotFoundException { if(courses.isEmpty())throw new RecordNotFoundException("No Courses."); System.out.println(prompt+":"); for(int i=0;i<courses.size();i++)System.out.println((i+1)+". "+courses.get(i).getRegistrationSummary()); int n=readInt("Select: ",1,courses.size()); return courses.get(n-1); }
    private static Enrollment selectEnrollment(String prompt) throws RecordNotFoundException { List<Enrollment> active=activeEnrollments(); if(active.isEmpty())throw new RecordNotFoundException("No active Enrollments."); System.out.println(prompt+":"); for(int i=0;i<active.size();i++)System.out.println((i+1)+". "+active.get(i).getRegistrationSummary()); int n=readInt("Select: ",1,active.size()); return active.get(n-1); }
    private static LibraryBook selectAvailableBook(String prompt) throws RecordNotFoundException { List<LibraryBook> available=new ArrayList<>(); for(LibraryBook b:books)if(b.getAvailableCopies()>0)available.add(b); if(available.isEmpty())throw new RecordNotFoundException("No available books."); System.out.println(prompt+":"); for(int i=0;i<available.size();i++)System.out.println((i+1)+". "+available.get(i).getRegistrationSummary()); int n=readInt("Select: ",1,available.size()); return available.get(n-1); }
    private static BookLoan selectActiveLoan(String prompt) throws RecordNotFoundException { List<BookLoan> active=activeLoans(); if(active.isEmpty())throw new RecordNotFoundException("No active loans."); System.out.println(prompt+":"); for(int i=0;i<active.size();i++)System.out.println((i+1)+". "+active.get(i).getRegistrationSummary()); int n=readInt("Select: ",1,active.size()); return active.get(n-1); }

    private static List<Identifiable> allIdentifiable() {
        List<Identifiable> result=new ArrayList<>();
        result.addAll(people); result.addAll(departments); result.addAll(courses); result.addAll(classrooms);
        result.addAll(enrollments); result.addAll(assessments); result.addAll(payments); result.addAll(books); result.addAll(loans);
        return result;
    }
}
