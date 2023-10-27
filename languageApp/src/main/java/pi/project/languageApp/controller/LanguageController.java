package pi.project.languageApp.controller;

import algebra.hr.bll.serviceImplementation.LanguageServiceImpl;
import algebra.hr.dal.entity.Language;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("languages")
public class LanguageController {
    private final LanguageServiceImpl _languageService;

    public LanguageController(LanguageServiceImpl _languageService) {
        this._languageService = _languageService;
    }

    @GetMapping("/list")
    public String listGenres(Model theModel) {

        List<Language> languages = _languageService.findAll();

        // add to the spring model
        theModel.addAttribute("languages", languages);

        return "languages/list-languages";
    }

    @GetMapping("/listSearch")
    public String searchLanguages(Language language, Model model, String keyword) {
        List<Language> languages;
        if(keyword != null) {
            languages = _languageService.getByKeyword(keyword);
        }else {
            languages = _languageService.findAll();
        }
        model.addAttribute("languages", languages);

        return "genres/list-languages";
    }

    @GetMapping("/showFormForAddLanguage")
    public String showFormForAddLanguage(Model theModel){

        //create the model attribute to bind form data
        Language language =  new Language();
        theModel.addAttribute("language", language);

        return "languages/language-form";
    }

    @GetMapping("/showFormForUpdateLanguage")
    public String showFormForUpdateLanguage(@RequestParam("languageId") int theId, Model theModel){
        //get the genre from the service
        Language language = _languageService.findById(theId);

        theModel.addAttribute("language", language);

        //send over to our form
        return "languages/language-form";
    }

    @PostMapping("/save")
    public String saveGenre(@Valid @ModelAttribute("language") Language language, BindingResult bindingResult, Model model) {

        // Check for validation errors
        if (bindingResult.hasErrors()) {
            return "languages/language-form";
        }

        List<Language> allDBLanguages = _languageService.findAll();

        Optional<Language> result = allDBLanguages.stream()
                .filter(languageRes -> language.getLanguageName().equals(languageRes.getLanguageName()))
                .findFirst();

        if(result.isPresent()){
            model.addAttribute("errorMessage", "Language with that name already exists!");
            return "languages/language-form";
        }

        _languageService.save(language);

        return "redirect:/languages/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("languageId") int theId){
        //delete the genre
        _languageService.deleteById(theId);

        //redirect to the /genres/list
        return "redirect:/languages/list";
    }
}
