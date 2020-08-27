package day4.Decorator;

import day4.Drinking.Drinking;
import day4.Enum.CupSize;

public class CupDecorator extends DrinkingDecorator {
    Drinking drinking;
    CupSize cupSize;
    public CupDecorator(Drinking drink, CupSize cupSize){
        this.drinking=drink;//将被装饰对象传入
        this.cupSize=cupSize;
    }
    @Override
    public String getDescription() {
        return drinking.getDescription()+","+cupSize.getDescription();
    }

    @Override
    public double cost() {
        return drinking.cost()+cupSize.getCost();
    }
}
