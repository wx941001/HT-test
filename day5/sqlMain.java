package day5;

import day5.Enum.ConcatEnum;
import day5.Enum.ConditionOperatorEnum;
import day5.Enum.FieldEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class sqlMain {
    public static void main(String[] args){
        SqlRequest sqlRequest;
        Scanner sc=new Scanner(System.in);
        boolean continueFlag=true;
        System.out.println("*******多条件查询器*******");
        while(continueFlag){

            int fieldChoose=0;
            int concatChoose=0;
            int conditionOperator=0;
            String filedVal="";
            ArrayList<ArrayList<Object>> userChoosedConditions=new ArrayList<ArrayList<Object>>();
            do{
                ArrayList<Object> choosedCondition=new ArrayList<>();
                System.out.println("请添加你要的条件字段：0-退出添加条件，1-company_name，2-contact_name，3-contact_title，4-region，5-postal_code，6-country");
                fieldChoose=sc.nextInt();
                if(fieldChoose==0)
                    break;
                System.out.println("请为该条件添加逻辑操作符：1-EQUAL，2-NOT_EQUAL，3-CONTAINS，4-NOT_CONTAINS");
                conditionOperator=sc.nextInt();
                System.out.println("请为该条件添加 条件间拼接符：1-AND，2-OR");
                concatChoose=sc.nextInt();
                System.out.println("请输入该条件的字段值：");
                filedVal=sc.next();
                choosedCondition.add(fieldChoose);
                choosedCondition.add(conditionOperator);
                choosedCondition.add(concatChoose);
                choosedCondition.add(filedVal);
                userChoosedConditions.add(choosedCondition);
            }while(fieldChoose!=0);
            SqlRequestBuildFactory factory=new SqlRequestBuildFactory(userChoosedConditions);
            sqlRequest=factory.build();
            System.out.println("生成的sql查询语句如下：");
            System.out.println(sqlRequest.getDescription());
            System.out.println("是否继续查询？yes/no");
            String input =sc.next();
            if(input.equals("yes")){
                continueFlag=true;
                SqlDecorator.isFirstAnd=true;
            }
            else
                continueFlag=false;
        }
    }
}
