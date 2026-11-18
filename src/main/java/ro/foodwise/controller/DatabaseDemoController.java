package ro.foodwise.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.foodwise.service.DatabaseDemoService;
import java.util.Map;

@RestController
@RequestMapping("/api/database")
public class DatabaseDemoController {
    private final DatabaseDemoService database;
    public DatabaseDemoController(DatabaseDemoService database) { this.database = database; }

    @GetMapping("/demo")
    public Map<String, Object> demo() { return database.snapshot(); }
}
