
/**
 * Write a description of LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;
public class LogAnalyzer {
    private ArrayList<LogEntry> records;
    
    public LogAnalyzer(){
        records = new ArrayList<LogEntry>();
    }
    
    public void readFile(String filename){
        FileResource fr = new FileResource(filename);
        for(String line : fr.lines()){
            LogEntry record = WebLogParser.parseEntry(line);
            records.add(record);
        }
    }
    
    public int countUniqueIPs(){
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for(LogEntry entry : records){
            String ip = entry.getIpAddress();
            if(!uniqueIPs.contains(ip)){
                uniqueIPs.add(ip);
            }
        }
        return uniqueIPs.size();
    }
    
    public void printAllHigherThanNum(int num){
        for(LogEntry entry : records){
            if(entry.getStatusCode() > num){
                System.out.println(entry);
            }
        }
    }
    
    public ArrayList<String> uniqueIPVisitsOnDay(String someday){
        ArrayList<String> list = new ArrayList<String>();
        for(LogEntry entry : records){
            String day = entry.getAccessTime().toString();
            day = day.substring(4,10);
            if(day.equals(someday) && !list.contains(entry.getIpAddress())){
                list.add(entry.getIpAddress());
            }
        }
        return list;
    }
    
    public int countUniqueIPsInRange(int low, int high){
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for(LogEntry entry : records){
            String ip = entry.getIpAddress();
            int statusCode = entry.getStatusCode();
            if(!uniqueIPs.contains(ip) && (statusCode >= low && statusCode <= high)){
                uniqueIPs.add(ip);
            }
        }
        return uniqueIPs.size();
    }
    
    public HashMap<String,Integer> countVisitsPerIp(){
        HashMap<String,Integer> counts = new HashMap<String,Integer>();
        for(LogEntry entry : records){
            String ip = entry.getIpAddress();
            if(!counts.containsKey(ip)){
                counts.put(ip,1);
            }else{
                int value = counts.get(ip);
                counts.put(ip,value+1);
            }
        }
        return counts;
    }
    
    public int mostNumberVisitsByIP(HashMap<String,Integer> IpMap){
        return Collections.max(IpMap.values());
    }
    
    public int countUniqueIPsMap(){
        HashMap<String,Integer> counts = countVisitsPerIp();
        return counts.size();
    }
    
    public ArrayList<String> iPsMostVisits(HashMap<String,Integer> IpMap){
        ArrayList<String> list = new ArrayList<String>();
        int max = mostNumberVisitsByIP(IpMap);
        for(String IP : IpMap.keySet()){
            if(IpMap.get(IP).equals(max)){
                list.add(IP);
            }
        }
        return list;
    }
    
    public HashMap<String, ArrayList<String>> iPsForDays(){
        HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
        for(LogEntry entry : records){
            ArrayList<String> list = new ArrayList<String>();
            String day = entry.getAccessTime().toString();
            day = day.substring(4,10);
            String ip = entry.getIpAddress();
            if(!map.containsKey(day)){
                list.add(ip);
                map.put(day,list);
            }else{
                ArrayList<String> tempList = map.get(day);
                tempList.add(ip);
                map.put(day,tempList);
            }
        }
        return map;
    }
    
    public String dayWithMostIPVisits(HashMap<String,ArrayList<String>> map){
        String day = "";
        int max = 0;
        for(String key : map.keySet()){
            if(map.get(key).size() > max){
                max = map.get(key).size();
                day = key;
            }
        }
        return day;
    }
    
    public ArrayList iPsWithMostVisitsOnDay(HashMap<String,ArrayList<String>> map, String day){
        ArrayList<String> list = map.get(day);
        HashMap<String,Integer> counts = new HashMap<>();
        for(String ip : list){
            if(!counts.containsKey(ip)){
                counts.put(ip,1);
            }else{
                int value = counts.get(ip);
                counts.put(ip,value+1);
            }
        }
        return iPsMostVisits(counts);
    }
    
    public void printAll(){
        for(LogEntry le : records){
            System.out.println(le);
        }
    }
}
