package com.Amr.vending.machine.rest;

import com.Amr.vending.machine.entity.Product;
import com.Amr.vending.machine.entity.User;
import com.Amr.vending.machine.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    // GET all products
    private ProductService productService;
    public ProductController(ProductService theProductService){
        this.productService=theProductService;
    }
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.findAll();
    }

    // GET a specific product by ID
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int productId) {
        Product theProduct= productService.findById(productId);
        if(theProduct==null){
            throw new RuntimeException("Product id not found - "+productId);
        }
        return theProduct;

    }

    // POST create a new product
    @PostMapping("/createProduct")
    public Product createProduct(@RequestBody Product theProduct) {
        theProduct.setId(0);
        Product dbProduct=productService.save(theProduct);
        return dbProduct;
    }

    // PUT update an existing product
    @PutMapping("/updateProduct/{id}")
    public Product updateProduct(@PathVariable int id, @RequestBody Product product) {
        Product theProduct=productService.findById(id);
        if(theProduct==null){
            throw new RuntimeException("Product id not found - "+id);
        }
        theProduct.setProductName(product.getProductName());
        theProduct.setAmountAvailable(product.getAmountAvailable());
        theProduct.setCost(product.getCost());
        theProduct.setUser(product.getUser());
        Product dbProduct=productService.save(theProduct);
        return dbProduct;
        
    }

    // DELETE delete a product
    @DeleteMapping("/deleteProduct/{id}")
    public void deleteProduct(@PathVariable int id) {
        // throw exception if null
        Product tempProduct=productService.findById(id);
        if(tempProduct==null){
            throw new RuntimeException("Product id not found - "+id);
        }
        productService.deleteById(id);

    }
    
    

}
