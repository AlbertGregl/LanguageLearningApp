package algebra.hr.dal.repository;

import algebra.hr.dal.entity.Phrase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhraseRepository extends JpaRepository<Phrase, Integer> {
    //jpa gives us all the basic CRUD operations that we need
}
