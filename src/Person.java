

import java.time.LocalDate;

public abstract class Person implements Identifiable, Registrable {
    private String id;
    private String name;
    private String email;
    private String phone;
    private LocalDate dateOfBirth;
    private boolean active;

    protected Person(String id, String name, String email, String phone, LocalDate dateOfBirth, boolean active) {
        this.id = id; this.name = name; this.email = email; this.phone = phone;
        this.dateOfBirth = dateOfBirth; this.active = active;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
    public String getDisplayName() { return name; }
    public abstract String getRoleDescription();

    public void validate() throws ValidationException {
        if (id == null || id.trim().isEmpty()) throw new ValidationException("ID is required.");
        if (name == null || name.trim().length() < MIN_NAME_LENGTH || name.trim().length() > MAX_NAME_LENGTH)
            throw new ValidationException("Name must be 2 to 60 characters.");
        if (email == null || email.length() > MAX_EMAIL_LENGTH || !email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"))
            throw new ValidationException("A valid email is required.");
        String digits = phone == null ? "" : phone.replace("+", "").replace(" ", "").replace("-", "");
        if (!digits.matches("\\d{" + MIN_PHONE_LENGTH + "," + MAX_PHONE_LENGTH + "}"))
            throw new ValidationException("Phone must contain 10 to 15 digits.");
        if (dateOfBirth == null || !dateOfBirth.isBefore(LocalDate.now()))
            throw new ValidationException("Date of birth must be in the past.");
    }
    public void printDetails() { System.out.println(getRegistrationSummary()); }
    public abstract String getRegistrationSummary();
}
