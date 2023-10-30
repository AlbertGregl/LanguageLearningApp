package algebra.hr.bll.serviceImplementation;

import algebra.hr.bll.service.TranslationPhraseService;
import algebra.hr.dal.entity.TranslationPhrase;
import algebra.hr.dal.repository.TranslationPhraseRepository;
import hr.algebra.utils.notFoundErrors.CustomNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TranslationPhraseServiceImpl implements TranslationPhraseService {
    private final TranslationPhraseRepository _translationPhraseRepository;

    public TranslationPhraseServiceImpl(TranslationPhraseRepository translationPhraseRepository) {
        _translationPhraseRepository = translationPhraseRepository;
    }

    @Override
    public List<TranslationPhrase> findAll() {
        return _translationPhraseRepository.findAll();
    }

    @Override
    public TranslationPhrase findById(int id) {
        Optional<TranslationPhrase> translationPhraseOptional = _translationPhraseRepository.findById(id);

        if (translationPhraseOptional.isEmpty()){
            throw new CustomNotFoundException("Translation Phrase id not found - " + id);
        }

        return translationPhraseOptional.get();
    }

    @Override
    @Transactional
    public TranslationPhrase save(TranslationPhrase translationPhrase) {
        return _translationPhraseRepository.save(translationPhrase);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        _translationPhraseRepository.deleteById(id);
    }
}
