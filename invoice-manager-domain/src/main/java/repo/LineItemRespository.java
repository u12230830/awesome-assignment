package repo;

import model.LineItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LineItemRespository extends JpaRepository<LineItem, Long> {
}
