import java.util.Objects;

/**
 * @author Beka Daniel Bonsa
 * a class that defines a hearing aid.
 */
public class HearingAid {
    private final int id;
    private final String type;
    private boolean is_Rented;
    private String ownerName;

    /**
     * constructor that initializes an object with all its attributes
     *
     * @param id
     * @param type
     * @param ownerName
     */
    public HearingAid (int id, String type, String ownerName) throws IllegalArgumentException{

        if(id<1001 || id > 9999) throw new IllegalArgumentException("ID must be between [1001-9999]");
        if(type.trim().isEmpty()) throw new IllegalArgumentException("You must specify the type of the hearing aid");

        this.id = id;
        this.type = type;
        this.is_Rented = true;
        this.ownerName = ownerName;
    }

    /**
     * constructor that doesn't require owner name as parameter to initialize the class
     * here solved by using "explicit constructor invocation".
     * @param id
     * @param type
     */
    public HearingAid (int id, String type) throws IllegalArgumentException{
        this(id, type, "not rented");
        this.is_Rented = false;
    }

    /**
     * get ID of hearing aid
     * @return ID as int
     */
    public int getId() {
        return id;
    }

    /**
     * get the type or description of the hearing aid apparatus
     * @return type as String
     */
    public String getType() {
        return type;
    }

    /**
     * Chek if apparatus is rented
     * @return true or false, true when its rented false when it's available for renting
     */
    public boolean is_Rented() {
        return is_Rented;
    }

    /**
     * get the hearing aid apparatus' current owner name
     * @return name of owner as String
     */
    public String getOwnerName() {
        return ownerName;
    }


    /**
     * set value of is_rented. when the product is returned and rented the value must change
     * @param is_Rented
     */
    public void setIs_Rented(boolean is_Rented) {
        this.is_Rented = is_Rented;
    }

    /**
     * Set current owner name. Since different people could rent the product
     * the owner name must correspond to the person renting the product
     * @param ownerName
     */
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }


    /**
     * equals method that checks if two HearingAid objects are equal based on their id. first line of the equals method
     * checks if the two objects have the same address in memory, if they do they must be one and the same.
     * 2nd line checks if the object is a null object or not made from the same class, if thats true then they are not
     * equal
     * 3rd and 4th line compare the objects based by id which is unique for every hearing aid apparatus.
     * @param o
     * @return Boolean: true if both are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HearingAid that = (HearingAid) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    /**
     * to string method that describes the hearing aid apparatus.
     * @return String, description
     */
    @Override
    public String toString() {
        if (is_Rented){
            return id+" "+type+" utleid til "+ ownerName;
        }
        else{
            return id+" "+type+" ledig";
        }
    }
}
