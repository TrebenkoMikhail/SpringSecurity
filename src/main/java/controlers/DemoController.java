package controlers;

import entities.Products;
import entities.Users;
import jakarta.persistence.Column;
import jakarta.persistence.JoinTable;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Table;
import org.hibernate.sql.ast.tree.expression.Collation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reposetories.ProductRepository;
import servecies.UserService;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class DemoController {
    private final UserService userService;
    private final ProductRepository productRepository;
    @GetMapping(value = "/")
    public String homePage() { return "home"; }

    @GetMapping(value = "/unsecured")
    public String unsecuredPage() { return "unsecured"; }

    @GetMapping(value = "auth_page")
    public  String authenticatedPage() { return "authenticated"; }

    @GetMapping(value = "admin")
    // @PreAuthorize ("hashRole('ADMIN')")
    public String adminPage() {return "admin"; }

    @GetMapping(value = "/products")
    public Products products(){return products();}

    @GetMapping(value = "/user_info")
    public String daoTestPage(Principal principal) {
        Users user = userService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("Username not found"));
        return "Authenticated user info: " + user.getUsername() + " : " + user.getEmail();
    }
    @GetMapping (value = "/products/add")
    public Products products(String title, int price) {
        products() = new Collation<Products>.add(new Products(title, price));
        return new Products(title, price);
        productRepository.save(products());
    }
    @GetMapping (value = "/products/edit")
    @JoinTable(name = "products")
    @Column (name= "title")
}
