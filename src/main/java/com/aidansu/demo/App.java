package com.aidansu.demo;

import com.aidansu.demo.model.Customer;
import feign.*;
import feign.Request;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.web.client.RestTemplate;


/**
 * Hello world!
 *
 */
public class App {


    public static void main( String[] args ) {

        System.out.println( "Spring ===================================" );
        RestTemplate restTemplate = new RestTemplate();
        Customer customer = restTemplate.getForObject("http://127.0.0.1:8080/spring/greeting?name=aidansu", Customer.class);
        System.out.println( "id = " + customer.getId() + "; name = "+customer.getName());

        System.out.println( "Feign ====================================" );

        FeignService service = Feign.builder()
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .options(new Request.Options(1000, 3500))
                .retryer(new Retryer.Default(5000, 5000, 3))
                .target(FeignService.class, "http://127.0.0.1:8080");

        Customer customer2 = service.getOwner("aidansu");
        System.out.println( "id = " + customer2.getId() + "; name = "+customer2.getName());

    }
}
