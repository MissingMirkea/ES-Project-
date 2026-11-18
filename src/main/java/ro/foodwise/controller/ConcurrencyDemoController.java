package ro.foodwise.controller;

import org.springframework.web.bind.annotation.*;
import ro.foodwise.service.ThreadAndSocketDemoService;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/demo")
public class ConcurrencyDemoController {
    private final ThreadAndSocketDemoService demo;
    public ConcurrencyDemoController(ThreadAndSocketDemoService demo) { this.demo = demo; }

    @GetMapping("/threads-sockets")
    public Map<String, String> roundTrip(@RequestParam(defaultValue="FoodWise") String message) throws IOException {
        return Map.of("transport", "TCP Socket", "execution", "ExecutorService thread", "response", demo.threadedSocketRoundTrip(message));
    }
}
