package day6.src;

import java.util.Stack;

public class SqlParser {
     static final char leftBracket ='(';
     static final char rightBracket =')';
     static final char blank = ' ';
    static final String pefix="select * from Customers where ";
    static Stack<Character> bracketStack=new Stack<Character>();//括号栈，( ）
    static Stack<String> operatorStack=new Stack<String>();//操作数栈：field和field的值
    //以及条件操作符栈：== != contains(以*代替) not_contains(以!*)
    static Stack<String> concatEnumStack=new Stack<String>();//条件之间的连接符栈：and or not
    public static String parse(String sql){
        sql=sql.replaceAll(" ","").//去除空格
                replaceAll("==","=").
                replaceAll("!=","^").
                replaceAll(">=","@").
                replaceAll("<=","#").
                replaceAll("!=","^").
                replaceAll("AND","&").
                replaceAll("OR","|");
        for(int i=0;i<sql.length();i++){
            if(sql.charAt(i)==leftBracket){
                bracketStack.push(leftBracket);
            }else if(sql.charAt(i)==rightBracket){
                //遇到右括号，弹出一个括号栈，
                // 弹出两个操作数栈和弹出一个操作数栈，拼接，检查连接符栈是否为空，
                // 不为空，则再弹出一个操作数栈，合并，入操作数栈；
                //为空，则直接入操作数栈
                bracketStack.pop();
               String first=operatorStack.pop();
               if(!operatorStack.isEmpty()&&isOperator(operatorStack.peek())){
                   String operator=operatorStack.pop();
                   String operatorLeft=operatorStack.pop();
                   String newCondition="("+" "+operatorLeft+" "+operator+" "+first+" "+")";
                   operatorStack.push(newCondition);
               }else if(!concatEnumStack.empty()){
                   String concat=concatEnumStack.pop();
                   if(concat.equals("not"))//&&bracketStack.isEmpty()//cancat是非，且已经是最外层的
                       operatorStack.push("("+concat+" "+first+")");
                   else{
                       String leftCondition=operatorStack.pop();
                       operatorStack.push("("+leftCondition+" "+concat+" "+first+")");
                   }
               }
            }else if(sql.charAt(i)=='&'){//AND和OR
                concatEnumStack.push("and");
            }else if(sql.charAt(i)=='|'){
                concatEnumStack.push("or");
            }else if(sql.charAt(i)=='!'){
                concatEnumStack.push("not");
            }else if(sql.charAt(i)=='='){
                operatorStack.push("==");
            }else if(sql.charAt(i)=='^'){
                operatorStack.push("<>");
            }else if(sql.charAt(i)=='<'|sql.charAt(i)=='>'){
                operatorStack.push(String.valueOf(sql.charAt(i)));
            }else if(sql.charAt(i)=='@'){
                operatorStack.push(">=");
            }else if(sql.charAt(i)=='#'){
                operatorStack.push("<=");
            }else {//不等号，等号 字母都入操作数/符栈
                String operatorStackElement="";
                while(i<sql.length()&&(('a'<=sql.charAt(i)&&sql.charAt(i)<='z')||('A'<=sql.charAt(i)&&sql.charAt(i)<='Z')
                        ||Character.isDigit(sql.charAt(i))||isChineseChar(sql.charAt(i))||
                        sql.charAt(i)=='"'||sql.charAt(i)=='_')){
                    operatorStackElement+=sql.charAt(i++);
                }
                --i;
                operatorStack.push(operatorStackElement);
            }
        }
        String res="";
        if(!concatEnumStack.isEmpty()||!operatorStack.isEmpty()){
            if(concatEnumStack.isEmpty()){
                res=pefix+operatorStack.pop();
            }else{
                if(operatorStack.size()==1){
                    res=pefix+concatEnumStack.pop()+operatorStack.pop();
                }else{
                    String right=operatorStack.pop();
                    String left=operatorStack.pop();
                    res=pefix+left+" "+concatEnumStack.pop()+" "+right;
                }
            }
        }
        System.out.println("operator Stack.size="+operatorStack.size());
        System.out.println("res："+res);
        return res;
    }
    public static boolean isChineseChar(char c) {
        return String.valueOf(c).matches("[\u4e00-\u9fa5]");
    }
    public static boolean isOperator(String input){
        if(input.equals("==")||input.equals("<>")||input.equals(">")||
                input.equals("<")||input.equals(">=")||input.equals("<="))
            return true;
        return false;
    }
    public static void main(String[]args){
        String testSql="(companyName != \"htsc\")";
        String res=SqlParser.parse(testSql);
        System.out.println(res);
    }
}
