package pea.example.ext.dubbo.api;

import pea.example.ext.dubbo.response.HelloResponse;

public interface GreetingService {

  String sayHello(String name);

  HelloResponse sayHello2(String name);
}
