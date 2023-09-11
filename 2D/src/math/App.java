package math;
public class App {
    public static void main(String[] args) throws Exception {
        Vector3 vector = new Vector3(5, 10, 59);
        Vector3 vector1 = new Vector3(8, 2, 94);
        Vector3 prueba=Vector3.crossProduct(vector, vector1);
        System.out.println("x: " + prueba.x +" y: " +prueba.y +" z:" + prueba.z);
    }
}
