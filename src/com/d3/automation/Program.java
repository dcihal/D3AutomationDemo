//package com.d3.automation;
//import com.gurock.testrail.APIClient;
//import com.gurock.testrail.APIException;
//import java.util.Map;
//import java.util.HashMap;
//import org.json.simple.JSONObject;
// 
//public class Program
//{
//	public static void main(String[] args) throws Exception
//	{
//		APIClient client = new APIClient("https://lodo.testrail.com");
//		client.setUser("dcihal@d3banking.com");
//		client.setPassword("D3B@nk!ng");
// 
//		
//		//JSONObject c = (JSONObject) client.sendGet("get_case/1");
//
//		Map data = new HashMap();
//		data.put("status_id", new Integer(1));
//		JSONObject r = (JSONObject) client.sendGet("get_user_by_email&email=dcihal@d3banking.com");
//		System.out.println(client.get("id"));
//
//	}
//}