import java.util.Random;

public class QuickSort {

    public static void main(String[] args) {

    }
    public static void quicksort(int []t, int v, int h){
        if(h-v > 2){
            int delepos = split(t,v,h);
            quicksort(t,v,delepos-1);
            quicksort(t,delepos+1,h);
        }
        else median3sort(t,v,h);
    }
    public static int split(int[] t, int v, int h) {
        int iv;
        int ih;
        int m = median3sort(t, v, h);
        int dv = t[m];
        ArrayManager.swap(t, m, h - 1);
        for(iv = v, ih = h-1;;){
            while(t[++iv] < dv);
            while(t[--ih] > dv);
            if(iv >= ih) break;
            ArrayManager.swap(t,iv,ih);
        }
        ArrayManager.swap(t,iv,h-1);
        return iv;
    }

    private static int median3sort(int[] t, int v, int h) {
        int m = (v+h)/2;
        if(t[v] > t[m]) ArrayManager.swap(t,v,m);
        if(t[m] > t[h]){
            ArrayManager.swap(t, m, h);
            if(t[v] > t[m]) ArrayManager.swap(t, v, m);
        }
        return m;
    }





}



