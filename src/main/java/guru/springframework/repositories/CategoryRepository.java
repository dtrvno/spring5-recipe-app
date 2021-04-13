package guru.springframework.repositories;

import guru.springframework.domain.Category;
import org.springframework.data.repository.CrudRepository;

import javax.sql.rowset.CachedRowSet;
import java.util.Optional;

public interface CategoryRepository extends CrudRepository <Category,Long> {
    Optional <Category>findByDescription(String description);
}
