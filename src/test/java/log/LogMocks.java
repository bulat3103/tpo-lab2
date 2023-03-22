package log;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.mockito.Mockito;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class LogMocks {
    private static final double eps = 0.001;

    public static Ln lnMock() {
        Ln lnMock = Mockito.mock(Ln.class);
        try {
            Reader lnIn = new FileReader("src/main/resources/csv/input/LnIn.csv");
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(lnIn);
            records.forEach(record -> Mockito.when(lnMock.ln(Double.parseDouble(record.get(0)), eps)).thenReturn(Double.valueOf(record.get(1))));
            Mockito.when(lnMock.ln(Double.NaN, eps)).thenReturn(Double.NaN);
            Mockito.when(lnMock.ln(Double.POSITIVE_INFINITY, eps)).thenReturn(Double.POSITIVE_INFINITY);
            Mockito.when(lnMock.ln(Double.NEGATIVE_INFINITY, eps)).thenReturn(Double.NaN);
            double[] negativeDoubles = new double[]{-1, -5, -10, -100};
            for (double d : negativeDoubles) {
                Mockito.when(lnMock.ln(d, eps)).thenReturn(Double.NaN);
            }
        } catch (IOException e) {
            System.err.println("No such file");
        }
        return lnMock;
    }

    public static Log logMock(int base, String fileName) {
        Log logMock = Mockito.mock(Log.class);
        try {
            Reader logIn = new FileReader("src/main/resources/csv/input/" + fileName);
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(logIn);
            records.forEach(record -> Mockito.when(logMock.log(Double.parseDouble(record.get(0)), eps)).thenReturn(Double.valueOf(record.get(1))));
            Mockito.when(logMock.log(Double.NaN, eps)).thenReturn(Double.NaN);
            Mockito.when(logMock.log(Double.POSITIVE_INFINITY, eps)).thenReturn(Double.POSITIVE_INFINITY);
            Mockito.when(logMock.log(Double.NEGATIVE_INFINITY, eps)).thenReturn(Double.NaN);
        } catch (IOException e) {
            System.err.println("No such file");
        }
        return logMock;
    }
}
