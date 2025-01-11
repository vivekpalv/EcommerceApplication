package com.ecom.amazon.Service;

import com.ecom.amazon.Entity.Attribute;
import com.ecom.amazon.Repository.AttributeRepository;
import org.springframework.stereotype.Service;

@Service
public class AttributeService {

    private final AttributeRepository attributeRepository;

    public AttributeService(AttributeRepository attributeRepository) {
        this.attributeRepository = attributeRepository;
    }

    public Attribute getAttribute(Long attributeId) {
        return attributeRepository.findById(attributeId).orElseThrow(() -> new RuntimeException("Attribute not found"));
    }

}
