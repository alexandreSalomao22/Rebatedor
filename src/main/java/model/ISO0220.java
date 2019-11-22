package model;

import java.util.Random;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class ISO0220 extends MessageISO {
    public ISO0220(String msg) {
        super(msg);
    }

    private DateTimeFormatter de12fmt = DateTimeFormat.forPattern("HHmmss");
    private DateTimeFormatter de13fmt = DateTimeFormat.forPattern("MMdd");

    @Override
    public String getNSU() {
        Random rand = new Random();
        return "F" + rand.nextInt(9) + "F" + rand.nextInt(9) + "F" + rand.nextInt(9) + "F" + rand.nextInt(9) + "F" + rand.nextInt(9) + "40404040404040";
    }

    public String processa() {
        String de07 = this.isoMessage.substring(54 * 2, (54 + 10) * 2);
        String de11 = this.isoMessage.substring(64 * 2, 64 * 2 + 6 * 2);
        String de37 = getNSU();
        //String de11 = isoMessage.substring(64, 70);
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

        //String msg230 = "008DF0F2F3F0F8F2F3F8F0F0F0F0F0C5F0F1F0F0F0F2F0F0F0F0F0F0F0F0F0F0F0F1F0F0F0F0F0F5F0F6F0F2F2F2F0F6F0F9F7F0F6F0F2F3F2F2F0F6F0F5F0F5F1F1F2F0F940404040404040F5F3F7F4F2F9F0F0F0F1F240F4F2F0F7F0F1F0F3F2F1F0F0F0F7E2A48385A2A296F0F3F1F0F4F0F0F0F0F0F0F0F0F5F0F0F0F0F0F0F0F0F0F0F2F0F0F0F0F0F0F0F0F0";
        String msg230 = "008D"
                + "F0F2F3F0"
                + "F8F2F3F8F0F0F0F0"
                + "F0C5F0F1F0F0F0F2F0F0F0F0F0F0F0F0F0F0F0F1F0F0F0F0"
                + de07 /* F0F5F0F6F0F2F2F2F0F6 */
                + de11 /* F0F9F7F0F6F0 */
                + de12hexebcdic /* F2F3F2F2F0F6 */
                + de13hexebcdic /* F0F5F0F5 */
                + de37 /* F1F1F2F0F940404040404040 */
                + "F5F3F7F4F2F9F0F0F0F1F240F4F2F0F7F0F1F0F3F2F1F0F0F0F7E2A48385A2A296F0F3F1F0F4F0F0F0F0F0F0F0F0F5F0F0F0F0F0F0F0F0F0F0F2F0F0F0F0F0F0F0F0F0";

        return msg230;
    }
}
