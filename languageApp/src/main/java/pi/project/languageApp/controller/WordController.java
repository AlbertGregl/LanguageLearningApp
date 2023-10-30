package pi.project.languageApp.controller;

import algebra.hr.bll.serviceImplementation.LanguageServiceImpl;
import algebra.hr.bll.serviceImplementation.WordServiceImpl;
import algebra.hr.dal.entity.Language;
import algebra.hr.dal.entity.Word;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("words")
public class WordController {
    private final WordServiceImpl _wordService;
    private final LanguageServiceImpl _languageService;

    public WordController(WordServiceImpl wordService, LanguageServiceImpl languageService) {
        _wordService = wordService;
        _languageService = languageService;
    }

    @GetMapping("/list")
    public String listWords(Model theModel) {

        List<Word> words = _wordService.findAll();

        // add to the spring model
        theModel.addAttribute("words", words);

        return "words/list-words";
    }

    @GetMapping("/showFormForAddWord")
    public String showFormForAddWord(Model theModel){

        //create the model attribute to bind form data
        Word word =  new Word();
        theModel.addAttribute("word", word);

        List<Language> languages = _languageService.findAll();
        theModel.addAttribute("languages", languages);

        return "words/word-form";
    }

    @GetMapping("/showFormForUpdateWord")
    public String showFormForUpdateWord(@RequestParam("wordId") int theId, Model theModel){
        //get the word from the service
        Word word = _wordService.findById(theId);

        theModel.addAttribute("word", word);

        //send over to our form
        return "words/word-form";
    }

    @PostMapping("/save")
    public String saveGenre(@Valid @ModelAttribute("word") Word word, BindingResult bindingResult, Model model) {

        // Check for validation errors
        if (bindingResult.hasErrors()) {
            return "words/word-form";
        }

        List<Word> allDBWords = _wordService.findAll();

        Optional<Word> result = allDBWords.stream()
                .filter(wordRes -> word.getWordText().equals(wordRes.getWordText()))
                .findFirst();

        if(result.isPresent()){
            model.addAttribute("errorMessage", "Word with that text already exists!");
            return "words/word-form";
        }

        _wordService.save(word);

        return "redirect:/words/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("wordId") int theId){
        //delete the word
        _wordService.deleteById(theId);

        //redirect to the /words/list
        return "redirect:/words/list";
    }
}
