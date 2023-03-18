import log.RightFunction;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.mockito.Mockito;
import trigonometric.Cos;
import trigonometric.LeftFunction;
import trigonometric.Sin;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class FunMocks {
    private static final double eps = 0.001;

    public static LeftFunction leftFunctionMock() {
        LeftFunction leftFunctionMock = Mockito.mock(LeftFunction.class);
        try {
            Reader lnIn = new FileReader("src/main/resources/csv/input/LeftFunctionIn.csv");
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(lnIn);
            records.forEach(record -> Mockito.when(leftFunctionMock.system(Double.parseDouble(record.get(0)), eps)).thenReturn(Double.valueOf(record.get(1))));
            Mockito.when(leftFunctionMock.system(Double.NaN, eps)).thenReturn(Double.NaN);
            Mockito.when(leftFunctionMock.system(Double.POSITIVE_INFINITY, eps)).thenReturn(Double.POSITIVE_INFINITY);
            Mockito.when(leftFunctionMock.system(Double.NEGATIVE_INFINITY, eps)).thenReturn(Double.NaN);
        } catch (IOException e) {
            System.err.println("No such file");
        }
        return leftFunctionMock;
    }

    public static RightFunction rightFunctionMock() {
        RightFunction rightFunctionMock = Mockito.mock(RightFunction.class);
        try {
            Reader lnIn = new FileReader("src/main/resources/csv/input/RightFunctionIn.csv");
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(lnIn);
            records.forEach(record -> Mockito.when(rightFunctionMock.system(Double.parseDouble(record.get(0)), eps)).thenReturn(Double.valueOf(record.get(1))));
            Mockito.when(rightFunctionMock.system(Double.NaN, eps)).thenReturn(Double.NaN);
            Mockito.when(rightFunctionMock.system(Double.POSITIVE_INFINITY, eps)).thenReturn(Double.POSITIVE_INFINITY);
            Mockito.when(rightFunctionMock.system(Double.NEGATIVE_INFINITY, eps)).thenReturn(Double.NaN);
        } catch (IOException e) {
            System.err.println("No such file");
        }
        return rightFunctionMock;
    }
}
