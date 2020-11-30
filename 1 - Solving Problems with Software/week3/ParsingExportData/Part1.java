import edu.duke.*;
import org.apache.commons.csv.*;

public class Part1 {
    public void listExporters(CSVParser parser, String exportOfInterest) {
        //for each row in the CSV File
        for(CSVRecord record : parser){
            //Look at the "Exports" column
            String rec = record.get("Exports");
            //Check if it contains exportOfInterest
            if(rec.indexOf(exportOfInterest) != -1){
                //If so, write down the "Country" from that row
                System.out.println(record.get("Country"));
            }
        }
    }

    public void whoExportsCoffee() {
        FileResource fr = new FileResource("exports/exports_small.csv");
        CSVParser parser = fr.getCSVParser();
        listExporters(parser, "coffee");
    }
    
    public String countryInfo(CSVParser parser, String country){
        String info = "";
        for(CSVRecord record : parser){
            if(record.get("Country").equals(country)){
                info = record.get("Country") + ": " + record.get("Exports") 
                                + ": " + record.get("Value (dollars)");
                break;                
            }else{
                info = "NOT FOUND";
            }
            
        }
        return info;
    }
    
    public void listExportersTwoProducts(CSVParser parser, 
                                            String exportItem1, 
                                            String exportItem2)
    {
        for(CSVRecord record : parser){
            String rec = record.get("Exports");
            if((rec.indexOf(exportItem1) != -1) && (rec.indexOf(exportItem2) != -1)){    
                System.out.println(record.get("Country"));
            }
        }
    }
    
    public int numberOfExporters(CSVParser parser, String exportItem){
        int exporterCount = 0;
        
        for(CSVRecord record : parser){
            String exp = record.get("Exports");
            if(exp.indexOf(exportItem) != -1){
                exporterCount += 1;
            }
        }
        
        return exporterCount;
    }

    public void bigExporters(CSVParser parser, String amount){
        
        for(CSVRecord record : parser){
            String value = record.get("Value (dollars)");
            String country = record.get("Country");
            if(value.length() > amount.length()){
                System.out.println(country + ": " + value);
            }
        }
    }
    
    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        //System.out.println(countryInfo(parser,"Nauru"));
        //listExportersTwoProducts(parser,"cotton","flowers");
        //System.out.println(numberOfExporters(parser,"cocoa"));
        bigExporters(parser,"$999,999,999,999");
    }
    
    
    
    
    
    
}