package log;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Log {
    private final Ln ln;
    private final int base;

    public Log(Ln ln, int base) {
        this.ln = ln;
        this.base = base;
    }

    public double log(double b, double eps) {
        return ln.ln(b, eps) / ln.ln(base, eps);
    }

    public void writeResToCSV(double x, double res, String file) {
        String text = x + "," + res + "\n";
        try {
            Files.write(Paths.get(file), text.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Не удалось записать в файл");
        }
    }
}
