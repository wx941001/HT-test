package day5;

import day4.Decorator.CupDecorator;
import day4.Decorator.MilkDecorator;
import day4.Decorator.SyrupDecorator;
import day4.Decorator.TemperatureDecorator;
import day4.Drinking.Cappuccino;
import day4.Drinking.Drinking;
import day4.Drinking.Frappuccino;
import day4.Enum.CupSize;
import day4.Enum.Milk;
import day4.Enum.Syrup;
import day4.Enum.Temperature;
import day5.Enum.ConcatEnum;
import day5.Enum.ConditionOperatorEnum;
import day5.Enum.FieldEnum;

import java.util.ArrayList;

public class SqlRequestBuildFactory {
    private ArrayList<ArrayList<Object>> userChoosedConditions;
    public SqlRequestBuildFactory(ArrayList<ArrayList<Object>> conditions) {
        userChoosedConditions=conditions;
    }

    public SqlRequest build(){
        SqlRequest sqlRequest=new SqlRequest();
        FieldEnum fieldEnum=FieldEnum.id;
        ConcatEnum concatEnum=ConcatEnum.AND;
        ConditionOperatorEnum conditionOperatorEnum=ConditionOperatorEnum.EQUAL;
        for(ArrayList<Object> condition:userChoosedConditions){
            int field=(int) condition.get(0);
            int conditionOperator=(int)condition.get(1);
            int concat=(int)condition.get(2);
            String fieldVal=(String)condition.get(3);
            switch (field){
                case 1:
                    fieldEnum=FieldEnum.company_name;
                    break;
                case 2:
                    fieldEnum=FieldEnum.contact_name;
                    break;
                case 3:
                    fieldEnum=FieldEnum.contact_title;
                    break;
                case 4:
                    fieldEnum=FieldEnum.region;
                    break;
                case 5:
                    fieldEnum=FieldEnum.postal_code;
                    break;
                case 6:
                    fieldEnum=FieldEnum.country;
                    break;
            }
            switch (concat){
                case 1:
                    concatEnum=ConcatEnum.AND;
                    break;
                case 2:
                    concatEnum=ConcatEnum.OR;
                    break;
            }
            switch (conditionOperator){
                case 1:
                    conditionOperatorEnum=ConditionOperatorEnum.EQUAL;
                    break;
                case 2:
                    conditionOperatorEnum=ConditionOperatorEnum.NOT_EQUAL;
                    break;
                case 3:
                    conditionOperatorEnum=ConditionOperatorEnum.CONTAINS;
                    break;
                case 4:
                    conditionOperatorEnum=ConditionOperatorEnum.NOT_CONTAINS;
                    break;
            }
            sqlRequest=new SqlDecorator(sqlRequest,fieldEnum,concatEnum,
                    conditionOperatorEnum,fieldVal);
        }
        return sqlRequest;
    }
}
