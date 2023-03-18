import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.Mockito;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class FunctionTest {
    public static final double eps = 0.1;
    public static final double delta = 0.1;
    public static Sec secMock;
    public static Cos cosMock;
    public static Sin sinMock;
    public static Csc cscMock;
    public static Ln lnMock;
    public static Log logMock;

    public static Reader secIn;
    public static Reader cosIn;
    public static Reader sinIn;
    public static Reader cscIn;
    public static Reader lnIn;
    public static Reader log2In;
    public static Reader log3In;
    public static Reader log5In;
    public static Reader log10In;

    @BeforeAll
    public static void init() {
        secMock = Mockito.mock(Sec.class);
        cosMock = Mockito.mock(Cos.class);
        sinMock = Mockito.mock(Sin.class);
        cscMock = Mockito.mock(Csc.class);
        lnMock = Mockito.mock(Ln.class);
        logMock = Mockito.mock(Log.class);
        try {
            secIn = new FileReader("src/main/resources/csv/input/SecIn.csv");
            cosIn = new FileReader("src/main/resources/csv/input/CosIn.csv");
            sinIn = new FileReader("src/main/resources/csv/input/SinIn.csv");
            cscIn = new FileReader("src/main/resources/csv/input/CscIn.csv");
            lnIn = new FileReader("src/main/resources/csv/input/LnIn.csv");
            log2In = new FileReader("src/main/resources/csv/input/Log2In.csv");
            log3In = new FileReader("src/main/resources/csv/input/Log3In.csv");
            log5In = new FileReader("src/main/resources/csv/input/Log5In.csv");
            log10In = new FileReader("src/main/resources/csv/input/Log10In.csv");
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(secIn);
            records.forEach(record -> Mockito.when(secMock.sec(Double.parseDouble(record.get(0)), eps)).thenReturn(Double.valueOf(record.get(1))));
            records = CSVFormat.DEFAULT.parse(cosIn);
            records.forEach(record -> Mockito.when(cosMock.cos(Double.parseDouble(record.get(0)), eps)).thenReturn(Double.valueOf(record.get(1))));
            records = CSVFormat.DEFAULT.parse(sinIn);
            records.forEach(record -> Mockito.when(sinMock.sin(Double.parseDouble(record.get(0)), eps)).thenReturn(Double.valueOf(record.get(1))));
            records = CSVFormat.DEFAULT.parse(cscIn);
            records.forEach(record -> Mockito.when(cscMock.csc(Double.parseDouble(record.get(0)), eps)).thenReturn(Double.valueOf(record.get(1))));
            records = CSVFormat.DEFAULT.parse(lnIn);
            records.forEach(record -> Mockito.when(lnMock.ln(Double.parseDouble(record.get(0)), eps)).thenReturn(Double.valueOf(record.get(1))));
            records = CSVFormat.DEFAULT.parse(log2In);
            records.forEach(record -> Mockito.when(logMock.log(2, Double.parseDouble(record.get(0)), eps)).thenReturn(Double.valueOf(record.get(1))));
            records = CSVFormat.DEFAULT.parse(log3In);
            records.forEach(record -> Mockito.when(logMock.log(3, Double.parseDouble(record.get(0)), eps)).thenReturn(Double.valueOf(record.get(1))));
            records = CSVFormat.DEFAULT.parse(log5In);
            records.forEach(record -> Mockito.when(logMock.log(5, Double.parseDouble(record.get(0)), eps)).thenReturn(Double.valueOf(record.get(1))));
            records = CSVFormat.DEFAULT.parse(log10In);
            records.forEach(record -> Mockito.when(logMock.log(10, Double.parseDouble(record.get(0)), eps)).thenReturn(Double.valueOf(record.get(1))));
        } catch (IOException e) {
            System.err.println("No such file");
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/input/SystemIn.csv")
    void testSystemWithMocks(double value, double expected) {
        Function function = new Function(secMock, cscMock, sinMock, lnMock, logMock);
        Assertions.assertEquals(expected, function.system(value, eps), delta);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/input/SystemIn.csv")
    void testWithSec(double value, double expected) {
        Function function = new Function(new Sec(cosMock), cscMock, sinMock, lnMock, logMock);
        Assertions.assertEquals(expected, function.system(value, eps), delta);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/input/SystemIn.csv")
    void testWithCos(double value, double expected) {
        Function function = new Function(new Sec(new Cos(sinMock)), cscMock, sinMock, lnMock, logMock);
        Assertions.assertEquals(expected, function.system(value, eps), delta);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/input/SystemIn.csv")
    void testWithCsc(double value, double expected) {
        Function function = new Function(secMock, new Csc(sinMock), sinMock, lnMock, logMock);
        Assertions.assertEquals(expected, function.system(value, eps), delta);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/input/SystemIn.csv")
    void testWithSin(double value, double expected) {
        Sin sin = new Sin();
        Function function = new Function(new Sec(new Cos(sin)), new Csc(sin), sin, lnMock, logMock);
        Assertions.assertEquals(expected, function.system(value, eps), delta);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/input/SystemIn.csv")
    void testWithLog(double value, double expected) {
        Function function = new Function(secMock, cscMock, sinMock, lnMock, new Log(lnMock));
        Assertions.assertEquals(expected, function.system(value, eps), delta);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/input/SystemIn.csv")
    void testWithLn(double value, double expected) {
        Function function = new Function(secMock, cscMock, sinMock, new Ln(), new Log());
        Assertions.assertEquals(expected, function.system(value, eps), delta);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/input/SystemIn.csv")
    void testWithAll(double value, double expected) {
        Function function = new Function();
        Assertions.assertEquals(expected, function.system(value, eps), delta);
    }
}
