import java.util.Date;
import java.util.Random;

public class Kurs {

    public static void main(String[] args) {
        int stocks[] = {-1, 3, -9, 2, 2, -1, 2, -1, -5};
        int randStocks[] = generateArrayWithSize(100);
        int[] result;

        Date start = new Date();
        int runder = 0;
        double tid;
        Date slutt;
        do {
            result = maximizeReturn(randStocks);
            slutt = new Date();
            ++runder;
        } while (slutt.getTime()-start.getTime() < 1000);
        tid = (double) (slutt.getTime()-start.getTime()) / runder;

        System.out.println("buy day: " + result[0] + ", sell day: " + result[1] + ", profit: " + result[2]+", time" +
                " usage per round: "+(tid));
        //System.out.println(Arrays.toString(randStocks));


    }

    public static int[] maximizeReturn(int stocks[]) {

        int temporaryMax = 0;
        int buyDate = 0;
        int sellDate = 0;
        for (int i = 0; i < stocks.length - 1; i++) {    //runs n times
            int profit = 0; // n
            for (int j = i + 1; j < stocks.length; j++) {  //runs n-1 times
                 profit +=  stocks[j];
                if (profit > temporaryMax)  {
                    temporaryMax = profit;
                    buyDate = i + 1;      //+1 so that we avoid saying day 0
                    sellDate = j + 1;
                }
            }
        }
        return new int[]{buyDate, sellDate, temporaryMax};
    }


    public static int[] generateArrayWithSize(int n){
        Random random = new Random();
        int[] array = new int[n];
        for(int i = 0; i<n;i++){
            array[i] = random.nextInt(21)-10;
        }
        return array;
    }



}


