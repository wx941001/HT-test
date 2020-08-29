package day5;

import day5.Enum.ConcatEnum;
import day5.Enum.ConditionOperatorEnum;
import day5.Enum.FieldEnum;

public class SqlDecorator extends Decorator {
    SqlRequest sqlRequest;
    FieldEnum field;
    ConcatEnum concat;
    ConditionOperatorEnum conditionOperator;
    String conditionVal;
    static final String blank=" ";
    static boolean isFirstAnd=true;
    @Override
    public String getDescription() {
        return addBrackets(sqlRequest.getDescription()+concatField());
    }

    public SqlDecorator(SqlRequest sqlRequest, FieldEnum field, ConcatEnum concat, ConditionOperatorEnum conditionOperator, String conditionVal) {
        this.sqlRequest = sqlRequest;
        this.field = field;
        this.concat = concat;
        this.conditionOperator = conditionOperator;
        this.conditionVal = conditionVal;
    }

    private String concatField(){
        String concatRes="";
        switch (conditionOperator){
            case EQUAL:
                concatRes= blank+convertConcat(concat)+blank+field+blank+"="+blank+convertConditionVal(conditionVal);
                break;
            case NOT_EQUAL:
                concatRes= blank+convertConcat(concat)+blank+field+blank+"!="+blank+convertConditionVal(conditionVal);
                break;
            case CONTAINS:
                concatRes= blank+convertConcat(concat)+blank+field+blank+"like"+blank+convertConditionVal("%"+conditionVal+"%");
                break;
            case NOT_CONTAINS:
                concatRes= blank+convertConcat(concat)+blank+field+blank+"not like"+blank+convertConditionVal("%"+conditionVal+"%");
                break;
        }
        return concatRes;
    }
    private String convertConditionVal(String input){
        return "\""+input+"\"";
    }
    //转换拼接符
    private String convertConcat(ConcatEnum concat){
        if(isFirstAnd){//第一个and/OR需要转成where
            isFirstAnd=false;
            return "where";
        }
        return concat.toString();
    }
    private String addBrackets(String sql){
        String s1=sql.substring(0,sql.indexOf("where")+5);
        String s2=sql.substring(sql.indexOf("where")+5,sql.length());
        return s1+"("+s2+")";

    }
}
