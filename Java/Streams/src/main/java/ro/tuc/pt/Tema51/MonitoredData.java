
package ro.tuc.pt.Tema51;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MonitoredData {
	private String startTime;
	private String endTime;
	private String activityLog;
	
	public MonitoredData(String startTime,String endTime, String activity) {
		
		this.startTime=startTime;
		this.endTime=endTime;
		this.activityLog=activity;
		
	}
	public String getStart_time() {
		return startTime;
	}
	public void setStart_time(String start_time) {
		this.startTime = start_time;
	}
	public String getEnd_time() {
		return endTime;
	}
	public void setEnd_time(String end_time) {
		this.endTime = end_time;
	}
	public String getActivity_log() {
		return activityLog;
	}
	public void setActivity_log(String activity_log) {
		this.activityLog = activity_log;
	}
	
	public String changeDataFormat()
	{
		DateFormat dayFormatter=new SimpleDateFormat("MM-dd");
		return dayFormatter.format(startTime);
	}
	
}
