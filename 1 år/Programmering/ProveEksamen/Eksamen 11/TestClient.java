import java.util.Scanner;

public class TestClient {

    private Scanner input = new Scanner(System.in);

    //create the propertyRegister class
    PropertyRegister properties = new PropertyRegister();



    public static void main(String[] args) {
        TestClient TC = new TestClient();
        while(true){
            TC.showMenu();
        }
    }

    private void showMenu(){
        System.out.println("press 1 to register a property, 6 same with hash maps");
        System.out.println("press 2 to show the average area of the properties, 7 same with hash maps");
        System.out.println("press 3 to search  for a property, 8 same with hash maps");
        System.out.println("press 4 to show all properties listed, 9 same with hash maps");
        System.out.println("press 5 to clear all properties, 10 same with hash maps");
        System.out.println("11 to remove a property, 12 to count properties listed");

        int choice = input.nextInt();

        switch (choice){
            case 1:
                System.out.println("type in municipality number: "); int mNum = input.nextInt();
                input.nextLine();
                System.out.println("type in municipality name: "); String mName = input.nextLine();
                System.out.println("type in lot number: "); int lotNum = input.nextInt();
                input.nextLine();
                System.out.println("type in section number: "); int secNum = input.nextInt();
                input.nextLine();
                System.out.println("type in area in m^2: "); float area = input.nextFloat();
                input.nextLine();
                System.out.println("type in owner name: "); String ownerName = input.nextLine();

                //spacing out the lines
                System.out.println("");
                //create a property based on user inputs then add it to the property list
                System.out.println(properties.registerProperty(mNum, mName, lotNum, secNum, area, ownerName));
                System.out.println("");
                break;

            case 2:
                System.out.println("the average property area is "+properties.averagePropertyArea());
                break;
            case 3:
                input.nextLine();
                System.out.println("type in knr, gnr and bnr. format(knr-gnr/bnr): "); String mls = input.nextLine();

                //check if it's not found. if not found then exit case 3
                if(properties.findProperty(mls) == null){
                    System.out.println("Propert with mls: "+mls+" could not be found");
                    break;
                }
                //if found then print out property details
                System.out.println(properties.findProperty(mls));
                break;
            case 4:
                System.out.println(properties.printProperties());
                break;
            case 5:
                properties.clearAllProperties();
                System.out.println("you have now cleared all properties");
                break;
            case 6:
                System.out.println("type in municipality number: "); int mNumH = input.nextInt();
                input.nextLine();
                System.out.println("type in municipality name: "); String mNameH = input.nextLine();
                System.out.println("type in lot number: "); int lotNumH = input.nextInt();
                input.nextLine();
                System.out.println("type in section number: "); int secNumH = input.nextInt();
                input.nextLine();
                System.out.println("type in area in m^2: "); float areaH = input.nextFloat();
                input.nextLine();
                System.out.println("type in owner name: "); String ownerNameH = input.nextLine();

                //spacing out the lines
                System.out.println("");
                //create a property based on user inputs then add it to the property list
                Property newProperty = new Property(mNumH, mNameH, lotNumH, secNumH, areaH, ownerNameH);
                properties.registerPropertyHashMap((mNumH+"-"+lotNumH+"/"+secNumH), newProperty);
                
                System.out.println(newProperty);
                break;
            case 7:
                System.out.println("Average area of the properties registered is: "+properties.
                        averagePropertyAreaHashMap()+"m^2");
                break;
            case 8:
                input.nextLine();
                System.out.println("put in mls key format(m-l/s): ");   String mlsKey = input.nextLine();
                if(properties.findPropertyHashMap(mlsKey) != null){
                    System.out.println(properties.findPropertyHashMap(mlsKey));
                    break;
                }
                System.out.println("Could not find a property with "+mlsKey);
                break;

            case 9:
                System.out.println(properties.printPropertiesHashMap());
                
            case 10:
                properties.clearAllPropertiesHashMap();
                System.out.println("The registered properties have been cleared");
                break;

            case 11:
                input.nextLine();
                System.out.println("type in mls key: "); String mlsK = input.nextLine();
                properties.removeProperty(mlsK);
                System.out.println(properties.printProperties());
                break;

            case 12:
                input.nextLine();
                System.out.println("there are currently "+properties.totalPropertiesRegistered()+
                        " properties registered");

        }

    }

}
