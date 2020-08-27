package day4.Drinking;

public abstract class Drinking {
    String description = "未选饮料种类";//咖啡描述，此时为未知咖啡
    public String getDescription()	//获取饮料描述的方法
    {
        return description;
    }
    public abstract double cost();	//抽象方法，获取价格金额
}
