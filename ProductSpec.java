package day3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductSpec {
    private HashMap<String,Object> properties;
    public ProductSpec(Map props){
        if(props==null)
            this.properties=new HashMap<>();
        else
            this.properties=new HashMap<String,Object>(props);
    }
    public Object getProperty(String propertyName){
        return properties.get(propertyName);
    }
    public HashMap getProperties(){
        return properties;
    }
    //将该查询条件的spec与遍历中的targetSpect进行匹配，条件中的spec的每一条属性可能包含多个值list,且价格为区间
    boolean matches(ProductSpec targetSpec){//targetSpec为库存的spec
         boolean matchFlag=false;
         for(Object key :properties.keySet()){//以查询spec的Property为比较标准，有则比较
             matchFlag=false;
             String propName=(String)key;
             Object targetProp= targetSpec.getProperty(propName);//target的该属性
             List<Object> condiProp=(ArrayList<Object>)properties.get(propName);//查询条件的spec的属性
             if(propName.equals("price")){
                 for(Object ob:condiProp){
                     String priceInterval=(String) ob;
                     String[] splitRes=priceInterval.split(":");
                     double lowerPrice=Double.valueOf(splitRes[0]);
                     double upperPrice=Double.valueOf(splitRes[1]);
                     double targetPrice=(double)targetProp;
                     if(targetPrice>=lowerPrice&&targetPrice<=upperPrice){
                         matchFlag=true;
                         break;
                     }
                 }
             }else{
                 for(Object ob:condiProp){
                     if(ob.equals(targetProp)){
                         matchFlag=true;
                         break;
                     }
                 }

             }
             if(!matchFlag)//只要有一条查询条件没有匹配，则返回false
                 break;
         }
         return matchFlag;
    }

    @Override
    public String toString() {
        return "ProductSpec{" +
                "properties=" + properties +
                '}';
    }
}
