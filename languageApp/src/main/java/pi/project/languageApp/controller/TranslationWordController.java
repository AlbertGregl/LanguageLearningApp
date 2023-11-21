package pi.project.languageApp.controller;

import algebra.hr.bll.blModels.BLTranslationWord;
import algebra.hr.bll.serviceImplementation.LanguageServiceImpl;
import algebra.hr.bll.serviceImplementation.TranslationWordServiceImpl;
import algebra.hr.bll.serviceImplementation.WordServiceImpl;
import algebra.hr.dal.entity.Language;
import algebra.hr.dal.entity.TranslationWord;
import algebra.hr.dal.entity.Word;
import hr.algebra.utils.notFoundErrors.CustomNotFoundException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("translationWords")
public class TranslationWordController {
    private final TranslationWordServiceImpl _translationService;
    private final WordServiceImpl _wordService;
    private final LanguageServiceImpl _languageService;

    public TranslationWordController(TranslationWordServiceImpl _translationService, WordServiceImpl _wordService, LanguageServiceImpl _languageService) {
        this._translationService = _translationService;
        this._wordService = _wordService;
        this._languageService = _languageService;
    }

    @GetMapping("/list")
    public String listTranslationWords(Model theModel) {

        List<TranslationWord> translations = _translationService.findAll();

        // add to the spring model
        theModel.addAttribute("translations", translations);

        return "translationWords/list-translationWords";
    }

    @GetMapping("/showFormForAddTranslationWord")
    public String showFormForAddTranslationWord(Model theModel){

        BLTranslationWord translation = new BLTranslationWord();
        theModel.addAttribute("translation", translation);

        List<Language> languages = _languageService.findAll();
        theModel.addAttribute("languages", languages);

        return "translationWords/translationWord-form";
    }

    @GetMapping("/showFormForUpdateTranslationWord")
    public String showFormForUpdateTranslationWord(@RequestParam("translationWordId") int theId, Model theModel){
        //get the phrase from the service
        TranslationWord translationWord = _translationService.findById(theId);

        Word base = _wordService.findById(translationWord.getBaseWord().getWordID());
        Word translated = _wordService.findById(translationWord.getTranslatedWord().getWordID());

        BLTranslationWord translation = new BLTranslationWord();

        translation.setTranslationID(translationWord.getTranslationWordID());

        translation.setBaseWord(base.getWordText());
        translation.setTranslatedWord(translated.getWordText());
        translation.setBaseLanguage(base.getLanguage().getLanguageID());
        translation.setTranslatedLanguage(translated.getLanguage().getLanguageID());


        theModel.addAttribute("translation", translation);

        List<Language> languages = _languageService.findAll();
        theModel.addAttribute("languages", languages);

        return "translationWords/translationWord-form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("translation") BLTranslationWord translationWord, BindingResult bindingResult, Model model) {

        // Always load languages to avoid null crashes during validation errors
        List<Language> languages = _languageService.findAll();
        model.addAttribute("languages", languages);

        // Check for validation errors
        if (bindingResult.hasErrors()) {
            return "translationWords/translationWord-form";
        }

        // Use findByWordText service to find or save base and translated words
        Word baseWordToAdd;
        Word translatedWordToAdd;

        try {
            baseWordToAdd = _wordService.findByWordText(translationWord.getBaseWord().trim());
        } catch (CustomNotFoundException e) {
            Language baseLanguage = _languageService.findById(translationWord.getBaseLanguage());
            baseWordToAdd = new Word(translationWord.getBaseWord().trim(), baseLanguage);
            _wordService.save(baseWordToAdd);
        }

        try {
            translatedWordToAdd = _wordService.findByWordText(translationWord.getTranslatedWord().trim());
        } catch (CustomNotFoundException e) {
            Language translatedLanguage = _languageService.findById(translationWord.getTranslatedLanguage());
            translatedWordToAdd = new Word(translationWord.getTranslatedWord().trim(), translatedLanguage);
            _wordService.save(translatedWordToAdd);
        }

        // Handle translation update or creation
        TranslationWord translationWordToAdd;
        if (translationWord.getTranslationID() > 0) {
            translationWordToAdd = _translationService.findById(translationWord.getTranslationID());
            translationWordToAdd.setBaseWord(baseWordToAdd);
            translationWordToAdd.setTranslatedWord(translatedWordToAdd);
        } else {
            translationWordToAdd = new TranslationWord(baseWordToAdd, translatedWordToAdd);
        }

        _translationService.save(translationWordToAdd);

        return "redirect:/translationWords/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("translationWordId") int theId){
        //delete the word
        _translationService.deleteById(theId);

        //redirect to the /phrases/list
        return "redirect:/translationWords/list";
    }

}
