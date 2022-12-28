package entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Table;

import java.util.Collection;

@Data
@Table(appliesTo = "products")
@NoArgsConstructor
public class Products {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private int price;

    @ManyToMany
    @JoinTable(name = "products",
            joinColumns = @JoinColumn(name = "products_title"),
            inverseJoinColumns = @JoinColumn(name = "products_price"))
    private Collection<Products> products;
    public Products(String title, int price) {
        this.title = title;
        this.price = price;
    }
}
