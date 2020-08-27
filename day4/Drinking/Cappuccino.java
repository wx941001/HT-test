package day4.Drinking;

public class Cappuccino extends Drinking {
    public Cappuccino(){
        description="卡布奇诺";
    }
    @Override
    public double cost() {
        return 10;
    }
}
