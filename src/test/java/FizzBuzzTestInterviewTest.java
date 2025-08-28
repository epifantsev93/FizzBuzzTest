import org.example.TestInterview;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FizzBuzzTestInterviewTest {

    TestInterview fizzBuzzTestInterviewTest = new TestInterview();

    @Test
    public void fizzBuzzTestFizz() {
        Assertions.assertArrayEquals(fizzBuzzTestInterviewTest.fizzBuzzTest(3), "Fizz".getBytes());
    }

    @Test
    public void fizzBuzzTestBuzz() {
        Assertions.assertArrayEquals(fizzBuzzTestInterviewTest.fizzBuzzTest(5), "Buzz".getBytes());
    }

    @Test
    public void fizzBuzzTestFizzBuzz() {
        Assertions.assertArrayEquals(fizzBuzzTestInterviewTest.fizzBuzzTest(15), "FizzBuzz".getBytes());
    }

    @Test
    public void fizzBuzzTestException() {
        Assertions.assertThrowsExactly(IllegalArgumentException.class, () -> fizzBuzzTestInterviewTest.fizzBuzzTest(1));
    }
}
