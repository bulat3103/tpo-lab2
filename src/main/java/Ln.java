import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.io.Writer;

public class Ln {
    public double ln(double x, double eps) {
        if (Double.isNaN(x) || x < 0.0) return Double.NaN;
        else if (x == Double.POSITIVE_INFINITY) return Double.POSITIVE_INFINITY;
        else if (x == 0.0) return Double.NEGATIVE_INFINITY;
        double constant = ((x - 1) * (x - 1)) / ((x + 1) * (x + 1));
        double sum = 0;
        double cur = (x - 1) / (x + 1);
        int step = 1;
        while (Math.abs(cur) > eps / 2) {
            sum += cur;
            cur = (2 * step - 1) * cur * constant / (2 * step + 1);
            step++;
        }
        return sum * 2;
    }

    public double writeResToCSV(double x, double eps, Writer out) {
        double res = ln(x, eps);
        try (CSVPrinter printer = CSVFormat.DEFAULT.print(out)) {
            printer.printRecord(x, res);
        } catch (IOException e) {
            System.out.println("No such file");
        }
        return res;
    }
}
