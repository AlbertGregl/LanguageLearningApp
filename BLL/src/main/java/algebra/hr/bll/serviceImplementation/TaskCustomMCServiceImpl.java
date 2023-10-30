package algebra.hr.bll.serviceImplementation;

import algebra.hr.bll.service.TaskCustomMCService;
import algebra.hr.dal.entity.TaskCustomMC;
import algebra.hr.dal.repository.TaskCustomMCRepository;
import hr.algebra.utils.notFoundErrors.CustomNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskCustomMCServiceImpl implements TaskCustomMCService {
    private final TaskCustomMCRepository _taskCustomMCRepository;

    public TaskCustomMCServiceImpl(TaskCustomMCRepository taskCustomMCRepository) {
        _taskCustomMCRepository = taskCustomMCRepository;
    }

    @Override
    public List<TaskCustomMC> findAll() {
        return _taskCustomMCRepository.findAll();
    }

    @Override
    public TaskCustomMC findById(int id) {
        Optional<TaskCustomMC> taskOptional = _taskCustomMCRepository.findById(id);

        if (taskOptional.isEmpty()){
            throw new CustomNotFoundException("Task CustomMC id not found - " + id);
        }
        return taskOptional.get();
    }

    @Override
    public TaskCustomMC save(TaskCustomMC customMC) {
        return _taskCustomMCRepository.save(customMC);
    }

    @Override
    public void deleteById(int id) {
        _taskCustomMCRepository.deleteById(id);
    }
}
