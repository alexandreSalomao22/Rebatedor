package model;

import java.util.Random;

public class ISO0102 extends MessageISO {
	public ISO0102(String msg) {
		super(msg);
	}

	@Override
	public String getNSU() {
		Random rand = new Random();
		return "F" + rand.nextInt(9) + "F" + rand.nextInt(9) + "F" + rand.nextInt(9) + "F" + rand.nextInt(9)
				+ "4040404040404040";
	}

	// automatic header.
	public String messageHexSize(int messageIntSize) {
		String size = String.valueOf(Integer.toHexString(messageIntSize / 2)).toUpperCase();
		while (size.length() < 4) {
			size = "0" + size;
		}
		return size;
	}

	public String processa() {
		String msg112;
		String de07 = this.isoMessage.substring(84, 104);
		String de37 = this.isoMessage.substring(124, 148);
		msg112 = "F0F1F1F2F0F2F0F0F0F0F0F0F0C1F0F0F0F0F0F2" + de07 + de37 + "F0F0F0F0F7E2A48385A2A296";

		msg112 = messageHexSize(msg112.length()) + msg112;

		return msg112;
	}
}
