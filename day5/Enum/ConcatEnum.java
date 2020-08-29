package day5.Enum;

public enum ConcatEnum {
    AND,OR;
    @Override
    public String toString() {
        if(this.equals(ConcatEnum.AND))
            return "and";
        else if(this.equals(ConcatEnum.OR))
            return "or";
        return "";
    }
}
