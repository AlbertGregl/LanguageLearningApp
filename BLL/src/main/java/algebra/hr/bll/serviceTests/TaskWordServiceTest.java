package algebra.hr.bll.serviceTests;

import algebra.hr.bll.serviceImplementation.TaskWordServiceImpl;
import algebra.hr.dal.entity.*;
import algebra.hr.dal.enums.TaskType;
import algebra.hr.dal.repository.TaskWordRepository;
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
public class TaskWordServiceTest {
    @Mock
    private TaskWordRepository _taskWordRepository;
    @InjectMocks
    private TaskWordServiceImpl _taskWordService;

    @Test
    public void testFindAllTaskWords() {
        Language german = new Language("German");
        Language english = new Language("English");

        Word wordEnglish1 = new Word("Good", english);
        Word wordGerman1 = new Word("Gut", german);

        Word wordEnglish2 = new Word("Night", english);
        Word wordGerman2 = new Word("Nacht", german);

        TranslationWord  translation = new TranslationWord(wordEnglish1,wordGerman1);
        TranslationWord  translation2 = new TranslationWord(wordEnglish2,wordGerman2);

        when(_taskWordRepository.findAll()).thenReturn(Arrays.asList(
                new TaskWord(TaskType.WORD, new ArrayList<Quiz>(), "Translate the text!: ", translation),
                new TaskWord(TaskType.WORD, new ArrayList<Quiz>(), "Translate the text!: ", translation2)));
        List<TaskWord> taskWords = _taskWordService.findAll();
        assertEquals(2, taskWords.size());
    }

    @Test
    public void testFindTaskWordById() {
        int id = 1;
        when(_taskWordRepository.findById(id)).thenReturn(Optional.of(new TaskWord()));
        TaskWord taskWord = _taskWordService.findById(id);
        assertNotNull(taskWord);
    }

    @Test
    public void testTaskWordSave() {
        Language german = new Language("German");
        Language english = new Language("English");

        Word wordEnglish = new Word("Good", english);
        Word wordGerman = new Word("Gut", german);

        TranslationWord  translation = new TranslationWord(wordEnglish,wordGerman);

        TaskWord taskWord = new TaskWord(TaskType.WORD, new ArrayList<Quiz>(), "Translate the text!: ", translation);

        when(_taskWordRepository.save(any(TaskWord.class))).thenReturn(taskWord);

        TaskWord savedTw = _taskWordService.save(taskWord);

        assertNotNull(savedTw);
    }

    @Test
    public void testDeleteTaskWordById() {
        int id = 1;

        _taskWordService.deleteById(id);
        verify(_taskWordRepository, times(1)).deleteById(id);
    }

}
