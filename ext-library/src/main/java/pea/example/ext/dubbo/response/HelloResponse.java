package pea.example.ext.dubbo.response;

import java.io.Serializable;

public class HelloResponse implements Serializable {
  private String value;

  public HelloResponse() {
  }

  public HelloResponse(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }
}
