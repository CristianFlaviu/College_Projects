package ro.tuc.pt.Tema51;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainClass {

	public static void main(String[] args) {
		File file = new File("Activities.txt");
		Scanner sc;
		String dateString = "2011-11-28 10:51:41";
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		DateFormat dayFormatter = new SimpleDateFormat("MM-dd");

		List<MonitoredData> aList = new ArrayList<>();
		Map<String, Long> aMap = null;
		String aString = null;

		List<Long> durationList = new ArrayList<>();

		Stream<String> stream = null;

		try {
			stream = Files.lines(Paths.get("Activities.txt"));
		} catch (IOException e) {

			e.printStackTrace();
		}

		stream.map(s -> s.split("\n")).flatMap(Arrays::stream).collect(Collectors.toList()).forEach(s -> {
			String[] astring = s.split("\t\t");
			aList.add(new MonitoredData(astring[0], astring[1], astring[2]));

		});

	System.out.println(
				"Number of days: " + countDays(aList) + "\n-------------------------------------------------------\n");
		
		System.out.println("\nCerinta2:");
		for (Entry<String, Long> entry : countActivities(aList).entrySet()) {
			System.out.println(entry.getKey() + " " + entry.getValue());
		}
	
		System.out.println("\n-----------------------------------------------------\nCerinta3");
		cerinta4(aList);
		
		System.out.println("\n-----------------------------------------\nCerinta4");
		List<Long> secondList = countDuration(aList, true);
		
		System.out.println("\n-------------------------Cerinta5");
		computeEntireDuration(aList);
		
		System.out.println("\n------------------------\nCerinta6");
		cerinta6(aList);
		
	}

	public static long countDays(List<MonitoredData> aList) {
		return aList.stream().map(a -> a.getStart_time().substring(0, 10)).distinct().count();
	}

	public static Map<String, Long> countActivities(List<MonitoredData> aList) {
		return aList.stream().collect(Collectors.groupingBy(a -> a.getActivity_log(), Collectors.counting()));

	}

	public static Map<String, Map<String, Long>> cerinta4(List<MonitoredData> aList) {
		DateFormat dayFormatter = new SimpleDateFormat("MM-dd");

		Map<String, List<MonitoredData>> mapdayActivities = aList.stream()
				.collect(Collectors.groupingBy(a -> a.getStart_time().substring(0, 10)));

		Map<String, Map<String, Long>> eachDay = new HashMap<>();

		for (Entry<String, List<MonitoredData>> entry : mapdayActivities.entrySet()) {

			for (MonitoredData aData : entry.getValue()) {
				eachDay.put(entry.getKey(), countActivities(entry.getValue()));
			}
		}

		for (Entry<String, Map<String, Long>> entry : eachDay.entrySet()) {

			System.out.println("\nDay: " + entry.getKey());
			for (Entry<String, Long> secondEntry : entry.getValue().entrySet()) {
				if (secondEntry.getKey().contains("\t")) {
					System.out.print(secondEntry.getKey() + " " + secondEntry.getValue() + "\n");
				} else {
					System.out.print(secondEntry.getKey() + "\t " + secondEntry.getValue() + "\n");
				}

			}
			System.out.println();
		}

		return null;

	}

	public static List<Long> countDuration(List<MonitoredData> aList, boolean afisare) {

		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		return aList.stream().map(a -> {
			try {
				long b = (formatter.parse(a.getEnd_time())).getTime() - formatter.parse(a.getStart_time()).getTime();
				if (afisare)
					System.out.println(a.getActivity_log() + " " + b / 1000);
				return b;
			} catch (ParseException e) {

				e.printStackTrace();
			}
			return null;
		}).collect(Collectors.toList());

	}

	public static Map<String ,Long> computeEntireDuration(List<MonitoredData> aList) {
		 
		 Map<String , List<MonitoredData>> aMap=aList.stream().collect(Collectors.groupingBy(a->a.getActivity_log()));
		 Map<String , Long> mapafinala=new HashMap<>();
		 
		 for (Entry<String,List<MonitoredData>> entry : aMap.entrySet())
		 	{
			 long sum=0; 
			List<Long> listaDurata=countDuration(entry.getValue(),false); 
			
			 for(long anumber:listaDurata) {
				 sum=anumber+sum; }
			 
			 mapafinala.put(entry.getKey(), sum);
		 
		 System.out.println(entry.getKey() +" "+sum);
		 
		 }
		 
		 return null;}



	public static void cerinta6(List<MonitoredData> aList)
		{	DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		   Map <String,Long> countAct=countActivities(aList);
		  Map<String, Long> filteredCountACt=aList.stream().filter(a->{
			try {
				return formatter.parse(a.getEnd_time()).getTime()-formatter.parse(a.getStart_time()).getTime()<5*60*1000;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		})
				  
			.collect(Collectors.groupingBy(a->a.getActivity_log(),Collectors.counting()));
 
	
		

	for(Entry<String,Long> entry:filteredCountACt.entrySet()) {
			if(entry.getValue()>=90.0/100*countAct.get(entry.getKey())) 
			{
			System.out.println(entry.getKey() ); 
			}
		//	System.out.println(entry.getKey() +" "+entry.getValue()); 
		}
	
	}
	

}


