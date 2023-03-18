import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.io.Writer;

public class Log {
    private final Ln ln;

    public Log() {
        this.ln = new Ln();
    }

    public Log(Ln ln) {
        this.ln = ln;
    }

    public double log(double a, double b, double eps) {
        return ln.ln(b, eps) / ln.ln(a, eps);
    }

    public double writeResToCSV(double a, double x, double eps, Writer out) {
        double res = log(a, x, eps);
        try (CSVPrinter printer = CSVFormat.DEFAULT.print(out)) {
            printer.printRecord(x, res);
        } catch (IOException e) {
            System.out.println("No such file");
        }
        return res;
    }
}
