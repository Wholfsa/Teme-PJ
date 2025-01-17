package lab3ex1;

public class Parabola {
    private int a, b, c;

    public Parabola(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    // metoda pentru calcularea varfului parabolei
    public double[] getVfParabola() {
        double x = -b / (2.0 * a);
        double y = -((b * b) - (4 * a * c)) / (4.0 * a);
        return new double[]{x, y};
    }

    @Override
    public String toString() {
        return String.format("f(x) = %dx^2 + %dx + %d", a, b, c);
    }

    // metoda statică pentru calcularea mijlocului segmentului
    public static double[] getMijloculSegmentului(double[] v1, double[] v2) {
        double x = (v1[0] + v2[0]) / 2.0;
        double y = (v1[1] + v2[1]) / 2.0;
        return new double[]{x, y};
    }

    // metoda statica pentru calcularea lungimii segmentului
    public static double getLungimeaSegmentului(double[] v1, double[] v2) {
        return Math.hypot(v2[0] - v1[0], v2[1] - v1[1]);
    }
}


