package algebra.hr.bll.serviceImplementation;

import algebra.hr.bll.service.TaskService;
import algebra.hr.dal.entity.Task;
import algebra.hr.dal.repository.TaskRepository;
import hr.algebra.utils.notFoundErrors.CustomNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository _taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        _taskRepository = taskRepository;
    }

    @Override
    public List<Task> findAll() {
        return _taskRepository.findAll();
    }

    @Override
    public Task findById(int id) {
        Optional<Task> taskOptional = _taskRepository.findById(id);

        if (taskOptional.isEmpty()){
            throw new CustomNotFoundException("Task id not found - " + id);
        }

        return taskOptional.get();
    }

    @Override
    public Task save(Task task) {
        return _taskRepository.save(task);
    }

    @Override
    public void deleteById(int id) {
        _taskRepository.deleteById(id);
    }
}
