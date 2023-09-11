package math;
public class Matrix4X4 {
    double [][] matrix;
    public Matrix4X4 (){
        matrix= new double [4][4];
        for (int i = 0;i < 4;i++){
            matrix[i][i]=1;
        }
    }
    public Matrix4X4(double [][] matrix){
        this.matrix = matrix;
    }
    public static Point4 times(Matrix4X4 matrix,Point4 point){
        double [] pointRes = new double[4];
        double [] punto = {point.x,point.y,point.z,point.w};
        for (int i = 0;i < 4;i++){
            double acum = 0;
            for (int j = 0;j < 4;j++){
                acum += matrix.matrix[i][j]*punto[j];
            } 
            pointRes[i] = acum;
        } 
        Point4 pointRet = new Point4(pointRes[0],pointRes[1], pointRes[2],pointRes[3]);
        return pointRet;
    }
    public static Matrix4X4 times(Matrix4X4 matrix1,Matrix4X4 matrix2){
        double [][] matrixRes = new double[4][4];
        for (int i = 0;i < 4;i++){
            for (int j = 0;j < 4;j++){
                double acum = 0;
                for(int k = 0;k < 4;k++){
                    acum += matrix1.matrix[i][k]*matrix2.matrix[k][j] ;
                }
                matrixRes[i][j]=acum;
            } 
        } 
        return new Matrix4X4(matrixRes);
    }
}