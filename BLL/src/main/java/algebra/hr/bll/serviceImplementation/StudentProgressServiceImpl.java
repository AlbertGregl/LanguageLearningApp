package algebra.hr.bll.serviceImplementation;

import algebra.hr.bll.service.StudentProgressService;
import algebra.hr.dal.entity.StudentProgress;
import algebra.hr.dal.repository.StudentProgressRepository;
import hr.algebra.utils.notFoundErrors.CustomNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentProgressServiceImpl implements StudentProgressService {
    private final StudentProgressRepository _studentProgressRepository;

    public StudentProgressServiceImpl(StudentProgressRepository studentProgressRepository) {
        _studentProgressRepository = studentProgressRepository;
    }

    @Override
    public List<StudentProgress> findAll() {
        return _studentProgressRepository.findAll();
    }

    @Override
    public StudentProgress findById(int id) {
        Optional<StudentProgress> spOptional = _studentProgressRepository.findById(id);

        if (spOptional.isEmpty()){
            throw new CustomNotFoundException("Student Progress id not found - " + id);
        }

        return spOptional.get();
    }

    @Override
    public StudentProgress save(StudentProgress studentProgress) {
        return _studentProgressRepository.save(studentProgress);
    }

    @Override
    public void deleteById(int id) {
        _studentProgressRepository.deleteById(id);
    }
}
