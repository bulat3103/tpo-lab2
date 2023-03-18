import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.io.Writer;

public class Csc {
    private final Sin sin;

    public Csc() {
        this.sin = new Sin();
    }

    public Csc(Sin sin) {
        this.sin = sin;
    }

    public double csc(double x, double eps) {
        double sinVal = sin.sin(x, eps);
        if (Double.isNaN(sinVal)) return Double.NaN;
        return 1 / sinVal;
    }

    public double writeResToCSV(double x, double eps, Writer out) {
        double res = csc(x, eps);
        try (CSVPrinter printer = CSVFormat.DEFAULT.print(out)) {
            printer.printRecord(x, res);
        } catch (IOException e) {
            System.out.println("No such file");
        }
        return res;
    }
}
