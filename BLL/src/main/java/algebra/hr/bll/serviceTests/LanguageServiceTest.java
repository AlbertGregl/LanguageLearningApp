package algebra.hr.bll.serviceTests;

import algebra.hr.bll.serviceImplementation.LanguageServiceImpl;
import algebra.hr.dal.entity.Language;
import algebra.hr.dal.repository.LanguageRepository;
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


//https://www.baeldung.com/mockito-junit-5-extension
@ExtendWith(MockitoExtension.class)
public class LanguageServiceTest {
    @Mock
    private LanguageRepository _languageRepository;
    @InjectMocks
    private LanguageServiceImpl _languageService;

    @Test
    public void testFindAllLanguages() {
        when(_languageRepository.findAll()).thenReturn(Arrays.asList(
                new Language("Spanish"),
                new Language("English")));
        List<Language> languages = _languageService.findAll();
        assertEquals(2, languages.size());
    }

    @Test
    public void testFindLanguageById() {
        int id = 1;
        when(_languageRepository.findById(id)).thenReturn(Optional.of(new Language()));
        Language language = _languageService.findById(id);
        assertNotNull(language);
    }

    @Test
    public void testLanguageSave() {
        Language language = new Language("Croatian");
        when(_languageRepository.save(any(Language.class))).thenReturn(language);

        Language savedLanguage = _languageService.save(language);

        assertNotNull(savedLanguage);
    }

    @Test
    public void testDeleteLanguageById() {
        int id = 1;

        _languageService.deleteById(id);
        verify(_languageRepository, times(1)).deleteById(id);
    }
}
