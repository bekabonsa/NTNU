

public class Shroom {

    private final String name;
    private final String description;
    private final boolean poisonous;

    public Shroom(String name, String description, boolean poisonous){
        this.name = name;
        this.description = description;
        this.poisonous = poisonous;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isPoisonous(){
        return poisonous;
    }

    @Override
    public String toString() {
        return "MushroomRegister{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", poisonous=" + poisonous +
                '}';
    }
}
