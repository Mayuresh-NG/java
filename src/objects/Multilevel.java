package objects;
public class Multilevel {
    public static void main(String[] args) {
        D d = new D();
        d.Aa();
    }
}

class A
{
    void Aa()
    {
        System.out.println("Class A");
    }
}

class B extends A
{
    void Bb()
    {
        System.out.println("Class B");
    }
}

class C extends B
{
    void Cc()
    {
        System.out.println("Class C");
    }
}

class D extends C
{
    void Dd()
    {
        System.out.println("Class C");
    }
}