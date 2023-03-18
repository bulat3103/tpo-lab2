import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.io.Writer;

public class Sin {
    public double sin(double x, double eps) {
        if (Double.POSITIVE_INFINITY == x || Double.NEGATIVE_INFINITY == x) return Double.NaN;
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
        if (Math.abs(result) > 1) return Double.NaN;
        if (Math.abs(result) < eps) return 0;
        return result;
    }

    public double writeResToCSV(double x, double eps, Writer out) {
        double res = sin(x, eps);
        try (CSVPrinter printer = CSVFormat.DEFAULT.print(out)) {
            printer.printRecord(x, res);
        } catch (IOException e) {
            System.out.println("No such file");
        }
        return res;
    }
}
