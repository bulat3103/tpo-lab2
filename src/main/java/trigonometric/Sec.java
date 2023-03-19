package trigonometric;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

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

    public void writeResToCSV(double x, double res, String file) {
        String text = x + "," + res + "\n";
        try {
            Files.write(Paths.get(file), text.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Не удалось записать в файл");
        }
    }
}
