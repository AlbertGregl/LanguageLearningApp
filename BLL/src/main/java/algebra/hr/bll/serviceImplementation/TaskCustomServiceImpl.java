package algebra.hr.bll.serviceImplementation;

import algebra.hr.bll.service.TaskCustomService;
import algebra.hr.dal.entity.TaskCustom;
import algebra.hr.dal.repository.TaskCustomRepository;
import hr.algebra.utils.notFoundErrors.CustomNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskCustomServiceImpl implements TaskCustomService {
    private final TaskCustomRepository _taskCustomRepository;

    public TaskCustomServiceImpl(TaskCustomRepository taskCustomRepository) {
        _taskCustomRepository = taskCustomRepository;
    }

    @Override
    public List<TaskCustom> findAll() {
        return _taskCustomRepository.findAll();
    }

    @Override
    public TaskCustom findById(int id) {
        Optional<TaskCustom> taskOptional = _taskCustomRepository.findById(id);

        if (taskOptional.isEmpty()){
            throw new CustomNotFoundException("Task Custom id not found - " + id);
        }
        return taskOptional.get();
    }

    @Override
    public TaskCustom save(TaskCustom taskCustom) {
        return _taskCustomRepository.save(taskCustom);
    }

    @Override
    public void deleteById(int id) {
        _taskCustomRepository.deleteById(id);
    }
}
