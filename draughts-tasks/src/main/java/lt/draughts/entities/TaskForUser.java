
package lt.draughts.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "TASK_FOR_USER")
@NamedQueries({
        @NamedQuery(name = "TaskForUser.GetAll", query = "SELECT t " +
                "FROM TaskForUser as t "),
        @NamedQuery(name = "TaskForUser.GetUsersResolvedTasks", query = "SELECT t " +
                "FROM TaskForUser as t " +
                "JOIN t.user as u " +
                "WHERE u.email=:email " +
                "AND t.taskStatus ='RESOLVED'"),
        @NamedQuery(name = "TaskForUser.CountUsersTasks", query = "SELECT COUNT(t) " +
                "from TaskForUser as t " +
                "JOIN t.user as u " +
                "WHERE u.email=:email " +
                "AND t.taskStatus ='RESOLVED'"),
        @NamedQuery(name = "TaskForUser.GetUsersTasks", query = "SELECT t " +
                "from TaskForUser as t " +
                "JOIN t.user as u " +
                "WHERE u.email=:email "),
        @NamedQuery(name = "TaskForUser.GetAllByUserAndTask", query = "SELECT t " +
                "FROM TaskForUser as t " +
                "JOIN t.user as u " +
                "JOIN t.task as ta " +
                "WHERE u.email=:email " +
                "AND ta.id=:taskID")

})
public class TaskForUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull(message = "Task cannot be null.")
    @ManyToOne
    @JoinColumn(name = "taskID")
    private Task task;

    @NotNull(message = "Task Resolution cannot be null.")
    @Column(name = "taskStatus")
    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;

    @NotNull(message = "Start date cannot be null.")
    @Column(name = "start")
    private Date start;

    @Column(name = "resolvedDate")
    private Date resolvedDate;

    @Column(name = "numberOfTries")
    private int numberOfTries;

    @NotNull(message = "User cannot be null.")
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
}