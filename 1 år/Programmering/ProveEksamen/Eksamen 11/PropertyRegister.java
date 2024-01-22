import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;


/**
 *  a class that has the ability to add and analyze properties
 *
 */
public class PropertyRegister {


    private ArrayList<Property> properties = new ArrayList<>();
    private HashMap<String, Property> propertiesHashMap = new HashMap<>();
    private HashSet<Property> propertiesHashSet = new HashSet<>();



    /**
     * Register a property, make it return void next time(didnt need a return type after all)
     * @param municipalityNum
     * @param municipalityName
     * @param lotNum
     * @param sectionNum
     * @param area
     * @param ownerName
     * @return
     */
    public Property registerProperty (int municipalityNum, String municipalityName, int lotNum, int sectionNum,
                                     float area, String ownerName){

        Property newProperty = new Property(municipalityNum, municipalityName, lotNum,sectionNum, area, ownerName);
        properties.add(newProperty);
        return new Property(municipalityNum, municipalityName, lotNum,sectionNum, area, ownerName);

    }

    /**
     * registers properties and stores it in the hash map with the key being mls and the value being a property
     * @param mls, type String. municipalityNum+"-"+lotNum+"/"+sectionNum
     * @param property, type Property
     * @return returnProperty, returns the property registered without the key
     */
    public Property registerPropertyHashMap(String mls, Property property){

        propertiesHashMap.put(mls, property);
        Property returnProperty = new Property(propertiesHashMap.get(mls));
        return returnProperty;
    }

    /**
    public Property registerPropertyHashList(int municipalityNum, String municipalityName, int lotNum, int sectionNum,
                                             float area, String ownerName){
        propertiesHashSet.add()

    }
*/
    public HashMap<String, Property> printPropertiesHashMap(){
        return propertiesHashMap;
    }


    /**
     * prints out all the registered properties
     * @return returns an arraylist consisting of properties registered. each property is a type of Property
     */
    public ArrayList<Property> printProperties(){

        ArrayList<Property> newProperties = new ArrayList<>();
        Collections.copy(newProperties, properties);
        return newProperties;
    }

    public Property findPropertyHashMap(String mls){

        for(String key: propertiesHashMap.keySet()){
            if(key.equals(mls))
                return propertiesHashMap.get(mls);


        }
        return null;
    }


    /**
     * find a property with the help of municipality, lot and section number all in one.
     * @param mls, String with all three numbers combined
     * @return property of type Propert, returns the property that has those three numbers.
     */
    public Property findProperty(String mls){

        for(Property p: properties){
            if(mls.equals(p.getMls())) return p;
        }
        return null;
    }

    /**
     * finds the average property area from the properties registered in the hashmap
     * @return average area, (property sum / property count)
     */
    public double averagePropertyAreaHashMap(){
        double sum = 0.0;
        int propertyCount = 0;
        for(Property value: propertiesHashMap.values()){
           sum += value.getArea();
           propertyCount++;
        }
        return sum/propertyCount;
    }

    /**
     * finds the average property area from the properties registered in the array list
     * @return average area, (property sum / property count)
     */
    public double averagePropertyArea(){
        int propertyCount = 0;
        int sum = 0;
        for (Property p: properties){
            sum+=p.getArea();
            propertyCount++;
        }
        return sum/propertyCount;
    }

    /**
     * remove a property from the registered properties
     * @param mls
     */
    public void removeProperty(String mls){
        properties.remove(findProperty(mls));
    }

    /**
     * returns amount of propertiess registered
     * @return int property count
     */
    public int totalPropertiesRegistered(){
        return properties.size();
    }

    /**
     * find properties that have the specified lot Number
     * @param ln lotNumber
     * @return an arraylist of properties that match the lotnumber given
     */
    public ArrayList<Property> findPropertyWithLotNum(String ln){
        ArrayList<Property> propertiesLotNum = new ArrayList<>();
        for(Property p: properties){
            if(ln.equals(p.getLotNum())) propertiesLotNum.add(p);
        }
        return propertiesLotNum;
    }

    /**
     * clears all properties in the arraylist
     */

    public void clearAllProperties(){
        properties.clear();
        propertiesHashMap.clear();
    }

    /**
     * clears all properties in the hashmap
     */

    public void clearAllPropertiesHashMap(){
        propertiesHashMap.clear();
    }

}
