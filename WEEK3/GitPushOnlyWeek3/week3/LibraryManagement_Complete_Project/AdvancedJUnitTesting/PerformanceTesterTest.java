import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import java.time.Duration;

public class PerformanceTesterTest {

    @Test
    public void testPerformTaskWithinTime() {
        PerformanceTester tester = new PerformanceTester();
        assertTimeout(Duration.ofMillis(1000), () -> tester.performTask());
    }
}