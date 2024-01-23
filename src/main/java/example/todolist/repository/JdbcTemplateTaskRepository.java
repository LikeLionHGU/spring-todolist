package example.todolist.repository;

import example.todolist.domain.Task;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

@RequiredArgsConstructor
public class JdbcTemplateTaskRepository implements TaskRepository {

  private final JdbcTemplate jdbcTemplate;

  @Override
  public void save(Task task) {
    jdbcTemplate.update("INSERT INTO task (title) VALUES (?)", task.getContent());
  }

  @Override
  public Optional<Task> findById(Long id) {
    return jdbcTemplate
        .query("SELECT * FROM task WHERE id = ?", BeanPropertyRowMapper.newInstance(Task.class), id)
        .stream()
        .findFirst();
  }

  @Override
  public List<Task> findAll() {
    return jdbcTemplate.query("SELECT * FROM task", BeanPropertyRowMapper.newInstance(Task.class));
  }

  @Override
  public void update(Task task) {
    jdbcTemplate.update("UPDATE task SET title = ? WHERE id = ?", task.getContent(), task.getId());
  }

  @Override
  public void delete(Task task) {
    jdbcTemplate.update("DELETE FROM task WHERE id = ?", task.getId());
  }
}
