package algebra.hr.bll.service;

import algebra.hr.dal.entity.ForumComment;

import java.util.List;

public interface ForumCommentService extends GenericService<ForumComment>{
    List<ForumComment> findByForumPostId(int forumPostId);
}
