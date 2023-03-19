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
    private static final String file = "C:\\Users\\admin\\IdeaProjects\\tpo-lab2\\src\\main\\resources\\csv\\output\\SecOut.csv";

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/input/SecIn.csv")
    public void testTableValues(double value, double expected) {
        double result = sec.sec(value, eps);
        assertEquals(expected, result, delta);
        sec.writeResToCSV(value, result, file);
    }

    @Test
    public void testNanValue() {
        double result = sec.sec(Double.NaN, eps);
        Assertions.assertEquals(Double.NaN, result, delta);
        sec.writeResToCSV(Double.NaN, result, file);
    }

    @Test
    public void testPositiveInfValue() {
        double result = sec.sec(Double.POSITIVE_INFINITY, eps);
        Assertions.assertEquals(Double.POSITIVE_INFINITY, result, delta);
        sec.writeResToCSV(Double.POSITIVE_INFINITY, result, file);
    }

    @Test
    public void testNegativeInfValue() {
        double result = sec.sec(Double.NEGATIVE_INFINITY, eps);
        Assertions.assertEquals(Double.NaN, result, delta);
        sec.writeResToCSV(Double.NEGATIVE_INFINITY, result, file);
    }
}
