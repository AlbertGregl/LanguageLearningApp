package algebra.hr.dal.repository;

import algebra.hr.dal.entity.ForumPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForumPostRepository extends JpaRepository<ForumPost,Integer> {
}
