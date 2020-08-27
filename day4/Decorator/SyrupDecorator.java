package day4.Decorator;

import day4.Drinking.Drinking;
import day4.Enum.Milk;
import day4.Enum.Syrup;
//果酱类装饰者：多一个属性amount
public class SyrupDecorator extends DrinkingDecorator {
    Drinking drinking;
    Syrup syrup;
    int amount=1;
    public SyrupDecorator(Drinking drink, Syrup syrup,int amount){
        this.drinking=drink;//将被装饰对象传入
        this.syrup=syrup;
        this.amount=amount;
    }
    @Override
    public String getDescription() {
        return drinking.getDescription()+","+syrup.getDescription()+"*"+amount;
    }

    @Override
    public double cost() {
        return drinking.cost()+syrup.getCost()*amount;
    }
}
