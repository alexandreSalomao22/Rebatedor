package model;

import java.util.Random;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class ISO0400 extends MessageISO {
    public ISO0400(String msg) {
        super(msg);
    }

    private DateTimeFormatter de12fmt = DateTimeFormat.forPattern("HHmmss");
    private DateTimeFormatter de13fmt = DateTimeFormat.forPattern("MMdd");

    @Override
    public String getNSU() {
        Random rand = new Random();
        return "F" + rand.nextInt(9) + "F" + rand.nextInt(9) + "F" + rand.nextInt(9) + "F" + rand.nextInt(9) + "4040404040404040";
    }

    public String processa() {
        String de07 = this.isoMessage.substring(54 * 2, (54 + 10) * 2);
        String de11 = this.isoMessage.substring(64 * 2, (64 + 6) * 2);
        String de37 = getNSU();
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
//        400 SENT:      00B4F0F4F0F0C2F2F3F8F0F0F0F0F0F8F4F0F0F0F0F0F0F0F0F0F0F0F4F0F0F0F0F0F0F0F1F1F0F0F3F0F7F3F0F0F0F0F0F0F5F1F1F1F0F0F0F5F0F2F1F4F1F3F2F0F2F3F3F1F7F0F1F1F1F3F1F8F0F5F0F2F1F0F8F34040404040404040F5F0F0F7F9F5F5F740404040404040F0F1F0F0F2F3F3F1F6F9F0F5F0F2F1F1F1F3F1F8F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F2F6F0F0F0F0F0F0F0F0F0F2F3F4F5F7F2F9F8F5F2F7F0F0F0F0F0F0F0F1
        //String msg410 = "0058F0F4F1F0F8F2F3F8F0F0F0F0F0C1F0F0F0F0F0F2F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F2F0F5F0F2F1F4F1F3F2F0F2F3F3F1F7F0F1F1F1F3F2F0F0F5F0F2F7F1F1F74040404040404040F0F0F0F0F7E2A48385A2A296F0F0";
        String msg410 = "005BF0F4F1F0F8F2F3F8F0F0F0F0F0C1F0F0F0F0F0F2F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F2"
                + de07 /* F0F5F0F4F0F0F0F0F0F7 */
                + de11 /* F2F3F4F1F4F5 */
                + de12hexebcdic /* F2F1F0F0F0F7 */
                + de13hexebcdic /* F0F5F0F3 */
                + de37
                + "F0F0F0F0F7E2A48385A2A296F0F0F2F0F0";
        return msg410;
    }
}
