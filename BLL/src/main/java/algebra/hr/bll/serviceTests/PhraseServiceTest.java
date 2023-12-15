package algebra.hr.bll.serviceTests;

import algebra.hr.bll.serviceImplementation.LanguageServiceImpl;
import algebra.hr.bll.serviceImplementation.PhraseServiceImpl;
import algebra.hr.dal.entity.Language;
import algebra.hr.dal.entity.Phrase;
import algebra.hr.dal.repository.PhraseRepository;
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
public class PhraseServiceTest {
    @Mock
    private PhraseRepository _phraseRepository;
    @InjectMocks
    private PhraseServiceImpl _phraseService;
    @InjectMocks
    private LanguageServiceImpl _languageService;

    @Test
    public void testFindAll() {
        Language lang = new Language("German");
        when(_phraseRepository.findAll()).thenReturn(Arrays.asList(
                new Phrase("Guten tag", lang),
                new Phrase("Auf viedersehn", lang)));
        List<Phrase> phrases = _phraseService.findAll();
        assertEquals(2, phrases.size());
    }

    @Test
    public void testFindById() {
        int id = 1;
        when(_phraseRepository.findById(id)).thenReturn(Optional.of(new Phrase()));
        Phrase phrase = _phraseService.findById(id);
        assertNotNull(phrase);
    }

    @Test
    public void testSave() {
        Language lang = new Language("Spanish");
        Phrase phrase = new Phrase("Como es tas shorty" ,lang);

        when(_phraseRepository.save(any(Phrase.class))).thenReturn(phrase);

        Phrase savedPhrase = _phraseService.save(phrase);

        assertNotNull(savedPhrase);
    }

    @Test
    public void testDeleteById() {
        int id = 1;

        _phraseService.deleteById(id);
        verify(_phraseRepository, times(1)).deleteById(id);
    }
}
