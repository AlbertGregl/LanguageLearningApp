package pi.project.languageApp.controller;

import algebra.hr.bll.blModels.BLTranslationPhrase;
import algebra.hr.bll.serviceImplementation.LanguageServiceImpl;
import algebra.hr.bll.serviceImplementation.PhraseServiceImpl;
import algebra.hr.bll.serviceImplementation.TranslationPhraseServiceImpl;
import algebra.hr.dal.entity.Language;
import algebra.hr.dal.entity.Phrase;
import algebra.hr.dal.entity.TranslationPhrase;
import hr.algebra.utils.notFoundErrors.CustomNotFoundException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("translationPhrases")
public class TranslationPhraseController {
    private final TranslationPhraseServiceImpl _translationService;
    private final PhraseServiceImpl _phraseService;
    private final LanguageServiceImpl _languageService;

    public TranslationPhraseController(TranslationPhraseServiceImpl translationService, PhraseServiceImpl phraseService, LanguageServiceImpl languageService) {
        _translationService = translationService;
        _phraseService = phraseService;
        _languageService = languageService;
    }

    @GetMapping("/list")
    public String listTranslationPhrases(Model theModel) {

        List<TranslationPhrase> translations = _translationService.findAll();

        // add to the spring model
        theModel.addAttribute("translations", translations);

        return "translationPhrases/list-translationPhrases";
    }

    @GetMapping("/showFormForAddTranslationPhrase")
    public String showFormForAddTranslationPhrase(Model theModel){

        BLTranslationPhrase translation = new BLTranslationPhrase();
        theModel.addAttribute("translation", translation);

        List<Language> languages = _languageService.findAll();
        theModel.addAttribute("languages", languages);

        return "translationPhrases/translationPhrase-form";
    }

    @GetMapping("/showFormForUpdateTranslationPhrase")
    public String showFormForUpdateTranslationPhrase(@RequestParam("translationPhraseId") int theId, Model theModel){
        //get the phrase from the service
        TranslationPhrase translationPhrase = _translationService.findById(theId);

        Phrase base = _phraseService.findById(translationPhrase.getBasePhrase().getPhraseID());
        Phrase translated = _phraseService.findById(translationPhrase.getTranslatedPhrase().getPhraseID());

        BLTranslationPhrase translation = new BLTranslationPhrase();

        translation.setTranslationID(translationPhrase.getPhraseTranslationID());

        translation.setBasePhrase(base.getPhraseText());
        translation.setTranslatedPhrase(translated.getPhraseText());
        translation.setBaseLanguage(base.getLanguage().getLanguageID());
        translation.setTranslatedLanguage(translated.getLanguage().getLanguageID());


        theModel.addAttribute("translation", translation);

        List<Language> languages = _languageService.findAll();
        theModel.addAttribute("languages", languages);

        return "translationPhrases/translationPhrase-form";
    }

    @PostMapping("/save")
    public String saveAdmin(@Valid @ModelAttribute("translation") BLTranslationPhrase translationPhrase, BindingResult bindingResult, Model model) {

        // Always load languages to avoid null crashes during validation errors
        List<Language> languages = _languageService.findAll();
        model.addAttribute("languages", languages);

        // Check for validation errors
        if (bindingResult.hasErrors()) {
            return "translationPhrases/translationPhrase-form";
        }

        // Use findByPhraseText service to find or save base and translated phrases
        Phrase basePhraseToAdd;
        Phrase translatedPhraseToAdd;

        try {
            basePhraseToAdd = _phraseService.findByPhraseText(translationPhrase.getBasePhrase().trim());
        } catch (CustomNotFoundException e) {
            Language baseLanguage = _languageService.findById(translationPhrase.getBaseLanguage());
            basePhraseToAdd = new Phrase(translationPhrase.getBasePhrase().trim(), baseLanguage);
            _phraseService.save(basePhraseToAdd);
        }

        try {
            translatedPhraseToAdd = _phraseService.findByPhraseText(translationPhrase.getTranslatedPhrase().trim());
        } catch (CustomNotFoundException e) {
            Language translatedLanguage = _languageService.findById(translationPhrase.getTranslatedLanguage());
            translatedPhraseToAdd = new Phrase(translationPhrase.getTranslatedPhrase().trim(), translatedLanguage);
            _phraseService.save(translatedPhraseToAdd);
        }

        // Handle translation update or creation
        TranslationPhrase translationPhraseToAdd;
        if (translationPhrase.getTranslationID() > 0) {
            translationPhraseToAdd = _translationService.findById(translationPhrase.getTranslationID());
            translationPhraseToAdd.setBasePhrase(basePhraseToAdd);
            translationPhraseToAdd.setTranslatedPhrase(translatedPhraseToAdd);
        } else {
            translationPhraseToAdd = new TranslationPhrase(basePhraseToAdd, translatedPhraseToAdd);
        }

        _translationService.save(translationPhraseToAdd);

        return "redirect:/translationPhrases/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("translationPhraseId") int theId){
        //delete the phrase
        _translationService.deleteById(theId);

        //redirect to the /phrases/list
        return "redirect:/translationPhrases/list";
    }
}
