package test.cdi;

import java.io.*;

public class Greeter {

    public void greet(PrintStream to, String name) {
        to.println(createGreeting(name));
    }

    String createGreeting(String name) {
        return "Hello, " + name;
    }

    @XxxIsAdded
    public String interceptorAddsXxxTo(String source) {
        return source;
    }
}
