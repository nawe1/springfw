package com.kh.api.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kh.api.model.vo.AirVo;

public class ApiJavaApp {
	
	public static final String SERVICE_KEY= "nknwiE8KsQh0A2WWxM8GbQees7QCC7dNT2%2FvYRslYmvUnukwtYhGhcRMiUANWBOzJ%2BR5LJ%2FtR0VaNO9FmZ9Jtw%3D%3D";
	
	public static void main(String[] args) throws IOException{
		
		
		//System.out.println("하이~");
		//순수 Java만으로 client Program을 만들어서 시도별 API서버로 요청 보내고 응답 받기!
		
		//요청을 보낼 URL이 필요함!!! => String타입으로 만들것
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty");
		sb.append("?serviceKey=");
		sb.append(SERVICE_KEY);
		sb.append("&sidoName=");
		sb.append(URLEncoder.encode("서울","UTF-8"));
		sb.append("&returnType=json");
		
		String url = sb.toString();
		
		//System.out.println(url);
		
		//Java코드를 가지고 URL로 요청을 보낼 것!
		//HttpURLConnection 객체를 활용해서 API서버로 요청
		//1. java.net.URL로 객체 생성 => 
		URL requestUrl = new URL(url);
		
		//2. 생성한 url객체를 가지고 HttpURLConnection 객체를 생성
		HttpURLConnection urlConnection = (HttpURLConnection)requestUrl.openConnection();
		
		//3.요청에 필요한 설정
		urlConnection.setRequestMethod("GET");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		
		
		//Buffer란? -> 엄청많은데 임시저장공간이라고 생각하면 된다. 
		/*
		System.out.println(br.readLine());
		
		
		
		String responseXml="";
		while((responseXml = br.readLine()) != null){
			System.out.println(responseXml);
		}
		*/
		
		String responseJson = br.readLine();
		//System.out.println(responseJson);
		
		
		//AirVo air = new AirVo();
		//air.setKhaiValue("57");
		
		
		//라이브러리
		//JsonObject, JsonArray => JSON => 자바데이터 => GSON라이브러리
		// + JsonParser
		//JSONObject, JSONArray => 자바데이터를 => JSON => JSON라이브러리
		
		JsonObject jsonObj = JsonParser.parseString(responseJson).getAsJsonObject();
		//System.out.println(responseJson);
		//System.out.println("---------------------");
		//System.out.println(jsonObj);
		
		
		JsonObject responeObj = jsonObj.getAsJsonObject("response"); //response라는 property로 접근 => {} JsonObject
 		//System.out.println("---------------------------");
		//System.out.println(responeObj);
		
		JsonObject bodyObj = responeObj.getAsJsonObject("body"); //body라는 property로 접근 => {} JsonObject
 		//System.out.println("---------------------------");
		//System.out.println(bodyObj);
		
		
		int totalCount = bodyObj.get("totalCount").getAsInt(); //totalCount라는 property로 접근 => int;
		//System.out.println(totalCount);
		
		
		JsonArray items =bodyObj.getAsJsonArray("items"); // items property => : [] JsonArray
		//System.out.println(items);
		
		JsonObject firstItem = items.get(0).getAsJsonObject();
		//System.out.println(firstItem);
		
		List<AirVo> list = new ArrayList();
		for(int i =0; i<items.size(); i++) {
			
			JsonObject item = items.get(i).getAsJsonObject();
		
			AirVo air = new AirVo();
			air.setPm10Value(item.get("pm10Value").getAsString());
			air.setStationName(item.get("stationName").getAsString());
			air.setDataTime(item.get("dataTime").getAsString());
			air.setO3Value(item.get("o3Value").getAsString());
			air.setKhaiValue(item.get("khaiValue").getAsString());
			
			list.add(air);
		}
		for(AirVo air : list) {
			System.out.println(air);
		}
		
		//자원 반납
		br.close();
		urlConnection.disconnect();
	}
}
