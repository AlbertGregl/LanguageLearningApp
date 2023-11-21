package pi.project.languageApp.controller;

import algebra.hr.bll.serviceImplementation.TaskPhraseServiceImpl;
import algebra.hr.dal.entity.TaskPhrase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller
@RequestMapping("games")
public class GameController {
    private final TaskPhraseServiceImpl _taskService;

    public GameController(TaskPhraseServiceImpl taskService) {
        _taskService = taskService;
    }

    @GetMapping("/phraseGame")
    public String gamePhrases(Model theModel) {
        List<TaskPhrase> allPhrases = _taskService.findAll();

        if (allPhrases != null && !allPhrases.isEmpty()) {
            Random random = new Random();

            int randomIndex = random.nextInt(allPhrases.size());

            TaskPhrase randomPhrase = allPhrases.get(randomIndex);

            String taskText = randomPhrase.getTaskText();

            String exerciseText = randomPhrase.getTranslationPhrase().getBasePhrase().getPhraseText();

            String phraseText = randomPhrase.getTranslationPhrase().getTranslatedPhrase().getPhraseText();

            // Split the phrase text into words
            List<String> choices = Arrays.asList(phraseText.split("\\s+"));

            //just like c++ shuffle
            Collections.shuffle(choices);

            theModel.addAttribute("taskText", taskText);
            theModel.addAttribute("exerciseText", exerciseText);
            theModel.addAttribute("choices", choices);
        } else {
            // Handle the case where there are no phrases
            theModel.addAttribute("taskText", "No task text available");
            theModel.addAttribute("exerciseText", "No phrases available.");
            theModel.addAttribute("choices", new ArrayList<String>());
        }

        return "games/index";
    }
}
