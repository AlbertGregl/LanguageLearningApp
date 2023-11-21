package algebra.hr.bll.serviceImplementation;

import algebra.hr.bll.service.PhraseService;
import algebra.hr.dal.entity.Phrase;
import algebra.hr.dal.repository.PhraseRepository;
import hr.algebra.utils.notFoundErrors.CustomNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PhraseServiceImpl implements PhraseService {
    private final PhraseRepository _phraseRepository;

    public PhraseServiceImpl(PhraseRepository phraseRepository) {
        _phraseRepository = phraseRepository;
    }

    @Override
    public List<Phrase> findAll() {
        return _phraseRepository.findAll();
    }

    @Override
    public Phrase findById(int id) {
        Optional<Phrase> phraseOptional = _phraseRepository.findById(id);

        if (phraseOptional.isEmpty()){
            throw new CustomNotFoundException("Phrase id not found - " + id);
        }

        return phraseOptional.get();
    }

    @Override
    @Transactional
    public Phrase save(Phrase phrase) {
        return _phraseRepository.save(phrase);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        _phraseRepository.deleteById(id);
    }

    @Override
    public List<Phrase> getByKeyword(String keyword) {
        return _phraseRepository.findByKeyword(keyword);
    }

    @Override
    public Phrase findByPhraseText(String phraseText) {
        List<Phrase> allDBPhrases = _phraseRepository.findAll();

        Optional<Phrase> optionalPhrase = allDBPhrases.stream()
                .filter(phrase -> phrase.getPhraseText().equals(phraseText.trim()))
                .findFirst();

        if (optionalPhrase.isPresent()) {
            return optionalPhrase.get();
        } else {
            throw new CustomNotFoundException("Phrase does not exist in the database!");
        }
    }
}
