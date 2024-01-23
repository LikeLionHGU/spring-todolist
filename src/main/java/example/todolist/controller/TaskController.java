package example.todolist.controller;

import example.todolist.domain.Task;
import example.todolist.exception.TaskNotFoundException;
import example.todolist.service.TaskService;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class TaskController {

  private final TaskService taskService;

  @GetMapping("/")
  public String displayTasks(Model model) {
    List<Task> tasks = taskService.getAllTasks();
    model.addAttribute("tasks", tasks);
    return "index";
  }

  @PostMapping("/tasks")
  public String addTask(@RequestParam String content) {
    taskService.addTask(content);
    return "redirect:/";
  }

  @PutMapping("/tasks/{id}")
  @ResponseBody
  public Map<String, Task> updateTask(@PathVariable Long id, @RequestBody TaskForm form) {
    Task task = taskService.updateTask(id, form.getContent());
    return Collections.singletonMap("task", task);
  }

  @DeleteMapping("/tasks/{id}")
  public ResponseEntity<Void> completeTask(@PathVariable Long id) {
    taskService.removeTask(id);
    return ResponseEntity.ok().build();
  }

  @ExceptionHandler(TaskNotFoundException.class)
  public ResponseEntity<String> handleTaskNotFoundException(TaskNotFoundException e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
  }
}
