public class Dish {
    private String name;
    private String type;
    private int price;
    private String recipe;

    public Dish(String name, String type, int price, String recipe){
        this.name = name;
        this.type = type;
        this.price = price;
        this.recipe = recipe;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getRecipe() {
        return recipe;
    }

    public String getType() {
        return type;
    }


    @Override
    public String toString() {
        return "Dish{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", recipe='" + recipe + '\'' +
                '}';
    }
}
