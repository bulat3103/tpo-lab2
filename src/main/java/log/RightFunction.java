package log;

import log.Ln;
import log.Log;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.io.Writer;

public class RightFunction {
    private final Ln ln;
    private final Log log2;
    private final Log log3;
    private final Log log5;
    private final Log log10;

    public RightFunction(Ln ln, Log log2, Log log3, Log log5, Log log10) {
        this.ln = ln;
        this.log2 = log2;
        this.log3 = log3;
        this.log5 = log5;
        this.log10 = log10;
    }

    public double system(double x, double eps) {
        return (((log10.log(x, eps) / log2.log(x, eps)) * log2.log(x, eps))
                * ((log10.log(x, eps) * log3.log(x, eps)) + log5.log(x, eps)))
                + (ln.ln(x, eps) - log2.log(x, eps));
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
