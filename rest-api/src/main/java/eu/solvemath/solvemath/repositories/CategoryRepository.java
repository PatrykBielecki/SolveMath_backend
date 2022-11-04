package eu.solvemath.solvemath.repositories;

import eu.solvemath.solvemath.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
