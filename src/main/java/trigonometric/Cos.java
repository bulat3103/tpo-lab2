package trigonometric;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Cos {
    private final Sin sin;

    public Cos(Sin sin) {
        this.sin = sin;
    }

    public double cos(double x, double eps) {
        double x2 = x;
        if (Double.POSITIVE_INFINITY == x) return Double.POSITIVE_INFINITY;
        if (Double.NEGATIVE_INFINITY == x) return Double.NaN;
        if (x > Math.PI) {
            while (x > Math.PI) x -= 2 * Math.PI;
        }
        if (x < -Math.PI) {
            while (x < -Math.PI) x += 2 * Math.PI;
        }
        double result = Math.sqrt(1 - sin.sin(x2, eps) * sin.sin(x2, eps));
        result = (x > Math.PI / 2 || x < -Math.PI / 2) ? -1 * result : result;
        if (Math.abs(result) > 1) return Double.NaN;
        if (Math.abs(result) <= eps) return 0;
        return result;
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
