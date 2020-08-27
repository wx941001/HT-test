package day4.Enum;

public enum Temperature {
    ICE("冰",0),DE_ICE("去冰",0),NORMAL("常温",0),HOT("热",0);
    private String description;
    private double cost;
    Temperature(String des,double cost){
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
