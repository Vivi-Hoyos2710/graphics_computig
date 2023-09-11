package math;
public class Point4 {
    double x,y,z,w;
    public Point4(){
        this.x=0;
        this.y=0;
        this.z=0;
        this.w=1;
    }
    public Point4 (double x,double y,double z){
        this.x=x;
        this.y=y;
        this.z=z;
        this.w=1;
    }
    public Point4 (double x,double y,double z,double w){
        this.x=x;
        this.y=y;
        this.z=z;
        this.w=w;
    }
}
