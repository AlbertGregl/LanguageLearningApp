package algebra.hr.bll.serviceImplementation;

import algebra.hr.bll.service.TaskAudioWordService;
import algebra.hr.dal.entity.TaskAudioWord;
import algebra.hr.dal.repository.TaskAudioWordRepository;
import hr.algebra.utils.notFoundErrors.CustomNotFoundException;

import java.util.List;
import java.util.Optional;

public class TaskAudioWordWordServiceImpl implements TaskAudioWordService {

    private final TaskAudioWordRepository _audioWordRepository;

    public TaskAudioWordWordServiceImpl(TaskAudioWordRepository audioWordRepository) {
        _audioWordRepository = audioWordRepository;
    }

    @Override
    public List<TaskAudioWord> findAll() {
        return _audioWordRepository.findAll();
    }

    @Override
    public TaskAudioWord findById(int id) {
        Optional<TaskAudioWord> taskOptional = _audioWordRepository.findById(id);

        if (taskOptional.isEmpty()){
            throw new CustomNotFoundException("Task Word id not found - " + id);
        }
        return taskOptional.get();
    }

    @Override
    public TaskAudioWord save(TaskAudioWord audioWord) {
        return _audioWordRepository.save(audioWord);
    }

    @Override
    public void deleteById(int id) {
        _audioWordRepository.deleteById(id);
    }
}
