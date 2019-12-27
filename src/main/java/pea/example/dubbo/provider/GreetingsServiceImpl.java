package pea.example.dubbo.provider;

import pea.example.ext.dubbo.api.GreetingService;
import pea.example.ext.dubbo.response.HelloResponse;

public class GreetingsServiceImpl implements GreetingService {

  @Override
  public String sayHello(String name) {
    return "hi, " + name;
  }

  @Override
  public HelloResponse sayHello2(String name) {
    return new HelloResponse("hi, " + name);
  }
}
