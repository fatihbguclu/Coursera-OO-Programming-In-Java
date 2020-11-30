
/**
 * Write a description of tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class tester {
    public void testLogEntry(){
        /*LogEntry le = new LogEntry("1.2.3.4",new Date(),"example Request",200,500);
        System.out.println(le);*/
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("data/weblog2_log");
        //la.printAll();
        //System.out.println(la.countUniqueIPs());
        /*for(String x : la.uniqueIPVisitsOnDay("Mar 24")){
            System.out.println(x);
        }*/
        System.out.println(la.uniqueIPVisitsOnDay("Sep 24").size());
        //System.out.println(la.countUniqueIPsInRange(200,299));      
        //la.printAllHigherThanNum(400);
        //System.out.println(la.iPsForDays());
        //System.out.println(la.mostNumberVisitsByIP(la.countVisitsPerIp()));
        //System.out.println(la.dayWithMostIPVisits(la.iPsForDays()));
        //System.out.println(la.iPsWithMostVisitsOnDay(la.iPsForDays(),"Sep 30"));
        //System.out.println(la.iPsMostVisits(la.countVisitsPerIp()));
    }
}
