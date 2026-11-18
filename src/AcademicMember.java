

import java.time.LocalDate;

public abstract class AcademicMember extends Person {
    private Department department;
    private String academicCode;
    private LocalDate admissionDate;
    private String status;
    private int studyYear;
    private String officeRoom;

    protected AcademicMember(String id, String name, String email, String phone, LocalDate dateOfBirth,
                             boolean active, Department department, String academicCode, LocalDate admissionDate,
                             String status, int studyYear, String officeRoom) {
        super(id, name, email, phone, dateOfBirth, active);
        this.department = department; this.academicCode = academicCode; this.admissionDate = admissionDate;
        this.status = status; this.studyYear = studyYear; this.officeRoom = officeRoom;
    }

    public Department getDepartment() { return department; }
    public void setDepartment(Department department) { this.department = department; }
    public String getAcademicCode() { return academicCode; }
    public void setAcademicCode(String academicCode) { this.academicCode = academicCode; }
    public LocalDate getAdmissionDate() { return admissionDate; }
    public void setAdmissionDate(LocalDate admissionDate) { this.admissionDate = admissionDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public int getStudyYear() { return studyYear; }
    public void setStudyYear(int studyYear) { this.studyYear = studyYear; }
    public String getOfficeRoom() { return officeRoom; }
    public void setOfficeRoom(String officeRoom) { this.officeRoom = officeRoom; }
    public abstract String getAcademicCategory();

    @Override
    public void validate() throws ValidationException {
        super.validate();
        if (department == null) throw new ValidationException("Department is required.");
        if (academicCode == null || academicCode.trim().isEmpty()) throw new ValidationException("Academic code is required.");
        if (admissionDate == null || admissionDate.isAfter(LocalDate.now())) throw new ValidationException("Admission date must not be in the future.");
        if (status == null || !status.matches("(?i)ACTIVE|INACTIVE|GRADUATED")) throw new ValidationException("Status must be ACTIVE, INACTIVE, or GRADUATED.");
        if (studyYear < 1 || studyYear > 6) throw new ValidationException("Study year must be 1 to 6.");
    }
}
