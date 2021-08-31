package app.utils;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class CsvManager implements FileManager {

    @Autowired
    private ResourceLoader resourceLoader;

    private ClassLoader appResourcesFolder;

    public CsvManager() {
        // this.appResourcesFolder = getClass().getClassLoader();
    }

    public List<String[]> readAll(String fileName, boolean skipHeader) throws Exception {
        List<String[]> data = new ArrayList<>();

        try {
            // ClassPathResource resource = new ClassPathResource(fileName);
            Logger.info("File: " +  fileName);
            Resource resource= resourceLoader.getResource("classpath:/" + fileName);
            InputStream inputStream= resource.getInputStream();
            CSVReader reader = new CSVReader(new InputStreamReader(inputStream));
            reader.skip( skipHeader ? 1 : 0);
            data = reader.readAll();
            reader.close();
        }
        catch (Exception e) {
            Logger.error(e.getMessage());
        }

        return data;
    }

    public void writeAll(String fileName, List<String[]> content) throws Exception {
        try {
            File file = new File(this.appResourcesFolder.getResource(fileName).getFile());
            CSVWriter writer = new CSVWriter(new FileWriter(file));
            writer.writeAll(content);
            writer.close();
            Logger.info("File " + file.getName() + "successfully written at " + file.getPath());
        }
        catch (Exception e) {
            Logger.error(e.getMessage());
        }
    }
}