package algebra.hr.bll.serviceImplementation;

import algebra.hr.bll.service.TranslationWordService;
import algebra.hr.dal.entity.TranslationWord;
import algebra.hr.dal.repository.TranslationWordRepository;
import hr.algebra.utils.notFoundErrors.CustomNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TranslationWordServiceImpl implements TranslationWordService {
    private final TranslationWordRepository _translationWordRepository;
    public TranslationWordServiceImpl(TranslationWordRepository translationWordRepository) {
        _translationWordRepository = translationWordRepository;
    }

    @Override
    public List<TranslationWord> findAll() {
        return _translationWordRepository.findAll();
    }

    @Override
    public TranslationWord findById(int id) {
        Optional<TranslationWord> translationWordOptional = _translationWordRepository.findById(id);

        if (translationWordOptional.isEmpty()){
            throw new CustomNotFoundException("Translated word id not found - " + id);
        }
        return translationWordOptional.get();
    }

    @Override
    public TranslationWord save(TranslationWord translationWord) {
        return _translationWordRepository.save(translationWord);
    }

    @Override
    public void deleteById(int id) {
        _translationWordRepository.deleteById(id);
    }
}
