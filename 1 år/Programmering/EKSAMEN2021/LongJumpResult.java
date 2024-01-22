import java.time.LocalTime;
import java.util.Objects;

/**
 * @author Beka D. Bonsa
 * a class that represents an athlete's performance in a long jump event.
 */
public class LongJumpResult {
    private LocalTime time;
    private String startNumber;
    private String name;
    private double result;
    private boolean is_faul;


    /**
     * Constructor used for initiating.
     * @param startNumber
     * @param name
     * @param result
     * @param is_faul
     * @param timeDate
     */
    public LongJumpResult(String startNumber, String name, double result, boolean is_faul, String timeDate){

        this.startNumber = startNumber;
        this.name = name;
        this.result = result;
        this.is_faul = is_faul;
        this.time = LocalTime.parse(timeDate);

    }

    /**
     * constructor for making deep copies in other classes
     * @param r
     */
    public LongJumpResult(LongJumpResult r){
        this.time = r.time;
        this.is_faul = r.is_faul;
        this.result = r.result;
        this.name = r.name;
        this.startNumber = r.startNumber;
    }

    /**
     * gets the time local time object
     * @return
     */
    public LocalTime getTime() {
        return time;
    }

    /**
     * gets the start number of the participant
     * @return
     */
    public String getStartNumber() {
        return startNumber;
    }

    /**
     * gets the name of the participant
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * gets the result of the jump for the participant
     * @return
     */
    public double getResult() {
        return result;
    }

    /**
     * returns true if the participant committed a faul, false if not
     * @return
     */
    public boolean is_faul() {
        return is_faul;
    }

    /**
     * setter method if start number is changed. for example if an athlete changes their last name (i dont know if it's
     * even possible). Having it here just in case such cases happen.
     * @param startNumber
     */
    public void setStartNumber(String startNumber) {
        this.startNumber = startNumber;
    }

    /**
     * Athletes could change their name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sometimes referees change their mind.
     * @param is_faul
     */
    public void setIs_faul(boolean is_faul) {
        this.is_faul = is_faul;
    }


    /**
     * checks if two objects of this class are equal based on the participants name, start number, time for the jump and
     * jump length
     * @param o
     * @return boolean, true if both objects are equal, false if not.
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LongJumpResult)) return false;
        LongJumpResult that = (LongJumpResult) o;
        return Double.compare(that.getResult(), getResult()) == 0 && getTime().equals(that.getTime())
                && getStartNumber().equals(that.getStartNumber()) && getName().equals(that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTime(), getStartNumber(), getName(), getResult());
    }


    @Override
    public String toString() {
        return

                "time=" + time +
                ", startNumber='" + startNumber + '\'' +
                ", name='" + name + '\'' +
                ", jump length result in meters =" + result +
                ", was the attempt flagged as faul? = " + ((is_faul == true) ? "yes": "No") +
                '}';
    }
}
