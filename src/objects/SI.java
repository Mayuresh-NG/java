package objects;

public class SI {
    public static void main(String[] args) {
        dog d = new dog();
        d.add(2,3);
        d.sub(5,3);
    }
}

class Animal
{
    void add(int a,int b)
    {
        int sum = a+b;
        System.out.println(sum);
    }
}

class dog extends Animal
{
    void sub(int a,int b)
    {
        int sum=a-b;
        System.out.println(sum);
    }
}
