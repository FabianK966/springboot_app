package net.javaguides.springboot.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class CsvInitializer implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        Path csvFile = Paths.get("employees.csv");

        if (!Files.exists(csvFile)) {
            Files.createFile(csvFile);
            // Optional: Kopfzeile schreiben
            Files.writeString(csvFile, "id,firstName,lastName,emailId\n");

            // Beispiel-Daten hinzufügen
            Files.writeString(csvFile,
                    "1,John,Doe,john.doe@example.com\n" +
                            "2,Jane,Smith,jane.smith@example.com\n",
                    java.nio.file.StandardOpenOption.APPEND);
        }
    }
}