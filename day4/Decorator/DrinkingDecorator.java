package day4.Decorator;

import day4.Drinking.Drinking;

abstract class DrinkingDecorator extends Drinking {
    public abstract String getDescription();//重写装饰者的描述

}
