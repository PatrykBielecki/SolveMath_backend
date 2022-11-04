package eu.solvemath.solvemath.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "users", schema = "postgres")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private Set<Order> orders;

    @Column(name = "user_name")
    private String name;
    @Column(name = "user_surname")
    private String surname;
    @Column(name = "user_additional_information")
    private String additionalInformation;
}
