package algebra.hr.dal.repository;

import algebra.hr.dal.entity.TranslationPhrase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TranslationPhraseRepository extends JpaRepository<TranslationPhrase, Integer> {
    //jpa gives us all the basic CRUD operations that we need
}
