import java.net.SocketOption;
import java.util.Scanner;
import java.util.Arrays;


public class Matriseklient {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("Enter the number of rows: ");
        int r = input.nextInt();

        System.out.println("Enter the number of columns: ");
        int c = input.nextInt();
        
        double[][] array = new double[r][c];

        for(int i=0; i<r;i++){

            for(int n = 0; n<c;n++){
                System.out.println("input Row "+(i+1)+" Column "+(n+1));
                array[i][n] = input.nextDouble();
            }
        }

        System.out.println("Elements in your array: ");

        for(int i=0; i<r;i++){
            for(int n = 0; n<c;n++){
                System.out.print(array[i][n]+" ");
                
            }
            
            System.out.println(); 
            System.out.println();   
        }
        Matrix matrixO = new Matrix(array, r,c);
        double[][] arrayAdd = new double[r][c];


        //adding matrises
        System.out.println(Arrays.deepToString(matrixO.addition(matrixO.getMatrix()).getMatrix()));
        //multiplying matrises
        System.out.println(Arrays.deepToString(matrixO.multiply(matrixO.getMatrix(), matrixO.getMatrix()).getMatrix()));
        
       System.out.println(Arrays.deepToString(matrixO.Transpose(matrixO.getMatrix()).getMatrix()));

       System.out.println(matrixO);
    }

}