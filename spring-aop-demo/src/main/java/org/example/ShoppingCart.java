package org.example;

import org.springframework.stereotype.Component;

@Component
public class ShoppingCart {
    public void checkout(){
        //Logging (aspect)
        //Authentication & Authorization (aspect)
        //Sanitize the Data (aspect)
        System.out.println("Checkout Method from ShoppingCard called");
    }

    public int quantity(){
        return 2;
    }
}
