package algebra.hr.dal.repository;

import algebra.hr.dal.entity.Phrase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhraseRepository extends JpaRepository<Phrase, Integer> {
    //jpa gives us all the basic CRUD operations that we need

    @Query(value = "select * from Phrase p where p.phraseText like %:keyword%", nativeQuery = true)
    List<Phrase> findByKeyword(@Param("keyword") String keyword);
}
