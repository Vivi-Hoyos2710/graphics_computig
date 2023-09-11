package math;
import java.lang.Math;
public class Vector3 {
    double x,y,z;
    public Vector3(){
        this.x=0;
        this.y=0;
        this.z=0;
    }
    public Vector3(double x,double y,double z){
        this.x=x;
        this.y=y;
        this.z=z;
    }
    public static Vector3 crossProduct(Vector3 vec1,Vector3 vec2){
        double x=vec1.y*vec2.z-vec1.z*vec2.y;
        double y=vec1.z*vec2.x-vec1.x*vec2.z;
        double z=vec1.x*vec2.y-vec1.y*vec2.x;
        Vector3 vecRes = new Vector3(x, y, z);
        return vecRes;
    }
    public static double pointProduct(Vector3 vec1,Vector3 vec2){
        double res= vec1.x * vec2.x + vec1.y * vec2.y + vec1.z * vec2.z;
        return res;
    }
    public double magnitude(){
        double res= Math.sqrt(Math.pow(this.x,2) + Math.pow(this.y,2) + Math.pow(this.z,2));
        return res;
    }
    public Vector3 normalizar(){
        double mag=this.magnitude();
        double x = this.x / mag;
        double y = this.y / mag;
        double z = this.z / mag;
        Vector3 vecRes = new Vector3(x, y, z);
        return vecRes;
    }
}
