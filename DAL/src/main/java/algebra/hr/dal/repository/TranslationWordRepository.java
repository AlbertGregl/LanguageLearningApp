package algebra.hr.dal.repository;

import algebra.hr.dal.entity.TranslationWord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TranslationWordRepository extends JpaRepository<TranslationWord, Integer> {
    //jpa gives us all the basic CRUD operations that we need
}