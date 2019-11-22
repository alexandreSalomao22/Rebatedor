package main;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class Consumer implements Runnable {
  private OutputStream out;
  
  public Consumer(Socket client, String message) {
    this.out = null;
    this.msg = message;
    try {
      this.out = client.getOutputStream();
    } catch (IOException e) {
      System.exit(-1);
    } 
  }
  private final String msg;
  
  public void run() {
    try {
      Thread.sleep(Y_SW_RebatedorAdquirencia.sleepTime);
      this.out.write(getReturningBytes());
      this.out.flush();
    } catch (Exception exception) {}
  }

  
  private byte[] getReturningBytes() {
    int NumberChars = this.msg.length();
    byte[] bytes22 = new byte[NumberChars / 2];
    for (int i = 0; i < NumberChars; i += 2) {
      bytes22[i / 2] = (byte)(Integer.parseInt(this.msg.substring(i, i + 2), 16) & 0xFF);
    }
    return bytes22;
  }
}
