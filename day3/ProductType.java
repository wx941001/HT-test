package day3;

public enum ProductType {
    DESKTOP,PC,SERVER;
    public String toString() {
        switch (this){
            case DESKTOP:return "Desktop";
            case PC:return "pc";
            case SERVER:return "server";
            default:return "Unspecified";
        }
    }
}
