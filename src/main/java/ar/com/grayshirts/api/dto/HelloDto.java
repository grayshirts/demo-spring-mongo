package ar.com.grayshirts.api.dto;

/**
 * Created by user on 20/09/16.
 */
public class HelloDto {

    public String message;
    public String name;

    public HelloDto() {}

    public HelloDto(String message, String name) {
        this.message = message;
        this.name = name;
    }
}
