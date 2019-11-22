package main;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.atomic.AtomicInteger;

public class Y_SW_RebatedorAdquirencia {
	private ServerSocket server;
	private static int port = 4550;
	public static int sleepTime = 500;
	public static final AtomicInteger threadsAtivas = new AtomicInteger(0);

	public static void main(String[] args) {
		if (args.length > 0)
			sleepTime = Integer.parseInt(args[0]);
		if (args.length > 1) {
			port = Integer.parseInt(args[1]);
		}
		(new Y_SW_RebatedorAdquirencia()).listenSocket();
	}

	public void listenSocket() {
		try {
			this.server = new ServerSocket(port);
		} catch (IOException e) {
			System.exit(-1);
		}
		while (true) {
			try {
				while (true) {
					(new Thread(new Controller(this.server.accept()))).start();
					break;
				}

			} catch (IOException e) {
				System.exit(-1);
			}
		}
	}

	protected void finalize() {
		try {
			this.server.close();
		} catch (IOException e) {
			System.exit(-1);
		}
	}
}
