package algebra.hr.bll.serviceTests;

import algebra.hr.bll.serviceImplementation.WordServiceImpl;
import algebra.hr.dal.entity.Language;
import algebra.hr.dal.entity.Word;
import algebra.hr.dal.repository.WordRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class WordServiceTest {
    @Mock
    private WordRepository _wordRepository;
    @InjectMocks
    private WordServiceImpl _wordService;

    @Test
    public void testFindAll() {
        Language lang = new Language("German");
        when(_wordRepository.findAll()).thenReturn(Arrays.asList(
                new Word("Nach", lang),
                new Word("Wasser", lang)));
        List<Word> words = _wordService.findAll();
        assertEquals(2, words.size());
    }

    @Test
    public void testFindById() {
        int id = 1;
        when(_wordRepository.findById(id)).thenReturn(Optional.of(new Word()));
        Word word = _wordService.findById(id);
        assertNotNull(word);
    }

    @Test
    public void testSave() {
        Language lang = new Language("Spanish");
        Word word = new Word("Ola" ,lang);

        when(_wordRepository.save(any(Word.class))).thenReturn(word);

        Word savedWord = _wordService.save(word);

        assertNotNull(savedWord);
    }

    @Test
    public void testDeleteById() {
        int id = 1;
        _wordService.deleteById(id);
        verify(_wordRepository, times(1)).deleteById(id);
    }
}
