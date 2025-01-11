package com.ecom.amazon.Utility;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.HashMap;

public class ErrorUtility {
    public static ResponseEntity<?> errorResponse(BindingResult result){
        HashMap<String, Object> errorHashMap = new HashMap<>();
        result.getFieldErrors().forEach(fieldError -> { errorHashMap.put(fieldError.getField(), fieldError.getDefaultMessage());});
        return ResponseEntity.badRequest().body(errorHashMap);
    }
}
