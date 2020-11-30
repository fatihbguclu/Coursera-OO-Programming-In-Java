
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
import java.util.*;

public class Part1 {
    public void totalBirths(FileResource fr){
        int totalBirths = 0;
        int totalGirls = 0;
        int totalBoys = 0;
        
        for(CSVRecord rec : fr.getCSVParser(false)){
            int numBorn = Integer.parseInt(rec.get(2));
            String gender = rec.get(1);
            totalBirths += numBorn;
            if(gender.equals("M")){
                totalBoys += numBorn;
            }else{
                totalGirls += numBorn;
            }
        }
        
        System.out.println("Total Births : " + totalBirths);
        System.out.println("Girl : " + totalGirls);        
        System.out.println("Boy : " + totalBoys);
    }
    
    public int numberOfGirls(FileResource fr){
        int num = 0;
        for(CSVRecord rec : fr.getCSVParser(false)){
            if(rec.get(1).equals("F")){
                num++;
            }
        }
        return num;
    }
    
    public int numberOfBoys(FileResource fr){
        int num = 0;
        for(CSVRecord rec : fr.getCSVParser(false)){
            if(rec.get(1).equals("M")){
                num++;
            }
        }
        return num;
    }
    
    public void testBirthNO(){
        FileResource fr = new FileResource();
        System.out.println(numberOfGirls(fr) + "  " + numberOfBoys(fr));
    }
    
    public void testTotalBirths(){
        FileResource fr = new FileResource();
        totalBirths(fr);
    }
    
    public long getRank(int year, String name, String gender){
        long rank = -1;
        FileResource fr = new FileResource("us_babynames_by_year/yob" + year + ".csv");
        CSVParser parser = fr.getCSVParser(false);
        
        for(CSVRecord rec : parser){
            String recName = rec.get(0);
            String recGender = rec.get(1);
            if(recName.equals(name) && recGender.equals(gender)){
                rank = rec.getRecordNumber();
            }
        }
        
        return rank;
    }
    
    public String getName(int year, long rank, String gender){
        String name = "";
        FileResource fr = new FileResource("us_babynames_by_year/yob" + year + ".csv");    
        CSVParser parser = fr.getCSVParser(false);
        
        for(CSVRecord rec : parser){
            long recRank = rec.getRecordNumber();
            String recGender = rec.get(1);
            
            if(recRank == rank && recGender.equals(gender)){
                name = rec.get(0);
            }
        }
        
        if(name != ""){return name;}
        else{return "NO NAME";}
        
    }
    
    public void whatIsNameInYear(String name, int year, int newYear, String gender){
        long rank = getRank(year,name,gender);
        String newName = getName(newYear,rank,gender);
        System.out.println(name + " born in " + year + " would be " 
                            + newName + " if she was born in " 
                            + newYear);
    }
    
    public int yearOfHighestRank(String name, String gender){
        int year = -1;
        long highestRank = 0;
        String fileName = "";
        DirectoryResource dr = new DirectoryResource();
        
        for(File f : dr.selectedFiles()){
        
            FileResource fr = new FileResource(f);
            for(CSVRecord rec : fr.getCSVParser(false)){
        
                String recName = rec.get(0);
                String recGender = rec.get(1);
        
                if(recName.equals(name) && recGender.equals(gender)){
                    long recRank = rec.getRecordNumber();
                    
                    if(highestRank == 0){
                        highestRank = recRank;
                        fileName = f.getName();
                    }else{
                        if(recRank < highestRank){
                            highestRank = recRank;
                            fileName = f.getName();
                        }
                    }
                }
            }
        }
        
        fileName = fileName.replaceAll("[^\\d]","");
        year = Integer.parseInt(fileName);
        
        return year;
    }
    
    public double getAverageRank(String name, String gender){
        double rankTotal = 0.0;
        int count = 0;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            for(CSVRecord rec : fr.getCSVParser(false)){
                String recName = rec.get(0);
                String recGender = rec.get(1);
                if(recName.equals(name) && recGender.equals(gender)){
                    if(gender == "F"){
                        rankTotal += (double) rec.getRecordNumber();
                    }else{
                        rankTotal += (double) rec.getRecordNumber() - numberOfGirls(fr);
                    }
                    count += 1;
                }
            }
        }
        double avgRank = rankTotal / (double) count;
        return avgRank;
    }
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender){
        int numBorn = 0;
        long rank = getRank(year,name,gender);
        FileResource fr = new FileResource();
        for(CSVRecord rec : fr.getCSVParser(false)){
            int recBorn = Integer.parseInt(rec.get(2));
            String recGender = rec.get(1);
            long recRank = rec.getRecordNumber();
            if(gender.equals(recGender) && rank > recRank){
                numBorn += recBorn;
            }
        }
        
        return numBorn;
    }
}












