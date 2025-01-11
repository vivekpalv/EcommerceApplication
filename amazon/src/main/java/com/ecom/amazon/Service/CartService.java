package com.ecom.amazon.Service;

import com.ecom.amazon.Entity.*;
import com.ecom.amazon.Repository.CartProductRepository;
import com.ecom.amazon.Repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartProductRepository cartProductRepository;


    public CartService(CartRepository cartRepository, CartProductRepository cartProductRepository) {
        this.cartRepository = cartRepository;
        this.cartProductRepository = cartProductRepository;
    }

    public Cart addProductToCart(Customer customer, Product product, Attribute selctedAttribute, int quantity){

        CartProduct cartProduct = new CartProduct();
        cartProduct.setProduct(product);
        cartProduct.setSelectedAttribute(selctedAttribute);
        cartProduct.setQuantity(quantity);

        Cart cart = customer.getCart();
        cart.addProduct(cartProduct);

        return this.cartRepository.save(cart);
    }

    public Cart removeProductFromCart(Customer customer, CartProduct cartProduct){

        //check CartProduct belongs to the Customer
        if(cartProduct.getCart().getId() != customer.getCart().getId()){
            throw new RuntimeException("Cart Product does not belong to the customer");
        }

        Cart cart = customer.getCart();
        cart.removeProduct(cartProduct);
        cartProductRepository.delete(cartProduct); // delete the CartProduct after unlinking it from the Cart

        return this.cartRepository.save(cart);
    }

    public CartProduct getCartProduct(Long cartProductId){
        return cartProductRepository.findById(cartProductId).orElseThrow(() -> new RuntimeException("Cart Product not found"));
    }

    public List<CartProduct> getCartProducts(Customer customer){
        return cartProductRepository.findByCartId(customer.getCart().getId());
    }
}
