package math;

import java.lang.Math;

public class Vector4 {
    public double [] vector = new double[4];

    public Vector4() {
        vector[0] = 0;
        vector[1] = 0;
        vector[2] = 0;
        vector[3] = 1;
    }

    public Vector4(double x, double y, double z) {
        vector[0] = x;
        vector[1] = y;
        vector[2] = z;
        vector[3] = 1;
    }

    public void normalizeW() {
        if(vector[3] == 0) {
            return;
        }
        vector[0] /= vector[3];
        vector[1] /= vector[3];
        vector[2] /= vector[3];
        vector[3] = 1;
    }

    public void normalize() {
        double mag=magnitude();
        vector[0] /= mag;
        vector[1] /= mag;
        vector[2] /= mag;
        vector[3] = 1;
    }

    public static Vector4 substract (Vector4 a, Vector4 b) {
        double x = a.vector[0] - b.vector[0];
        double y = a.vector[1] - b.vector[1];
        double z = a.vector[2] - b.vector[2];
        Vector4 vec = new Vector4(x, y, z);
        return vec;
    }

    public static Vector4 minus (Vector4 a) {
        double x = -a.vector[0] ;
        double y = -a.vector[1] ;
        double z = -a.vector[2] ;
        Vector4 vec = new Vector4(x, y, z);
        return vec;
    }

    public double magnitude(){
        double res= Math.sqrt(Math.pow(this.vector[0],2) + Math.pow(this.vector[1],2) + Math.pow(this.vector[2],2));
        return res;
    }

    public static Vector4 crossProduct(Vector4 vec1,Vector4 vec2){
        double x=vec1.vector[1]*vec2.vector[2]-vec1.vector[2]*vec2.vector[1];
        double y=vec1.vector[2]*vec2.vector[0]-vec1.vector[0]*vec2.vector[2];
        double z=vec1.vector[0]*vec2.vector[1]-vec1.vector[1]*vec2.vector[0];
        Vector4 vecRes = new Vector4(x, y, z);
        return vecRes;
    }

    public static double pointProduct(Vector4 vec1,Vector4 vec2){
        double res= vec1.vector[0] * vec2.vector[0] + vec1.vector[1] * vec2.vector[1] + vec1.vector[2] * vec2.vector[2];
        return res;
    }

    public String toString() {
        return "(" + vector[0] + ", " + vector[1] + ", " + vector[2] + ")";
    }

}
