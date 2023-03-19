package trigonometric;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
public class CosTest {
    private static final double delta = 0.05;
    private static final double eps = 0.001;

    private final Cos cos = new Cos(TriMocks.sinMock());
    private static final String file = "C:\\Users\\admin\\IdeaProjects\\tpo-lab2\\src\\main\\resources\\csv\\output\\CosOut.csv";

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/input/CosIn.csv")
    public void testTableValues(double value, double expected) {
        double result = cos.cos(value, eps);
        assertEquals(expected, result, delta);
        cos.writeResToCSV(value, result, file);
    }

    @Test
    public void testNanValue() {
        double result = cos.cos(Double.NaN, eps);
        assertEquals(Double.NaN, result, delta);
        cos.writeResToCSV(Double.NaN, result, file);
    }

    @Test
    public void testPositiveInfValue() {
        double result = cos.cos(Double.POSITIVE_INFINITY, eps);
        Assertions.assertEquals(Double.POSITIVE_INFINITY, result, delta);
        cos.writeResToCSV(Double.POSITIVE_INFINITY, result, file);
    }

    @Test
    public void testNegativeInfValue() {
        double result = cos.cos(Double.NEGATIVE_INFINITY, eps);
        Assertions.assertEquals(Double.NaN, result, delta);
        cos.writeResToCSV(Double.NEGATIVE_INFINITY, result, file);
    }
}
