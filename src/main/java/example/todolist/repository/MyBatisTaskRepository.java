package example.todolist.repository;

import example.todolist.domain.Task;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface MyBatisTaskRepository extends TaskRepository {
  @Override
  @Insert("INSERT INTO task (title) " + "VALUES (#{title})")
  void save(Task task);

  @Override
  @Select("SELECT * FROM task WHERE id = #{id}")
  Optional<Task> findById(Long id);

  @Override
  @Select("SELECT * FROM task")
  List<Task> findAll();

  @Override
  @Update("UPDATE task " + "SET title = #{title} " + "WHERE id = #{id}")
  void update(Task task);

  @Override
  @Delete("DELETE FROM task WHERE id = #{id}")
  void delete(Task task);
}
