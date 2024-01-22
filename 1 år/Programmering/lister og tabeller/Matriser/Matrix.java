import java.util.Arrays;
import java.util.Arrays;

final public class Matrix {

    private final double[][] matrix;
    private double[][] newMatrix;
    private final int r;
    private final int c;

       public Matrix(double[][] array, int r, int c){
           this.matrix = array;
           this.newMatrix = new double[r][c];
           this.r = r;
           this.c = c;
       }

       public double[][] getMatrix(){
           return matrix;
       }


       public Matrix addition(double[][] matrixI){

        for(int i = 0; i<matrix.length;i++){

            for(int n = 0; n<matrix.length;n++){

                newMatrix[i][n] = matrix[i][n] + matrixI[i][n];

            }
        }

        
        return new Matrix(newMatrix, r, c);
       }


       public Matrix multiply(double[][] matrix, double[][] matrixI){
            int aColumn;
            int bColumn;
            int aRow;
            int bRow;
            double[][] matrixM;

        if(matrix[0].length == matrixI.length){
             aColumn = matrix[0].length;
             bColumn = matrixI[0].length;
             aRow = matrix.length;
             bRow = matrixI.length;
             matrixM = new double[aRow][bColumn];

            for(int i=0; i<bColumn; i++){

                    for(int n=0; n<aRow;n++){

                        for(int j=0; j<aColumn;j++){

                            matrixM[n][i] = matrixM[n][i] + matrix[n][j] * matrixI[j][i];      

                        }

                    }

            }
            return new Matrix(matrixM, matrixM.length, matrixM[0].length);

        }
        else{
            System.out.println("To multiply these matrices the column count of A must equal the row count of B");
            return null;
        }
       }


    /**
     * transposes matrix
     * @param matrixI
     * @return new matrix
     */
    public Matrix Transpose(double[][] matrixI){

        int r = matrixI.length;
        int c = matrixI[0].length;
        double[][] matrixTP = new double[matrixI[0].length][matrixI.length];

        for(int i = 0; i<r; i++){

            for(int n=0; n<c;n++){
                matrixTP[n][i] = matrixI[i][n];
            }

        }

            return new Matrix(matrixTP, r, c);

       }




    @Override
    public String toString() {
        return "Matrix{" +
                "matrix=" + Arrays.deepToString(matrix) +
                ", newMatrix=" + Arrays.deepToString(newMatrix) +
                '}';
    }
}