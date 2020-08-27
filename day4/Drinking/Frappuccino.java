package day4.Drinking;

public class Frappuccino extends Drinking{
    public Frappuccino(){
        description="星冰乐";
    }
    @Override
    public double cost() {
        return 20;
    }
}
