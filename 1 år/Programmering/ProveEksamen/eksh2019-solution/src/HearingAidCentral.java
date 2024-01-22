import java.util.ArrayList;

public class HearingAidCentral {

    private String name;
    private ArrayList<HearingAid> register = new ArrayList<>();

    public HearingAidCentral(String name) {
        this.name = name;
    }


    /**
     * register a hearing aid into the register
     * @param aid
     * @return true or false, true for successfully registered objects and false if it already exists in the list
     */
    public boolean registerHearingAid(HearingAid aid){
        if(register.contains(aid)) return false;
        register.add(aid);
        return true;
    }

    /**
     * register a hearing aid using necessary information
     * @param id
     * @param type
     * @param ownerName
     * @return true for success, false when it already is registered.
     */
    public boolean registerHearingAid(int id, String type, String ownerName){

       HearingAid aid = new HearingAid(0,"NULL","NULL");

        if(ownerName.trim().isEmpty()) aid = new HearingAid(id, type);
        else{
            new HearingAid(id, type, ownerName);
        }

        if(register.contains(aid)) return false;
        register.add(aid);
        return true;
    }

    public boolean renOut(String name, int aidID){
        for(HearingAid a : register){
            if(a.getID() == aidID){
                return a.rentOut(name);
            }
        }
        return false;
    }

    public boolean returnHearingAid(int aidId){
        for(HearingAid a : register){
            if (a.getID() == aidId){
                return a.deliver();
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder info = new StringBuilder(name + "\n");
        for(HearingAid a : register){
            info.append(a.toString()).append("\n");
        }
        return info.toString();
    }

    public ArrayList<HearingAid> getHearingAidsOfType(String type){
        ArrayList<HearingAid> available = new ArrayList<>();
        for(HearingAid a : register) {
            if (a.isAvailable() && a.getTYPE().equals(type)) {
                available.add(a);
            }
        }
        return available;
    }
}
