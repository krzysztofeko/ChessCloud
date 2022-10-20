package org.example.controller;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.stream.IntStream;

@RestController
public class Test {

    @Cacheable("x")
    @GetMapping("/xd")
    public String xd() {
        return "xd";
    }

    @Cacheable("x")
    @GetMapping("/")
    public String home(@RequestParam Optional<String> revert) {
        if(revert.isEmpty()) {
            return "Please test revert parameter :)";
        }
        StringBuffer stringBuffer = new StringBuffer(revert.get());
        long count = IntStream.range(1, 1000000)
                .filter(operand -> {
                    stringBuffer.append(operand);
                    return true;
                }).count();
        return stringBuffer.reverse().toString();
    }
}
