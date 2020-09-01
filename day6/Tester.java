package day6;

import day6.src.SqlParser;
import org.junit.Assert;
import org.junit.Test;

public class Tester {
    SqlParser sqlParser=new SqlParser();
    @Test
    public void test_SingleCondition(){
        String expected="select * from Customers where ( companyName <> \"htsc\" )";
        Assert.assertEquals(expected,sqlParser.parse("(companyName != \"htsc\")"));
    }
    @Test
    public void test_Or(){
        String expected="select * from Customers where ( companyName <> \"htsc\" ) or ( age <= 30 )";
        Assert.assertEquals(expected,sqlParser.parse("(companyName != \"htsc\") OR (age <=30) "));
    }
    @Test
    public void test_And(){
        String expected="select * from Customers where ( companyName == \"htsc\" ) and ( age <= 30 )";
        Assert.assertEquals(expected,sqlParser.parse("(companyName == \"htsc\") AND (age <=30) "));
    }
    @Test
    public void test_Not1(){
        String expected="select * from Customers where not(( companyName == \"htsc\" ) and (( age < 30 ) or ( name == \"张\" )))";
        Assert.assertEquals(expected,sqlParser.parse("!((companyName == \"htsc\") AND ( (age <30) OR (name == \"张\")))"));
    }
    @Test
    public void test_Not2(){
        String expected="select * from Customers where ( companyName <> \"htsc\" ) or ((not ( age <= 30 )) and ( age > 20 ))";
        Assert.assertEquals(expected,sqlParser.parse("( companyName != \"htsc\")OR((!(age <=30)) AND (age >20 ))"));
    }
    @Test
    public void test_Not3(){
        String expected="select * from Customers where (( companyName <> \"htsc\" ) and ( country == \"China\" )) or ((not ( age <= 30 )) and (( age <> 20 ) or ( contact_title == \"经理\" )))";
        Assert.assertEquals(expected,sqlParser.parse("((companyName != \"htsc\")AND (country==\"China\")) OR ((!(age <=30)) AND ((age !=20) OR(contact_title==\"经理\")))"));
    }
}
