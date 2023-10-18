package algebra.hr.bll.serviceImplementation;

import algebra.hr.bll.service.TaskPhraseService;
import algebra.hr.dal.entity.TaskPhrase;
import algebra.hr.dal.repository.TaskPhraseRepository;
import hr.algebra.utils.notFoundErrors.CustomNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TaskPhraseServiceImpl implements TaskPhraseService {
    private final TaskPhraseRepository _taskPhraseRepository;

    public TaskPhraseServiceImpl(TaskPhraseRepository taskPhraseRepository) {
        _taskPhraseRepository = taskPhraseRepository;
    }

    @Override
    public List<TaskPhrase> findAll() {
        return _taskPhraseRepository.findAll();
    }

    @Override
    public TaskPhrase findById(int id) {
        Optional<TaskPhrase> phraseOptional = _taskPhraseRepository.findById(id);

        if (phraseOptional.isEmpty()){
            throw new CustomNotFoundException("Task Phrase id not found - " + id);
        }

        return phraseOptional.get();
    }

    @Override
    @Transactional
    public TaskPhrase save(TaskPhrase phrase) {
        return _taskPhraseRepository.save(phrase);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        _taskPhraseRepository.deleteById(id);
    }
}
