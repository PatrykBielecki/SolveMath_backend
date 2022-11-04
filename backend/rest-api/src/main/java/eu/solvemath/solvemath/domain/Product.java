package eu.solvemath.solvemath.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "products", schema = "postgres")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer id;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "product")
    private Set<Order> orders;

    @Column(name = "product_name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "product_category_id", referencedColumnName = "category_id")
    private Category category;

    @Column(name = "product_actual_quantity_in_stock")
    private Integer ActualQuantityInStock;
    @Column(name = "product_max_quantity_in_stock")
    private Integer MaxQuantityInStock;
}
