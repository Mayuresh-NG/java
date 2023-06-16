public class abstraction {
    public static void main(String[] args) {
        B o = new B();
        o.sum();
        o.sub();
    }
}

abstract class A
{
    abstract void sum();
    abstract void sub();
}

class B extends A
{
    void sum()
    {
        System.out.println(10+20);
    }

    void sub()
    {
        System.out.println(10-20);
    }
}