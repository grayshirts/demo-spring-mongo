package ar.com.grayshirts.api.controller;

import ar.com.grayshirts.api.dto.HelloDto;
import ar.com.grayshirts.api.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.constraints.NotNull;


@RestController
public class HelloController {

    @Autowired
    private HelloService helloService;

    @RequestMapping(path = "/hello", method = RequestMethod.POST)
    public HelloDto hello(@RequestBody @Validated NameDto nameDto) {
        String helloTo = helloService.hello(nameDto.name);
        return new HelloDto(helloTo, nameDto.name);
    }

    public static class NameDto {
        @NotNull
        public String name;
    }
}
