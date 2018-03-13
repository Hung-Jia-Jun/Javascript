package com.example.test;
 
import android.os.Build;
import android.os.Bundle;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import com.unity3d.player.UnityPlayer;
import com.unity3d.player.UnityPlayerActivity;

import android.R.string;
import android.accessibilityservice.GestureDescription.Builder;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;
@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
public class MainActivity extends UnityPlayerActivity {  

	 private final static String COMMAND_AIRPLANE_ON = "settings put global airplane_mode_on 1 \n " +
	            "am broadcast -a android.intent.action.AIRPLANE_MODE --ez state true\n ";
	    private final static String COMMAND_AIRPLANE_OFF = "settings put global airplane_mode_on 0 \n" +
	            " am broadcast -a android.intent.action.AIRPLANE_MODE --ez state false\n ";
	    private final static String COMMAND_SU = "su";
	    
    @Override  
    protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
    }  
    
    public void testMethod(String data) { 
        Log.i("TAG", "The data was "+data);
    }
    
    
    public boolean isAirplaneModeOn(Context context)
    {

             return Settings.Global.getInt(getContentResolver(), 
                     Settings.Global.AIRPLANE_MODE_ON, 0) != 0;

    }
    //判断飞行模式开关
    public boolean isAirplaneModeOn() 
    {
    //4.2以下
       if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) 
       {
           return Settings.System.getInt(getContentResolver(), 
                   Settings.System.AIRPLANE_MODE_ON, 0) != 0;          
       } 
       else //4.2或4.2以上
       {
           return Settings.Global.getInt(getContentResolver(), 
                   Settings.Global.AIRPLANE_MODE_ON, 0) != 0;
       }   
    }
    public void setAirplaneModeOn(boolean isEnable) 
    {  
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) 
       {
            Settings.System.putInt(getContentResolver(),  
                            Settings.System.AIRPLANE_MODE_ON,isEnable ? 1:0);  
                 Intent intent = new Intent(Intent.ACTION_AIRPLANE_MODE_CHANGED);  
                            intent.putExtra("state", isEnable);  
                            sendBroadcast(intent); 
       }
    else //4.2或4.2以上 
       {

               if (isEnable)
                      writeCmd(COMMAND_AIRPLANE_ON);
                  else
                      writeCmd(COMMAND_AIRPLANE_OFF);
       }  

    }  
  //写入shell命令
    public static void writeCmd(String command){
         try{
             Process su = Runtime.getRuntime().exec(COMMAND_SU);
             DataOutputStream outputStream = new DataOutputStream(su.getOutputStream());

             outputStream.writeBytes(command);
             outputStream.flush();

             outputStream.writeBytes("exit\n");
             outputStream.flush();
             try {
                 su.waitFor();
             } catch (Exception e) {
                 e.printStackTrace();
             }

             outputStream.close();
         }catch(Exception e){
             e.printStackTrace();
         }
     }
    
    
    
	public void setAirplaneMode() throws IOException{
		
		setAirplaneModeOn(true);
		
}
	
	public void execCommand(String command) throws IOException 
	{
	    Runtime runtime = Runtime.getRuntime();    
	    Process proc = runtime.exec(command);  
	        InputStream inputstream = proc.getInputStream();  
	        InputStreamReader inputstreamreader = new InputStreamReader(inputstream);  
	        BufferedReader bufferedreader = new BufferedReader(inputstreamreader);  
	        String line = "";  
	        StringBuilder sb = new StringBuilder(line);
	        UnityPlayer.UnitySendMessage("Main Camera","showMessage",command); 
	        while ((line = bufferedreader.readLine()) != null) {  
	            //System.out.println(line);  
	                sb.append(line);  
	                sb.append('\n');  
	        }   
	        try {  
	            if (proc.waitFor() != 0) {  
	            	 UnityPlayer.UnitySendMessage("Main Camera","showMessage","exit value = " + proc.exitValue()); 
	                System.err.println("exit value = " + proc.exitValue());  
	            }  
	        }  
	        catch (InterruptedException e) {    
	        	 UnityPlayer.UnitySendMessage("Main Camera","showMessage",e.toString()); 
	            System.err.println(e);  
	        }  
	    }  
	}  
	
	  
  
