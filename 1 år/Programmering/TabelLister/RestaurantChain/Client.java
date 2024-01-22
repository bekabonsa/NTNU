import java.util.*;

public class Client {

    private Scanner input = new Scanner(System.in);
    MenuRegister Menus_and_dishes = new MenuRegister();
    ArrayList<Dish> newDishes = new ArrayList<>();
    ArrayList<Dish> menuDishes = new ArrayList<>();

    public static void main(String[] args) {

        Client c = new Client();

        while(true){
            c.menu();
        }

    }

    public void menu(){
        System.out.println("");
        System.out.println("type 1 to register a dish");
        System.out.println("type 2 to find a dish with a name");
        System.out.println("type 3 to find all dishes with a specified type");
        System.out.println("type 4 to register a menu with dishes");
        System.out.println("type 5 to see all menus within a total price range");
        System.out.println("type 6 to show all menus");

        int in = input.nextInt();
        switch (in){
            case 1:
                registerDish();
                break;
            case 2:
                dishWithName();
            case 3:
                dishWithType();
                break;
            case 4:
                registerMenu();
                break;
            case 5:
                sortedMenuPrice();
                break;
            case 6:
                 showMenus();
        }

    }

    public void registerDish(){
        input.nextLine();
        System.out.println("name of dish: "); String name = input.nextLine();
        System.out.println("price of dish: "); int price = input.nextInt();
        input.nextLine();
        System.out.print("dish type (appetizer, desert, main course etc: "); String type = input.nextLine();
        System.out.print("recipe (optional): "); String recipe = input.nextLine();
        Dish D = new Dish(name, type,price,recipe);
        //register the dish
        Menus_and_dishes.registerDish(D);
        //show the dish for the user
        System.out.println(D);
        //keep track of newly created dishes for later, if needed
        newDishes.add(D);

    }

    public void dishWithName(){
        input.nextLine();
        System.out.println("Dish name: "); String name = input.nextLine();
        System.out.println(Menus_and_dishes.findDish(name));
    }

    public void dishWithType(){
        input.nextLine();
        System.out.println("dish type: "); String type = input.nextLine();
        printArray(Menus_and_dishes.dishType(type));
    }

    public void registerMenu(){
        input.nextLine();
        boolean exit = false;
        menuDishes.clear();
        while(!exit) {
            System.out.println("type 1 to add recently added dishes to the menu");
            System.out.println("type 2 to manually add dishes to the menu");
            System.out.println("type 3 to stop adding dishes to the menu");

            int choice = input.nextInt();

            switch (choice) {
                case 1:
                    Menu menu = new Menu(newDishes);
                    Menus_and_dishes.registerMenu(menu);
                    break;
                case 2:
                    input.nextLine();
                    System.out.println("name of dish: "); String name = input.nextLine();
                    System.out.println("price of dish: "); int price = input.nextInt();
                    input.nextLine();
                    System.out.print("dish type (appetizer, desert, main course etc: "); String type = input.nextLine();
                    System.out.print("recipe (optional): "); String recipe = input.nextLine();
                    Dish D = new Dish(name, type,price,recipe);
                    //register the dish
                    Menus_and_dishes.registerDish(D);
                    //show the dish for the user
                    System.out.println(D);
                    //keep track of newly created dishes to add when pressing 3
                    menuDishes.add(D);
                    break;
                case 3:
                    Menu menuNew = new Menu(menuDishes);
                    //register and show dishes in the newly created menu
                    printArray(Menus_and_dishes.registerMenu(menuNew).dishes);
                    exit = !exit;
                    break;
                default:
                    System.out.println("Type in a number");
            }

        }
    }


    public void sortedMenuPrice(){
        System.out.println("price range from");
        int rangeMin = input.nextInt();
        input.nextLine();
        System.out.println("price range to");
        int rangeMax = input.nextInt();

        //filter then show menus in the given price range
        System.out.println(Menus_and_dishes.menusInPriceRange(rangeMin,rangeMax));
    }


    public void showMenus(){
        printArrayM(Menus_and_dishes.getMenus());
    }


    public void printArray(ArrayList<Dish> list) {
        System.out.println("");
        if (list.size() > 0) {
            System.out.println("");
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i).toString()+"\n");
            }
        } else {
            System.out.println("No dishes found");
        }
    }

    public void printArrayM(ArrayList<Menu> list) {
        System.out.println("");
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i).toString()+"\n");
            }
        } else {
            System.out.println("No menus found");
        }
    }
}
