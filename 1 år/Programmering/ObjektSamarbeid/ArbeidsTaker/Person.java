

public final class Person {

    private final String firstname;
    private final String lastname;
    private final int birthyear;


    public Person(String firstname, String lastname, int birthyear){

        this.firstname = firstname;
        this.lastname = lastname;
        this.birthyear = birthyear;

    }




    public int getBirthyear() {
        return birthyear;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", birthyear=" + birthyear +
                '}';
    }
}
