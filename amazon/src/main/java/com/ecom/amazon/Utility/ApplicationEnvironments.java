package com.ecom.amazon.Utility;

import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;

public class ApplicationEnvironments {

//    @Value("${jwt.secret}") // get the value from application.properties (@Value annotation not works with static keyword, because it requires getter to initialize value)
    public static String JWT_SECRET = "EcommerceApplicationEcommerceApplication"; //user at least '32 characters' long string, else we will get runtime error

    public static List<String> getShouldNotFilterPathsByJwt() {
        ArrayList<String> excludedPaths = new ArrayList<>();
        excludedPaths.add("/authentication/customerSignUp");
        excludedPaths.add("/authentication/vendorSignUp");
        excludedPaths.add("/authentication/loginCustomer");
        excludedPaths.add("/authentication/loginVendor");

        return excludedPaths;
    }



}
