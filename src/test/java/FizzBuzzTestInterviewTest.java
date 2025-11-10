import org.example.TestInterview;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    public void coinChangeTestOne() {
        TestInterview mockEmailService = Mockito.mock(TestInterview.class);
        UserService userService = new UserService(mockEmailService);

        String userEmail = "test@example.com";

        // Выполнение действия
        userService.sendWelcomeEmail(userEmail);

        // Проверка, что метод send был вызван на mockEmailService один раз
        Mockito.verify(mockEmailService, Mockito.times(1))
                .send(userEmail, "Добро пожаловать!", "Текст приветствия");
        Assertions.assertArrayEquals(fizzBuzzTestInterviewTest.fizzBuzzTest(5), "Buzz".getBytes());
    }
}
