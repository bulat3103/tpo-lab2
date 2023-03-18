import log.Ln;
import log.Log;
import log.RightFunction;
import trigonometric.*;

public class TestMain {
    public static void main(String[] args) {
        Sin sin = new Sin();
        Cos cos = new Cos(sin);
        Sec sec = new Sec(cos);
        Csc csc = new Csc(sin);
        Ln ln = new Ln();
        Log log2 = new Log(ln, 2);
        Log log3 = new Log(ln, 3);
        Log log5 = new Log(ln, 5);
        Log log10 = new Log(ln, 10);
        LeftFunction leftFunction = new LeftFunction(sec, csc, sin);
        RightFunction rightFunction = new RightFunction(ln, log2, log3, log5, log10);
        Function function = new Function(leftFunction, rightFunction);
        double result = function.system(0.25, 0.001);
        System.out.println(result);
    }
}
