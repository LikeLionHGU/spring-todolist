package example.todolist.exception;

public class TaskNotFoundException extends RuntimeException {
  public TaskNotFoundException() {
    super("[ERROR] Task Not Found");
  }
}
