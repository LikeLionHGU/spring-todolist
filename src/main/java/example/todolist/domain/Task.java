package example.todolist.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Task {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "task_id")
  private Long id;

  @Column(name = "content", nullable = false)
  private String content;

  public Task(String content) {
    this.content = content;
  }

  public void updateContent(String content) {
    this.content = content;
  }
}
