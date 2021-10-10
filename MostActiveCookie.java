import java.util.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MostActiveCookie {
    public static String FILENAME = "";
    public static String FLAG = "";
    public static String DATE = "";
    public static final String ERROR_MSG_INCORRECT_FLAG = "Incorrect usage. Please use the proper flag (-d).";
    public static final String ERROR_MSG_INCORRECT_USAGE = "Incorrect usage. \nPlease follow this format: ./most_active_cookie [filename] [-d] [date]";
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static void main(String[] args) throws FileNotFoundException {
        // assign args to globals

        if(args.length != 3) {
            System.out.println(ERROR_MSG_INCORRECT_USAGE);
            return;
        }

        FILENAME = args[0];
        FLAG = args[1];
        DATE = args[2];

        if(!FLAG.equals("-d")) {
            System.out.println(ERROR_MSG_INCORRECT_FLAG);
            return;
        }

        HashMap<String, Integer> csv;

        try {
            csv = parseCSV(FILENAME);
        }
        catch(Exception e) {
            return;
        }
        
        // now, we can simply find the maximum value and add to our return set
        printMostCommonCookie(csv);
    }

    public static HashMap<String, Integer> parseCSV(String fileName) throws FileNotFoundException {
        HashMap<String, Integer> parsedCSV = new HashMap<String, Integer>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            // skip header of csv
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                if(values.length != 2) {
                    throw new Exception();
                }


                TokenPair currTokenPair = parseTokenPair(values);

                // we only want to add cookies that are on the same day of interest
                if(!isSameDate(LocalDate.parse(DATE,formatter), currTokenPair.getDate())) {
                    continue;
                }

                if(parsedCSV.containsKey(currTokenPair.getTokenId())) {
                    parsedCSV.put(currTokenPair.getTokenId(), parsedCSV.get(currTokenPair.getTokenId())+1);
                }
                else {
                    parsedCSV.put(currTokenPair.getTokenId(), 1);
                }
            }
        }
        catch(FileNotFoundException fnfe) {
            System.out.println("Error: File " + FILENAME + " not found.");
        }    
        catch(Exception e) {
            System.out.println("Error: The file is not in the correct format.");
        }
        return parsedCSV;
    }

    public static boolean isSameDate(LocalDate inputDate, LocalDate currentDate) {
        return inputDate.equals(currentDate);
    }

    public static TokenPair parseTokenPair(String[] values) {
        LocalDate parsedDate = LocalDate.parse(values[1].substring(0,10),formatter);
        TokenPair tokenPair = new TokenPair(values[0], parsedDate);
        
        return tokenPair;
    }

    public static HashSet<String> printMostCommonCookie(HashMap<String, Integer> csv) {
        HashSet<String> toPrint = new HashSet<String>();
        Integer maxValue = 0;

        for(String key: csv.keySet()) {
            // if equal to current max, simple add 
            if(csv.get(key) == maxValue) {
                toPrint.add(key);
            }
            else if(csv.get(key) > maxValue){
                // if greater, clear the set and set maxvalue
                toPrint.clear();
                maxValue = csv.get(key);
                toPrint.add(key);
            }
        }

        // now print the contents of toPrint
        for(String str : toPrint) {
            System.out.println(str);
        }
        return toPrint;
    }
}

