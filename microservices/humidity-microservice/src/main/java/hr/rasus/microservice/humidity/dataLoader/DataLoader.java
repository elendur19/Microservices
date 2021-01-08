package hr.rasus.microservice.humidity.dataLoader;

import hr.rasus.microservice.humidity.entity.Humidity;
import hr.rasus.microservice.humidity.repo.HumidityRepo;


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

    private final HumidityRepo humidityRepo;
    private static final String csvFileLocation ="/home/huginn/faks/rassus/lab_prof/resources/mjerenja.csv";
    private List<String> readings = new ArrayList<>();

    @Autowired
    public DataLoader(HumidityRepo humidityRepo) {
        this.humidityRepo = humidityRepo;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        readings = readFile();
        for (String reading : readings) {
            String[] data = reading.split(",");
            humidityRepo.save(new Humidity(Double.parseDouble(data[2])));
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
