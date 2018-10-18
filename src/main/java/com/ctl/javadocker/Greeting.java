package com.ctl.javadocker;

public class Greeting {

  private final long id;
  private final String content;
  private final String host;

  public Greeting(long id, String content, String host) {
      this.id = id;
      this.content = content;
      this.host = host;
  }

  public long getId() {
      return id;
  }

  public String getContent() {
      return content;
  }
  
  public String getHost() {
	  return host;
  }
}
