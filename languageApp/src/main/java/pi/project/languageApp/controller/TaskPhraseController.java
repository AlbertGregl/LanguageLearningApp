package pi.project.languageApp.controller;

import algebra.hr.bll.blModels.BLPhraseTask;
import algebra.hr.bll.serviceImplementation.TaskPhraseServiceImpl;
import algebra.hr.bll.serviceImplementation.TranslationPhraseServiceImpl;
import algebra.hr.dal.entity.TaskPhrase;
import algebra.hr.dal.entity.TranslationPhrase;
import algebra.hr.dal.enums.TaskType;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("phraseTasks")
public class TaskPhraseController {
    private final TaskPhraseServiceImpl _taskService;
    private final TranslationPhraseServiceImpl _phraseService;

    public TaskPhraseController(TaskPhraseServiceImpl taskService, TranslationPhraseServiceImpl phraseService) {
        _taskService = taskService;
        _phraseService = phraseService;
    }

    @GetMapping("/list")
    public String listTranslationTasks(Model theModel) {

        List<TaskPhrase> tasks = _taskService.findAll();

        // add to the spring model
        theModel.addAttribute("tasks", tasks);

        return "phraseTasks/list-phraseTasks";
    }

    @GetMapping("/showFormForAddPhraseTask")
    public String showFormForAddPhraseTask(Model theModel){

        BLPhraseTask task = new BLPhraseTask();
        theModel.addAttribute("task", task);

        List<TranslationPhrase> phrases = _phraseService.findAll();
        theModel.addAttribute("phrases", phrases);

        return "phraseTasks/phraseTask-form";
    }

    @GetMapping("/showFormForUpdatePhraseTask")
    public String showFormForUpdatePhraseTask(@RequestParam("taskID") int theId, Model theModel){
        //get the task from the service
        TaskPhrase taskPhrase = _taskService.findById(theId);

        BLPhraseTask task = new BLPhraseTask();

        task.setTaskID(taskPhrase.getTaskID());

        task.setTranslationPhrase(taskPhrase.getTranslationPhrase().getPhraseTranslationID());
        task.setTaskText(taskPhrase.getTaskText());


        theModel.addAttribute("task", task);

        List<TranslationPhrase> phrases = _phraseService.findAll();
        theModel.addAttribute("phrases", phrases);

        return "phraseTasks/phraseTask-form";
    }

    @PostMapping("/save")
    public String saveTask(@Valid @ModelAttribute("task") BLPhraseTask phraseTask, BindingResult bindingResult, Model model) {

        // Always load all translations to avoid null crashes during validation errors
        List<TranslationPhrase> phrases = _phraseService.findAll();
        model.addAttribute("phrases", phrases);

        // Check for validation errors
        if (bindingResult.hasErrors()) {
            return "phraseTasks/phraseTask-form";
        }

        TranslationPhrase translation = _phraseService.findById(phraseTask.getTranslationPhrase());

        // Handle translation update or creation
        TaskPhrase taskPhraseToAdd = new TaskPhrase();

        if (phraseTask.getTaskID() > 0) {
            taskPhraseToAdd = _taskService.findById(phraseTask.getTaskID());
            taskPhraseToAdd.setTranslationPhrase(translation);
            taskPhraseToAdd.setTaskText(phraseTask.getTaskText());
        } else {
            taskPhraseToAdd = new TaskPhrase(TaskType.PHRASE,new ArrayList<>(),phraseTask.getTaskText(),translation);
        }

        _taskService.save(taskPhraseToAdd);

        return "redirect:/phraseTasks/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("taskID") int theId){
        //delete the word
        _taskService.deleteById(theId);

        //redirect to the /xxxx/list
        return "redirect:/phraseTasks/list";
    }
}
