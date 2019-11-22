package model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;


public class ISO0100 extends MessageISO {
    public ISO0100(String msg) {
        super(msg);
    }

    private DateTimeFormatter de12fmt = DateTimeFormat.forPattern("HHmmss");
    private DateTimeFormatter de13fmt = DateTimeFormat.forPattern("MMdd");

    @Override
    public String getNSU() {
        Random rand = new Random();
        return "F" + rand.nextInt(9) + "F" + rand.nextInt(9) + "F" + rand.nextInt(9) + "F" + rand.nextInt(9) + "4040404040404040";
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
    	
        // numero do cartao (ll var e prefixo)
        String de02prefixo = isoMessage.substring(36 * 2, (36 + 8) * 2);
        String msg110; 
        int tamanhomsg = isoMessage.length();
        
        GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(new Date());
		int daysmore = 30;
		gc.add(Calendar.DAY_OF_MONTH, daysmore);
		SimpleDateFormat new_date = new SimpleDateFormat("ddMMyyyy");
		String dt30 = new_date.format(gc.getTime());
		
        int bit2size = Integer.parseInt(isoMessage.substring(72,76).toUpperCase().replace("F", ""));
        int bit3size = 6;
        int bit4size = 12;
        int bit7start = (bit2size+bit3size+bit4size+38)*2;
        int bit11start = (bit7start+20);

        String de11 = isoMessage.substring(bit11start, bit11start+12);
        String de07 = isoMessage.substring(bit7start, bit7start+20);
		
        if (de02prefixo.equals("F1F4F3F6F4F9F0F1")) {
        	
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
            
    		String dt30more = 
    				"F" + dt30.charAt(0)
    				+ "F" + dt30.charAt(1)
    				+ "F" + dt30.charAt(2)
    				+ "F" + dt30.charAt(3)
    				+ "F" + dt30.charAt(4)
    				+ "F" + dt30.charAt(5)
    				+ "F" + dt30.charAt(6)
    				+ "F" + dt30.charAt(7)
    				;
    
            String de37 = getNSU();
        	
        	if (tamanhomsg < 1600) {
        		
        		msg110 = "F0F1F1F0F8F2F3F8F0F0F0F0F0C5F0F1F0F0F0F2F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F2"
                        + de07 /* F0F5F0F4F0F3F5F6F4F2 */
                        + de11 /* F2F3F4F2F9F2 */
                        + de12hexebcdic /* F0F0F5F6F4F2 */
                        + de13hexebcdic /* F0F5F0F4 */
                        + de37 /* F5F0F0F74040404040404040 */
                        + "F1F2F1F8F2F2"
                        + "F0F0"
                        + "F0F3F7"
                        + "40F4F2F0F7F0F1F0F3F2F1F0F9F2F0F3F1F3F2F9F3F0F2F0F1"
                        + "F0F7F0F8"
                        + dt30more /*FXFXFXFXFXFXFXFX*/
                        + "F0F0F7E2A48385A2A296F0F0"
                        + "F2F0F0";
        		
        		msg110 = messageHexSize(msg110.length()) + msg110;
        		return msg110;
        		
        	} else
        	{
        		msg110 = "F0F1F1F0F8F2F3F8F0F0F0F0F0C5F0F1F0F0F0F2F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F2"
                        + de07 /* F0F5F0F4F0F3F5F6F4F2 */
                        + de11 /* F2F3F4F2F9F2 */
                        + de12hexebcdic /* F0F0F5F6F4F2 */
                        + de13hexebcdic /* F0F5F0F4 */
                        + de37 /* F5F0F0F74040404040404040 */
                        + "F1F6F3F8F7F0"
                        + "F0F0"
                        + "F0F9F1"
                        + "40F4F2F1F3F0F1F0F3F2F1F1F0F3F0F2F0F6F4F3F2F08c5ea13e21c53e63F0081111dc99810543000000F4F4F2F05845316e4d31303275546c70487768707270476bF9F2F0F3F1F3F2F9F3F0F2F0F1"
                        + "F0F7F0F8"
                        + dt30more /*FXFXFXFXFXFXFXFX*/
                        + "F0F0F7E2A48385A2A296F0F0"
                        + "F2F0F0";
        		msg110 = messageHexSize(msg110.length()) + msg110;
        		return msg110;
        	}

            
        } else {
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
            
            String dt30more = 
    				"F" + dt30.charAt(0)
    				+ "F" + dt30.charAt(1)
    				+ "F" + dt30.charAt(2)
    				+ "F" + dt30.charAt(3)
    				+ "F" + dt30.charAt(4)
    				+ "F" + dt30.charAt(5)
    				+ "F" + dt30.charAt(6)
    				+ "F" + dt30.charAt(7)
    				;
            String de37 = getNSU();
    
    
            if (tamanhomsg < 1600) {
        		
        		msg110 = "F0F1F1F0F8F2F3F8F0F0F0F0F0C5F0F1F0F0F0F2F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F2"
                        + de07 /* F0F5F0F4F0F3F5F6F4F2 */
                        + de11 /* F2F3F4F2F9F2 */
                        + de12hexebcdic /* F0F0F5F6F4F2 */
                        + de13hexebcdic /* F0F5F0F4 */
                        + de37 /* F5F0F0F74040404040404040 */
                        + "F1F2F1F8F2F2"
                        + "F0F0"
                        + "F0F3F7"
                        + "40F4F2F0F7F0F1F0F3F2F1F0F9F2F0F3F1F3F2F9F3F0F2F0F1"
                        + "F0F7F0F8"
                        + dt30more /*FXFXFXFXFXFXFXFX*/
                        + "F0F0F7E2A48385A2A296F0F0"
                        + "F2F0F0";
        		msg110 = messageHexSize(msg110.length()) + msg110;
        		return msg110;
        		
        	} else
        	{
        		msg110 = "F0F1F1F0F8F2F3F8F0F0F0F0F0C5F0F1F0F0F0F2F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F2"
                        + de07 /* F0F5F0F4F0F3F5F6F4F2 */
                        + de11 /* F2F3F4F2F9F2 */
                        + de12hexebcdic /* F0F0F5F6F4F2 */
                        + de13hexebcdic /* F0F5F0F4 */
                        + de37 /* F5F0F0F74040404040404040 */
                        + "F1F6F3F8F7F0"
                        + "F0F0"
                        + "F0F9F1"
                        + "40F4F2F1F3F0F1F0F3F2F1F1F0F3F0F2F0F6F4F3F2F08c5ea13e21c53e63F0081111dc99810543000000F4F4F2F05845316e4d31303275546c70487768707270476bF9F2F0F3F1F3F2F9F3F0F2F0F1"
                        + "F0F7F0F8"
                        + dt30more /*FXFXFXFXFXFXFXFX*/
                        + "F0F0F7E2A48385A2A296F0F0"
                        + "F2F0F0";
        		msg110 = messageHexSize(msg110.length()) + msg110;
        		return msg110;
        	}
        }
      
        
    }
}
