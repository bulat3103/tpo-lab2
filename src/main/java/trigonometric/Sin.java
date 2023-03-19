package trigonometric;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Sin {
    public double sin(double x, double eps) {
        if (Double.POSITIVE_INFINITY == x) return Double.POSITIVE_INFINITY;
        if (Double.NEGATIVE_INFINITY == x || Double.isNaN(x)) return Double.NaN;
        if (x >= 0) {
            while (x > Math.PI * 2) x -= Math.PI * 2;
        } else if (x < 0) {
            while (x < Math.PI * 2) x += Math.PI * 2;
        }
        double fact = 1;
        double result_l = 1;
        double result = 0;
        double pow = x;
        int sign = 1;
        int count = 1;
        while (Math.abs(result_l - result) > eps) {
            fact /= count;
            result_l = result;
            result += sign * fact * pow;
            sign = -sign;
            fact /= (count + 1);
            pow *= x * x;
            count += 2;
        }
        if (Math.abs(result) - eps > 1) return Double.NaN;
        if (Math.abs(result) < eps) return 0;
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
