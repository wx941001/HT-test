package day4;

import day4.Decorator.CupDecorator;
import day4.Decorator.MilkDecorator;
import day4.Decorator.SyrupDecorator;
import day4.Drinking.Cappuccino;
import day4.Drinking.Drinking;
import day4.Drinking.Frappuccino;
import day4.Enum.CupSize;
import day4.Enum.Milk;
import day4.Enum.Syrup;

import java.util.Scanner;

public class DrinkingMain {
    public static void main(String[] args) {

        Scanner sc=new Scanner(System.in);
        System.out.println("请输入您需要的饮品种类：1-卡布奇诺，2-星冰乐");
        int drinkingType=sc.nextInt();
        System.out.println("请输入您需要的杯型：1-小杯，2-中杯，3-大杯");
        int cupSize=sc.nextInt();
        System.out.println("请输入您需要的温度：1-冰，2-去冰，3-常温，4-热");
        int tempera=sc.nextInt();
        System.out.println("请输入您需要的牛奶：1-全脂牛奶，2-燕麦奶，3-脱脂牛奶");
        int milk=sc.nextInt();
        System.out.println("请输入您需要的糖浆：0-不需要，1-香草糖浆，2-焦糖糖浆，3-摩卡酱");
        int syrup=sc.nextInt();
        int syrupAmount=0;
        if(syrup!=0){
            System.out.println("请输入您需要的糖浆份数：");
            syrupAmount=sc.nextInt();
        }
        DrinkingBuildFactory factory=new DrinkingBuildFactory(drinkingType,cupSize,tempera,milk,syrup,syrupAmount);
        Drinking drinking=factory.build();
        System.out.println("您的饮品订单信息如下："+drinking.getDescription()+","+"\n"+"共需支付"+drinking.cost()+"元");
    }

}
