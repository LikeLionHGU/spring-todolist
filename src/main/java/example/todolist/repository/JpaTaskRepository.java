package example.todolist.repository;

import example.todolist.domain.Task;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class JpaTaskRepository implements TaskRepository {

  @PersistenceContext private EntityManager em;

  @Override
  public void save(Task task) {
    em.persist(task);
  }

  @Override
  public Optional<Task> findById(Long id) {
    return Optional.ofNullable(em.find(Task.class, id));
  }

  @Override
  public List<Task> findAll() {
    return em.createQuery("SELECT t FROM Task t", Task.class).getResultList();
  }

  @Override
  public void update(Task task) {}

  @Override
  public void delete(Task task) {
    em.remove(task);
  }
}
