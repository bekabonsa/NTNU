import java.util.ArrayList;

public class Menu {

    private int menuId;
    private int totalPrice = 0;
    ArrayList<Dish> dishes = new ArrayList<Dish>();

    public Menu(ArrayList<Dish> dishes){
        this.dishes = dishes;
    }

    public Menu(){

    }

    public void addDish(Dish dish){
        dishes.add(dish);
    }

    public int totalMenuPrice(){
        int price = 0;
        for(Dish D: dishes){
            price+=D.getPrice();
        }
        totalPrice = price;
        return price;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "menuId=" + menuId +'\'' +
                ", totalPrice=" + totalPrice +'\'' +
                ", dishes=" + dishes +'\'' +
                '}';
    }
}
