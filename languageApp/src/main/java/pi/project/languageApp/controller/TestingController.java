package pi.project.languageApp.controller;

import algebra.hr.bll.serviceImplementation.*;
import algebra.hr.dal.entity.*;
import algebra.hr.dal.enums.TaskType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("testing")
public class TestingController {

    private static final Logger logger = LoggerFactory.getLogger(TestingController.class);
    private final LanguageServiceImpl _languageService;
    private final PhraseServiceImpl _phraseService;
    private final TranslationPhraseServiceImpl _translationPhraseService;
    private final TaskPhraseServiceImpl _taskPhraseService;
    private final QuizServiceImpl _quizService;

    public TestingController(LanguageServiceImpl languageService, PhraseServiceImpl phraseService
                                ,TranslationPhraseServiceImpl translationPhraseService,
                                TaskPhraseServiceImpl taskPhraseService,
                                QuizServiceImpl quizService) {
        _languageService = languageService;
        _phraseService = phraseService;
        _translationPhraseService = translationPhraseService;
        _taskPhraseService = taskPhraseService;
        _quizService = quizService;
    }

    @GetMapping("/")
    public String tester() {
        return "Hello world";
    }

    @GetMapping("/list")
    public String listLanguages(Model theModel) {
        logger.info("Fetching list of languages...");
        logger.info("Fetching list of phrases...");

        //// Create languages
        //Language croatian = new Language("Croatian");
        //Language english = new Language("English");
//
        //_languageService.save(croatian);
        //_languageService.save(english);
//
        //// Create phrases
        //Phrase croatianPhrase = new Phrase("Dobar dan", croatian);
        //Phrase englishPhrase = new Phrase("Good day", english);
//
        //_phraseService.save(croatianPhrase);
        //_phraseService.save(englishPhrase);
//
        //// Create translation
        //TranslationPhrase translation = new TranslationPhrase(croatianPhrase, englishPhrase);
        //_translationPhraseService.save(translation);

        Quiz quiz = new Quiz("Basics german");
        _quizService.save(quiz);
        TranslationPhrase translationPhrase = _translationPhraseService.findById(1);

        TaskType taskType = TaskType.PHRASE;  // Choose the appropriate TaskType enum value
        String taskText = "Your task text here";
        TaskPhrase taskPhrase = new TaskPhrase(taskType, quiz, taskText, translationPhrase);

        _taskPhraseService.save(taskPhrase);


        List<Language> languages = _languageService.findAll();
        List<Phrase> phrases = _phraseService.findAll();
        List<TranslationPhrase> translationPhrases = _translationPhraseService.findAll();
        List<TaskPhrase> taskPhrases = _taskPhraseService.findAll();

        // add to the spring model
        theModel.addAttribute("languages", languages);
        theModel.addAttribute("phrases", phrases);
        theModel.addAttribute("translationPhrases", translationPhrases);
        theModel.addAttribute("taskPhrases", taskPhrases);

        String langCount = Integer.toString(languages.size());
        String phraseCount = Integer.toString(phrases.size());
        String translationCount = Integer.toString(translationPhrases.size());

        logger.info("Languages " + langCount);
        logger.info("Phrases: " +  phraseCount);
        logger.info("Translations: " +  translationCount);

        logger.info("Redirecting...");

        return "testing/list-testing";
    }
}
