package ro.foodwise.service;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ThreadAndSocketDemoServiceTest {
    @Test void threadAndSocketRoundTripReturnsServerResponse() throws IOException {
        ThreadAndSocketDemoService service = new ThreadAndSocketDemoService();
        try { assertEquals("SERVER_RECEIVED: Salut", service.threadedSocketRoundTrip("Salut")); }
        finally { service.shutdown(); }
    }
}
