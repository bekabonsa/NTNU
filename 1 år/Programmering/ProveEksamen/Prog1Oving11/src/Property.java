import java.util.Objects;

/**
 *
 */
public class Property {

    private final int municipalityNumber;

    private final String municipalityName;

    private final int lotNumber;

    private final int sectionNumber;

    private String name;

    private double area;

    private String ownerName;

    /**
     * Constructor for a Property that requires all needed info about a property
     * @param municipalityNumber an int that represents the municipality that the property is in
     * @param municipalityName the name of the municipality that the property is in
     * @param lotNumber an int that represents the lot where the property is located
     * @param sectionNumber an int that represents the section that the property is in
     * @param name the name of the property
     * @param area double that represents the size of the property in m2
     * @param ownerName name of the owner of the property
     * @throws IllegalArgumentException thrown if the Municipality number is outside of the interval 101 to 5054
     */
    public Property(int municipalityNumber, String municipalityName,
                    int lotNumber, int sectionNumber, String name,
                    double area, String ownerName)  throws IllegalArgumentException{

        if(!(101 <= municipalityNumber && municipalityNumber <=5054)){
            throw new IllegalArgumentException("Municipality number must be between 101 and 5054");
        }
        this.municipalityNumber = municipalityNumber;
        this.municipalityName = municipalityName;
        this.lotNumber = lotNumber;
        this.sectionNumber = sectionNumber;
        this.name = name;
        this.area = area;
        this.ownerName = ownerName;
    }
    public Property(int municipalityNumber, String municipalityName,
                    int lotNumber, int sectionNumber,
                    double area, String ownerName) throws IllegalArgumentException{

        this(municipalityNumber, municipalityName, lotNumber, sectionNumber, "This property has no name",
                area, ownerName);

    }

    public Property(int municipalityNumber, int lotNumber, int sectionNumber)
            throws IllegalArgumentException{
        this(municipalityNumber, null, lotNumber, sectionNumber,
                null,
                -1, null);

    }

    //Copy constructor
    public Property(Property property)
            throws IllegalArgumentException{
        this(property.getMunicipalityNumber(), property.getMunicipalityName(),
                property.getLotNumber(), property.getSectionNumber(), property.getName(),property.getArea(), property.getOwnerName() );

    }
    public int getMunicipalityNumber() {
        return municipalityNumber;
    }

    /**
     * Method to retrieve the municipality name of a property
     * @return the municipality name as a String
     */
    public String getMunicipalityName() {
        return municipalityName;
    }

    public int getLotNumber() {
        return lotNumber;
    }

    public int getSectionNumber() {
        return sectionNumber;
    }

    public String getName() {
        return name;
    }

    /**
     * Method to change the name of the property
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getPropertyId(){
        return this.municipalityNumber + "-" + this.lotNumber + "/" +this.sectionNumber;
    }

    @Override
    public String toString() {
        return "Property{" +
                "municipalityNumber=" + municipalityNumber +
                ", municipalityName='" + municipalityName + '\'' +
                ", lotNumber=" + lotNumber +
                ", sectionNumber=" + sectionNumber +
                ", name='" + name + '\'' +
                ", area=" + area +
                ", ownerName='" + ownerName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Property property = (Property) o;
        return this.getPropertyId().equals(property.getPropertyId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(municipalityNumber, municipalityName, lotNumber, sectionNumber, name, area, ownerName);
    }
}
