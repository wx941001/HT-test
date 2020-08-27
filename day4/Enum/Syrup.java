package day4.Enum;

public enum Syrup {
    XCTJ("香草糖浆",2),JTTJ("焦糖糖浆",2),MOKAJ("摩卡酱",3);
    private String description;
    private double cost;
    Syrup(String des,double cost){
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
