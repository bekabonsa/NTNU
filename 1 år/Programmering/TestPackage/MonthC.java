package TestPackage;

import java.util.Arrays;

public class MonthC {
    String monthName;
    int[] rain;



    public MonthC(String monthName, int[] rain){

        this.monthName = monthName;
        //shallow copy
        //this.rain = rain;

        //deep copy
        for(int i = 0; i < rain.length; i++){
            this.rain[i]=rain[i];
        }

    }

    public String getMonthName(){
        return monthName;
    }

    public int getNrOfRainyDays(){
        return rain.length;
    }

        public int findNumberOfDaysMax(){
        int counter = 0;
        int max = findMax();
        for(int i = 0; i < rain.length;i++){
            if (rain[i] == max) counter ++;
        }
        return  counter;
        }

    private int findMax() {
        return 1;
    }

    public void findNumberOfDaysLess(int limit){
        int counter = 0;
    }

    public void findDeviation(){
        if(rain.length>1){
            int average = findAverage();
            long sumSquared = 0;
            for (int i = 0; i<rain.length; i++){
                sumSquared += (long) (rain[i] - average) *(rain[i]);
            }
        }
    }

    private int findAverage() {
        return 1;
    }

    public int findValue(int value){
    return Arrays.binarySearch(rain, value);
    }
}
