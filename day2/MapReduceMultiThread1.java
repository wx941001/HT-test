import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MapReduceMultiThread1 implements Runnable{
    private CountDownLatch countDownLatch;
    private int index;
    private static int cntDownLatch;
    private String filePath;
    private List<String> wordList=new  ArrayList<String>();
    private HashMap<String,Integer> mappingMap=new HashMap<String,Integer>();
    private HashMap<String,ArrayList<Integer>> shufflingMap=new HashMap<String,ArrayList<Integer>>();
    private HashMap<String,Integer> reduceMap=new HashMap<String,Integer>();
    private static ArrayList<HashMap<String,Integer>> reduceMapList=new ArrayList<HashMap<String,Integer>>();

    public MapReduceMultiThread1(String file, CountDownLatch countDownLatch,int index){
        this.filePath=file;
        this.countDownLatch=countDownLatch;
        this.index=index;
    }
    @Override
    public void run() {
        System.out.println("线程"+index+"开始运行");
        readTxtFile(filePath);
        mapping();
        shuffling();
        reducing();
        countDownLatch.countDown();
        System.out.println("线程"+index+"运行结束");
    }
    private void processLine(String inputStr){
        if(inputStr.trim()==""||inputStr==null){
            return;
        }
        inputStr=inputStr.replaceAll("\n", " ")
                .replaceAll("[^a-zA-Z' ]", " ")
                .replaceAll("[ ]+", " ")
                .replaceAll(" '", " ")
                .replaceAll("' ", " ")
                .toLowerCase();
        String[] words=inputStr.split(" ");
        wordList=Arrays.asList(words);
    }
    //获取文件下所有文件名
    public static ArrayList<String> getFilesName(String filePath){
        ArrayList<String> fileNames=new ArrayList<String>();
        File f=new File(filePath);
        if(!f.exists()){
            System.out.println(filePath+"文件夹不存在");
            return fileNames;
        }
        File[] files=f.listFiles();
        for(File file:files)
            fileNames.add(filePath+"//"+file.getName());
        return fileNames;
    }
    public void readTxtFile(String filePath){
        try {
            String encoding="GBK";
            File file=new File(filePath);
            if(file.isFile() && file.exists()){ //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file),encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                StringBuffer sb = new StringBuffer();
                String lineTxt;
                while((lineTxt = bufferedReader.readLine()) != null){
                    sb.append("\n"+lineTxt);
                }
                read.close();
                processLine(sb.toString());
            }else{
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
    }
    //mapping 以所有的单词为键，值1
    private void mapping(){
        for(String s:this.wordList){
            this.mappingMap.put(s,1);
        }
    }
    //shuffling 以不重复的单词为键 值为List:1,1,1，..
    private void shuffling(){
         for(String key : mappingMap.keySet()){
             if(shufflingMap.containsKey(key)){
                 ArrayList<Integer> tempList=shufflingMap.get(key);
                 tempList.add(1);
                 shufflingMap.put(key,tempList);
             }else{
                 ArrayList<Integer> newList=new ArrayList<Integer>();
                 newList.add(1);
                 shufflingMap.put(key,newList);
             }
         }
    }
    //reducing 以单词-频数 统计
    private void reducing(){
        for(Map.Entry<String, ArrayList<Integer>> entry : shufflingMap.entrySet()){
            String mapKey = entry.getKey();
            ArrayList mapValue = entry.getValue();
            reduceMap.put(mapKey,mapValue.size());
        }
        reduceMapList.add(reduceMap);
    }
    private static int merge(int reduceResult1,int reduceResult2){
        return reduceResult1+reduceResult2;
    }
    //整合各个局部reduce结果
    public static HashMap<String,Integer> getResult(ArrayList<HashMap<String,Integer>> reduceMapList){
        HashMap<String,Integer> reseultMap=new HashMap<>();
        for(HashMap<String,Integer> reduceMap:reduceMapList){
            for(Map.Entry<String, Integer> entry:reduceMap.entrySet()){
                String key=entry.getKey();
                Integer val=entry.getValue();
                if(reseultMap.containsKey(key)){
                    Integer temp=reseultMap.get(key);
                    reseultMap.put(key,merge(temp,val));
                }else{
                    reseultMap.put(key,val);
                }
            }
        }
        return reseultMap;
    }

    public static void main(String args[]){
        long start = System.currentTimeMillis();
        ArrayList<String> fileNames=getFilesName("F:\\王曦资料\\入职\\编码培训营\\words");
        cntDownLatch=fileNames.size();
        CountDownLatch countDownLatch = new CountDownLatch(cntDownLatch);
        ExecutorService es = Executors.newFixedThreadPool(10);
        for(int i=0;i<fileNames.size();i++){
            es.submit(new MapReduceMultiThread1(fileNames.get(i),countDownLatch,i));
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        HashMap<String,Integer> reduceMap=getResult(reduceMapList);//统计多个reduceMap结果
        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;
        System.out.println("多线程版本处理时间为："+timeElapsed+"ms，共发现单词数量为："+reduceMap.size());
        for(Map.Entry<String, Integer> entry : reduceMap.entrySet())
            System.out.println(entry.getKey()+":"+entry.getValue());
    }
}
