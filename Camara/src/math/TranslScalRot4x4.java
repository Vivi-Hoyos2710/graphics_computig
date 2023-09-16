package math;

public class TranslScalRot4x4 extends Matrix4x4 {

    double dx = 0;
    double dy = 0;
    double dz = 0;
    double sx = 1;
    double sy = 1;
    double sz = 1;
    double thetaX = 0;
    double thetaY = 0;
    double thetaZ = 0;
    double centerX = 0;
    double centerY = 0;
    double centerZ = 0;

    public TranslScalRot4x4() {
        super();
    }

    public TranslScalRot4x4(double dx, double dy, double dz,
            double sx, double sy, double sz,
            double thetaX, double thetaY, double thetaZ,
            double centerX, double centerY, double centerZ) {
        super();
        this.dx = dx;
        this.dy = dy;
        this.dz = dz;
        this.sx = sx;
        this.sy = sy;
        this.sz = sz;
        this.thetaX = thetaX;
        this.thetaY = thetaY;
        this.thetaZ = thetaZ;
        this.centerX = centerX;
        this.centerY = centerY;
        this.centerZ = centerZ;
        Translation4x4 translation = new Translation4x4(dx, dy, dz);
        Scaling4x4 scal = new Scaling4x4(sx, sy, sz);
        RotationX4x4 rotationX = new RotationX4x4(thetaX);
        RotationY4x4 rotationY = new RotationY4x4(thetaY);
        RotationZ4x4 rotationZ = new RotationZ4x4(thetaZ);
        //
        Translation4x4 translationCenter = new Translation4x4(-centerX, -centerY, -centerZ);
        Matrix4x4 scalCenter = super.times(scal, translationCenter);
        Matrix4x4 zCenter = super.times(rotationZ, scalCenter);
        Matrix4x4 xCenter = super.times(rotationY, zCenter);
        Matrix4x4 yCenter = super.times(rotationX, xCenter);
        Matrix4x4 traslacionBack = new Translation4x4(centerX, centerY, centerZ);
        Matrix4x4 resultDevuelta= super.times(traslacionBack, yCenter);
        Matrix4x4 finalResult= super.times(translation, resultDevuelta);
        this.matrix = finalResult.matrix;

    }

}