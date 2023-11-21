package pi.project.languageApp.controller;

import algebra.hr.bll.blModels.BLWordTask;
import algebra.hr.bll.serviceImplementation.TaskWordServiceImpl;
import algebra.hr.bll.serviceImplementation.TranslationWordServiceImpl;
import algebra.hr.dal.entity.TaskWord;
import algebra.hr.dal.entity.TranslationWord;
import algebra.hr.dal.enums.TaskType;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("wordTasks")
public class TaskWordController {
    private final TaskWordServiceImpl _taskService;
    private final TranslationWordServiceImpl _wordTranslationService;

    public TaskWordController(TaskWordServiceImpl _taskService, TranslationWordServiceImpl _wordTranslationService) {
        this._taskService = _taskService;
        this._wordTranslationService = _wordTranslationService;
    }

    @GetMapping("/list")
    public String listTasks(Model theModel) {

        List<TaskWord> tasks = _taskService.findAll();

        // add to the spring model
        theModel.addAttribute("tasks", tasks);

        return "wordTasks/list-wordTasks";
    }

    @GetMapping("/showFormForAddWordTask")
    public String showFormForAddWordTask(Model theModel){

        BLWordTask task = new BLWordTask();
        theModel.addAttribute("task", task);

        List<TranslationWord> words = _wordTranslationService.findAll();
        theModel.addAttribute("words", words);

        return "wordTasks/wordTask-form";
    }

    @GetMapping("/showFormForUpdateWordTask")
    public String showFormForUpdateWordTask(@RequestParam("taskID") int theId, Model theModel){
        //get the task from the service
        TaskWord taskWord = _taskService.findById(theId);

        BLWordTask task = new BLWordTask();

        task.setTaskID(taskWord.getTaskID());

        task.setTranslationWord(taskWord.getTranslationWord().getTranslationWordID());
        task.setTaskText(taskWord.getTaskText());


        theModel.addAttribute("task", task);

        List<TranslationWord> words = _wordTranslationService.findAll();
        theModel.addAttribute("words", words);

        return "wordTasks/wordTask-form";
    }


    @PostMapping("/save")
    public String saveTask(@Valid @ModelAttribute("task") BLWordTask wordTask, BindingResult bindingResult, Model model) {

        // Always load all translations to avoid null crashes during validation errors
        List<TranslationWord> words = _wordTranslationService.findAll();
        model.addAttribute("words", words);

        // Check for validation errors
        if (bindingResult.hasErrors()) {
            return "wordTasks/wordTask-form";
        }

        TranslationWord translation = _wordTranslationService.findById(wordTask.getTranslationWord());

        // Handle translation update or creation
        TaskWord taskWordToAdd = new TaskWord();

        if (wordTask.getTaskID() > 0) {
            taskWordToAdd = _taskService.findById(wordTask.getTaskID());
            taskWordToAdd.setTranslationWord(translation);
            taskWordToAdd.setTaskText(wordTask.getTaskText());
        } else {
            taskWordToAdd = new TaskWord(TaskType.WORD,new ArrayList<>(),wordTask.getTaskText(),translation);
        }

        _taskService.save(taskWordToAdd);

        return "redirect:/wordTasks/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("taskID") int theId){
        //delete the word
        _taskService.deleteById(theId);

        //redirect to the /xxxx/list
        return "redirect:/wordTasks/list";
    }

}
