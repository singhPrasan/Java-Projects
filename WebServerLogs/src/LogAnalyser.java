import java.util.ArrayList;
import java.util.HashMap;

import edu.duke.FileResource;

public class LogAnalyser {
	private ArrayList<LogEntry> records;  
	
	public LogAnalyser(){
		records = new ArrayList<>();
	}

	//Reads logs as string from the specified file and converts them to LogEntry object
	//Adds the object to records list
	public void readFile(String fileName){
		FileResource fr = new FileResource(fileName);
		for(String line : fr.lines()){
			LogEntry currLog = WebLogParser.parseEntry(line);
			records.add(currLog);
		}
	}
	
	
	//Returns the number of unique Ip addresses from which request to a particular site was made
	public int countUniqueIPs(){
		ArrayList<String> ipList = new ArrayList<>();
		for(LogEntry le : records){
			String ip = le.getIpAddress();
			if(!ipList.contains(ip))
				ipList.add(ip);
		}
		return ipList.size();
	}
	
	
	//Prints all the logs from the records list
	public void printAll(){
		for(LogEntry le : records)
			System.out.println(le);
	}
	
	
	//Prints all the web logs whose status code is greater than the given status code
	public void printAllHigherThanNum(int num){
		System.out.println("Logs with status codes greater than ["+num+"] :");
		for(LogEntry le : records){
			int statusCode = le.getStatusCode();
			if(statusCode > num)
				System.out.println(le);
		}
	}
	
	
	//Returns a list of all unique IPs that visited the given site on any given day
	public ArrayList<String> uniqueIPVisitsOnDay(String someday){
		ArrayList<String> uniqVisits = new ArrayList<>();
		for(LogEntry le : records){
			String currDay = le.getAccessTime().toString().substring(4, 10);
			String uniqIP = le.getIpAddress();
			if( currDay.equals(someday) && !isUniqueIP(uniqVisits, uniqIP))
				uniqVisits.add(uniqIP);
		}
		return uniqVisits;
	}
	
	
	//Returns the number of unique IP addresses within a given range of status codes
	public int countUniqueIPsInRange(int low, int high){
		int count = 0;
		ArrayList<String> uniqIPList = new ArrayList<>();
		for(LogEntry le : records){
			int statuscode = le.getStatusCode();
			String uniqIP = le.getIpAddress();
			if(!isUniqueIP(uniqIPList, uniqIP) &&statuscode >= low && statuscode<=high){
				count++;
				uniqIPList.add(uniqIP);
			}
		}
		return count;
	}
	
	
	//Returns a table containing number of visits by each IP address to the site
	public HashMap<String, Integer> countVisitsPerIP(){
		HashMap<String, Integer> counts = new HashMap<>();
		for(LogEntry le : records){
			String ip = le.getIpAddress();
			if(!counts.containsKey(ip))
				counts.put(ip, 1);
			else
				counts.put(ip, counts.get(ip)+1);
		}
		return counts;
	}
	
	
	//Returns a table containing number of visits by each IP address to the site
	public HashMap<String, Integer> countVisitsPerIP(ArrayList<String> ipList){
		HashMap<String, Integer> counts = new HashMap<>();
		for(String ip : ipList){
			if(!counts.containsKey(ip))
				counts.put(ip, 1);
			else
				counts.put(ip, counts.get(ip)+1);
		}
		return counts;
	}
	
	
	//Returns whether the requested ip is present in the given list or not
	private boolean isUniqueIP(ArrayList<String> list, String ip){
		if(list.contains(ip)) return true;
		return false;
	}
	
	
	//Returns maximum number of visits to a site 
	public int mostNumberVisitsPerIP(HashMap<String, Integer> map){
		int maxVisits = -1;
		for(String ip : map.keySet()){
			maxVisits = Math.max(maxVisits, map.get(ip));
		}
		return maxVisits;
	}
	
	
	//Returns a list of ip addresses that visit a particular site most
	public ArrayList<String> iPsMostVisits(HashMap<String, Integer> map){
		ArrayList<String> maxIPs = new ArrayList<>();
		int maxVisits = mostNumberVisitsPerIP(map);
		for(String ip : map.keySet()){
			if( map.get(ip) == maxVisits)
				maxIPs.add(ip);
		}
		return maxIPs;
	}
	
	
	//Returns a tabular data of all ip addresses that visited the site day wise
	public HashMap<String, ArrayList<String>> iPsForDays(){
		HashMap<String, ArrayList<String>> daysAndIps = new HashMap<>();
		for( LogEntry le : records){
			String day = le.getAccessTime().toString().substring(4, 10);
			String ip = le.getIpAddress();
			if(!daysAndIps.containsKey(day)){
				ArrayList<String> iPlist = new ArrayList<>();
				iPlist.add(ip);
				daysAndIps.put(day, iPlist);
			}else{
				ArrayList<String> iPlist = daysAndIps.get(day);
				iPlist.add(ip);
				daysAndIps.put(day, iPlist);
			}
		}
		return daysAndIps;
	}
	
	
	//Returns the day which has the most number of visits 
	public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> map){
		String maxDay = "";
		int maxIPCount = 0;
		for(String day : map.keySet()){
			int currIPCount = map.get(day).size();
			if( currIPCount > maxIPCount ){
				maxIPCount = currIPCount;
				maxDay = day;
			}
		}
		return maxDay;
	}
	
	
	//Returns a list of IP addresses that had the most accesses on the given day.
	public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> map, String day){
		ArrayList<String> ipList = map.get(day);
		HashMap<String, Integer> count = countVisitsPerIP(ipList);
		return iPsMostVisits(count);
	}
	
	
	
	//Test Client
	public static void main(String[] args) {
		LogAnalyser la = new LogAnalyser();
		la.readFile("data/weblog2_log");
		la.printAll();
		System.out.println("Number of unique IP addresses from which the site was accessed : "+la.countUniqueIPs());
		la.printAllHigherThanNum(400);
		System.out.println("Unique IP addresses :"+la.uniqueIPVisitsOnDay("Sep 27"));
		System.out.println("Number of IPs within status code range :"+la.countUniqueIPsInRange(200, 299));
		HashMap<String, Integer> visitsPerIP = la.countVisitsPerIP();
		System.out.println("Number of visits by each IP address : \n" +visitsPerIP);
		System.out.println("Maximum number of visits : "+la.mostNumberVisitsPerIP(visitsPerIP));
		System.out.println("IPs with maximum visits : "+la.iPsMostVisits(visitsPerIP));
		
		HashMap<String, ArrayList<String>> dayWiseIPs = la.iPsForDays();
		System.out.println("IP visits day wise :\n"+dayWiseIPs);
		System.out.println("Day with most number of visits : "+la.dayWithMostIPVisits(dayWiseIPs));
		System.out.println("IP addresses with most accesses on a given day : "+la.iPsWithMostVisitsOnDay(dayWiseIPs, "Sep 30"));
	}
}
