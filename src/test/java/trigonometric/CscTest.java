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
    private static final String file = "C:\\Users\\admin\\IdeaProjects\\tpo-lab2\\src\\main\\resources\\csv\\output\\CscOut.csv";

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/input/CscIn.csv")
    public void testTableValues(double value, double expected) {
        double result = csc.csc(value, eps);
        assertEquals(expected, result, delta);
        csc.writeResToCSV(value, result, file);
    }

    @Test
    public void testNanValue() {
        double result = csc.csc(Double.NaN, eps);
        Assertions.assertEquals(Double.NaN, result, delta);
        csc.writeResToCSV(Double.NaN, result, file);
    }

    @Test
    public void testPositiveInfValue() {
        double result = csc.csc(Double.POSITIVE_INFINITY, eps);
        Assertions.assertEquals(Double.POSITIVE_INFINITY, result, delta);
        csc.writeResToCSV(Double.POSITIVE_INFINITY, result, file);
    }

    @Test
    public void testNegativeInfValue() {
        double result = csc.csc(Double.NEGATIVE_INFINITY, eps);
        Assertions.assertEquals(Double.NaN, result, delta);
        csc.writeResToCSV(Double.NEGATIVE_INFINITY, result, file);
    }
}
