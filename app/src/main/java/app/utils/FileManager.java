package app.utils;

import java.util.List;

public interface FileManager {
    List<String[]> readAll(String fileName, boolean skipHeader) throws Exception;
    void writeAll(String fileName, List<String[]> content) throws Exception;
}
