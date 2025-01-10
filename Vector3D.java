import java.util.Vector;

public class Vector3D {
    public double x;
    public double y;
    public double z;

    public Vector3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX(){
        return this.x;
    }

    public double getY(){
        return this.y;
    }

    public double getZ(){
        return this.z;
    }

    @Override
    public String toString() {
        return "(" + String.format("%.2f", getX())  +
                ", " + String.format("%.2f", getY()) +
                ", " + String.format("%.2f", getZ()) + ")";
    }

    public double getMagnitude(){
        double sum = Math.pow(getX(), 2) + Math.pow(getY(), 2) + Math.pow(getZ(), 2);
        return Math.sqrt(sum);
    }

    public Vector3D normalize(){
        if (getMagnitude() == 0){
            throw new IllegalStateException("Cannot normalize this vector");
        }
        return new Vector3D(getX()/getMagnitude(), getY()/getMagnitude(), getZ()/getMagnitude());
    }

    public Vector3D add(Vector3D v2){
        return new Vector3D(getX() + v2.getX(),getY() + v2.getY(), getZ() + v2.getZ());
    }

    public Vector3D multiply(double constant){
        return new Vector3D(getX() * constant, getY() * constant, getZ() * constant);
    }

    public double dotProduct(Vector3D v2){
        return (getX() * v2.getX()) + (getY() * v2.getY() + (getZ() * v2.getZ()));
    }

    public double angleBetween(Vector3D v2){
        double dotProd = dotProduct(v2);
        double combinedMag = getMagnitude() * v2.getMagnitude();
        double radians =  Math.acos(dotProd/combinedMag);
        double result = Math.toDegrees(radians);
        if (0 > result || result > 180){
            throw new IllegalStateException("Angle between is not valid");
        } else {
            return result;
        }
    }

    public Vector3D crossProduct(Vector3D v2){
        double xComponent = (getY() * v2.getZ()) - (getZ() * v2.getY());
        double yComponent = (getZ() * v2.getX()) - (getX() * v2.getZ());
        double zComponent = (getX() * v2.getY()) - (getY() * v2.getX());
        return new Vector3D(xComponent, yComponent, zComponent);
    }

    public static void main(String[] args){
        Vector3D vector1 = new Vector3D(2.0, 3.0, 1.0);
        Vector3D vector2 = new Vector3D(2.3, 3.2, 1.6);
        System.out.println(vector2.multiply(2));
        System.out.println(vector1.normalize());
        System.out.println(vector1.angleBetween(vector2));
        System.out.println(vector1.crossProduct(vector2));
    }
}
