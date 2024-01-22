import java.util.ArrayList;

public class PropertyRegister {
    private ArrayList<Property> properties;

    public PropertyRegister() {
        this.properties = new ArrayList<>();
    }

    public ArrayList<Property> getProperties() {
        ArrayList<Property> propertiesCopy = new ArrayList<>();
        for (Property property: properties) {
            propertiesCopy.add(new Property(property));
        }
        return propertiesCopy;
    }

    public boolean addProperty(int municipalityNumber, String municipalityName,
                               int lotNumber, int sectionNumber, String name,
                               double area, String ownerName) {
        Property property;
        if (name.isEmpty()) {
            property = new Property(municipalityNumber, municipalityName, lotNumber, sectionNumber, area, ownerName);
        } else {
            property = new Property(municipalityNumber, municipalityName, lotNumber, sectionNumber, name, area, ownerName);
        }
        if (!properties.contains(property)) {
            this.properties.add(property);
            return true;
        }
        return false;
    }
    public Property findProperty(int municipalityNumber, int lotNumber, int sectionNumber){
        Property propertyToFind = new Property(municipalityNumber, lotNumber, sectionNumber);
        for (Property property: properties) {
            if(property.equals(propertyToFind)){
                return new Property(property);
            }
        }
        return null;
    }

    public boolean deleteProperty(int municipalityNumber, int lotNumber, int sectionNumber){
        Property property = findProperty(municipalityNumber, lotNumber, sectionNumber);
        if(property != null){
            properties.remove(property);
            return true;
        }
        return false;
    }

    public int getSize(){
        return this.properties.size();
    }

    public double getAvarageArea(){
        double sum = 0;
        for (Property property: properties) {
            sum += property.getArea();
        }
        return sum / properties.size();
    }

    public ArrayList<Property> findPropertiesByLotNumber(int lotNumber){
        ArrayList<Property> foundProperties = new ArrayList<>();
        for (Property property: properties){
            if(property.getLotNumber() == lotNumber){
                foundProperties.add(new Property(property));
            }
        }
        return foundProperties;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Property property : properties){
            sb.append(property).append("\n");
        }
        return sb.toString();
    }

}
