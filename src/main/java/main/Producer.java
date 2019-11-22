package main;

import java.net.Socket;
import model.ISO0100;
import model.ISO0102;
import model.ISO0200;
import model.ISO0202;
import model.ISO0220;
import model.ISO0400;
import model.MessageISO;

public class Producer implements Runnable {
	public final Socket client;
	private final String msg;

	public Producer(Socket client, String msgBody) {
		this.client = client;
		this.msg = msgBody.toUpperCase();
	}

	public void run() {
		MessageISO iso = this.getMsgISO();
		if (iso != null) {
			(new Consumer(this.client, iso.processa())).run();
		}
	}

	private MessageISO getMsgISO() {
		String msgType = this.msg.substring(0, 8);
		  if (msgType.equals("F0F2F0F0")) {
			return new ISO0200(this.msg);
		} if (msgType.equals("F0F2F0F2")) {
			return new ISO0202(this.msg);
		} if (msgType.equals("F0F1F0F0")) {
			return new ISO0100(this.msg);
		} if (msgType.equals("F0F1F0F2")) {
			return new ISO0102(this.msg);
		} if (msgType.equals("F0F2F2F0")) {
			return new ISO0220(this.msg);
		} if (msgType.equals("F0F4F0F0")) {
			return new ISO0400(this.msg);
		}
		
		
		System.out.println(this.msg);
		throw new IllegalArgumentException("exhaustion: [" + msgType + "]");
	}
}
