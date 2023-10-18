package algebra.hr.bll.serviceImplementation;

import algebra.hr.bll.service.TaskAudioPhraseService;
import algebra.hr.dal.entity.TaskAudioPhrase;
import algebra.hr.dal.repository.TaskAudioPhraseRepository;
import hr.algebra.utils.notFoundErrors.CustomNotFoundException;

import java.util.List;
import java.util.Optional;

public class TaskAudioPhraseServiceImpl implements TaskAudioPhraseService {
    private final TaskAudioPhraseRepository _taskAudioPhraseRepository;

    public TaskAudioPhraseServiceImpl(TaskAudioPhraseRepository taskAudioPhraseRepository) {
        _taskAudioPhraseRepository = taskAudioPhraseRepository;
    }

    @Override
    public List<TaskAudioPhrase> findAll() {
        return _taskAudioPhraseRepository.findAll();
    }

    @Override
    public TaskAudioPhrase findById(int id) {
        Optional<TaskAudioPhrase> taskOptional = _taskAudioPhraseRepository.findById(id);

        if (taskOptional.isEmpty()){
            throw new CustomNotFoundException("Task Audio Phrase id not found - " + id);
        }
        return taskOptional.get();
    }

    @Override
    public TaskAudioPhrase save(TaskAudioPhrase audioPhrase) {
        return _taskAudioPhraseRepository.save(audioPhrase);
    }

    @Override
    public void deleteById(int id) {

    }
}
