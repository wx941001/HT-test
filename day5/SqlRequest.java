package day5;

public class SqlRequest {
    static final String tableName="Customers";
    static final String description = "select * from "+tableName;//sql语句，默认为
    public String getDescription()
    {
        return description;
    }

}
