package day5;

import day5.Enum.ConcatEnum;
import day5.Enum.ConditionOperatorEnum;
import day5.Enum.FieldEnum;

public class sqlMain {
    public static void main(String[] args){
        SqlRequest sqlRequest=new SqlRequest();
        sqlRequest=new SqlDecorator(sqlRequest,FieldEnum.company_name,ConcatEnum.AND,
                ConditionOperatorEnum.EQUAL,"华泰");
        sqlRequest=new SqlDecorator(sqlRequest,FieldEnum.company_name,ConcatEnum.OR,
                ConditionOperatorEnum.EQUAL,"南京证券");
//        sqlRequest=new SqlDecorator(sqlRequest,FieldEnum.country,ConcatEnum.AND,
//                ConditionOperatorEnum.CONTAINS,"m");
//        sqlRequest=new SqlDecorator(sqlRequest,FieldEnum.postal_code,ConcatEnum.AND,
//                ConditionOperatorEnum.NOT_CONTAINS,"00");
        sqlRequest=new SqlDecorator(sqlRequest,FieldEnum.contact_name,ConcatEnum.AND,
                ConditionOperatorEnum.CONTAINS,"王");
        sqlRequest=new SqlDecorator(sqlRequest,FieldEnum.contact_title,ConcatEnum.AND,
                ConditionOperatorEnum.EQUAL,"小菜鸟");
        sqlRequest=new SqlDecorator(sqlRequest,FieldEnum.contact_name,ConcatEnum.AND,
                ConditionOperatorEnum.NOT_CONTAINS,"工");
        System.out.print(sqlRequest.getDescription());
    }
}
