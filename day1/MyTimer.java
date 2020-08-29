import java.util.Random;

public class MyTimer {
    public static void main(String args[]){
        Random random=new Random();
        MyLinkedList list=new MyLinkedList(60);
        for(int i=0;i<150;i++){
            try {
                Thread.sleep(1000); //1000 毫秒
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            int newInt=random.nextInt(100)+1;
            ListNode node=new ListNode(newInt);
            System.out.println(node.val+"在第"+(i+1)+"秒插入");
            list.add(node);

        }
        System.out.println("list.size="+list.size+"-----倒序打印");
        list.timeReversePrint();
    }

}
