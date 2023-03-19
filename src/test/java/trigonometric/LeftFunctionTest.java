package trigonometric;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
public class LeftFunctionTest {
    private static final double delta = 0.5;
    private static final double eps = 0.001;

    private final Sin sinMock = TriMocks.sinMock();
    private final Csc cscMock = TriMocks.cscMock();
    private final Sec secMock = TriMocks.secMock();
    private final Sin sin = new Sin();
    private final Cos cos = new Cos(sin);
    private final Csc csc = new Csc(sin);
    private final Sec sec = new Sec(cos);

    private LeftFunction leftFunction;
    private static final String file = "C:\\Users\\admin\\IdeaProjects\\tpo-lab2\\src\\main\\resources\\csv\\output\\LeftFunctionOut.csv";

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/input/LeftFunctionIn.csv")
    public void allMockTest(double value, double expected) {
        leftFunction = new LeftFunction(secMock, cscMock, sinMock);
        double result = leftFunction.system(value, eps);
        assertEquals(expected, result, delta);
        leftFunction.writeResToCSV(value, result, file);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/input/LeftFunctionIn.csv")
    public void secStubTest(double value, double expected) {
        leftFunction = new LeftFunction(sec, cscMock, sinMock);
        double result = leftFunction.system(value, eps);
        assertEquals(expected, result, delta);
        leftFunction.writeResToCSV(value, result, file);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/input/LeftFunctionIn.csv")
    public void cscStubTest(double value, double expected) {
        leftFunction = new LeftFunction(secMock, csc, sinMock);
        double result = leftFunction.system(value, eps);
        assertEquals(expected, result, delta);
        leftFunction.writeResToCSV(value, result, file);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/input/LeftFunctionIn.csv")
    public void sinStubTest(double value, double expected) {
        leftFunction = new LeftFunction(secMock, cscMock, sin);
        double result = leftFunction.system(value, eps);
        assertEquals(expected, result, delta);
        leftFunction.writeResToCSV(value, result, file);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/input/LeftFunctionIn.csv")
    public void allStubTest(double value, double expected) {
        leftFunction = new LeftFunction(sec, csc, sin);
        double result = leftFunction.system(value, eps);
        assertEquals(expected, result, delta);
        leftFunction.writeResToCSV(value, result, file);
    }
}
