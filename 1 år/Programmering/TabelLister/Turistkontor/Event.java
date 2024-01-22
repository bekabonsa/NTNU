import java.util.ArrayList;

public class Event {

    private int eventNumber;
    private String eventName;
    private String eventLocation;
    private String host;
    private String eventType;
    private double eventDateTime;

    public Event(int eventNumber, String eventName, String eventLocation, String host, String eventType,
                 double eventDateTime){

        this.eventNumber = eventNumber;
        this.eventName = eventName;
        this.eventLocation = eventLocation;
        this.eventType = eventType;
        this.host = host;
        this.eventDateTime = eventDateTime;

    }

    public void setEventNumber(int eventNumber) {
        this.eventNumber = eventNumber;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public void setEventDateTime(double eventDateTime) {
        this.eventDateTime = eventDateTime;
    }

    public double getEventDateTime() {
        return eventDateTime;
    }

    public int getEventNumber() {
        return eventNumber;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventType() {
        return eventType;
    }

    public String getHost() {
        return host;
    }

    @Override
    public String toString() {
        return "Events{" +
                "eventNumber=" + eventNumber +
                ", eventName='" + eventName + '\'' +
                ", eventLocation='" + eventLocation + '\'' +
                ", host='" + host + '\'' +
                ", eventType='" + eventType + '\'' +
                ", eventDateTime=" + eventDateTime +
                '}';
    }
}
