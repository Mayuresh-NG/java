package objects;

public class Multiple {
    public static void main(String[] args) {
        C1 c = new C1();
        c.a(8,9);
        c.b(12,9);

    }
}

interface A11
{
    public void a(int a,int b);
}

interface B11
{
    public void b(int a,int b);
}

class C1 implements A11,B11
{
    public void a(int a,int b)
    {
        int sum = a+b;
        System.out.println(sum);
    }

    public void b(int a,int b)
    {
        int sub = a-b;
        System.out.println(sub);
    }
}