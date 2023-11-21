package algebra.hr.bll.serviceImplementation;

import algebra.hr.bll.service.ForumCommentService;
import algebra.hr.dal.entity.ForumComment;
import algebra.hr.dal.repository.ForumCommentRepository;
import hr.algebra.utils.notFoundErrors.CustomNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ForumCommentServiceImpl implements ForumCommentService {
    private final ForumCommentRepository _forumCommentRepository;

    public ForumCommentServiceImpl(ForumCommentRepository forumCommentRepository) {
        _forumCommentRepository = forumCommentRepository;
    }

    @Override
    public List<ForumComment> findAll() {
        return _forumCommentRepository.findAll();
    }

    @Override
    public ForumComment findById(int id) {
        Optional<ForumComment> forumCommentOptional = _forumCommentRepository.findById(id);

        if (forumCommentOptional.isEmpty()){
            throw new CustomNotFoundException("ForumComment id not found - " + id);
        }

        return forumCommentOptional.get();
    }

    @Override
    public ForumComment save(ForumComment forumComment) {
        return _forumCommentRepository.save(forumComment);
    }

    @Override
    public void deleteById(int id) {
        _forumCommentRepository.deleteById(id);
    }

    @Override
    public List<ForumComment> findByForumPostId(int forumPostId) {
        return _forumCommentRepository.findByForumPost_ForumPostID(forumPostId);
    }
}
