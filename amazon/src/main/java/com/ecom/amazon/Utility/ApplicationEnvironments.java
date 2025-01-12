package com.ecom.amazon.Utility;

import org.springframework.beans.factory.annotation.Value;

public class ApplicationEnvironments {

//    @Value("${jwt.secret}") // get the value from application.properties (@Value annotation not works with static keyword, because it requires getter to initialize value)
    public static String JWT_SECRET = "EcommerceApplicationEcommerceApplication"; //user at least '32 characters' long string, else we will get runtime error

}
