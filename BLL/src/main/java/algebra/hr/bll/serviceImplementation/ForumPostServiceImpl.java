package algebra.hr.bll.serviceImplementation;

import algebra.hr.bll.service.ForumPostService;
import algebra.hr.dal.entity.ForumPost;
import algebra.hr.dal.repository.ForumPostRepository;
import hr.algebra.utils.notFoundErrors.CustomNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ForumPostServiceImpl implements ForumPostService {

    private final ForumPostRepository _forumPostRepository;

    public ForumPostServiceImpl(ForumPostRepository forumPostRepository) {
        _forumPostRepository = forumPostRepository;
    }

    @Override
    public List<ForumPost> findAll() {
        return _forumPostRepository.findAll();
    }

    @Override
    public ForumPost findById(int id) {
        Optional<ForumPost> forumPostOptional = _forumPostRepository.findById(id);

        if (forumPostOptional.isEmpty()){
            throw new CustomNotFoundException("ForumPost id not found - " + id);
        }

        return forumPostOptional.get();
    }

    @Override
    public ForumPost save(ForumPost forumPost) {
        return _forumPostRepository.save(forumPost);
    }

    @Override
    public void deleteById(int id) {
        _forumPostRepository.deleteById(id);
    }
}
