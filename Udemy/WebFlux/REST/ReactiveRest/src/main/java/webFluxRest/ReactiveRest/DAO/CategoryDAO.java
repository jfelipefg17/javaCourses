package webFluxRest.ReactiveRest.DAO;

import com.reactiveApp.SecondExample.documents.Category;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CategoryDAO extends ReactiveMongoRepository<Category, String> {
}
