public class Casting {

    public static void main(String[] args) {
        Animal animal = new Animal();
        animalAction(animal);
    }

    public static void animalAction(Animal animal) {
        animal.makeNoise();
        if(animal instanceof Dog){
            Dog dog = (Dog) animal;
            dog.growl();
        }

    }
}


