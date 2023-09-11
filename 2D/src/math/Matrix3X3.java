package math;
public class Matrix3X3 {
    public double [][] matrix;
    public Matrix3X3 (){
        matrix= new double [3][3];
        for (int i = 0;i < 3;i++){
            matrix[i][i]=1;
        }
    }
    public Matrix3X3(double [][] matrix){
        this.matrix = matrix;
    }
    public static Point3 times(Matrix3X3 matrix,Point3 point){
        double [] pointRes = new double[3];
        double [] punto = {point.x,point.y,point.w};
        for (int i = 0;i < 3;i++){
            double acum = 0;
            for (int j = 0;j < 3;j++){
                acum += matrix.matrix[i][j]*punto[j];
            } 
            pointRes[i] = acum;
        } 
        Point3 pointRet = new Point3(pointRes[0],pointRes[1], pointRes[2]);
        return pointRet;
    }
    public static Matrix3X3 times(Matrix3X3 matrix1,Matrix3X3 matrix2){
        double [][] matrixRes = new double[3][3];
        for (int i = 0;i < 3;i++){
            for (int j = 0;j < 3;j++){
                double acum = 0;
                for(int k = 0;k < 3;k++){
                    acum += matrix1.matrix[i][k]*matrix2.matrix[k][j] ;
                }
                matrixRes[i][j]=acum;
            } 
        } 
        return new Matrix3X3(matrixRes);
    }
}