import java.io.*;
import java.time.LocalDate;

public class Main
{
    private String currentInput;

    public static void main(String[] args)
    {

        Metaphone3 metaphone3;
        BufferedReader inputReader;
        BufferedWriter resultWriter;
        
        // Delete results file if exists.
        File resultsFile = new File("Results.txt");
        if (resultsFile.exists()) {
            resultsFile.delete();
        }
        
        try {
            inputReader = new BufferedReader(new FileReader("Inputs.txt"));
            resultWriter = new BufferedWriter(new FileWriter("Results.txt", true));
            metaphone3 = new Metaphone3();
            
            LocalDate startTime = LocalDate.now();
            System.out.println(startTime);
            
            resultWriter.write("Input,Metaphone,AlternateMetaphone,ExactMetaphone,AlternateExactMetaphone"); // Header row.
            resultWriter.newLine();
            
            // Read the Inputs file line-by-line.
            String line = inputReader.readLine();
            while (line != null) {
                metaphone3.SetWord(line);
                
                metaphone3.SetEncodeVowels(false);
                metaphone3.SetEncodeExact(false);
                metaphone3.Encode();
                String metaphone = metaphone3.GetMetaph();
                String alternateMetaphone = metaphone3.GetAlternateMetaph();
                
                metaphone3.SetEncodeVowels(true);
                metaphone3.SetEncodeExact(true);
                metaphone3.Encode();
                String exactMetaphone = metaphone3.GetMetaph();
                String alternateExactMetaphone = metaphone3.GetAlternateMetaph();
                
                String result = line + "," + metaphone + "," + alternateMetaphone + "," + exactMetaphone + "," + alternateExactMetaphone;
                
                resultWriter.write(result);
                resultWriter.newLine();
                line = inputReader.readLine();
            }
            
            inputReader.close();
            resultWriter.close();
            
            LocalDate endTime = LocalDate.now();
            System.out.println(endTime);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}