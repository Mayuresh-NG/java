package poly;

public class overiding {
    public static void main(String[] args) {
        A a = new A();
        a.add(9,5);
    }
}

class A
{
    void add(int a,int b)
    {
        System.out.println("Class A"+(a+b));
    }
}

class B extends A
{
    void add(int a,int b)
    {
        System.out.println("Class B "+(a+b));
    }
}