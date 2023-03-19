package log;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

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

    public void writeResToCSV(double x, double res, String file) {
        String text = x + "," + res + "\n";
        try {
            Files.write(Paths.get(file), text.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Не удалось записать в файл");
        }
    }
}
