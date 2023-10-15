package pi.project.languageApp.controller;

import algebra.hr.bll.serviceImplementation.LanguageServiceImpl;
import algebra.hr.bll.serviceImplementation.PhraseServiceImpl;
import algebra.hr.bll.serviceImplementation.TranslationPhraseServiceImpl;
import algebra.hr.dal.entity.Language;
import algebra.hr.dal.entity.Phrase;
import algebra.hr.dal.entity.TranslationPhrase;
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

    public TestingController(LanguageServiceImpl languageService, PhraseServiceImpl phraseService
                                ,TranslationPhraseServiceImpl translationPhraseService) {
        _languageService = languageService;
        _phraseService = phraseService;
        _translationPhraseService = translationPhraseService;

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


        List<Language> languages = _languageService.findAll();
        List<Phrase> phrases = _phraseService.findAll();
        List<TranslationPhrase> translationPhrases = _translationPhraseService.findAll();

        // add to the spring model
        theModel.addAttribute("languages", languages);
        theModel.addAttribute("phrases", phrases);
        theModel.addAttribute("translationPhrases", translationPhrases);

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
