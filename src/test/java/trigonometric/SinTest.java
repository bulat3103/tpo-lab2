package trigonometric;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
public class SinTest {
    private static final double delta = 0.05;
    private static final double eps = 0.001;

    private final Sin sin = new Sin();
    private static final String file = "C:\\Users\\admin\\IdeaProjects\\tpo-lab2\\src\\main\\resources\\csv\\output\\SinOut.csv";

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/input/SinIn.csv")
    public void testTableValues(double value, double expected) {
        double result = sin.sin(value, eps);
        assertEquals(expected, result, delta);
        sin.writeResToCSV(value, result, file);
    }

    @Test
    public void testNanValue() {
        double result = sin.sin(Double.NaN, eps);
        Assertions.assertEquals(Double.NaN, sin.sin(Double.NaN, eps), delta);
        sin.writeResToCSV(Double.NaN, result, file);
    }

    @Test
    public void testPositiveInfValue() {
        double result = sin.sin(Double.POSITIVE_INFINITY, eps);
        Assertions.assertEquals(Double.POSITIVE_INFINITY, sin.sin(Double.POSITIVE_INFINITY, eps), delta);
        sin.writeResToCSV(Double.POSITIVE_INFINITY, result, file);
    }

    @Test
    public void testNegativeInfValue() {
        double result = sin.sin(Double.NEGATIVE_INFINITY, eps);
        Assertions.assertEquals(Double.NaN, sin.sin(Double.NEGATIVE_INFINITY, eps), delta);
        sin.writeResToCSV(Double.NEGATIVE_INFINITY, result, file);
    }
}
