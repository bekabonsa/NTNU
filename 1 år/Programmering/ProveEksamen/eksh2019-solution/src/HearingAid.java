
/**
 * The type Hearing aid.
 */
public class HearingAid {
    private final int ID; //1001-9999
    private final String TYPE;
    private boolean available;
    private String rentedOutName;

    /**
     * Constructor for HearingAid class.
     *
     * @param ID   the hearing aid ID, must be in the interval 1001-9999
     * @param TYPE the type of hearing aid, cannot be blank
     * @throws IllegalArgumentException the illegal argument exception
     */
    public HearingAid(int ID, String TYPE) throws IllegalArgumentException {
        if(ID < 1001 || ID > 9999) throw new IllegalArgumentException("The id must be in the interval 1001-9999");
        if(TYPE.trim().isEmpty()) throw new IllegalArgumentException("You must provide a description for the type");
        this.ID = ID;
        this.TYPE = TYPE;
        available = true;
        rentedOutName = "";
    }

    /**
     * Constructor for HearingAid class.
     *
     * @param ID              the hearing aid ID, must be in the interval 1001-9999
     * @param TYPE            the TYPE of hearing aid, cannot be blank
     * @param rentedOutToName the name of the person renting this hearing aid, cannot be blank
     * @throws IllegalArgumentException the illegal argument exception
     */
    public HearingAid(int ID, String TYPE, String rentedOutToName) throws IllegalArgumentException{
        if(ID < 1001 || ID > 9999) throw new IllegalArgumentException("The ID must be in the interval 1001-9999");
        if(TYPE.trim().isEmpty()) throw new IllegalArgumentException("You must provide a description for the TYPE");
        if(rentedOutToName.isBlank()) throw new IllegalArgumentException("If a hearing aid is rented out, the person it's rented out to must be specified");
        this.ID = ID;
        this.TYPE = TYPE.trim();
        this.available = false;
        this.rentedOutName = rentedOutToName.trim();
    }

    /**
     * Rent out boolean.
     *
     * @param name the name
     * @return the boolean
     */
    public boolean rentOut(String name){
        if(name.isBlank()) throw new IllegalArgumentException("You must specify a name when renting out");
        if(!available) return false;
        rentedOutName = name.trim();
        available = false;
        return true;
    }

    /**
     * Deliver boolean.
     *
     * @return the boolean
     */
    public boolean deliver(){
        if(available) return false;
        rentedOutName = "";
        available = true;
        return true;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getID() {
        return ID;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public String getTYPE() {
        return TYPE;
    }

    /**
     * Is available boolean.
     *
     * @return the boolean
     */
    public boolean isAvailable() {
        return available;
    }

    /**
     * Gets rented out name.
     *
     * @return the rented out name
     */
    public String getRentedOutName() {
        return rentedOutName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(!(o instanceof HearingAid)) return false;
        HearingAid that = (HearingAid) o;
        return ID == that.ID;
    }

    @Override
    public String toString() {
        if(available){
            return ID + " " + TYPE + " available";
        }
        return ID + " " + TYPE + " rented out to " + rentedOutName;
    }
}
