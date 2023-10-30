package algebra.hr.dal.repository;

import algebra.hr.dal.entity.ForumComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForumCommentRepository extends JpaRepository<ForumComment,Integer> {
}
