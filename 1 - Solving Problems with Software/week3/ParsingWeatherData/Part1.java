import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class Part1 {
    public CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord coldestRecord = null;
        
        for(CSVRecord currentRecord : parser){
            if(coldestRecord == null){
                coldestRecord = currentRecord;
            }else{
                coldestRecord = compareTwoRecord(coldestRecord,currentRecord);
            }
        }
        
        return coldestRecord;
    }
    
    public CSVRecord compareTwoRecord(CSVRecord coldest, CSVRecord current){
    
        double coldestTemp = Double.parseDouble(coldest.get("TemperatureF"));
        double currentTemp = Double.parseDouble(current.get("TemperatureF"));
        if(coldestTemp > currentTemp && currentTemp > (-999)){
            return current;
        }
        return coldest;
    }
    
    public void testColdestHourInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        
        CSVRecord coldestRecord = coldestHourInFile(parser);
        System.out.println(coldestRecord.get("TemperatureF") + " : " + coldestRecord.get("DateUTC"));
    }

    public String fileWithColdestTemperature(){
        File f = null;
        CSVRecord coldestRecord = null;
        
        DirectoryResource dr = new DirectoryResource();
        for(File file : dr.selectedFiles()){
            
            FileResource fr = new FileResource(file);
            CSVParser parser = fr.getCSVParser();
            CSVRecord currentRecord = coldestHourInFile(parser);
            
            if(coldestRecord == null){
                coldestRecord = currentRecord;
                f = file;
            }else{
                coldestRecord = compareTwoRecord(coldestRecord,currentRecord);
                f = file;
            }
            
        }
        
        return f.getAbsolutePath();
    }
    
    public void printAllHourInDay(CSVParser parser){
        for(CSVRecord record : parser){
            System.out.println(record.get("DateUTC") + ":  " + record.get("TemperatureF"));
        }
    }
    
    public void testFileWithColdestTemperature(){
        String coldestDayFileName = fileWithColdestTemperature();
        File f = new File(coldestDayFileName);
        String fileName = f.getName();
        System.out.println("Coldest day was in file " + fileName);
        
        FileResource fr = new FileResource(f);
        CSVParser parser = fr.getCSVParser();
        CSVRecord record = coldestHourInFile(parser);
        System.out.println("Coldest Temperature is: " + record.get("TemperatureF"));
        
        System.out.println("All the temperatures on the coldest day were ");
        parser = fr.getCSVParser();
        printAllHourInDay(parser);
    }

    public CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord lowestHumidity = null;
        
            for(CSVRecord current : parser){
                if(lowestHumidity == null){
                    lowestHumidity = current;
                }else if(current.get("Humidity").equals("N/A")){
                    continue;
                }else{
                    Double lowestH = Double.parseDouble(lowestHumidity.get("Humidity"));
                    Double currentH = Double.parseDouble(current.get("Humidity"));
                    if(currentH < lowestH){
                        lowestHumidity = current;
                    }
                }
            }
        
        return lowestHumidity;
    }
    
    public void testLowestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord lowestRecord = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was " + lowestRecord.get("Humidity") + " at " 
        + lowestRecord.get("DateUTC"));
    }

    public CSVRecord lowestHumidityInManyFiles(){
        DirectoryResource dr = new DirectoryResource();
        CSVRecord lowestRecord = null;
        
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord current = lowestHumidityInFile(parser);
            if(lowestRecord == null){
                lowestRecord = current;
            }else{
                Double lowestH = Double.parseDouble(lowestRecord.get("Humidity"));
                Double currentH = Double.parseDouble(current.get("Humidity"));
                if(currentH < lowestH){
                    lowestRecord = current;
                }            
            }
        }
 
        return lowestRecord;
    }
    
    public void testLowestHumidityInManyFiles(){
        CSVRecord lowestRecord = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was " + lowestRecord.get("Humidity") 
        + " at " + lowestRecord.get("DateUTC") );
    }
    
    public Double averageTemperatureInFile(CSVParser parser){
        Double average = 0.0;
        Double sum = 0.0;
        int length = 0;
        
        for(CSVRecord record : parser){
            length += 1;
            sum += Double.parseDouble(record.get("TemperatureF"));
        }
        average = sum/length;
        return average;
    }
    
    public void testAverageTemperatureInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        Double average = averageTemperatureInFile(parser);
        System.out.println("Average temperature in file is " + average);
    }
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
        Double average = 0.0;
        Double sum = 0.0;
        int length = 0;
        
        for(CSVRecord record : parser){
            Double humidity = Double.parseDouble(record.get("Humidity"));
            if(humidity >= value){
                length += 1;
                sum += Double.parseDouble(record.get("TemperatureF"));                
            }
        }
        
        average = sum / length ;
        return average;
    }
    
    public void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        Double average = averageTemperatureWithHighHumidityInFile(parser,80);
        System.out.println("Average Temp when high Humidity is " + average);    
    }
}










