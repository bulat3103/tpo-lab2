package trigonometric;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

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

    public void writeResToCSV(double x, double res, String file) {
        String text = x + "," + res + "\n";
        try {
            Files.write(Paths.get(file), text.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Не удалось записать в файл");
        }
    }
}
