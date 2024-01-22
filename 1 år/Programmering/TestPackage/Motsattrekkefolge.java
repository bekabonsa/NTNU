package TestPackage;

import java.util.Arrays;

public class Motsattrekkefolge {

    public static void main(String[] args){
        int[] tab1 = {1,4,6,-2};
        int[] tab2 = {1,4,6,-2};
        int indeks = 0;
        for (int i = tab1.length-1; i >= 0; i--){

            tab2[indeks++] = tab1[i];
            System.out.println(tab2[indeks-1]);

        }
        System.out.println(Arrays.asList(tab2).indexOf(4));
        //Array.copyofrang java.util.Arrays

    }

}
