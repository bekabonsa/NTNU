
import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.SortedMap;


/**
 * Represents the text based user interface of the application.
 * This class makes use of the Scanner-class to get input from the
 * user via the standard console.
 *
 * To be used by the students in Ålesund and Gjøvik (IDATA1001/IDATG1001)
 *
 * @author arne
 * @version 2021-12-02
 */

public class LongJumpUI {

    //TODO: Add your fields here
    private static final String VERSION = "v1.0-SNAPSHOT";
    private LongJumpRegister register = new LongJumpRegister();
    private Scanner input = new Scanner(System.in);

    String[] menuItems
            = {
                "1. Register a long jump result",
                "2. List all results",
                "3. Show all results by a given athlete",
                "4. Show the best result",
                "5. Calculate the avarage result"
            };

    // Constants defining the different menu options, to be used in the
    // switch-case.
    private static final int ADD_RESULT = 1;
    private static final int LIST_ALL_RESULTS = 2;
    private static final int SHOW_RESULT_BY_ATHLETE = 3;
    private static final int SHOW_BEST_RESULT = 4;
    private static final int CALCULATE_AVERAGE_RESULT = 5;
    private static final int EXIT = 6;

    /**
     * Creates an instance of the LongJumpUI User interface.
     */
    public LongJumpUI() {
        //TODO: Add your own constructor code here
    }

    /**
     * Starts the application by showing the menu and retrieving input from the
     * user. Continues until the user decides to exit the application.
     */
    void start() {

        boolean quit = false;

        while (!quit) {
            int menuSelection = this.getMenuChoice();
            switch (menuSelection) {
                case ADD_RESULT:
                    boolean is_faul;
                    System.out.println("Start Number: "); String startNumber = input.nextLine();
                    System.out.println("Name: "); String name = input.nextLine();
                    System.out.println("Jump length in meters"); double result = input.nextDouble(); input.nextLine();
                    System.out.println("Was the attempt flagged as faul? (y/n): "); String faul = input.nextLine();
                    if(faul.trim().equals("y")){
                        is_faul = true;
                    }
                    else{
                        is_faul = false;
                    }
                    System.out.println("Time (hh:mm): "); String time = input.nextLine();

                    //if a result is already registered
                    if(!register.registerLongJump(startNumber, name, result, is_faul, time)){
                        System.out.println("The following result has already been registered");
                    }
                    else{
                        register.registerLongJump(startNumber, name, result, is_faul, time);
                    }
                    break;

                case LIST_ALL_RESULTS:
                    System.out.println(register);
                    break;

                case SHOW_RESULT_BY_ATHLETE:
                    System.out.println("Athlete name: "); String participantName = input.nextLine();
                    System.out.println(register.find_Participant_Results(participantName));
                    break;

                case SHOW_BEST_RESULT:
                    System.out.println("The best result/s is/are: "+register.best_Performance());
                    break;

                case CALCULATE_AVERAGE_RESULT:
                    System.out.println(register.calculateAverageJump()+" meters was the average jump result");
                    break;

                case EXIT:
                    System.out.println("\nThank you for using the Long Jump Application "
                            + VERSION + ". Bye!\n");
                    quit = true;
                    break;

                default:
                    System.out.println(
                            "\nERROR: Please provide a number between 1 and " + this.menuItems.length + "..\n");
            }
        }
    }

    /**
     * Displays the menu to the user, and waits for the users input. The user is
     * expected to input an integer between 1 and the max number of menu items..
     * The method returns the input from the user. If the input from the user is
     * invalid, 0 is returned.
     *
     * @return the menu number (between 1 and max menu item number) provided by
     * the user.
     */
    private int getMenuChoice() {
        int menuSelection = 0;

        System.out.println("\n**** Long Jump Results Tool " + VERSION + " ****\n");
        for (String menuItem : menuItems) {
            System.out.println(menuItem);
        }
        int maxMenuItemNumber = menuItems.length + 1;
        System.out.println(maxMenuItemNumber + ". Exit\n");
        System.out.println("Please choose menu item (1-" + maxMenuItemNumber + "): ");

        Scanner reader = new Scanner(System.in);
        if (reader.hasNextInt()) {
            menuSelection = reader.nextInt();
        } else {
            System.out.println("You must enter a number, not text");
        }
        return menuSelection;
    }

    /**
     * The main start method of the application.
     *
     * @param args Commandline arguments as an array of String
     */
    public static void main(String[] args) {
        LongJumpUI longJumpUI = new LongJumpUI();
        longJumpUI.start();
    }

}
