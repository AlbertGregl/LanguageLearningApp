package algebra.hr.dal.repository;

import algebra.hr.dal.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WordRepository extends JpaRepository<Word, Integer> {
    //jpa gives us all the basic CRUD operations that we need

    @Query(value = "select * from Word w where w.wordText like %:keyword%", nativeQuery = true)
    List<Word> findByKeyword(@Param("keyword") String keyword);
}
