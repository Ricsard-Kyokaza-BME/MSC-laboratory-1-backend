package hu.bme.msc.agiletool.logic;

public class SomeLogic {

    private int a;
    private int b;

    public SomeLogic(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public int multiply() {
        return a*b;
    }

    public int addition() { return a+b; }

    public double division() { return a/b; }

}
