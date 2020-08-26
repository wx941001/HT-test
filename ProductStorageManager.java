package day3;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class ProductStorageManager {
    private List<Product> storages=new ArrayList<>();
    private String serialNumber=generateUUID();
    public void addProduct(double price,ProductSpec spec){
        Product product=new Product(serialNumber,price,spec);
        storages.add(product);
    }
    //根据条件spec去库存中查询匹配的产品
    public List<Product> search(ProductSpec spec){
        ArrayList<Product> matchedProductList=new ArrayList<>();
        for(Product prd:storages){
            if(spec.matches(prd.getProductSpec()))
                matchedProductList.add(prd);
        }
        return matchedProductList;
    }
    String generateUUID(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String date=df.format(new Date()).toString();
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        return  uuid+date;
    }
}
