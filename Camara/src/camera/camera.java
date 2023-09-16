package camera;

import math.Vector4;
import java.lang.Math;

public class camera {
    static double  r = 1;
    static double  theta = 0;
    static double  phi = 0;

    public static UVN4X4 createUVN(){
        double x = r * Math.cos(Math.toRadians(theta))*Math.sin(Math.toRadians(phi));
        double y = r * Math.sin(Math.toRadians(theta))*Math.sin(Math.toRadians(phi));
        double z = r * Math.cos(Math.toRadians(phi));
        Vector4 from = new Vector4(x,y,z);
        Vector4 lookAt = new Vector4(0,0,-1);
        Vector4 up = new Vector4(1,0,0);
        UVN4X4 uvn = new UVN4X4(from, lookAt, up);
        return uvn;
    }
}
