package com.auth2.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.auth2.Exception.ProductNotFoundException;
import com.auth2.Model.Product;
import com.auth2.Repository.ProductRepository;

@RestController
@CrossOrigin("http://localhost:3002")
public class ProductController {


    @Autowired
    private ProductRepository productRepository;


    @PostMapping("/addproduct")
    Product newUser(@RequestBody Product newProduct) {
        return productRepository.save(newProduct);
    }


    @GetMapping("/listproduct")
    List<Product> getAllUsers() {
        return productRepository.findAll();
    }


    @GetMapping("/product/{id}")
    Product getUserById(@PathVariable Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }


    @PutMapping("/updateproduct/{id}")
    Product updateUser(@RequestBody Product newProduct, @PathVariable Long id) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setName(newProduct.getName());
                    product.setDescription(newProduct.getDescription());
                    product.setPrice(newProduct.getPrice());
                    product.setExpirydate(newProduct.getExpirydate());
                    return productRepository.save(product);
                }).orElseThrow(() -> new ProductNotFoundException(id));
    }

    @DeleteMapping("/deleteproduct/{id}")
    String deleteUser(@PathVariable Long id){
        if(!productRepository.existsById(id)){
            throw new ProductNotFoundException(id);
        }
        productRepository.deleteById(id);
        return  "Product with id "+id+" has been deleted success.";
    }
     @GetMapping("/search")
        List<Product> getAllUsers(Model model , @Param("keyword") String keyword) {
            Iterable<Product> product = productRepository.findAll(keyword);
            model.addAttribute("product",product);
            return productRepository.findAll(keyword);
        }
}
