import java.util.ArrayList;

/**
 * @author Beka D. Bonsa
 * A class that stores and manages results of participants
 */
public class LongJumpRegister {

    ArrayList<LongJumpResult> results = new ArrayList<>();


    /**
     * registers a long jump result for a given participant
     * checks if time format is wrong and throws illegalargumentexception
     * @param startNumber
     * @param name
     * @param result
     * @param is_faul
     * @param time
     * @return boolean, true if registry was successful false if it already is registered
     * @throws IllegalArgumentException, the illegal argument exception if its incorrect time format
     */
    public boolean registerLongJump (String startNumber, String name, double result, boolean is_faul, String time) throws IllegalArgumentException{
        if(time.isEmpty() || !time.contains(":"))throw new IllegalArgumentException("Incorrect format for time.");
        LongJumpResult newLongJump = new LongJumpResult(startNumber, name, result, is_faul, time);
        if(results.contains(newLongJump)) return false;
        results.add(newLongJump);
        return true;
    }

    /**
     * prints all the results in the register by deep copying the object elements in results
     * @return ArrayList, results.
     */
    public ArrayList<LongJumpResult> printAllResults(){
        ArrayList<LongJumpResult> copyResults = new ArrayList<>();
        for(LongJumpResult r: results){
            copyResults.add(new LongJumpResult(r));
        }
        return copyResults;
    }

    /**
     * finds all the results a given participant has in the registry
     * @param name
     * @return ArrayList of all the results found
     */
    public ArrayList<LongJumpResult> find_Participant_Results(String name){
        ArrayList<LongJumpResult> participantResults = new ArrayList<>();
        for (LongJumpResult r: results){
            if(r.getName().equals(name)) participantResults.add(new LongJumpResult(r));
        }

        return participantResults;
    }

    /**
     * A method to find the best performance based on how far an athlete has jumped. find the longest jump
     * (multiple results if they were equal jumps)
     * the method checks for if a jump was flagged as a faul before considering it as a candidate for best jump
     * @return returns the best performance/s. ArrayList of results for best performance
     */
    public ArrayList<LongJumpResult> best_Performance(){
        double bestJump = 0;
        ArrayList<LongJumpResult> equalCompetitors = new ArrayList<>();


        for(LongJumpResult r: results){
            if(r.getResult()>bestJump && !r.is_faul()){
                bestJump = r.getResult();
            }
        }

        equalCompetitors = this.findResultsByJumpLength(bestJump);
        return equalCompetitors;
    }

    /**
     * a method that finds results with specified jump length;
     * @param jumpLength
     * @return ArrayList of found results with the specified jump length
     */
    public ArrayList<LongJumpResult> findResultsByJumpLength(double jumpLength){
        ArrayList<LongJumpResult> jumpResults = new ArrayList<>();
        for (LongJumpResult r: results){
            if (jumpLength == r.getResult() && !r.is_faul()) jumpResults.add(new LongJumpResult(r));
        }

        return jumpResults;
    }


    /**
     * calculates average jump of all the results in the register
     * the method only counts legitimate jump attempts and calculates the average accordingly
     * @return double average jump length
     */
    public double calculateAverageJump(){
        double sum = 0;
        int faultyAttempts = 0;

        for (LongJumpResult r: results){
            if(r.is_faul()){
                faultyAttempts++;
                continue;
            }
            sum+=r.getResult();
        }
        return sum/(double)(results.size()-faultyAttempts);

    }

    /**
     * toString ved hjelp av javas StringBuilder klasse.
     * @return String med resultatet
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for(LongJumpResult r: results){
            str.append((r+"\n"));
        }
        String result = str.toString();
        return result;
    }
}
