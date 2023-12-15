package algebra.hr.bll.serviceTests;

import algebra.hr.bll.serviceImplementation.TaskPhraseServiceImpl;
import algebra.hr.dal.entity.*;
import algebra.hr.dal.enums.TaskType;
import algebra.hr.dal.repository.TaskPhraseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskPhraseServiceTest {
    @Mock
    private TaskPhraseRepository _taskPhraseRepository;
    @InjectMocks
    private TaskPhraseServiceImpl _taskPhraseService;

    @Test
    public void testFindAllTaskPhrases() {
        Language german = new Language("German");
        Language english = new Language("English");

        Phrase phraseEnglish1 = new Phrase("Good day", english);
        Phrase phraseGerman1 = new Phrase("Guten tag", german);

        Phrase phraseEnglish2 = new Phrase("Hello friend", english);
        Phrase phraseGerman2 = new Phrase("Halo freund", german);

        TranslationPhrase translation = new TranslationPhrase(phraseEnglish1,phraseGerman1);
        TranslationPhrase  translation2 = new TranslationPhrase(phraseEnglish2,phraseGerman2);

        when(_taskPhraseRepository.findAll()).thenReturn(Arrays.asList(
                new TaskPhrase(TaskType.PHRASE, new ArrayList<Quiz>(), "Translate the text!: ", translation),
                new TaskPhrase(TaskType.PHRASE, new ArrayList<Quiz>(), "Translate the text!: ", translation2)));
        List<TaskPhrase> taskPhrases = _taskPhraseService.findAll();
        assertEquals(2, taskPhrases.size());
    }

    @Test
    public void testFindTaskPhraseById() {
        int id = 1;
        when(_taskPhraseRepository.findById(id)).thenReturn(Optional.of(new TaskPhrase()));
        TaskPhrase taskPhrase = _taskPhraseService.findById(id);
        assertNotNull(taskPhrase);
    }

    @Test
    public void testTaskPhraseSave() {
        Language german = new Language("German");
        Language english = new Language("English");

        Phrase phraseEnglish1 = new Phrase("Good day", english);
        Phrase phraseGerman1 = new Phrase("Guten tag", german);

        TranslationPhrase translation = new TranslationPhrase(phraseEnglish1,phraseGerman1);

        TaskPhrase taskPhrase = new TaskPhrase(TaskType.PHRASE, new ArrayList<Quiz>(), "Translate the text!: ", translation);

        when(_taskPhraseRepository.save(any(TaskPhrase.class))).thenReturn(taskPhrase);

        TaskPhrase savedTp = _taskPhraseService.save(taskPhrase);

        assertNotNull(savedTp);
    }

    @Test
    public void testDeleteTaskPhraseById() {
        int id = 1;

        _taskPhraseService.deleteById(id);
        verify(_taskPhraseRepository, times(1)).deleteById(id);
    }

}
