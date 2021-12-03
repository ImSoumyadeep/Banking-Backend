package com.Edgeverve.demo.Services;

public class EncyptionDecryption {

	public static String encryption(String data){
		char[] arrayCreated=data.toCharArray();
		for(int i=0;i<data.length();i++){
		if(i%2==0){
		arrayCreated[i]=(char)((int)arrayCreated[i]+1);
		}else{
		arrayCreated[i]=(char)((int)arrayCreated[i]-1);
		}
		}
		return new String(arrayCreated);
		}




		public static String decryption(String data){
		char[] arrayCreated=data.toCharArray();
		for(int i=0;i<data.length();i++){
		if(i%2==0){
		arrayCreated[i]=(char)((int)arrayCreated[i]-1);
		}else{
		arrayCreated[i]=(char)((int)arrayCreated[i]+1);
		}
		}
		return new String(arrayCreated);
		}
	
}
