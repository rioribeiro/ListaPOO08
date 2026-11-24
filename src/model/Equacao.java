package model;

public class Equacao {

    private double a;
    private double b;
    private double c;

    public Equacao(double a, double b, double c) {
        setA(a);
        setB(b);
        setC(c);
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        if (a == 0) {
            throw new IllegalArgumentException("O coeficiente 'a' não pode ser zero em uma equação do 2º grau.");
        }
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }

    public double delta() {
        return (b * b) - (4 * a * c);
    }

    public boolean temRaizesReais() {
        return delta() >= 0;
    }

    public double x1() {
        if (!temRaizesReais()) {
            throw new ArithmeticException("A equação não possui raízes reais (Δ < 0).");
        }
        return (-b + Math.sqrt(delta())) / (2 * a);
    }

    public double x2() {
        if (!temRaizesReais()) {
            throw new ArithmeticException("A equação não possui raízes reais (Δ < 0).");
        }
        return (-b - Math.sqrt(delta())) / (2 * a);
    }

    public double y(double x) {
        return (a * x * x) + (b * x) + c;
    }

    @Override
    public String toString() {
        return String.format("y = %.2fx² + %.2fx + %.2f", a, b, c);
    }
}
