package log;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
public class LnTest {
    private static final double delta = 0.05;
    private static final double eps = 0.001;

    private final Ln ln = new Ln();

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/input/LnIn.csv")
    public void testTableValues(double value, double expected) {
        double result = ln.ln(value, eps);
        assertEquals(expected, result, delta);
    }

    @Test
    public void testNanValue() {
        Assertions.assertEquals(Double.NaN, ln.ln(Double.NaN, eps), delta);
    }

    @Test
    public void testPositiveInfValue() {
        Assertions.assertEquals(Double.POSITIVE_INFINITY, ln.ln(Double.POSITIVE_INFINITY, eps), delta);
    }

    @Test
    public void testNegativeInfValue() {
        Assertions.assertEquals(Double.NaN, ln.ln(Double.NEGATIVE_INFINITY, eps), delta);
    }
}
