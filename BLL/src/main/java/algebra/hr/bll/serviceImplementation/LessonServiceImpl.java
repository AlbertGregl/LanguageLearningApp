package algebra.hr.bll.serviceImplementation;

import algebra.hr.bll.service.LessonService;
import algebra.hr.dal.entity.Lesson;
import algebra.hr.dal.repository.LessonRepository;
import hr.algebra.utils.notFoundErrors.CustomNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LessonServiceImpl implements LessonService {
    private final LessonRepository _lessonRepository;

    public LessonServiceImpl(LessonRepository lessonRepository) {
        _lessonRepository = lessonRepository;
    }

    @Override
    public List<Lesson> findAll() {
        return _lessonRepository.findAll();
    }

    @Override
    public Lesson findById(int id) {
        Optional<Lesson> lessonOptional = _lessonRepository.findById(id);

        if (lessonOptional.isEmpty()){
            throw new CustomNotFoundException("Lesson id not found - " + id);
        }

        return lessonOptional.get();
    }

    @Override
    public Lesson save(Lesson lesson) {
        return _lessonRepository.save(lesson);
    }

    @Override
    public void deleteById(int id) {
        _lessonRepository.deleteById(id);
    }
}
