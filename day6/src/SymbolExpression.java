package day6.src;

import day6.src.Expression;

import java.util.HashMap;

public class SymbolExpression extends Expression {

    protected Expression left;
    protected Expression right;

    public SymbolExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String interpreter(HashMap<String, String> var) {
        // TODO Auto-generated method stub
        return "";
    }

}