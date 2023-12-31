package pi.project.languageApp.controller;

import algebra.hr.bll.service.TaskService;
import algebra.hr.bll.serviceImplementation.*;
import algebra.hr.dal.entity.*;
import algebra.hr.dal.enums.Difficulty;
import algebra.hr.dal.enums.LessonCode;
import algebra.hr.dal.enums.TaskType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping("testing")
public class TestingController {

    private static final Logger logger = LoggerFactory.getLogger(TestingController.class);
    private final LanguageServiceImpl _languageService;
    private final PhraseServiceImpl _phraseService;
    private final TranslationPhraseServiceImpl _translationPhraseService;
    private final TaskPhraseServiceImpl _taskPhraseService;
    private final TaskService _taskService;
    private final QuizServiceImpl _quizService;
    private final LessonServiceImpl _lessonService;
    private final UserServiceImpl _userService;

    public TestingController(LanguageServiceImpl languageService, PhraseServiceImpl phraseService
                                , TranslationPhraseServiceImpl translationPhraseService,
                             TaskPhraseServiceImpl taskPhraseService,
                             TaskService taskService, QuizServiceImpl quizService, LessonServiceImpl lessonService, UserServiceImpl userService) {
        _languageService = languageService;
        _phraseService = phraseService;
        _translationPhraseService = translationPhraseService;
        _taskPhraseService = taskPhraseService;
        _taskService = taskService;
        _quizService = quizService;
        _lessonService = lessonService;
        _userService = userService;
    }

    @GetMapping("/")
    public String tester() {
        return "Hello world";
    }

    @GetMapping("/list")
    public String listLanguages(Model theModel) {
        logger.info("Fetching list of languages...");
        logger.info("Fetching list of phrases...");

        // Create languages
        Language croatian = new Language("Croatian");
        Language english = new Language("English");

        _languageService.save(croatian);
        _languageService.save(english);

        // Create phrases
        Phrase croatianPhrase = new Phrase("Dobar dan", croatian);
        Phrase englishPhrase = new Phrase("Good day", english);

        _phraseService.save(croatianPhrase);
        _phraseService.save(englishPhrase);

        // Create translation
        TranslationPhrase translation = new TranslationPhrase(croatianPhrase, englishPhrase);
        _translationPhraseService.save(translation);


        Quiz quiz = new Quiz("Basics german");
        _quizService.save(quiz);
        TranslationPhrase translationPhrase = _translationPhraseService.findById(1);

        List<Quiz> addQuizesForLesson = new ArrayList<>();
        addQuizesForLesson.add(quiz);
//
        TaskType taskType = TaskType.PHRASE;  // Choose the appropriate TaskType enum value
        String taskText = "Your task text here";
        TaskPhrase taskPhrase = new TaskPhrase(taskType, addQuizesForLesson, taskText, translationPhrase);
//
        _taskPhraseService.save(taskPhrase);

        List<Quiz> addQuizes = new ArrayList<>();
        addQuizes.add(quiz);
        //Create lesson
        Lesson lesson = new Lesson(LessonCode.CRO_US, Difficulty.EASY,addQuizes);
        _lessonService.save(lesson);


        List<Language> languages = _languageService.findAll();
        List<Phrase> phrases = _phraseService.findAll();
        List<TranslationPhrase> translationPhrases = _translationPhraseService.findAll();
        //List<TaskPhrase> taskPhrases = _taskPhraseService.findAll();
        List<Lesson> lessons = _lessonService.findAll();

        List<Task> allTasks = _taskService.findAll();  // however you're fetching tasks

        List<TaskPhrase> taskPhrases = allTasks.stream()
                .filter(t -> t instanceof TaskPhrase)
                .map(t -> (TaskPhrase) t)
                .collect(Collectors.toList());


        // add to the spring model
        theModel.addAttribute("languages", languages);
        theModel.addAttribute("phrases", phrases);
        theModel.addAttribute("translationPhrases", translationPhrases);
        theModel.addAttribute("taskPhrases", taskPhrases);
        theModel.addAttribute("lessons", lessons);

        String langCount = Integer.toString(languages.size());
        String phraseCount = Integer.toString(phrases.size());
        String translationCount = Integer.toString(translationPhrases.size());
        String lessonsCount = Integer.toString(lessons.size());

        logger.info("Languages " + langCount);
        logger.info("Phrases: " +  phraseCount);
        logger.info("Translations: " +  translationCount);
        logger.info("Lessons: " +  lessonsCount);

        logger.info("Redirecting...");

        return "testing/list-testing";
    }
}
