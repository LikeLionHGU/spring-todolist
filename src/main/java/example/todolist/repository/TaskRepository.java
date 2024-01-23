package example.todolist.repository;

import example.todolist.domain.Task;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository {
  void save(Task task);

  Optional<Task> findById(Long id);

  List<Task> findAll();

  void update(Task task);

  void delete(Task task);
}
