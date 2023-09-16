package camera;
import math.*;
public class UVN4X4 extends Matrix4x4  {
    public Vector4 n;
    public Vector4 u;
    public Vector4 v;
    double [][] matrix = new double[4][4];
    public UVN4X4(Vector4 from, Vector4 lookAt, Vector4 up){
        super();
        n=Vector4.substract(from, lookAt);
        n.normalize();
        u =Vector4.crossProduct(up, n);
        u.normalize();
        v = Vector4.crossProduct(n, u);

        matrix [0][0]=u.vector[0];
        matrix [0][1]=u.vector[1];
        matrix [0][2]=u.vector[2];

        matrix [1][0]=v.vector[0];
        matrix [1][1]=v.vector[1];
        matrix [1][2]=v.vector[2];

        matrix [2][0]=n.vector[0];
        matrix [2][1]=n.vector[1];
        matrix [2][2]=n.vector[2];
        
        matrix [0][3]=Vector4.pointProduct(Vector4.minus(u), from);
        matrix [1][3]=Vector4.pointProduct(Vector4.minus(v), from);
        matrix [2][3]=Vector4.pointProduct(Vector4.minus(n), from);

        matrix [3][0]=0;
        matrix [3][1]=0;
        matrix [3][2]=0;
        matrix [3][3]=1;
    }
}
