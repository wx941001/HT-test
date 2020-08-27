package day4.Decorator;

import day4.Drinking.Drinking;
import day4.Enum.CupSize;
import day4.Enum.Milk;

public class MilkDecorator extends DrinkingDecorator {
    Drinking drinking;
    Milk milk;
    public MilkDecorator(Drinking drink, Milk milk){
        this.drinking=drink;//将被装饰对象传入
        this.milk=milk;
    }
    @Override
    public String getDescription() {
        return drinking.getDescription()+","+milk.getDescription();
    }

    @Override
    public double cost() {
        return drinking.cost()+milk.getCost();
    }
}
