package pi.project.languageApp.controller;

import algebra.hr.bll.blModels.BLCustomTask;
import algebra.hr.bll.serviceImplementation.TaskCustomServiceImpl;
import algebra.hr.dal.entity.TaskCustom;
import algebra.hr.dal.enums.TaskType;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("customTasks")
public class TaskCustomController {
    private final TaskCustomServiceImpl _taskService;

    public TaskCustomController(TaskCustomServiceImpl taskService) {
        _taskService = taskService;
    }

    @GetMapping("/list")
    public String listTranslationTasks(Model theModel) {

        List<TaskCustom> tasks = _taskService.findAll();

        // add to the spring model
        theModel.addAttribute("tasks", tasks);

        return "customTasks/list-customTasks";
    }

    @GetMapping("/showFormForAddCustomTask")
    public String showFormForAddCustomTask(Model theModel){

        //create the model attribute to bind form data
        TaskCustom task =  new TaskCustom();
        theModel.addAttribute("task", task);

        return "customTasks/customTask-form";
    }

    @GetMapping("/showFormForUpdateCustomTask")
    public String showFormForUpdateCustomTask(@RequestParam("taskID") int theId, Model theModel){
        //get the task from the service
        TaskCustom taskCustom = _taskService.findById(theId);

        BLCustomTask task = new BLCustomTask();

        task.setTaskID(taskCustom.getTaskID());

        task.setTaskText(taskCustom.getTaskText());
        task.setTaskAnswer(taskCustom.getTaskAnswer());

        theModel.addAttribute("task", task);

        return "customTasks/customTask-form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("task") BLCustomTask taskCustom, BindingResult bindingResult, Model model) {

        // Check for validation errors
        if (bindingResult.hasErrors()) {
            return "customTasks/customTask-form";
        }

        TaskCustom taskToAdd = new TaskCustom();

        if (taskCustom.getTaskID() > 0) {
            taskToAdd = _taskService.findById(taskCustom.getTaskID());
            taskToAdd.setTaskText(taskCustom.getTaskText());
            taskToAdd.setTaskAnswer(taskCustom.getTaskAnswer());
        } else {
            taskToAdd = new TaskCustom(TaskType.WORD,new ArrayList<>(), taskCustom.getTaskText(), taskCustom.getTaskAnswer());
        }

        _taskService.save(taskToAdd);

        return "redirect:/customTasks/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("taskID") int theId){
        //delete the word
        _taskService.deleteById(theId);

        //redirect to the /xxxx/list
        return "redirect:/customTasks/list";
    }
}
