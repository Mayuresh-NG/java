package objects;

public class Hierarchical {
    public static void main(String[] args) {
        cat c = new cat();
        elephant e = new elephant();
        parrot p = new parrot();
        c.categ();
        e.categ();
        p.categ();
    }
}

class Animals
{
    void categ()
    {
        System.out.println("Animals");
    }
}

class cat extends Animals
{

}

class elephant extends Animals
{

}

class parrot extends Animals
{

}