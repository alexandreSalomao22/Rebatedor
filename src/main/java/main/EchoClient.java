package main;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class EchoClient {
	static OutputStream out = null;
	static InputStream in = null;

	public static void main(String[] args) {
		int socketServerPORT = 4550;
		String host = "127.0.0.1";
		int nThreads = 10;
		int nExec = 15;
		int nSuccess = 0;
		int nErro = 0;

		String[] msgs = {
				"0612F0F2F0F0C6F2F2F4F4F0F0F0F0F0F6F1F8F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F3F1F1F6F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F3F0F0F4F0F0F0F0F0F0F0F0F0F8F0F0F1F1F1F2F1F7F1F9F4F4F1F8F4F6F0F5F0F1F0F1F0F0F0F0F1F2F3F4F1F0F8F84040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040F0F1F940F4F2F0F7F0F1F0F3F2F1F0F9F2F0F3F0F0F0F9F8F6F5F6F7404040404040404040F5F5F6F0F0F2F640404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040F0F0F0F0F0F0F0F0F0F0F0F0F0F0F0F040404040404040404040404040C8819797A840C7A4A840404040404040404040404040404040404040404040F0404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040F0F2F6F1F0F0F1F1F8F1F1F1F2F1F5F0F2F4F6F4F6F0F5F0F0F0F0F1F0F0F1",
				"0074F0F2F0F2F2F2F1F8F0F0F0F0F0F8F0F0F0F0F0F0F0F0F3F0F0F4F1F1F1F2F1F7F1F9F4F4F1F5F1F9F4F4F1F1F1F2F2F6F0F0F4F3F94040404040" };

		try {
			Socket client = new Socket("127.0.0.1", 4550);
			client.setTcpNoDelay(true);
			out = client.getOutputStream();
			in = client.getInputStream();

			long init = System.currentTimeMillis();

			System.out.println("Init: " + init);

			for (int i = 0; i < nExec; i++) {

				for (String msg : msgs) {

					byte[] ob = getReturningBytes(msg);

					writeMessage(ob);

					String receivedMessage = readMessage();
					nSuccess++;
				}
			}

			System.out.println("Total: " + (System.currentTimeMillis() - init));

			out.close();
			in.close();

			client.close();
		} catch (Exception exception) {
		}
	}

	private static String readMessage() {
		String headerBytes = null;

		try {
			headerBytes = readBytes(0, 2);
		} catch (IOException iOException) {
		}

		if (headerBytes == null) {
			return null;
		}

		int msgSize = getMessageSize(headerBytes);

		if (msgSize <= 0) {
			return "";
		}

		String msgBody = "";

		try {
			msgBody = readBytes(0, msgSize);
		} catch (IOException iOException) {
		}

		return msgBody;
	}

	private static int getMessageSize(String msgHeader) {
		int value = Integer.parseInt(msgHeader, 16);
		if (value > 8192)
			return 8192;
		return value;
	}

	private static void writeMessage(byte[] ob) {
		try {
			out.write(ob);
			out.flush();
		} catch (IOException iOException) {
		}
	}

	private static byte[] getReturningBytes(String msg) {
		int NumberChars = msg.length();
		byte[] bytes22 = new byte[NumberChars / 2];

		for (int i = 0; i < NumberChars; i += 2) {
			bytes22[i / 2] = (byte) (Integer.parseInt(msg.substring(i, i + 2), 16) & 0xFF);
		}

		return bytes22;
	}

	private static String readBytes(int offset, int size) throws IOException {
		byte[] data = new byte[size];

		in.read(data, offset, size);

		if (data[0] == 0 && data[1] == 0) {
			return null;
		}

		String stb = new String();
		int n;
		for (n = 0; n < data.length; n++) {
			stb = stb + Integer.toHexString(Integer.parseInt(String.valueOf(data[n] & 0xFF)));
		}

		return stb;
	}
}
