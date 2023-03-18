package trigonometric;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.io.Writer;

public class LeftFunction {
    private final Sec sec;
    private final Csc csc;
    private final Sin sin;

    public LeftFunction(Sec sec, Csc csc, Sin sin) {
        this.sec = sec;
        this.csc = csc;
        this.sin = sin;
    }

    public double system(double x, double eps) {
        return (sec.sec(x, eps) * sec.sec(x, eps) + csc.csc(x, eps))
                * (sin.sin(x, eps) * sin.sin(x, eps) * sin.sin(x, eps));
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
