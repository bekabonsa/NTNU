import java.util.*;

public class Client {

    private Scanner input = new Scanner(System.in);
    private Eventregister eventRegister = new Eventregister();

    public static void main(String[] args) {

    Client c = new Client();

    while(true){
        c.menu();
    }

    }

    public void menu(){
        System.out.println("");
        System.out.println("input 1 to register an event");
        System.out.println("input 2 to find all events at a given location");
        System.out.println("input 3 to find all events on a given date");
        System.out.println("input 4 to find all events within given dates");
        System.out.println("input 5 to see all sorted events");

        int in = input.nextInt();
        switch (in){
            case 1:
                registerEvent();
                break;
            case 2:
                eventsWithLocation();
            case 3:
                eventsWithDate();
                break;
            case 4:
                eventsBetweenDates();
                break;
            case 5:
                allEventsSorted();
                break;
        }

    }

    public void registerEvent(){
        input.nextLine();
        System.out.println("Name: "); String name = input.nextLine();
        System.out.println("Event Id: "); int id = input.nextInt();
        input.nextLine();
        System.out.print("Place: "); String place = input.nextLine();
        System.out.print("Host: "); String host = input.nextLine();
        System.out.print("Type: "); String type = input.nextLine();
        System.out.print("dateAtime (YYYYMMDDTTMM): "); double dateAtime = input.nextDouble();
        Event E = new Event(id,name, place, host, type, dateAtime);
        //register the event and show event details
        System.out.println(eventRegister.registerEvent(E));

    }

    public void eventsWithLocation(){
        input.nextLine();
        System.out.println("Location: "); String location = input.nextLine();
        System.out.println(eventRegister.eventsAt(location));
    }
    public void eventsWithDate(){

        System.out.println("Date (format: YYYYMMDD): "); double date = input.nextInt();
        System.out.println(eventRegister.eventsOn(date));
    }
    public void eventsBetweenDates(){
        System.out.println("From (format: YYYYMMDD): "); int from = input.nextInt();
        input.nextLine();
        System.out.println("To (format: YYYYMMDD): "); int to = input.nextInt();
        System.out.println(eventRegister.eventsBetweenDates(from, to));
    }
    public void allEventsSorted(){
        System.out.println("Type 1 to filter by location");
        System.out.println("Type 2 to filter by event type");
        System.out.println("Type 3 to filter by time");
        int in = input.nextInt();

        printArray(eventRegister.sortBy(in));
    }

    public void printArray(ArrayList<Event> list) {
        System.out.println("");
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i).toString());
            }
        } else {
            System.out.println("No events found");
        }
    }
}
