package pi.project.languageApp.controller;

import algebra.hr.bll.blModels.BLTranslationPhrase;
import algebra.hr.bll.serviceImplementation.LanguageServiceImpl;
import algebra.hr.bll.serviceImplementation.PhraseServiceImpl;
import algebra.hr.bll.serviceImplementation.TranslationPhraseServiceImpl;
import algebra.hr.dal.entity.Language;
import algebra.hr.dal.entity.TranslationPhrase;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @PostMapping("/save")
    public String saveAdmin(@Valid @ModelAttribute("translation") TranslationPhrase translation, BindingResult bindingResult, Model model) {

        // Always load countries to avoid null crashes during validation errors
        List<Language> languages = _languageService.findAll();
        model.addAttribute("languages", languages);

        // Check for validation errors
        if (bindingResult.hasErrors()) {
            return "translationPhrases/translationPhrase-form";
        }

        //user.setPassword(_securityService.doBCryptPassEncoding(user.getPassword()));
        //user.setConfirmed(true);
//
        //// Save the new admin to the database
        //_userService.save(user);
//
        //// Assign the ROLE_ADMIN and ROLE_USER role to the new admin
        //Role r = new Role(user.getUsername(), "ROLE_USER");
        //Role r2 = new Role(user.getUsername(), "ROLE_ADMIN");
        //_roleService.save(r);
        //_roleService.save(r2);

        return "redirect:/users/list";
    }

}
