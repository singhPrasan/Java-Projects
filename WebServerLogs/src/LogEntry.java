import java.util.Date;

public class LogEntry {
	private String ipAddress;
	private Date accessTime;
	private String request;
	private int statusCode;
	private int bytesReturned;
	
	public LogEntry(String ipAddress, Date accessTime, String request, int statusCode, int bytesReturned) {
		this.ipAddress = ipAddress;
		this.accessTime = accessTime;
		this.request = request;
		this.statusCode = statusCode;
		this.bytesReturned = bytesReturned;
	}
	
	public String getIpAddress() {
		return ipAddress;
	}
	public Date getAccessTime() {
		return accessTime;
	}
	public String getRequest() {
		return request;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public int getBytesReturned() {
		return bytesReturned;
	}
	
	public String toString(){
		return ipAddress+" "+accessTime+" "+request+" "+statusCode+" "+bytesReturned;
	}
}
