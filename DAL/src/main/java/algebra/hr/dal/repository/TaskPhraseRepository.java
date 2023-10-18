package algebra.hr.dal.repository;

import algebra.hr.dal.entity.TaskPhrase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskPhraseRepository extends JpaRepository<TaskPhrase,Integer> {
}
