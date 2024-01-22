import javax.sound.sampled.Port;
import java.lang.Cloneable;
/**
 * the class that is responsible for defining what a property is.
 */
public class Property {

    private int municipalityNum;
    private String municipalityName;
    private int lotNum;
    private int sectionNum;
    private String name;
    private float area;
    private String ownerName;
    //mls m in mls = municipality number, l = lot number, s= section number
    private String mls;

    /**
     * constructor with all the classes attributes
     * @param municipalityNum
     * @param municipalityName
     * @param lotNum
     * @param sectionNum
     * @param name
     * @param area
     * @param ownerName
     */
    public Property(int municipalityNum, String municipalityName, int lotNum, int sectionNum, String name, float area
    , String ownerName){
        this.municipalityName = municipalityName;
        this.municipalityNum = municipalityNum;
        this.lotNum = lotNum;
        this.sectionNum = sectionNum;
        this.name = name;
        this.area = area;
        this.ownerName = ownerName;
        this.mls = sectionNum+"-"+lotNum+"/"+sectionNum;
    }

    /**
     * constructor with all class attributes except name, since not all properties have names.
     * @param municipalityNum
     * @param municipalityName
     * @param lotNum
     * @param sectionNum
     * @param area
     * @param ownerName
     */
    public Property(int municipalityNum, String municipalityName, int lotNum, int sectionNum, float area
            , String ownerName){

        this.municipalityName = municipalityName;
        this.municipalityNum = municipalityNum;
        this.lotNum = lotNum;
        this.sectionNum = sectionNum;
        this.area = area;
        this.ownerName = ownerName;
        this.mls = sectionNum+"-"+lotNum+"/"+sectionNum;
    }

    public Property(Property p){
        this.municipalityName = p.getMunicipalityName();
        this.municipalityNum = p.getMunicipalityNum();
        this.lotNum = p.getLotNum();
        this.sectionNum = p.getSectionNum();
        this.area = p.getArea();
        this.ownerName = p.getOwnerName();
        this.mls = p.getMls();
    }

    public String getName() {
        return name;
    }

    public float getArea() {
        return area;
    }

    public int getLotNum() {
        return lotNum;
    }

    public int getMunicipalityNum() {
        return municipalityNum;
    }

    public int getSectionNum() {
        return sectionNum;
    }

    public String getMls() {
        return mls;
    }

    public String getMunicipalityName() {
        return municipalityName;
    }

    public String getOwnerName() {
        return ownerName;
    }



    /**
     * in case the owner of the property changes his/her name
     * @param ownerName
     */
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String formattedNumber(){
        return (municipalityNum+"-"+lotNum+"/"+sectionNum);
    }

    @Override
    public String toString() {
        return "Property{" +
                "municipalityNum=" + municipalityNum +
                ", municipalityName='" + municipalityName + '\'' +
                ", lotNum=" + lotNum +
                ", sectionNum=" + sectionNum +
                ", name='" + name + '\'' +
                ", area=" + area +
                ", ownerName='" + ownerName + '\'' +
                ", mls='" + mls + '\'' +
                '}';
    }
}
