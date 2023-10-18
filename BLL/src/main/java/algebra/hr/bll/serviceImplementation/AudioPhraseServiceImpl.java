package algebra.hr.bll.serviceImplementation;

import algebra.hr.bll.service.AudioPhraseService;
import algebra.hr.dal.entity.AudioPhrase;
import algebra.hr.dal.repository.AudioPhraseRepository;
import hr.algebra.utils.notFoundErrors.CustomNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AudioPhraseServiceImpl implements AudioPhraseService {
    private final AudioPhraseRepository _audioPhraseRepository;

    public AudioPhraseServiceImpl(AudioPhraseRepository audioPhraseRepository) {
        _audioPhraseRepository = audioPhraseRepository;
    }

    @Override
    public List<AudioPhrase> findAll() {
        return _audioPhraseRepository.findAll();
    }

    @Override
    public AudioPhrase findById(int id) {
        Optional<AudioPhrase> audioOptional = _audioPhraseRepository.findById(id);

        if (audioOptional.isEmpty()){
            throw new CustomNotFoundException("Audio Phrase id not found - " + id);
        }

        return audioOptional.get();
    }

    @Override
    public AudioPhrase save(AudioPhrase audioPhrase) {
        return _audioPhraseRepository.save(audioPhrase);
    }

    @Override
    public void deleteById(int id) {
        _audioPhraseRepository.deleteById(id);
    }
}
