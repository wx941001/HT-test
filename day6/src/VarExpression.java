package day6.src;

import day6.src.Expression;

import java.util.HashMap;

public class VarExpression extends Expression {

    private String key;

    public VarExpression(String key) {
        this.key = key;
    }

    @Override
    public String interpreter(HashMap<String, String> var) {
        return var.get(this.key);
    }

}
