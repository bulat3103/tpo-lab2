package log;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
public class RightFunctionTest {
    private static final double delta = 0.5;
    private static final double eps = 0.001;

    private final Ln lnMock = LogMocks.lnMock();
    private final Log log2Mock = LogMocks.logMock(2, "Log2In.csv");
    private final Log log3Mock = LogMocks.logMock(3, "Log3In.csv");
    private final Log log5Mock = LogMocks.logMock(5, "Log5In.csv");
    private final Log log10Mock = LogMocks.logMock(10, "Log10In.csv");
    private final Ln ln = new Ln();
    private final Log log2 = new Log(ln, 2);
    private final Log log3 = new Log(ln, 3);
    private final Log log5 = new Log(ln, 5);
    private final Log log10 = new Log(ln, 10);

    private RightFunction rightFunction;

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/input/RightFunctionIn.csv")
    public void allMockTest(double value, double expected) {
        rightFunction = new RightFunction(lnMock, log2Mock, log3Mock, log5Mock, log10Mock);
        double result = rightFunction.system(value, eps);
        assertEquals(expected, result, delta);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/input/RightFunctionIn.csv")
    public void lnStubTest(double value, double expected) {
        rightFunction = new RightFunction(ln, log2Mock, log3Mock, log5Mock, log10Mock);
        double result = rightFunction.system(value, eps);
        assertEquals(expected, result, delta);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/input/RightFunctionIn.csv")
    public void log2StubTest(double value, double expected) {
        rightFunction = new RightFunction(lnMock, log2, log3Mock, log5Mock, log10Mock);
        double result = rightFunction.system(value, eps);
        assertEquals(expected, result, delta);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/input/RightFunctionIn.csv")
    public void log3StubTest(double value, double expected) {
        rightFunction = new RightFunction(lnMock, log2Mock, log3, log5Mock, log10Mock);
        double result = rightFunction.system(value, eps);
        assertEquals(expected, result, delta);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/input/RightFunctionIn.csv")
    public void log5StubTest(double value, double expected) {
        rightFunction = new RightFunction(lnMock, log2Mock, log3Mock, log5, log10Mock);
        double result = rightFunction.system(value, eps);
        assertEquals(expected, result, delta);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/input/RightFunctionIn.csv")
    public void log10StubTest(double value, double expected) {
        rightFunction = new RightFunction(lnMock, log2Mock, log3Mock, log5Mock, log10);
        double result = rightFunction.system(value, eps);
        assertEquals(expected, result, delta);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/input/RightFunctionIn.csv")
    public void allStubTest(double value, double expected) {
        rightFunction = new RightFunction(ln, log2, log3, log5, log10);
        double result = rightFunction.system(value, eps);
        assertEquals(expected, result, delta);
    }
}
