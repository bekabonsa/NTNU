import java.util.Scanner;

public class Client {
    // Constants representing the different menu choices
    private final static int ADD_HEARING_AID = 1;
    private final static int LIST_ALL_HEARING_AIDS = 2;
    private final static int NEW_PERSON_RENTING = 3;
    private final static int CANCEL_RENTING = 4;

    // ---- add more constants as needed ---
    private final static int EXIT = 9;

    public static void main(String[] args) {
        start();
    }

    /**
     * Presents the menu for the user, and awaits input from the user. The menu
     * choice selected by the user is being returned.
     *
     * @return the menu choice by the user as a positive number starting from 1.
     * If 0 is returned, the user has entered a wrong value
     */
    private static int showMenu()
    {
        int menuChoice = 0;
        System.out.println("\n***** Hearing Aid Central v0.1 *****\n");
        System.out.println("1. Add hearing aid");
        System.out.println("2. List all hearing aids");
        System.out.println("3. Register a new leieforhold"); //TODO translate
        System.out.println("4. Cancel renting a hearing aid");
        //TODO: Add more menus

        System.out.println("9. Quit"); // Or another number than 9
        System.out.println("\nPlease select from the menu.\n");
        Scanner sc = new Scanner(System.in);
        if (sc.hasNextInt())
        {
            menuChoice = sc.nextInt();
        } else
        {
            System.out.println("You must enter a number, not text");
        }
        return menuChoice;
    }

    /**
     * Starts the application. This is the main loop of the application,
     * presenting the menu, retrieving the selected menu choice from the user,
     * and executing the selected functionality.
     */
    public static void start() {
        Scanner sc = new Scanner(System.in);
        HearingAidCentral central = new HearingAidCentral("Williams central");
        boolean finished = false;
        while (!finished) {
            int menuChoice = showMenu();
            switch (menuChoice)
            {
                case ADD_HEARING_AID:
                    System.out.println("Please enter a number between 1001-9999:"); //TODO here we should check that the number actually is in the specified interval
                    int id = sc.nextInt();
                    while(id < 1001 || id > 9999){
                        System.out.println("You did not enter a valid ID, must be in the interval 1001-9999:");
                        id = sc.nextInt();
                    }
                    sc.nextLine();
                    System.out.println("Please enter the hearing aid type");
                    String type = sc.nextLine();
                    System.out.println("Please enter the name of the person renting a hearing aid, leave blank if no one are renting it");
                    String name = sc.nextLine();
                    HearingAid aid = null;
                    if(name.isBlank()){
                        try{
                            aid = new HearingAid(id, type);
                        }catch (IllegalArgumentException e){
                            System.out.println(e.getMessage());
                        }


                    }else{
                        aid = new HearingAid(id, type, name); //Add exception handling
                    }
                    central.registerHearingAid(aid); //TODO may throw an exception, catch it in later development
                    break;
                case LIST_ALL_HEARING_AIDS:
                    System.out.println(central.toString());
                    break;
                case NEW_PERSON_RENTING:
                    System.out.println("Here are all the hearing aids: \n" + central.toString());
                    System.out.println("Enter the hearing aid ID for the hearing aid you want to rent");
                    id = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter your name");
                    name = sc.nextLine();
                    if(central.renOut(name, id)){
                        System.out.println(name + " successfully rented the hearing aid with id " + id);
                    }else{
                        System.out.println("The hearing aid with id " + id + " was not in the register or someone else is already renting it");
                    }
                    break;
                case CANCEL_RENTING:
                    System.out.println("Enter the hearing aid ID");
                    id = sc.nextInt();
                    boolean canceled = central.returnHearingAid(id);
                    if(canceled){
                        System.out.println("The hearing aid with ID " + id + " is now available for renting");
                    }else{
                        System.out.println("The hearing aid with ID " + id + " is already available or it does not exist in the register");
                    }
                    break;
                case EXIT:
                    System.out.println("Thank you for using the Hearing Aid Central app!\n");
                    finished = true;
                    break;
                default:
                    System.out.println("Unrecognized menu selected..");
                    break;
            }
        }
    }
}
