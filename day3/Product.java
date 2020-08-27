package day3;

public class Product {
    private String serialNumber;
    private Double price;
    private ProductSpec productSpec;
    Product(String serialNumber,double price,ProductSpec productSpec){
        this.price=price;
        this.serialNumber=serialNumber;
        productSpec.getProperties().put("price",price);
        this.productSpec=productSpec;
    }
    public String getSerialNumber() {
        return serialNumber;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public ProductSpec getProductSpec() {
        return productSpec;
    }

    @Override
    public String toString() {
        return "Product{" +
                "serialNumber='" + serialNumber + '\'' +
                ", price=" + price +
                ", productSpec=" + productSpec +
                '}';
    }
}
