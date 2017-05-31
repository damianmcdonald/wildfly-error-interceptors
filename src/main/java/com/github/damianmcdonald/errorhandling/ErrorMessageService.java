package com.github.damianmcdonald.errorhandling;

public class ErrorMessageService {

    @InformativeErrorInterceptorBinding(errorMsg = "working.example.error.message")
    String workingExample() {
        return "I'm working";
    }

    @InformativeErrorInterceptorBinding(errorMsg = "failing.example.error.message")
    String failingExample() {
       throw new RuntimeException("failing Example error");
    }

}
