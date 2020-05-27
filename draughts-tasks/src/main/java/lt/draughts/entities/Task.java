package lt.draughts.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "TASK")
@NamedQueries({
        @NamedQuery(name = "Task.GetAll", query = "SELECT t FROM Task as t "),
        @NamedQuery(name = "Task.Count", query = "SELECT COUNT(t) FROM Task as t ")
})
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Lob
    private int[][] position;

    @ElementCollection
    @CollectionTable(name="tasks_solution_moves")
    private List<String> solution = new ArrayList<>();

    @NotNull(message="Task Complexity cannot be null.")
    @Column(name = "taskComplexity")
    private TaskComplexity taskComplexity;

    @NotNull(message="Task Type cannot be null.")
    @Column(name = "taskType")
    private TaskType taskType;

    @NotNull(message="Task Result cannot be null.")
    @Column(name = "taskResult")
    private TaskResult taskResult;
}