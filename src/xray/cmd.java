package xray;

import java.io.IOException;

public class cmd {
    public void winRun(String cmd) {
        try{
        	//String xrayPath = System.getProperty("user.dir");
        	//xrayPath = xrayPath.replace("\\", "/") + "/xray/xray.exe ";
        	System.out.println("cmd /c xray.exe " + cmd);
            Process pro = Runtime.getRuntime().exec("cmd /c xray.exe " + cmd);
        }catch(IOException exception){
            //System.out.println(exception);
        }
    }
    public void macRun(String cmd) {
        try{
        	//String xrayPath = System.getProperty("user.dir") + "./xray ";
            Process pro = Runtime.getRuntime().exec("nohup " + "./xray " + cmd + " &");
        }catch(IOException exception){
            //System.out.println(exception);
        }
    }
    
    public void run(String cmd) {
    	//≈–∂œœµÕ≥÷¥––√¸¡Ó
    	String os = System.getProperty("os.name"); 
    	if(os.toLowerCase().startsWith("win")){ 
    		new cmd().winRun(cmd);
    	}else {
    		new cmd().macRun(cmd);;
    	}
    }
    

}
