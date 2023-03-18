package log;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
public class Log3Test {
    private static final double delta = 0.05;
    private static final double eps = 0.001;

    private static final Log log3 = new Log(LogMocks.lnMock(), 3);

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/input/Log3In.csv")
    public void testTableValues(double value, double expected) {
        double result = log3.log(value, eps);
        assertEquals(expected, result, delta);
    }

    @Test
    public void testNaNValue() {
        double result = log3.log(Double.NaN, eps);
        assertEquals(Double.NaN, result, delta);
    }

    @Test
    public void testPositiveInfValue() {
        double result = log3.log(Double.POSITIVE_INFINITY, eps);
        assertEquals(Double.POSITIVE_INFINITY, result, delta);
    }

    @Test
    public void testNegativeInfValue() {
        double result = log3.log(Double.NEGATIVE_INFINITY, eps);
        assertEquals(Double.NaN, result, delta);
    }
}
