package pi.project.languageApp.controller;

import algebra.hr.bll.serviceImplementation.LanguageServiceImpl;
import algebra.hr.bll.serviceImplementation.PhraseServiceImpl;
import algebra.hr.dal.entity.Language;
import algebra.hr.dal.entity.Phrase;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("phrases")
public class PhraseController {
    private PhraseServiceImpl _phraseService;
    private LanguageServiceImpl _languageService;

    public PhraseController(PhraseServiceImpl phraseService, LanguageServiceImpl languageService) {
        _phraseService = phraseService;
        _languageService = languageService;
    }

    @GetMapping("/list")
    public String listPhrases(Model theModel) {

        List<Phrase> phrases = _phraseService.findAll();

        // add to the spring model
        theModel.addAttribute("phrases", phrases);

        return "phrases/list-phrases";
    }

    @GetMapping("/listSearch")
    public String searchPhrases(Phrase phrase, Model model, String keyword) {
        List<Phrase> phrases;
        if(keyword != null) {
            phrases = _phraseService.getByKeyword(keyword);
        }else {
            phrases = _phraseService.findAll();
        }
        model.addAttribute("phrases", phrases);

        return "phrases/list-languages";
    }


    @GetMapping("/showFormForAddPhrase")
    public String showFormForAddPhrase(Model theModel){

        //create the model attribute to bind form data
        Phrase phrase =  new Phrase();
        theModel.addAttribute("phrase", phrase);

        List<Language> languages = _languageService.findAll();
        theModel.addAttribute("languages", languages);

        return "phrases/phrase-form";
    }

    @GetMapping("/showFormForUpdatePhrase")
    public String showFormForUpdatePhrase(@RequestParam("phraseId") int theId, Model theModel){
        //get the phrase from the service
        Phrase phrase = _phraseService.findById(theId);

        theModel.addAttribute("phrase", phrase);

        //send over to our form
        return "phrases/phrase-form";
    }

    @PostMapping("/save")
    public String saveGenre(@Valid @ModelAttribute("phrase") Phrase phrase, BindingResult bindingResult, Model model) {

        // Check for validation errors
        if (bindingResult.hasErrors()) {
            return "phrases/phrase-form";
        }

        List<Phrase> allDBPhrases = _phraseService.findAll();

        Optional<Phrase> result = allDBPhrases.stream()
                .filter(phraseRes -> phrase.getPhraseText().equals(phraseRes.getPhraseText()))
                .findFirst();

        if(result.isPresent()){
            model.addAttribute("errorMessage", "Phrase with that text already exists!");
            return "phrases/phrase-form";
        }

        _phraseService.save(phrase);

        return "redirect:/phrases/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("phraseId") int theId){
        //delete the phrase
        _phraseService.deleteById(theId);

        //redirect to the /phrases/list
        return "redirect:/phrases/list";
    }
}

