import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import junit.framework.TestCase;

public class TestHelloWorld extends TestCase {
    @Test
    public void testHelloWorldMessage() {
        assertNotEquals("Hello World!", "Hello World");
    }
}