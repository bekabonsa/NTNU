import java.util.Scanner;

class Client{
    // Constants representing the different menu choices
    private final int ADD_HEARING_AID = 1;
    private final int LIST_ALL_HEARING_AIDS = 2;
    private final int CANCEL_LOAN = 3;
    private final int CHANGE_LOAN_STATUS = 4;
    // ---- add more constants as needed ---
    private final int EXIT = 9;
    private Scanner input = new Scanner(System.in);

    private HearingAidCentral hearingAidCentral = new HearingAidCentral();

    /**
     * Presents the menu for the user, and awaits input from the user. The menu
     * choice selected by the user is being returned.
     *
     * @return the menu choice by the user as a positive number starting from 1.
     * If 0 is returned, the user has entered a wrong value
     */
    private int showMenu()
    {
        int menuChoice = 0;
        System.out.println("\n***** Hearing Aid Central v0.1 *****\n");
        System.out.println("1. Add hearing aid");
        System.out.println("2. List all hearing aids");
        System.out.println("3. Cancel loan");
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

    public static void main(String[] args) {
        Client client = new Client();
        while(true){
            client.start();
        }
    }

    /**
     * Starts the application. This is the main loop of the application,
     * presenting the menu, retrieving the selected menu choice from the user,
     * and executing the selected functionality.
     */
    public void start() {
        boolean finished = false;
// The while-loop will run as long as the user has not selected
// to quit the application
        while (!finished) {
            int menuChoice = this.showMenu();
            int id;
            switch (menuChoice)
            {
                case ADD_HEARING_AID:
                    System.out.println("Give the hearing aid an id:");
                    id = input.nextInt(); input.nextLine();

                    System.out.println("Specify the type of the hearing aid");
                    String type = input.nextLine();
                    System.out.println("Owner name (leave blank if it's not rented): ");
                    String ownerName = input.nextLine();

                    //checks if the function returns true or false, true for success false for already registered
                    if(hearingAidCentral.registerHearingAid(id, type, ownerName))
                    hearingAidCentral.registerHearingAid(id, type, ownerName);
                    else
                        System.out.println("The id for the product is already in use. please use a different id.");
                    break;

                case LIST_ALL_HEARING_AIDS:

                    System.out.println("NTNU-sentralen");
                    System.out.println("Registrerte hjelpemidler");
                    for(HearingAid H: hearingAidCentral.getHearingAids()){
                        System.out.println(H);
                    }
                    break;

                case CANCEL_LOAN:
                    System.out.println("Specify the id of the product you wish to cancel");
                    id = input.nextInt();
                    hearingAidCentral.terminateLoan(id);

                    break;

                case CHANGE_LOAN_STATUS:
                    System.out.println("Specify the id for the product you wish to change ownership for");
                    id = input.nextInt(); input.nextLine();
                    System.out.println("Transfer ownership to: ");
                    String newOwner = input.nextLine();
                    hearingAidCentral.rentHearingAid(id,newOwner);
                    break;
                case EXIT:
                    System.out.println("Thank you for using the Properties app!\n");
                    finished = true;
                    break;
                default:
                    System.out.println("Unrecognized option selected..");
                    break;
            }
        }
    }
}
