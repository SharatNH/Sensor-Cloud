package run;

public class Countries {

public Countries(String sid,String sgp,String type)
{
	this.setSensorgp(sgp);
	this.setSensorid(sid);
	this.setSensing(type);
	//this.setThreshold(threshold);
}

public Countries() {
}
//public int threshold;
public String sid;
public String sgp;
public String sensing;
public int len;


public void setSensorgp(String sensorgp) {
this.sgp = sensorgp;
}
public String getSensorgp() {
	return sgp;
}

public void setSensing(String sense) {
this.sensing = sense;
String [] typepara = null;
typepara = sensing.split(",");
len = typepara.length;
System.out.println(len);
}
public int getSensing() {
	return len;
}
public void setSensorid(String sensorid) {
this.sid= sensorid;
}
public String getSensorid() {
return sid;
}
/*
public void setThreshold(int t) {
this.threshold = t;
}
public int getThreshold() {
	return threshold;
}
*/
}