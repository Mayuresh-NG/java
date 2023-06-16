package threads;

public class interfacethread {
    public static void main(String...x) {
        AAA a = new AAA();
        BBB b = new BBB();
        CCC c = new CCC();

        Thread t1 = new Thread(a);
        t1.start();
        Thread t2 = new Thread(b);
        t2.start();
        Thread t3 = new Thread(c);
        t3.start();
    }
}

class AAA implements Runnable
{
    public void run()
    {
        for (int i=1;i<=10;i++)
        {
            System.out.println("Thread A is running "+i);
        }
    }
}

class BBB implements Runnable
{
    public void run()
    {
        for (int i=1;i<=10;i++)
        {
            System.out.println("Thread B is running "+i);
        }
    }
}

class CCC implements Runnable
{
    public void run()
    {
        for (int i=1;i<=10;i++)
        {
            System.out.println("Thread C is running "+i);
        }
    }
}