public class Person {
    private int personID;
    private String lastName;
    private String  firstName;
    private int address;
    private String city;

    Person(int personID, String lastName, String firstName, int address, String city) {
        this.personID = personID;
        this.lastName = lastName;
        this.firstName = firstName;
        this.address = address;
        this.city = city;
    }

    int getPersonID() { return personID; }

    String getLastName() { return lastName; }

    String getFirstName() { return firstName; }

    int getAddress() { return address; }

    String getCity() { return city; }

    @Override
    public String toString() {
        return "Person{" +
                "personID=" + personID +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", address=" + address +
                ", city='" + city + '\'' +
                '}';
    }
}
