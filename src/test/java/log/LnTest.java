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
    private static final String file = "C:\\Users\\admin\\IdeaProjects\\tpo-lab2\\src\\main\\resources\\csv\\output\\LnOut.csv";

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/input/LnIn.csv")
    public void testTableValues(double value, double expected) {
        double result = ln.ln(value, eps);
        assertEquals(expected, result, delta);
        ln.writeResToCSV(value, result, file);
    }

    @Test
    public void testNanValue() {
        double result = ln.ln(Double.NaN, eps);
        Assertions.assertEquals(Double.NaN, result, delta);
        ln.writeResToCSV(Double.NaN, result, file);
    }

    @Test
    public void testPositiveInfValue() {
        double result = ln.ln(Double.POSITIVE_INFINITY, eps);
        Assertions.assertEquals(Double.POSITIVE_INFINITY, result, delta);
        ln.writeResToCSV(Double.POSITIVE_INFINITY, result, file);
    }

    @Test
    public void testNegativeInfValue() {
        double result = ln.ln(Double.NEGATIVE_INFINITY, eps);
        Assertions.assertEquals(Double.NaN, result, delta);
        ln.writeResToCSV(Double.NEGATIVE_INFINITY, result, file);
    }
}
