import java.util.ArrayList;

public class HearingAidCentral {
    private ArrayList<HearingAid> hearingAids = new ArrayList<>();

    /**
     * get method for the arraylist hearingAids
     * @return an array list of HearingAids
     */
    public ArrayList<HearingAid> getHearingAids(){
        return hearingAids;
    }

    /**
     * Add a hearing aid apparatus to the list
     * @param hearingAid
     * @return
     */
    public boolean registerHearingAid(HearingAid hearingAid) {
            if (hearingAids.contains(hearingAid)) return false;
            hearingAids.add(hearingAid);
            return true;
    }

    public boolean registerHearingAid(int id, String type, String ownerName){
        HearingAid aid = new HearingAid(id,type,ownerName);

        if(hearingAids.contains(aid)) return false;
        hearingAids.add(aid);
        return true;
    }

    /**
     * rent hearing aid with the use of hearing aid id and the new owner's name
     * @param id
     * @param newOwnerName
     * @return text confirmation, successful or not successful.
     */
    public String rentHearingAid(int id, String newOwnerName){
        for(HearingAid H: hearingAids){
            if(H.getId() == id){
                if(H.is_Rented()) return "The specified hearing aid has already been rented.";
                else{
                    H.setOwnerName(newOwnerName);
                    H.setIs_Rented(true);
                    return newOwnerName+" has now rented the product.";
                }
            }
        }
        return "The ID: " + id + " could not be found";
    }

    /**
     * finds and terminates the loan of a hearing aid
     * @param id
     * @return confirmation as successful or not successful.
     */
    public String terminateLoan(int id){
        for (HearingAid H: hearingAids){
            if(id == H.getId()){
                if(!H.is_Rented()) return "The hearing aid with the id: "+H.getId()+" has not been rented yet.";
                H.setIs_Rented(false);
                H.setOwnerName("not rented");
                return H.getOwnerName()+"'s loan of the hearing aid with the id: " +H.getId()+
                        " has now been terminated.";
            }
        }
        return "The hearing aid with the id: "+id+"  could not be found.";
    }

    /**
     * prints all the hearing aids registered with their information.
     * @return all hearing aids in a string format
     */
    public String printAllAids(){
        if(hearingAids.isEmpty()) return "There are no registered hearing aids.";
        String newLine = System.getProperty("line.separator");//This will retrieve line separator dependent on OS.
        String printedList = "";
        for (HearingAid H: hearingAids){
            printedList+=(H.toString()+newLine);
        }
        return printedList;
    }

    /**
     * finds all the hearing aids with a specified type
     * @param type
     * @return list of all matching hearing aids with a given type
     */
    public ArrayList<HearingAid> findType(String type){
        ArrayList<HearingAid> matchingList = new ArrayList<>();
        for(HearingAid H: hearingAids){
            if(H.getType().equals(type)) matchingList.add(H);
        }
        return matchingList;
    }



}
