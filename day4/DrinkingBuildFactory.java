package day4;

import day4.Decorator.CupDecorator;
import day4.Decorator.MilkDecorator;
import day4.Decorator.SyrupDecorator;
import day4.Decorator.TemperatureDecorator;
import day4.Drinking.Cappuccino;
import day4.Drinking.Drinking;
import day4.Drinking.Frappuccino;
import day4.Enum.CupSize;
import day4.Enum.Milk;
import day4.Enum.Syrup;
import day4.Enum.Temperature;

public class DrinkingBuildFactory {
    int drinkingType;
    int cupSize;
    int tempera;
    int milk;
    int syrup;
    int syrupAmount;

    public DrinkingBuildFactory(int drinkingType, int cupSize, int tempera, int milk, int syrup, int syrupAmount) {
        this.drinkingType = drinkingType;
        this.cupSize = cupSize;
        this.tempera = tempera;
        this.milk = milk;
        this.syrup = syrup;
        this.syrupAmount = syrupAmount;
    }

    public Drinking build(){
        Drinking drinking=new Cappuccino();//默认
        switch (drinkingType){
            case 1:
                drinking = new Cappuccino();
                break;
            case 2:
                drinking=new Frappuccino();
                break;
        }
        switch (cupSize){
            case 1:
                drinking = new CupDecorator(drinking,CupSize.SMALL);
                break;
            case 2:
                drinking=new CupDecorator(drinking,CupSize.MIDDLE);
                break;
            case 3:
                drinking=new CupDecorator(drinking,CupSize.BIG);
        }
        switch (tempera){
            case 1:
                drinking = new TemperatureDecorator(drinking,Temperature.ICE);
                break;
            case 2:
                drinking = new TemperatureDecorator(drinking,Temperature.DE_ICE);
                break;
            case 3:
                drinking = new TemperatureDecorator(drinking,Temperature.NORMAL);
                break;
            case 4:
                drinking = new TemperatureDecorator(drinking,Temperature.HOT);
                break;
        }
        switch (milk){
            case 1:
                drinking=new MilkDecorator(drinking,Milk.QZNN);
                break;
            case 2:
                drinking=new MilkDecorator(drinking,Milk.YMN);
                break;
            case 3:
                drinking=new MilkDecorator(drinking,Milk.TZNN);
                break;
        }
        switch (syrup){
            case 0:
                break;
            case 1:
                drinking=new SyrupDecorator(drinking,Syrup.XCTJ,syrupAmount);
                break;
            case 2:
                drinking=new SyrupDecorator(drinking,Syrup.JTTJ,syrupAmount);
                break;
            case 3:
                drinking=new SyrupDecorator(drinking,Syrup.MOKAJ,syrupAmount);
                break;
        }
        return drinking;
    }
}
