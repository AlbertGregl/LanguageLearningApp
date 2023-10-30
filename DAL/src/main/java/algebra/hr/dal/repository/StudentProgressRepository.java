package algebra.hr.dal.repository;

import algebra.hr.dal.entity.StudentProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentProgressRepository extends JpaRepository<StudentProgress,Integer> {
}
