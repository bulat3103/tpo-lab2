import log.RightFunction;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import trigonometric.LeftFunction;

import java.io.IOException;
import java.io.Writer;

public class Function {
    private final LeftFunction leftFunction;
    private final RightFunction rightFunction;

    public Function(LeftFunction leftFunction, RightFunction rightFunction) {
        this.leftFunction = leftFunction;
        this.rightFunction = rightFunction;
    }

    public double system(double x, double eps) {
        if (x <= 0) {
            return leftFunction.system(x, eps);
        } else {
            return rightFunction.system(x, eps);
        }
    }

    public double writeResToCSV(double x, double eps, Writer out) {
        double res = system(x, eps);
        try (CSVPrinter printer = CSVFormat.DEFAULT.print(out)) {
            printer.printRecord(x, res);
        } catch (IOException e) {
            System.out.println("No such file");
        }
        return res;
    }
}
