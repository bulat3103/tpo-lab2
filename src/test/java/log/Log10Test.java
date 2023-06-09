package log;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
public class Log10Test {
    private static final double delta = 0.05;
    private static final double eps = 0.001;

    private static final Log log10 = new Log(LogMocks.lnMock(), 10);
    private static final String file = "C:\\Users\\admin\\IdeaProjects\\tpo-lab2\\src\\main\\resources\\csv\\output\\Log10Out.csv";

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/input/Log10In.csv")
    public void testTableValues(double value, double expected) {
        double result = log10.log(value, eps);
        assertEquals(expected, result, delta);
        log10.writeResToCSV(value, result, file);
    }

    @Test
    public void testNaNValue() {
        double result = log10.log(Double.NaN, eps);
        assertEquals(Double.NaN, result, delta);
        log10.writeResToCSV(Double.NaN, result, file);
    }

    @Test
    public void testPositiveInfValue() {
        double result = log10.log(Double.POSITIVE_INFINITY, eps);
        assertEquals(Double.POSITIVE_INFINITY, result, delta);
        log10.writeResToCSV(Double.POSITIVE_INFINITY, result, file);
    }

    @Test
    public void testNegativeInfValue() {
        double result = log10.log(Double.NEGATIVE_INFINITY, eps);
        assertEquals(Double.NaN, result, delta);
        log10.writeResToCSV(Double.NEGATIVE_INFINITY, result, file);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1, -5, -10, -100})
    public void testNegativeValues(double value) {
        double result = log10.log(value, eps);
        Assertions.assertEquals(Double.NaN, result, delta);
        log10.writeResToCSV(Double.NaN, result, file);
    }
}
