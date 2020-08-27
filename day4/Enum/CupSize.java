package day4.Enum;

public enum CupSize {
    SMALL("小杯",2.0),MIDDLE("中杯",3.0),BIG("大杯",4.0);
    private String description;
    private double cost;
    CupSize(String des,double cost){
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
