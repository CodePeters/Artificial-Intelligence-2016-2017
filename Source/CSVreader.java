import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class CSVreader {

    public Vector<String []> v = new Vector<String[]>();

     CSVreader (String csvFile)
    {
        String line = "";
        String cvsSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            br.readLine();
            while ((line = br.readLine()) != null) {

                String [] new_line =line.split(cvsSplitBy);
                v.add(new_line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
