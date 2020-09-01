package day6.src;

import java.util.HashMap;

public class NotExpression extends SymbolExpression {
    public NotExpression(Expression left, Expression right) {
        super(left, right);
    }
    public String interpreter(HashMap<String, String> var) {
        return super.left.interpreter(var) + "=="+super.right.interpreter(var);
    }
}
