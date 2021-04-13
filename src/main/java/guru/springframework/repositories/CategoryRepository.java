package guru.springframework.repositories;

import guru.springframework.domain.Category;
import org.springframework.data.repository.CrudRepository;

import javax.sql.rowset.CachedRowSet;

public interface CategoryRepository extends CrudRepository <Category,Long> {
}
