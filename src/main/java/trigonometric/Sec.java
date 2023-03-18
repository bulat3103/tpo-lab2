package trigonometric;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.io.Writer;

public class Sec {
    private final Cos cos;

    public Sec(Cos cos) {
        this.cos = cos;
    }

    public double sec(double x, double eps) {
        double cosVal = cos.cos(x, eps);
        if (Double.POSITIVE_INFINITY == cosVal) return Double.POSITIVE_INFINITY;
        if (Double.isNaN(cosVal) || cosVal == Double.NEGATIVE_INFINITY) return Double.NaN;
        if (Math.abs(cosVal) <= eps * 5) return Double.POSITIVE_INFINITY;
        return 1 / cosVal;
    }

    public double writeResToCSV(double x, double eps, Writer out) {
        double res = sec(x, eps);
        try (CSVPrinter printer = CSVFormat.DEFAULT.print(out)) {
            printer.printRecord(x, res);
        } catch (IOException e) {
            System.out.println("No such file");
        }
        return res;
    }
}
