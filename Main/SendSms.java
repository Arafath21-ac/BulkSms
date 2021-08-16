package Main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;
import javax.net.ssl.HttpsURLConnection;

public class SendSms {
	public static void main(String[] args) {//entry point of the program
		System.out.println("Project Name : Sending BUlk SMS Through Java \nBy Arafath");
		System.out.println("Starting The Program");
		System.out.print("Please Enter The Phone Numbers With Seperated By \",\" : " );
		
		//Creating a scanner class for entering the numbers from arrays
		Scanner s=new Scanner(System.in);
		String mobileNo=s.next();
		String mobileNoArr[]=mobileNo.split(",");
		s.close();
		
		for(int i=0;i<mobileNoArr.length;i++) {
		
			SendSms.sendSms("The Message is send Through Java ",mobileNoArr[i]);
		}
		
		System.out.println("Message sent to the Entered Numbers");
		
	}
	
	//creating sendSms method
	public static void sendSms(String message,String number) {
	
		try {
		//getting apikey,sender id,language,router from fast2sms.com
		String apiKey="Drl2fb3kcKMPF6Z0VnC9RjE1tyqISJhB5aow7HuQYxeviNdmpggVbSCyMO0jnIZtoFikDLaEGmBNxU9v";
		String sender_Id="TXTIND";
		
		message=URLEncoder.encode(message,"UTF-8");//urlencoder
		String language="english";
		String route="v3";
		
		String myUrl="https://www.fast2sms.com/dev/bulkV2?authorization="+apiKey+"&sender_id="+sender_Id+"&message="+message+"&language="+language+"&route="+route+"&numbers="+number;
		
		URL url=new URL(myUrl);//casting to url
		
		//httpsurlconnection
		HttpsURLConnection con=(HttpsURLConnection)url.openConnection();
		
		//httpsurlconnection methods
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent","Mozilla/5.0");
		con.setRequestProperty("cache-control", "no-cache");

		int code=con.getResponseCode();
		System.out.println("Response Code :"+code);
		
		StringBuffer response=new StringBuffer();
		
		BufferedReader br=new BufferedReader(new InputStreamReader(con.getInputStream())); 
		
		while (true) {
			String line=br.readLine();
			if(line==null) {
				break;
			} 
			response.append(line);
		} 

		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
