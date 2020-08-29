import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class MapReduceSingleThread {
    private List<String> wordList=new  ArrayList<>();
    private HashMap<String,Integer> mappingMap=new HashMap<String,Integer>();
    private HashMap<String,ArrayList<Integer>> shufflingMap=new HashMap<>();
    private HashMap<String,Integer> reduceMap=new HashMap<>();

    public void processLine(String inputStr){
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
            mappingMap.put(s,1);
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
    }

    public static void main(String args[]){
        MapReduceSingleThread instance=new MapReduceSingleThread();
        long start = System.currentTimeMillis();
        instance.readTxtFile("F:\\王曦资料\\入职\\编码培训营\\words.txt");
        instance.mapping();
        instance.shuffling();
        instance.reducing();
        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;
        System.out.println("单线程版本处理时间为："+timeElapsed+"ms，共发现单词数量为："+instance.reduceMap.size());
        for(Map.Entry<String, Integer> entry : instance.reduceMap.entrySet())
            System.out.println(entry.getKey()+":"+entry.getValue());
    }
}
