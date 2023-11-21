package algebra.hr.dal.repository;

import algebra.hr.dal.entity.ForumComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ForumCommentRepository extends JpaRepository<ForumComment,Integer> {
    // Custom query method to find comments by forum post ID
    List<ForumComment> findByForumPost_ForumPostID(int forumPostId);
}
