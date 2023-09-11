package math;
public class Point3 {
    public double x,y,w;
    public Point3(){
        this.x=0;
        this.y=0;
        this.w=0;
    }
    public Point3 (double x,double y){
        this.x=x;
        this.y=y;
        this.w=1;
    }
    public Point3 (double x,double y,double w){
        this.x=x;
        this.y=y;
        this.w=w;
    }
    
    
}
