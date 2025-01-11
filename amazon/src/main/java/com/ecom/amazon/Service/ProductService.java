package com.ecom.amazon.Service;

import com.ecom.amazon.DTO.Request.AttributeDTO;
import com.ecom.amazon.DTO.Request.AttributeFieldDTO;
import com.ecom.amazon.DTO.Request.CreateProductDTO;
import com.ecom.amazon.Entity.Attribute;
import com.ecom.amazon.Entity.Embeded.AttributeField;
import com.ecom.amazon.Entity.Product;
import com.ecom.amazon.Entity.Vendor;
import com.ecom.amazon.Repository.AttributeRepository;
import com.ecom.amazon.Repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final AttributeRepository attributeRepository;

    public ProductService(ProductRepository productRepository, AttributeRepository attributeRepository) {
        this.productRepository = productRepository;
        this.attributeRepository = attributeRepository;
    }

    public Product createProduct(CreateProductDTO productDTO, Vendor vendor){

        Product product = new Product();

        product.setVendor(vendor);
        product.setTitle(productDTO.getTitle());
        product.setDescription(productDTO.getDescription());
        product.setQuantity(productDTO.getQuantity());
        product.setPrice(productDTO.getPrice());
        product.setMaximumSellingPrice(productDTO.getMaximumSellingPrice());

        for (String tag: productDTO.getTags()){
            product.addTag(tag);
        }

        for (AttributeDTO attribute: productDTO.getAttributes()){
            Attribute newAtt = new Attribute();
            newAtt.setPrice(attribute.getPrice());

            for (AttributeFieldDTO field: attribute.getFields()){
                AttributeField attributeField = new AttributeField(field.getFieldKey(), field.getFieldValue());
                System.out.println("field service: "+field.toString());
                newAtt.addField(field.getFieldKey(), field.getFieldValue());
            }

//            Attribute savedAttribute = attributeRepository.save(newAtt);

            product.addAttribute(newAtt);
        }

        vendor.addProduct(product);

        return productRepository.save(product);
    }

    public List<Product> searchedProducts(String searchString, int page, int size){
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Product> products = productRepository.filteredProduct(searchString, pageRequest);
        List<Product> content = products.getContent();
        int totalPages = products.getTotalPages();
        long totalElements = products.getTotalElements();

        System.out.println("Total pages: " + totalPages + " Total elements: " + totalElements);

        return content;
    }

    public Product getProduct(long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found By id: " + productId + " | may be invalid id"));
        return product;
    }

    public Product getProduct(long productId, long attributeId){
        if (productId <= 0 || attributeId <= 0){ throw new RuntimeException("Product Id or Attribute Id is invalid"); }
        Product product = productRepository.findProductByProductIdAndAttributeId(productId, attributeId).orElseThrow(() -> new RuntimeException("Product not found By productId: " + productId + " and attributeId: " + attributeId));
        return product;
    }
}
