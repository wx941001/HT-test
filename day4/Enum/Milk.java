package day4.Enum;

public enum Milk {
    QZNN("全脂牛奶",2),YMN("燕麦奶",3),TZNN("脱脂牛奶",4);
    private String description;
    private double cost;
    Milk(String des,double cost){
        this.description=des;
        this.cost=cost;
    }
    public String getDescription() {
        return description;
    }

    public double getCost() {
        return cost;
    }
}
