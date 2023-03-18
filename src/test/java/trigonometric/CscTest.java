package trigonometric;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
public class CscTest {
    private static final double delta = 0.05;
    private static final double eps = 0.001;

    private final Csc csc = new Csc(TriMocks.sinMock());

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/input/CscIn.csv")
    public void testTableValues(double value, double expected) {
        double result = csc.csc(value, eps);
        assertEquals(expected, result, delta);
    }

    @Test
    public void testNanValue() {
        Assertions.assertEquals(Double.NaN, csc.csc(Double.NaN, eps), delta);
    }

    @Test
    public void testPositiveInfValue() {
        Assertions.assertEquals(Double.POSITIVE_INFINITY, csc.csc(Double.POSITIVE_INFINITY, eps), delta);
    }

    @Test
    public void testNegativeInfValue() {
        Assertions.assertEquals(Double.NaN, csc.csc(Double.NEGATIVE_INFINITY, eps), delta);
    }
}
