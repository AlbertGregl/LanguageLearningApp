package algebra.hr.dal.repository;

import algebra.hr.dal.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordRepository extends JpaRepository<Word, Integer> {
    //jpa gives us all the basic CRUD operations that we need
}
