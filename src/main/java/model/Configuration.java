package model;


public class Configuration
{
  private Integer maxTimeout;
  private Integer port;
  private Long responseDelay;
  
  public Configuration() {
    this.maxTimeout = Integer.valueOf(3000);
    this.port = Integer.valueOf(4550);
    this.responseDelay = Long.valueOf(0L);
  }
  public Configuration(Integer maxTimeout, Integer port, Long responseDelay) {
    this.maxTimeout = Integer.valueOf((maxTimeout != null) ? maxTimeout.intValue() : 3000);
    this.port = Integer.valueOf((port != null) ? port.intValue() : 4550);
    this.responseDelay = Long.valueOf((responseDelay != null) ? responseDelay.longValue() : 0L);
  }

  
  public Integer getMaxTimeout() { return this.maxTimeout; }

  
  public void setMaxTimeout(Integer maxTimeout) { this.maxTimeout = maxTimeout; }

  
  public Integer getPort() { return this.port; }

  
  public void setPort(Integer port) { this.port = port; }


  
  public Long getResponseDelay() { return this.responseDelay; }

  
  public void setResponseDelay(Long responseDelay) { this.responseDelay = responseDelay; }
}
