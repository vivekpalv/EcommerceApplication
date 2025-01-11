package com.ecom.amazon.Utility;

import java.math.BigDecimal;

public class Validation {
    public static boolean isCouponValidPriceRange(BigDecimal minPrice, BigDecimal maxPrice){
        if (minPrice.compareTo(maxPrice) < 0){ // we need to handle exception where we use this method by using catch or throws
            throw new IllegalArgumentException("Minimum price should be less than maximum price");
        }

        return true;
    }
}
