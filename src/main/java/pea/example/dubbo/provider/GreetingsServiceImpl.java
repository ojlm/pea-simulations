package pea.example.dubbo.provider;

import pea.example.ext.dubbo.api.GreetingService;

public class GreetingsServiceImpl implements GreetingService {

  @Override
  public String sayHello(String name) {
    return "hi, " + name;
  }

  @Override
  public String sayHello2(String name) {
    return "hi, " + name;
  }
}
