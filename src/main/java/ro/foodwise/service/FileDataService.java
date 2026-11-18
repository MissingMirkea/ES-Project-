package ro.foodwise.service;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import java.io.*;
import java.nio.file.*;
import java.util.Map;

@Service
public class FileDataService {
    private final ObjectMapper mapper = new ObjectMapper();
    public void writePreferences(String goal, double budget) throws IOException {
        Path path=Paths.get("data/preferences.json"); Files.createDirectories(path.getParent());
        mapper.writeValue(path.toFile(), Map.of("goal",goal,"budget",budget));
    }
    public Map<?,?> readPreferences() throws IOException {
        Map<?,?> preferences = mapper.readValue(Paths.get("data/preferences.json").toFile(), Map.class);
        Object budget = preferences.get("budget");
        if (budget != null) Double.parseDouble(String.valueOf(budget));
        return preferences;
    }
}
