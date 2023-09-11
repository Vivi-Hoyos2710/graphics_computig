import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

import math.Matrix3X3;
import math.Point3;

import javax.swing.JFrame;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class GraficarFiguras2 extends JPanel implements KeyListener {
    public static Tuple[] vector;
    public static Tuple[] vectorInicial;
    public static Tuple[] aristas;
    public static int initialX = 0;
    public static int initialY = 0;
    public static int dx = 0;
    public static int dy = 0;
    public static int dang = 0;
    public static int sx = 1;
    public static int sy = 1;
    public static final int WIDTH = 900;
    public static final int HEIGHT = 740;

    public GraficarFiguras2() {
        super();
        // super.paintComponent(g);
        this.setFocusable(true);        // CAMBIO
        this.requestFocusInWindow();               // CAMBIO
        this.addKeyListener(this);                  // CAMBIO

    }

    // ------------------------------------------------------------------------------
    public Tuple[] ponerEnCero() {
        double[][] MC = { { 1, 0, -initialX }, { 0, 1, -initialY }, { 0, 0, 1 } }; // CAMBIO
        Matrix3X3 matrizCentrar = new Matrix3X3(MC);
        Tuple[] vect = new Tuple[vector.length];
        for (int i = 0; i < vector.length; i++) {
            Point3 punto = new Point3(vectorInicial[i].value1, vectorInicial[i].value2);
            Point3 puntoResultado = Matrix3X3.times(matrizCentrar, punto);

            vect[i] = new Tuple((int) puntoResultado.x, (int) puntoResultado.y);

        }
        return vect;
    }

   public Tuple[] retornarAPosicionInicial() {
        double[][] MC = { { 1, 0, initialX }, { 0, 1, initialY }, { 0, 0, 1 } }; // CAMBIO
        Matrix3X3 matrizCentrar = new Matrix3X3(MC);
        Tuple[] vect = new Tuple[vector.length];
        for (int i = 0; i < vector.length; i++) {
            Point3 punto = new Point3(vectorInicial[i].value1, vectorInicial[i].value2);
            Point3 puntoResultado = Matrix3X3.times(matrizCentrar, punto);

            vect[i] = new Tuple((int) puntoResultado.x, (int) puntoResultado.y);

        }
        return vect;
    }

     

    public void transformar() {
        double[][] MTras = { { 1, 0, dx }, { 0, 1, dy }, { 0, 0, 1 } };
        Matrix3X3 matrizTraslacion = new Matrix3X3(MTras);

        double[][] MRotate = { { Math.cos(Math.toRadians(dang)), -(Math.sin(Math.toRadians(dang))), 0 },
                { Math.sin(Math.toRadians(dang)), Math.cos(Math.toRadians(dang)), 0 }, { 0, 0, 1 } };
        Matrix3X3 matrizRotar = new Matrix3X3(MRotate);

        double[][] MScale = { { sx, 0, 0 }, { 0, sy, 0 }, { 0, 0, 1 } };
        Matrix3X3 matrizEscalar = new Matrix3X3(MScale);

        Tuple[] figCenter = ponerEnCero();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.println("mE:" + matrizTraslacion.matrix[i][j]);
            }
        }

        // Aplicar matrix escalamiento
        for (int i = 0; i < vector.length; i++) {
            Point3 punto = new Point3(figCenter[i].value1, figCenter[i].value2);
            Point3 puntoResultado = Matrix3X3.times(matrizEscalar, punto);

            figCenter[i].value1 = (int) puntoResultado.x;
            figCenter[i].value2 = (int) puntoResultado.y;
        }

        // Aplicar matrix rotación
        for (int i = 0; i < vector.length; i++) {
            Point3 punto = new Point3(figCenter[i].value1, figCenter[i].value2);
            Point3 puntoResultado = Matrix3X3.times(matrizRotar, punto);

            figCenter[i].value1 = (int) puntoResultado.x;
            figCenter[i].value2 = (int) puntoResultado.y;
        }

        // Retornar a posición inicial
        double[][] MC = { { 1, 0, initialX }, { 0, 1, initialY }, { 0, 0, 1 } }; // CAMBIO
        Matrix3X3 matrizRetornar = new Matrix3X3(MC);
        for (int i = 0; i < vector.length; i++) {
            Point3 punto = new Point3(figCenter[i].value1, figCenter[i].value2);
            Point3 puntoResultado = Matrix3X3.times(matrizRetornar, punto);

            figCenter[i].value1 = (int) puntoResultado.x;
            figCenter[i].value2 = (int) puntoResultado.y;
        }
        vector = figCenter;

        // Aplicar matrix traslación
        for (int i = 0; i < vector.length; i++) {
            Point3 punto = new Point3(figCenter[i].value1, figCenter[i].value2);
            Point3 puntoResultado = Matrix3X3.times(matrizTraslacion, punto);

            figCenter[i].value1 = (int) puntoResultado.x;
            figCenter[i].value2 = (int) puntoResultado.y;
        }
        vector = figCenter;

        for (int i = 0; i < vector.length; i++) {
            System.out.println("despues");
            System.out.println("X: " + vector[i].value1 + " Y: " + vector[i].value2);

        }

    }

    public Tuple[] toNormal(Tuple[] fig) {
        Tuple[] normal = new Tuple[vector.length];
        double[][] MN = { { 1, 0, dx }, { 0, 1, dy }, { 0, 0, 1 } };
        Matrix3X3 matrizNormal = new Matrix3X3(MN);
        for (int i = 0; i < vector.length; i++) {
            Point3 punto = new Point3(fig[i].value1, fig[i].value2);
            Point3 puntoResultado = Matrix3X3.times(matrizNormal, punto);

            normal[i] = new Tuple((int) puntoResultado.x, (int) puntoResultado.y);

        }
        return normal;
    }

    // -------------------------------------------------------------------------------
    @Override
    public void paintComponent(Graphics g) {

        dibujarPlano(g);

        for (int i = 0; i < vector.length; i++) {
            System.out.println("antes");
            System.out.println("X: " + vector[i].value1 + " Y: " + vector[i].value2);

        }
        g.setColor(Color.blue);
        dibujarFigura(g);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int tecla = e.getKeyCode();
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaa " + tecla);

        /*
         * double [][] MR = {{1,0,10},{0,1,0},{0,0,1}};
         * Matrix3X3 matrizDerecha = new Matrix3X3(MR);
         * 
         * double [][] ML = {{1,0,-10},{0,1,0},{0,0,1}};
         * Matrix3X3 matrizIzquierda = new Matrix3X3(ML);
         * 
         * double [][] MU = {{1,0,0},{0,1,10},{0,0,1}};
         * Matrix3X3 matrizArriba = new Matrix3X3(MU);
         * 
         * double [][] MD = {{1,0,0},{0,1,-10},{0,0,1}};
         * Matrix3X3 matrizAbajo = new Matrix3X3(MD);
         * 
         * double [][] MRotateRight =
         * {{Math.cos(Math.toRadians(10)),-(Math.sin(Math.toRadians(10))),0},{Math.sin(
         * Math.toRadians(10)),Math.cos(Math.toRadians(10)),0},{0,0,1}};
         * Matrix3X3 matrizRotarDerecha = new Matrix3X3(MRotateRight);
         * 
         * double [][] MRotateLeft =
         * {{Math.cos(Math.toRadians(10)),Math.sin(Math.toRadians(10)),0},{-(Math.sin(
         * Math.toRadians(10))),Math.cos(Math.toRadians(10)),0},{0,0,1}};
         * Matrix3X3 matrizRotarIzquierda = new Matrix3X3(MRotateLeft);
         * 
         * double [][] MMagnify = {{2,0,0},{0,2,0},{0,0,1}};
         * Matrix3X3 matrizAgrandar = new Matrix3X3(MMagnify);
         * 
         * double [][] MReduce = {{0.5,0,0},{0,0.5,0},{0,0,1}};
         * Matrix3X3 matrizReducir = new Matrix3X3(MReduce);
         */
        double[][] MTras = { { 1, 0, dx }, { 0, 1, dy }, { 0, 0, 1 } };
        Matrix3X3 matrizTraslacion = new Matrix3X3(MTras);

        if (tecla == KeyEvent.VK_W) {
            dy -= 10;
            transformar();
            repaint();
            System.out.println(dy);
        } else if (tecla == KeyEvent.VK_S) {
            /*
             * for (int i = 0; i < vector.length; i++)
             * {
             * //Point3 punto = new Point3(vector[i].value1, vector[i].value2);
             * Point3 punto = new Point3(vector[i].value1, vector[i].value2);
             * Point3 puntoResultado = Matrix3X3.times(matrizAbajo, punto);
             * 
             * vector[i].value1 = (int)puntoResultado.x;
             * vector[i].value2 = (int)puntoResultado.y;
             * }
             */
            dy += 10;
            transformar();
            repaint();
            System.out.print(dy);
        } else if (tecla == KeyEvent.VK_D) {
            /*
             * for (int i = 0; i < vector.length; i++)
             * {
             * Point3 punto = new Point3(vector[i].value1, vector[i].value2);
             * Point3 puntoResultado = Matrix3X3.times(matrizDerecha, punto);
             * 
             * vector[i].value1 = (int)puntoResultado.x;
             * vector[i].value2 = (int)puntoResultado.y;
             * }
             */
            dx -= 10;
            transformar();
            repaint();
            System.out.println(dx);
        } else if (tecla == KeyEvent.VK_A) {
            // for (int i = 0; i < vector.length; i++)
            // {
            // Point3 punto = new Point3(vector[i].value1, vector[i].value2);
            // Point3 puntoResultado = Matrix3X3.times(matrizIzquierda, punto);

            // vector[i].value1 = (int)puntoResultado.x;
            // vector[i].value2 = (int)puntoResultado.y;
            // }
            dx += 10;
            transformar();
            repaint();
            System.out.println(dx);
        } else if (tecla == KeyEvent.VK_E) {
            dang += 10;
            // for (int i = 0; i < vector.length; i++)
            // {
            // Point3 punto = new Point3(vector[i].value1, vector[i].value2);
            // Point3 puntoResultado = Matrix3X3.times(matrizRotarDerecha, punto);

            // vector[i].value1 = (int)puntoResultado.x;
            // vector[i].value2 = (int)puntoResultado.y;
            // }
            transformar();
            repaint();
            System.out.println(dang);
        }
        // -------------------------------------------------------------------
        else if (tecla == KeyEvent.VK_Q) {
            dang -= 10;
            // for (int i = 0; i < vector.length; i++)
            // {
            // /* //Point3 punto = new Point3(vector[i].value1, vector[i].value2);
            // //Point3 puntoResultado = Matrix3X3.times(matrizRotarIzquierda, punto);
            // Point3 punto = new Point3(figCenter[i].value1, figCenter[i].value2);
            // Point3 puntoResultado = Matrix3X3.times(matrizRotarIzquierda, punto);

            // figCenter[i].value1=(int)puntoResultado.x;
            // figCenter[i].value2=(int)puntoResultado.y;*/

            // vector[i].value1 = (int)puntoResultado.x;
            // vector[i].value2 = (int)puntoResultado.y;
            // }
            // Tuple[] vect=toNormal(figCenter);
            // vector = vect;
            transformar();
            repaint();
            System.out.println(dang);
        } else if (tecla == KeyEvent.VK_M) {
            sx += 1;
            sy += 1;
            // for (int i = 0; i < vector.length; i++)
            // {
            // Point3 punto = new Point3(vector[i].value1, vector[i].value2);
            // Point3 puntoResultado = Matrix3X3.times(matrizAgrandar, punto);

            // vector[i].value1 = (int)puntoResultado.x;
            // vector[i].value2 = (int)puntoResultado.y;
            // }
            transformar();
            repaint();
            System.out.println(sx);
            System.out.println(sy);
        } else if (tecla == KeyEvent.VK_N) {
            sx -= 1;
            sy -= 1;
            // for (int i = 0; i < vector.length; i++)
            // {
            // Point3 punto = new Point3(vector[i].value1, vector[i].value2);
            // Point3 puntoResultado = Matrix3X3.times(matrizReducir, punto);

            // vector[i].value1 = (int)puntoResultado.x;
            // vector[i].value2 = (int)puntoResultado.y;
            // }
            transformar();
            repaint();
            System.out.println(sx);
            System.out.println(sy);
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    class Tuple {
        int value1;
        int value2;

        public Tuple(int value1, int value2) {
            this.value1 = value1;
            this.value2 = value2;
        }
    }

    public void tomarPunto(String fileName) {
        try {
            Scanner scanner = new Scanner(new File(fileName));
            int numPoints = scanner.nextInt();
            vector = new Tuple[numPoints];
            for (int i = 1; i <= numPoints; i++) {
                int x = scanner.nextInt();
                int y = scanner.nextInt();

                vector[i - 1] = new Tuple(x, y);
            }

            int numLines = scanner.nextInt();
            aristas = new Tuple[numLines];
            for (int i = 1; i <= numLines; i++) {
                int indice1 = scanner.nextInt();
                int indice2 = scanner.nextInt();

                aristas[i - 1] = new Tuple(indice1, indice2);
            }
            scanner.close();

            int minX = vector[0].value1;
            int maxX = vector[0].value1;
            int minY = vector[0].value2;
            int maxY = vector[0].value2;
            for (int i = 0; i < vector.length; i++) {
                // encontrar los valores minimos y maximos de la figura
                if (vector[i].value1 < minX)
                    minX = vector[i].value1;
                if (vector[i].value2 < minY)
                    minY = vector[i].value2;
                if (vector[i].value1 > maxX)
                    maxX = vector[i].value1;
                if (vector[i].value2 > maxY)
                    maxY = vector[i].value2;
            }
            vectorInicial = vector;
            int centerX = (maxX + minX) / 2;        // CAMBIO
            int centerY = (maxY + minY) / 2;        // CAMBIO

            initialX = centerX;                 // CAMBIO
            initialY = centerY;                 // CAMBIO
            System.out.println("dx: " + dx);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void dibujarFigura(Graphics g) {
        // double [][] M = {{1,0,100},{0,1,50},{0,0,1}}; // Se crea la matriz que
        // permite trasladar la figura
        // Matrix3X3 matriz = new Matrix3x3(M); // Se declara la matriz como un objeto
        // Matrix3x3

        for (int i = 1; i <= vector.length; i++) {
            double x = vector[i - 1].value1;
            double y = vector[i - 1].value2;

            int xp = (int) x; // Se guardan las coordenadas del nuevo punto en X
            int yp = (int) y; // Se guardan las coordenadas del nuevo punto en Y
            g.setColor(Color.blue);
            dibujarPunto(g, xp, yp);
        }

        for (int i = 1; i <= aristas.length; i++) {
            int indice1 = aristas[i - 1].value1;
            int indice2 = aristas[i - 1].value2;
            g.setColor(Color.blue);
            dibujarLinea(g, vector[indice1].value1, vector[indice1].value2, vector[indice2].value1,
                    vector[indice2].value2);
        }
    }

    public void dibujarPlano(Graphics g) {
        g.setColor(Color.red);
        dibujarLinea(g, -WIDTH, 0, WIDTH, 0);
        g.setColor(Color.green);
        dibujarLinea(g, 0, -HEIGHT, 0, HEIGHT);
    }

    public static void dibujarPunto(Graphics g, int x, int y) {
        int xj = x + WIDTH / 2;
        int yj = HEIGHT / 2 - y;
        g.drawLine(xj, yj, xj, yj);
    }

    public void dibujarLinea(Graphics g, int x1, int y1, int x2, int y2) {
        int xj1 = x1 + WIDTH / 2;
        int yj1 = HEIGHT / 2 - y1;
        int xj2 = x2 + WIDTH / 2;
        int yj2 = HEIGHT / 2 - y2;
        g.drawLine(xj1, yj1, xj2, yj2);
    }

    public static void main(String[] args) {
        // Crear un nuevo Frame
        JFrame frame = new JFrame("Puntos");
        // Al cerrar el frame, termina la ejecución de este programa
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Agregar un JPanel que se llama GraficarFiguras
        GraficarFiguras2 g = new GraficarFiguras2();
        String fileName = "casa.txt";
        g.tomarPunto(fileName);

        frame.add(g);
        // Asignarle tamaño
        frame.setSize(WIDTH, HEIGHT);
        // Poner el frame en el centro de la pantalla
        frame.setLocationRelativeTo(null);
        // Mostrar el frame
        frame.setVisible(true);

    }
}