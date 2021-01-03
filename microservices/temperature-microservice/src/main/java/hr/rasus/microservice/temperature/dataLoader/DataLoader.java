package hr.rasus.microservice.temperature.dataLoader;

import hr.rasus.microservice.temperature.entity.Temperature;
import hr.rasus.microservice.temperature.repo.MeasurementRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements ApplicationRunner {

    private final MeasurementRepo measurementRepo;
    private static final String csvFileLocation = "C:\\Users\\misla\\fer\\1.semestar_diplomski\\RASUS\\lab_prof\\mjerenja.csv";
    private List<String> readings = new ArrayList<>();

    @Autowired
    public DataLoader(MeasurementRepo measurementRepo) {
        this.measurementRepo = measurementRepo;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        readings = readFile();
        for (String reading : readings) {
            String[] data = reading.split(",");
            measurementRepo.save(new Temperature(Double.parseDouble(data[0])));
        }
    }

    private List<String> readFile() {
        String line;
        List<String> readings = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(DataLoader.csvFileLocation))) {
            while ((line = br.readLine()) != null) {
                line = line.replaceAll(",,", ",0,");
                if (line.split(",")[0].equals("Temperature")) {
                    continue;
                }
                readings.add(line);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return readings;
    }
}
