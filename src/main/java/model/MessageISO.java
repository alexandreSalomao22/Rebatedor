package model;

public abstract class MessageISO
{
  String isoMessage;
  
  public MessageISO(String msg) { this.isoMessage = msg; }


  
  public String getIso() { return this.isoMessage; }


  
  public void setIso(String iso) { this.isoMessage = iso; }
  
  public abstract String processa();
  
  public abstract String getNSU();
}
