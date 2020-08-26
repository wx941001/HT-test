package day3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
* 系统库存中的productSpec的map中的值都是单值
* 查询的spec中map的值都是一个List<>
* */
public class ProductTest {
    public static void main(String[]args){
        ProductStorageManager storageManager=new ProductStorageManager();
        initStorages(storageManager);//初始化库存，添加商品
        HashMap<String,Object> props=new HashMap<>();
        List<Object> typeList=new ArrayList<>();
        typeList.add(ProductType.DESKTOP);
        List<String> sizeList=new ArrayList<>();
        sizeList.add("14");
        sizeList.add("15");
        List<String> cpuList=new ArrayList<>();
        cpuList.add("Intel");
        List<String> priceIntervalList=new ArrayList<>();
        priceIntervalList.add("2000:5000");
        props.put("ProductType",typeList);
        props.put("Size",sizeList);
        props.put("cpu",cpuList);
        props.put("price",priceIntervalList);//查询时的price属性传string："最低价格:最高价格"
        ProductSpec querySpec=new ProductSpec(props);
        List<Product> queryRes=storageManager.search(querySpec);
        for(Product p:queryRes)
            System.out.print(p);
    }
    static void initStorages(ProductStorageManager pdtStorageManager){
        HashMap<String,Object> props1=new HashMap<>();
        props1.put("ProductType",ProductType.DESKTOP);
        props1.put("Size","14");
        props1.put("cpu","Intel");
        ProductSpec spec1=new ProductSpec(props1);

        HashMap<String,Object> props2=new HashMap<>();
        props2.put("ProductType",ProductType.DESKTOP);
        props2.put("Size","13");
        props2.put("cpu","Intel");
        ProductSpec spec2=new ProductSpec(props2);

        HashMap<String,Object> props3=new HashMap<>();
        props3.put("ProductType",ProductType.PC);
        props3.put("Size","20");
        props3.put("cpu","Intel");
        ProductSpec spec3=new ProductSpec(props3);

        HashMap<String,Object> props4=new HashMap<>();
        props4.put("ProductType",ProductType.PC);
        props4.put("Size","20");
        props4.put("cpu","AMD");
        ProductSpec spec4=new ProductSpec(props4);

        HashMap<String,Object> props5=new HashMap<>();
        props5.put("ProductType",ProductType.SERVER);
        props5.put("Size","15");
        props5.put("cpu","Intel");
        ProductSpec spec5=new ProductSpec(props5);

        HashMap<String,Object> props6=new HashMap<>();
        props6.put("ProductType",ProductType.SERVER);
        props6.put("Size","16");
        props6.put("cpu","AMD");
        ProductSpec spec6=new ProductSpec(props6);
        pdtStorageManager.addProduct(3000,spec1);
        pdtStorageManager.addProduct(5000,spec2);
        pdtStorageManager.addProduct(4000,spec3);
        pdtStorageManager.addProduct(6000,spec4);
        pdtStorageManager.addProduct(10000,spec5);
        pdtStorageManager.addProduct(20000,spec6);
    }
}
