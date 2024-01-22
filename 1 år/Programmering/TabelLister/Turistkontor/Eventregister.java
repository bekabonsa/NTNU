import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.lang.Math;
import java.util.Comparator;


/**
 * @author Beka Bonsa
 * class for editing events.
 */
public class Eventregister {

    private ArrayList<Event> events = new ArrayList<>();

    /**
     * register an event
     * @param event object event
     * @return event object, to show the user what they have added
     */
    public Event registerEvent(Event event){
        events.add(event);
        return event;
    }

    public ArrayList<Event> getEventsList(){
        return events;
    }

    /**
     * total amount of events in a certain location
     * @param location String location name
     * @return newList, ArrayList of events with a certain location
     */
    public ArrayList<Event> eventsAt(String location){
        ArrayList<Event> newList = new ArrayList<Event>();
        for(Event e: events){
            if(location.equals(e.getEventLocation()))newList.add(e);
        }
        return newList;
    }

    /**
     * total events in a given date
     * @param date int date, int date = (int)"year"+"month"+"date"
     * @return newList, ArrayList of events with a certain date
     */
    public ArrayList<Event> eventsOn(double date){

        ArrayList<Event> newList = new ArrayList<Event>();
        for(Event e: events){
            if(date == (int)Math.floor(e.getEventDateTime()/10000))newList.add(e);
        }
        return newList;
    }


    public ArrayList<Event> eventsBetweenDates(int date1, int date2){

        ArrayList<Event> newList = new ArrayList<Event>();
        for (Event e:events) {
            int eventDate = (int) Math.floor(e.getEventDateTime()/10000);
            if(eventDate >= date1 && eventDate<=date2)newList.add(e);
        }
        Comparator<Event> timeFilterA = (Event E1, Event E2) -> (int)E1.getEventDateTime()-(int)E2.getEventDateTime();
        Collections.sort(newList, timeFilterA);
        return newList;
    }

    public ArrayList<Event> sortBy (int filter){
        switch (filter){


            /*
              Comparator<Event> locationFilter = (Event l1, Event l2) -> l1.getEventLocation()
                        .compareTo(l2.getEventLocation());
                ArrayList<Event> sortedArrayL = events;
                Collections.sort(sortedArrayL, locationFilter);
                return sortedArrayL;

             */




            case 1:
                Comparator<Event> locationFilter = (Event l1, Event l2) -> l1.getEventLocation()
                        .compareTo(l2.getEventLocation());
                ArrayList<Event> sortedArrayL = events;
                Collections.sort(sortedArrayL, locationFilter);
                return sortedArrayL;

            case 2:
                Comparator<Event> typeFilter = (Event e1, Event e2) -> e1.getEventType().compareTo(e2.getEventType());
                ArrayList<Event> sortedArrayT = events;
                Collections.sort(sortedArrayT, typeFilter);
                return sortedArrayT;

            case 3:
                Comparator<Event> timeFilter = (Event T1, Event T2) -> (int)
                        (T1.getEventDateTime()- T2.getEventDateTime());
                ArrayList<Event> sortedArrayTime = events;
                Collections.sort(sortedArrayTime, timeFilter);
                return sortedArrayTime;

            default:
                System.out.println("You must type in a number ranging from 1-3");
                Client client = new Client();
                client.allEventsSorted();
                break;
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Eventregister)) return false;
        Eventregister that = (Eventregister) o;
        return Objects.equals(events, that.events);
    }

    @Override
    public int hashCode() {
        return Objects.hash(events);
    }
}
