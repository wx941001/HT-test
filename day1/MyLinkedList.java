public class MyLinkedList {
    ListNode head,cur;
    int size=0;
    int capacigty=0;//容量：生长周期
    ListNode[] markedArr=new ListNode[100];
    MyLinkedList(int cap){
        this.capacigty=cap;
    }
    public synchronized void add(ListNode node){
        if( markedArr[node.val-1] !=null){//已经有该数字,删除原有链表中该数字
            ListNode targetNode=markedArr[node.val-1];
            if(targetNode.next==null)//第一遍插入且已经是最新节点，无需操作
                return;
            this.delete(targetNode);//删除
        }
        //在链表头插入该节点
        //如果链表为空
        if(size==0){
            this.head=node;
            this.cur=head;
            size++;
        }else{//不为空
            if(size<capacigty){
                cur.next=node;
                node.pre=cur;
                cur=node;
                markedArr[node.val-1]=node;
                size++;
            }else{
                head=node;
                cur.next=head;
                head.pre=cur;
                cur=head;
                markedArr[node.val-1]=node;
            }
        }

    }
    private void delete(ListNode node){
        ListNode pNext=node.next;
        node.pre.next=pNext;
        pNext.pre=node.pre;
        node.pre=null;
        node.next=null;
        size--;
    }
    //按插入时间倒序排列
    void timeReversePrint(){
        for(int i=0;i<size;i++){
            System.out.println(cur.val+" 被存储的时间为"+i+"秒");
            cur=cur.pre;
        }
    }
}
