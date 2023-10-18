package algebra.hr.bll.serviceImplementation;

import algebra.hr.bll.service.TaskWordService;
import algebra.hr.dal.entity.TaskWord;
import algebra.hr.dal.repository.TaskWordRepository;
import hr.algebra.utils.notFoundErrors.CustomNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskWordServiceImpl implements TaskWordService {

    private final TaskWordRepository _taskWordRepository;

    public TaskWordServiceImpl(TaskWordRepository taskWordRepository) {
        _taskWordRepository = taskWordRepository;
    }

    @Override
    public List<TaskWord> findAll() {
        return _taskWordRepository.findAll();
    }

    @Override
    public TaskWord findById(int id) {
        Optional<TaskWord> wordOptional = _taskWordRepository.findById(id);

        if (wordOptional.isEmpty()){
            throw new CustomNotFoundException("Task Word id not found - " + id);
        }
        return wordOptional.get();
    }

    @Override
    public TaskWord save(TaskWord word) {
        return _taskWordRepository.save(word);
    }

    @Override
    public void deleteById(int id) {
        _taskWordRepository.deleteById(id);
    }
}
