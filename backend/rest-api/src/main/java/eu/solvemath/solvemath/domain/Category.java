package eu.solvemath.solvemath.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "products_categories", schema = "postgres")
@ToString
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Integer id;
    @Column(name = "category_name")
    private String name;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "category")
    private Set<Product> products = new HashSet<>();
}
