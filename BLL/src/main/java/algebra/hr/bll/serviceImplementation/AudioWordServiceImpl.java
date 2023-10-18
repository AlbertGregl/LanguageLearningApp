package algebra.hr.bll.serviceImplementation;

import algebra.hr.bll.service.AudioWordService;
import algebra.hr.dal.entity.AudioWord;
import algebra.hr.dal.repository.AudioWordRepository;
import hr.algebra.utils.notFoundErrors.CustomNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AudioWordServiceImpl implements AudioWordService {
    private final AudioWordRepository _audioWordRepository;

    public AudioWordServiceImpl(AudioWordRepository audioWordRepository) {
        _audioWordRepository = audioWordRepository;
    }

    @Override
    public List<AudioWord> findAll() {
        return _audioWordRepository.findAll();
    }

    @Override
    public AudioWord findById(int id) {
        Optional<AudioWord> audioOptional = _audioWordRepository.findById(id);

        if (audioOptional.isEmpty()){
            throw new CustomNotFoundException("Audio Word id not found - " + id);
        }

        return audioOptional.get();
    }

    @Override
    public AudioWord save(AudioWord audioWord) {
        return _audioWordRepository.save(audioWord);
    }

    @Override
    public void deleteById(int id) {
        _audioWordRepository.deleteById(id);
    }
}
