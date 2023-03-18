package trigonometric;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
public class SecTest {
    private static final double delta = 0.05;
    private static final double eps = 0.001;
    private final Sec sec = new Sec(TriMocks.cosMock());

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/input/SecIn.csv")
    public void testTableValues(double value, double expected) {
        double result = sec.sec(value, eps);
        assertEquals(expected, result, delta);
    }

    @Test
    public void testNanValue() {
        Assertions.assertEquals(Double.NaN, sec.sec(Double.NaN, eps), delta);
    }

    @Test
    public void testPositiveInfValue() {
        Assertions.assertEquals(Double.POSITIVE_INFINITY, sec.sec(Double.POSITIVE_INFINITY, eps), delta);
    }

    @Test
    public void testNegativeInfValue() {
        Assertions.assertEquals(Double.NaN, sec.sec(Double.NEGATIVE_INFINITY, eps), delta);
    }
}
