package algebra.hr.bll.serviceImplementation;

import algebra.hr.bll.service.LanguageService;
import algebra.hr.dal.entity.Language;
import algebra.hr.dal.repository.LanguageRepository;
import hr.algebra.utils.notFoundErrors.CustomNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LanguageServiceImpl implements LanguageService {
    private final LanguageRepository _languageRepository;

    @Autowired
    public LanguageServiceImpl(LanguageRepository languageRepository) {
        _languageRepository = languageRepository;
    }

    @Override
    public List<Language> findAll() {
        return _languageRepository.findAll();
    }

    @Override
    public Language findById(int id) {
        Optional<Language> languageOptional = _languageRepository.findById(id);

        if (languageOptional.isEmpty()){
            throw new CustomNotFoundException("Language id not found - " + id);
        }

        return languageOptional.get();
    }

    @Override
    @Transactional
    public Language save(Language language) {
        return _languageRepository.save(language);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        _languageRepository.deleteById(id);
    }
}
