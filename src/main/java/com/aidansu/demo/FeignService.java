package com.aidansu.demo;

import com.aidansu.demo.model.Customer;
import feign.Param;
import feign.RequestLine;

/**
 * Created by aidan on 17-5-22.
 */
public interface FeignService {

    @RequestLine("GET /spring/greeting?name={name}")
    Customer getOwner(@Param(value = "name") String name);

}
