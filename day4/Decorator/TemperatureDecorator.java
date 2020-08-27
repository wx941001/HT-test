package day4.Decorator;

import day4.Drinking.Drinking;
import day4.Enum.Milk;
import day4.Enum.Temperature;

public class TemperatureDecorator extends DrinkingDecorator {
    Drinking drinking;
    Temperature temperature;
    public TemperatureDecorator(Drinking drink, Temperature temperature){
        this.drinking=drink;//将被装饰对象传入
        this.temperature=temperature;
    }
    @Override
    public String getDescription() {
        return drinking.getDescription()+","+temperature.getDescription();
    }

    @Override
    public double cost() {
        return drinking.cost()+temperature.getCost();
    }
}
