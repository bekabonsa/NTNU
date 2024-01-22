import java.util.Scanner;

public class ClientProgram {

    private final PropertyRegister propertyRegister;

    private final Scanner input;

    private final int ADD_PROPERTY = 1;
    private final int LIST_ALL_PROPERTIES = 2;
    private final int FIND_PROPERTY = 3;
    private final int CALCULATE_AVERAGE_AREA = 4;
    private final int REMOVE_PROPERTY = 5;
    private final int EXIT = 6;

    public ClientProgram(){
        propertyRegister = new PropertyRegister();
        input = new Scanner(System.in);
    }

    public void testData() {
        propertyRegister.addProperty(1445, "Gloppen", 77, 631, "", 1017.6, "Jens Olsen");
        propertyRegister.addProperty(1445, "Gloppen", 77, 131, "Syningom", 661.3, "Nicolay Madsen");
        propertyRegister.addProperty(1445, "Gloppen", 75, 19, "Fugletun", 650.6, "Evilyn Jensen");
        propertyRegister.addProperty(1445, "Gloppen", 74, 188, "", 1457.2, "Karl Ove Bråten");
        propertyRegister.addProperty(1445, "Gloppen", 69, 47, "Høiberg", 1339.4, "Elsa Indregård");
    }

    public void registerNewProperty() {
        System.out.print("Enter municipality number: ");
        int municipalityNumber = input.nextInt();

        System.out.print("Enter municipality name: ");
        String municipalityName = input.next();
        input.nextLine();

        System.out.print("Enter lot number: ");
        int lotNumber = input.nextInt();

        System.out.print("Enter section number: ");
        int sectionNumber = input.nextInt();


        System.out.print("Enter name: ");
        String name = input.next();
        input.nextLine();

        System.out.print("Enter area in m2: ");
        double area = input.nextDouble();

        System.out.print("Enter owner name: ");
        String ownerName = input.next();
        input.nextLine();

        System.out.println(propertyRegister.addProperty(municipalityNumber, municipalityName, lotNumber, sectionNumber, name, area, ownerName));
    }

    public void getAllProperties() {
        for (Property property : propertyRegister.getProperties()) {
            System.out.println(property);
        }
    }

    public void findProperty() {
        System.out.print("Enter municipality number: ");
        int municipalityNumber = input.nextInt();

        System.out.print("Enter lot number: ");
        int lotNumber = input.nextInt();

        System.out.print("Enter section number: ");
        int sectionNumber = input.nextInt();

        System.out.println(propertyRegister.findProperty(municipalityNumber, lotNumber, sectionNumber));
    }

    public void calculateAverageArea() {
        System.out.println(propertyRegister.getAvarageArea() + "m2");
    }

    public void removeProperty() {
        System.out.print("Enter municipality number: ");
        int municipalityNumber = input.nextInt();

        System.out.print("Enter lot number: ");
        int lotNumber = input.nextInt();

        System.out.print("Enter section number: ");
        int sectionNumber = input.nextInt();

        boolean isRemoved = propertyRegister.deleteProperty(municipalityNumber, lotNumber, sectionNumber);

        if (isRemoved) {
            System.out.println("Property removed.");
        } else {
            System.out.println("Property to remove not found.");
        }
    }

    private void showMenu() {
        int menuChoice = 0;

        System.out.println("\n***** Property Register Application v0.1 *****\n");
        System.out.println("1. Add Property");
        System.out.println("2. List all Properties");
        System.out.println("3. Search for Property");
        System.out.println("4. Calculate average area");
        System.out.println("6. Quit");
        System.out.println("\nPlease enter a number between 1 and 6.");

        if (input.hasNextInt()) {
            menuChoice = input.nextInt();
        } else {
            System.out.println("You must enter a number, not text");
        }

        switch (menuChoice) {
            case ADD_PROPERTY:
                registerNewProperty();
                break;
            case LIST_ALL_PROPERTIES:
                getAllProperties();
                break;
            case FIND_PROPERTY:
                findProperty();
                break;
            case CALCULATE_AVERAGE_AREA:
                calculateAverageArea();
                break;
            case REMOVE_PROPERTY:
                removeProperty();
            case EXIT:
                System.out.println("Thank you for using the Real estate app!\n");
                System.exit(0);
                break;
            default:
                System.out.println("Unrecognized menu choice selected..");
                break;
        }
    }

    public static void main(String[] args) {
        ClientProgram ClientProgram = new ClientProgram();
        ClientProgram.testData();

        while (true) {
            ClientProgram.showMenu();
        }
    }
}
