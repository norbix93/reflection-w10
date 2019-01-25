package reflectionw10;

import exercise1.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BookReader {

    List<String> readLines = new ArrayList<>();
    FileReader fileReader = new FileReader();


    public List<String> readLines(String filePath) {
        List<String> getLines = fileReader.asList(filePath);
        for (String line : getLines.toString().split(" ")) {
            String newLine = "";
            List<String> symbols = Arrays.asList(";", "-", ",", "\\.", ":","’s","’","‘","#","/","?",
                    "!","\\*","\\(","\\)","\\[","\\]");
            newLine = line.replaceAll(symbols.toString(), "");
            readLines.add(newLine.toLowerCase());
        }
        return readLines;
    }
}
