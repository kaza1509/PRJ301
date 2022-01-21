package entity;

/**
 *
 * @author kazaf
 */
public class Employee {

    private int employeeId;//auto number
    private String lastName, firstName, title, titleOfCourtesy, birthDate, hireDate, address, city;
    private String region, postalCode, country, homePhone, extension, photo, Notes;
    private int reportsTo;
    private String photoPath;

    public Employee() {
    }

    public Employee(int employeeId, String lastName, String firstName, String title, String titleOfCourtesy, String birthDate, String hireDate, String address, String city, String region, String postalCode, String country, String homePhone, String extension, String photo, String Notes, int reportsTo, String photoPath) {
        this.employeeId = employeeId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.title = title;
        this.titleOfCourtesy = titleOfCourtesy;
        this.birthDate = birthDate;
        this.hireDate = hireDate;
        this.address = address;
        this.city = city;
        this.region = region;
        this.postalCode = postalCode;
        this.country = country;
        this.homePhone = homePhone;
        this.extension = extension;
        this.photo = photo;
        this.Notes = Notes;
        this.reportsTo = reportsTo;
        this.photoPath = photoPath;
    }

    //No image for update
    public Employee(int employeeId, String lastName, String firstName, String title, String titleOfCourtesy, String birthDate, String hireDate, String address, String city, String region, String postalCode, String country, String homePhone, String extension, String Notes, int reportsTo, String photoPath) {
        this.employeeId = employeeId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.title = title;
        this.titleOfCourtesy = titleOfCourtesy;
        this.birthDate = birthDate;
        this.hireDate = hireDate;
        this.address = address;
        this.city = city;
        this.region = region;
        this.postalCode = postalCode;
        this.country = country;
        this.homePhone = homePhone;
        this.extension = extension;
        this.Notes = Notes;
        this.reportsTo = reportsTo;
        this.photoPath = photoPath;
    }

    public Employee(String lastName, String firstName, String title, String titleOfCourtesy, String birthDate,
            String hireDate, String address, String city, String region, String postalCode, String country,
            String homePhone, String extension, String photo, String Notes, int reportsTo, String photoPath) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.title = title;
        this.titleOfCourtesy = titleOfCourtesy;
        this.birthDate = birthDate;
        this.hireDate = hireDate;
        this.address = address;
        this.city = city;
        this.region = region;
        this.postalCode = postalCode;
        this.country = country;
        this.homePhone = homePhone;
        this.extension = extension;
        this.photo = photo;
        this.Notes = Notes;
        this.reportsTo = reportsTo;
        this.photoPath = photoPath;
    }

    //No image 14 - for insert
    public Employee(String lastName, String firstName, String title, String titleOfCourtesy, String birthDate,
            String hireDate, String address, String city, String region, String postalCode, String country,
            String homePhone, String extension, String Notes, int reportsTo, String photoPath) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.title = title;
        this.titleOfCourtesy = titleOfCourtesy;
        this.birthDate = birthDate;
        this.hireDate = hireDate;
        this.address = address;
        this.city = city;
        this.region = region;
        this.postalCode = postalCode;
        this.country = country;
        this.homePhone = homePhone;
        this.extension = extension;
        this.Notes = Notes;
        this.reportsTo = reportsTo;
        this.photoPath = photoPath;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleOfCourtesy() {
        return titleOfCourtesy;
    }

    public void setTitleOfCourtesy(String titleOfCourtesy) {
        this.titleOfCourtesy = titleOfCourtesy;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getHireDate() {
        return hireDate;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String Notes) {
        this.Notes = Notes;
    }

    public int getReportsTo() {
        return reportsTo;
    }

    public void setReportsTo(int reportsTo) {
        this.reportsTo = reportsTo;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    @Override
    public String toString() {
        return "Employee{" + "employeeId=" + employeeId + ", lastName=" + lastName + ", firstName=" + firstName + ", title=" + title + ", titleOfCourtesy=" + titleOfCourtesy + ", birthDate=" + birthDate + ", hireDate=" + hireDate + ", address=" + address + ", city=" + city + ", region=" + region + ", postalCode=" + postalCode + ", country=" + country + ", homePhone=" + homePhone + ", extension=" + extension + ", photo=" + photo + ", Notes=" + Notes + ", reportsTo=" + reportsTo + ", photoPath=" + photoPath + '}';
    }

}
