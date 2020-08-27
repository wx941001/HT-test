package day4.Tester;

import day4.Drinking.Drinking;
import day4.DrinkingBuildFactory;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

@SuppressWarnings("deprecation")
public class MyTester {

	@Test
	public void testTermLoanCapital() {
		DrinkingBuildFactory factory1=new DrinkingBuildFactory(1,1,2,1,1,2);
		Drinking drinking1=factory1.build();
		DrinkingBuildFactory factory2=new DrinkingBuildFactory(1,1,3,1,1,1);
		Drinking drinking2=factory2.build();
		DrinkingBuildFactory factory3=new DrinkingBuildFactory(1,1,3,1,0,0);
		Drinking drinking3=factory3.build();
		DrinkingBuildFactory factory4=new DrinkingBuildFactory(2,3,3,1,2,1);
		Drinking drinking4=factory4.build();
		DrinkingBuildFactory factory5=new DrinkingBuildFactory(1,3,2,2,3,3);
		Drinking drinking5=factory5.build();
		assertEquals("卡布奇诺，小杯，常温，加全脂牛奶，加香草糖浆*2", 18, drinking1.cost(),0.0);
		assertEquals("卡布奇诺，小杯，常温，加全脂牛奶，加香草糖浆*1", 16, drinking2.cost(),0.0);
		assertEquals("卡布奇诺，小杯，常温，加全脂牛奶", 14, drinking3.cost(),0.0);
		assertEquals("星冰乐，大杯，去冰，加燕麦牛奶，加焦糖酱*1",28 , drinking4.cost(),0.0);
		assertEquals("卡布奇诺，大杯，去冰，加燕麦牛奶，加摩卡酱*3", 26, drinking5.cost(),0.0);
	}
}
