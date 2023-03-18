import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.io.Writer;

public class Function {
    private final Sec sec;
    private final Csc csc;
    private final Sin sin;
    private final Ln ln;
    private final Log log;

    public Function() {
        this.sin = new Sin();
        this.csc = new Csc(this.sin);
        this.sec = new Sec(new Cos(this.sin));
        this.ln = new Ln();
        this.log = new Log(this.ln);
    }

    public Function(Sec sec, Csc csc, Sin sin, Ln ln, Log log) {
        this.sec = sec;
        this.csc = csc;
        this.sin = sin;
        this.ln = ln;
        this.log = log;
    }

    public double system(double x, double eps) {
        if (x <= 0) {
            return (sec.sec(x, eps) * sec.sec(x, eps) + csc.csc(x, eps))
                    * (sin.sin(x, eps) * sin.sin(x, eps) * sin.sin(x, eps));
        } else {
            return (((log.log(10, x, eps) / log.log(2, x, eps)) * log.log(2, x, eps))
                    * ((log.log(10, x, eps) * log.log(3, x, eps)) + log.log(5, x, eps)))
                    + (ln.ln(x, eps) - log.log(2, x, eps));
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
