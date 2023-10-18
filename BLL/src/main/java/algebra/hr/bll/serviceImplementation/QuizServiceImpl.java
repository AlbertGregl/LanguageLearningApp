package algebra.hr.bll.serviceImplementation;

import algebra.hr.bll.service.QuizService;
import algebra.hr.dal.entity.Quiz;
import algebra.hr.dal.repository.QuizRepository;
import hr.algebra.utils.notFoundErrors.CustomNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizServiceImpl implements QuizService {
    private final QuizRepository _quizRepository;

    public QuizServiceImpl(QuizRepository quizRepository) {
        _quizRepository = quizRepository;
    }
    @Override
    public List<Quiz> findAll() {
        return _quizRepository.findAll();
    }

    @Override
    public Quiz findById(int id) {
        Optional<Quiz> quizOptional = _quizRepository.findById(id);

        if (quizOptional.isEmpty()){
            throw new CustomNotFoundException("Quiz id not found - " + id);
        }

        return quizOptional.get();
    }

    @Override
    public Quiz save(Quiz quiz) {
        return _quizRepository.save(quiz);
    }

    @Override
    public void deleteById(int id) {
        _quizRepository.deleteById(id);
    }
}
