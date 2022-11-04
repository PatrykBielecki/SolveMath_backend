package eu.solvemath.solvemath.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "orders", schema = "postgres")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product product;

    @Column(name = "product_quantity")
    private Integer productQuantity;

    @Column(name = "order_created_at")
    private Date createdAt;

    @Column(name = "order_extra_info")
    private String informationAdditional;

    @Column(name = "order_status")
    @Enumerated(EnumType.STRING)
    private InformationStatus informationStatus;

    @Column(name = "order_date_to_return")
    private Date dateToReturn;
}
