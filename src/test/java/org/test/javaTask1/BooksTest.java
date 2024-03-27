package org.test.javaTask1;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.test.javaTask1.Product.getProducts;

//Obtain a list of products belonging to category “Books” with price > 100 using
//stream.

public class BooksTest {
    public static void main(String[] args) {
        Set<Product> products = getProducts();
        List<Product> books = products.stream()
                .filter(product -> product.getCategory().equals("Books") && product.getPrice() > 100)
                .collect(Collectors.toList());

        System.out.print(books);
    }
}
