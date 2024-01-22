import java.util.ArrayList;

public class MenuRegister {
    private ArrayList<Menu> menus = new ArrayList<Menu>();
    private ArrayList<Dish> dishes = new ArrayList<Dish>();


    public ArrayList<Dish> getDishes() {
        return dishes;
    }

    public ArrayList<Menu> getMenus() {
        return menus;
    }

    /**
     * register dish and add it to the dishes list
     * @param dish, Dish type
     */
    public void registerDish(Dish dish){
        dishes.add(dish);
    }

    /**
     * find dish by name
     * @param dish, String dish (name of dish)
     * @return return Dish with the name of dish, assuming each dish has a unique name;
     */
    public Dish findDish(String dish){
        for(Dish d : dishes) {
            if (d.getName().equals(dish)) return d;
        }
        return null;
    }

    /**
     * filter dishes by their type
     * @param type, String type: type of dish
     * @return ArrayList<Dish> all the dishes with the specified "type" type
     */
    public ArrayList<Dish> dishType(String type){
        ArrayList<Dish> filterDish = new ArrayList<>();
        for(Dish d : dishes) {
            if (d.getType().equals(type)) filterDish.add(d);
        }
        return filterDish;
    }

    /**
     * register a menu
     * @param menu, Menu type
     */
    public Menu registerMenu(Menu menu){
        menus.add(menu);
        return menu;
    }


    /**
     * find menus with the total price between given price-range
     * @return ArrayList<Menu>, list of menus that are within the price range
     */
    public ArrayList<Menu> menusInPriceRange(int from, int to){

            ArrayList<Menu> filteredMenus = new ArrayList<>();

            for(Menu M: menus){
                if(M.totalMenuPrice()>=from && M.totalMenuPrice()<=to) filteredMenus.add(M);
            }
            return filteredMenus;

    }

}
