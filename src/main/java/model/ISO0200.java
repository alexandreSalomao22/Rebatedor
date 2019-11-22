package model;

import java.util.Random;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class ISO0200 extends MessageISO {
	public ISO0200(String msg) {
		super(msg);
	}

	private DateTimeFormatter de12fmt = DateTimeFormat.forPattern("HHmmss");
	private DateTimeFormatter de13fmt = DateTimeFormat.forPattern("MMdd");

	@Override
	public String getNSU() {
		Random rand = new Random();
		return "F" + rand.nextInt(9) + "F" + rand.nextInt(9) + "F" + rand.nextInt(9) + "F" + rand.nextInt(9) + "F"
				+ rand.nextInt(9) + "40404040404040";
	}
	
	//automatic header.
	public String messageHexSize(int messageIntSize){
		  String size = String.valueOf(Integer.toHexString(messageIntSize/2)).toUpperCase();
		  while(size.length() < 4){
		     size = "0" + size;
		  }
		  return size;
		}

	@Override
    public String processa() {

        String msg210;

        int tamanhomsg = isoMessage.length();
        int bit2size = Integer.parseInt(isoMessage.substring(72,76).toUpperCase().replace("F", ""));
        int bit3size = 6;
        int bit4size = 12;
        int bit7start = (bit2size+bit3size+bit4size+38)*2;
        int bit11start = (bit7start+20);

        String de11 = isoMessage.substring(bit11start, bit11start+12);
        String de07 = isoMessage.substring(bit7start, bit7start+20);
        
        DateTime now = new DateTime();
        String de12ascii = de12fmt.print(now);
        String de13ascii = de13fmt.print(now);
        String de12hexebcdic =
                "F" + de12ascii.charAt(0)
                + "F" + de12ascii.charAt(1)
                + "F" + de12ascii.charAt(2)
                + "F" + de12ascii.charAt(3)
                + "F" + de12ascii.charAt(4)
                + "F" + de12ascii.charAt(5)
                ;
        String de13hexebcdic =
                "F" + de13ascii.charAt(0)
                + "F" + de13ascii.charAt(1)
                + "F" + de13ascii.charAt(2)
                + "F" + de13ascii.charAt(3)
                ;
        String de37 = getNSU();
        //verificação sem3ds
        if (tamanhomsg < 1600) {
        	msg210 = "F0F2F1F0" //tipo mensagem 210
        			+ "F8F2F3F8F0F0F0F0F0c5F0F1F0F0F0F2F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F2" //mapa bits
        			+ de07
        			+ de11
        			+ de12hexebcdic
        			+ de13hexebcdic
        			+ de37
        			+ "F5F4F1F7F0F1" //d38
        			+ "F0F0" //d39
        			+ "F0F2F5"
        			+ "40F4F2F0F7F0F1F0F3F2F1F0F9F2F0F3F1F3F2F9F3F0F2F0F1"
        			+ "F0F0F7e2a48385a2a296"
        			+ "F0F0"
        			+ "F2F0F0"
        			;
        	msg210 = messageHexSize(msg210.length()) + msg210;
        	
        	return msg210;
        	
        } else {
        	msg210 = "F0F2F1F0" //tipo mensagem 210
        			+ "F8F2F3F8F0F0F0F0F0c5F0F1F0F0F0F2F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F2" //mapa bits
        			+ de07
        			+ de11
        			+ de12hexebcdic
        			+ de13hexebcdic
        			+ de37
        			+ "F5F4F1F7F0F1" //d38
        			+ "F0F0" //d39
        			+ "F0F7F9"
        			+ "40F4F2F1F3F0F1F0F3F2F1F1F0F3F0F2F0F6F4F3F2F08c5ea13e21c53e63F0081111dc99810513000000F4F4F2F05845316e4d31303275546c70487768707270476bF9F2F0F3F1F3F2F9F3F0F2F0F1"
        			+ "F0F0F7e2a48385a2a296"
        			+ "F0F0"
        			+ "F2F0F0"
        			;
        	
        	msg210 = messageHexSize(msg210.length()) + msg210;
        	return msg210;
        }
        
        
      
    }
}
