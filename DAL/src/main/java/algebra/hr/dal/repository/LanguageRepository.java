package algebra.hr.dal.repository;

import algebra.hr.dal.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Integer> {
    //jpa gives us all the basic CRUD operations that we need

    //Custom query
    @Query(value = "SELECT * FROM Language l WHERE l.languageName LIKE :keyword", nativeQuery = true)
    List<Language> findByKeyword(@Param("keyword") String keyword);

}
