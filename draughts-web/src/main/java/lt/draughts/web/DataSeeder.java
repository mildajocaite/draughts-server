package lt.draughts.web;

import lt.draughts.entities.*;
import lt.draughts.entities.dto.BoardDTO;
import lt.draughts.entities.dto.CreateBoardDTO;
import lt.draughts.entities.dto.CreateTask;
import lt.draughts.entities.request.RegistrationForm;
import lt.draughts.repositories.GameRepository;
import lt.draughts.repository.*;
import lt.draughts.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class DataSeeder {

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    UserService userService;

    @Autowired
    BoardService boardService;

    @Autowired
    GameServices gameServices;

    @Autowired
    TaskService taskService;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    TaskForUserRepository taskForUserRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    GameRepository gameRepository;

    @Autowired
    BoardRepository boardRepository;

    @PostConstruct
    public void loadData() {
        try {
            Role coach_role = new Role();
            coach_role.setName(RoleName.ROLE_COACH);
            roleRepository.createRole(coach_role);

            Role student_role = new Role();
            student_role.setName(RoleName.ROLE_STUDENT);
            roleRepository.createRole(student_role);


            CreateBoardDTO createBoardDTO = new CreateBoardDTO();
            createBoardDTO.setName("Mildos lenta");
            createBoardDTO.setCode("889998");
            BoardDTO boardMilda = boardService.createBoard(createBoardDTO);

            CreateBoardDTO createBoardDTO2 = new CreateBoardDTO();
            createBoardDTO2.setName("Tado lenta");
            createBoardDTO2.setCode("884448");
            boardService.createBoard(createBoardDTO2);

            CreateBoardDTO createBoardDTO3 = new CreateBoardDTO();
            createBoardDTO3.setName("Juozo lenta");
            createBoardDTO3.setCode("889975");
            boardService.createBoard(createBoardDTO3);

            CreateBoardDTO createBoardDTO4 = new CreateBoardDTO();
            createBoardDTO4.setName("Antano lenta");
            createBoardDTO4.setCode("889971");
            boardService.createBoard(createBoardDTO4);

            CreateBoardDTO createBoardDTO5 = new CreateBoardDTO();
            createBoardDTO5.setName("Justo lenta");
            createBoardDTO5.setCode("589971");
            boardService.createBoard(createBoardDTO5);

            RegistrationForm coach = new RegistrationForm();
            coach.setEmail("treneris@gmail.com");
            coach.setFirstname("Jonas");
            coach.setLastname("Jonaitis");
            coach.setPassword("asd123");
            coach.setPhone("+37061587912");
            coach.setRole("ROLE_COACH");
            userService.createUser(coach);

            RegistrationForm coach2 = new RegistrationForm();
            coach2.setEmail("petras@gmail.com");
            coach2.setFirstname("Petras");
            coach2.setLastname("Petraitis");
            coach2.setPassword("asd123");
            coach2.setPhone("+37061584412");
            coach2.setRole("ROLE_COACH");
            userService.createUser(coach2);

            RegistrationForm student = new RegistrationForm();
            student.setEmail("mokinys@gmail.com");
            student.setFirstname("Milda");
            student.setLastname("Jocaitė");
            student.setPassword("asd123");
            student.setPhone("+37069587912");
            student.setBoardCode("889998");
            student.setCoach("treneris@gmail.com");
            student.setRole("ROLE_STUDENT");
            User userMilda = userService.createUser(student);

            RegistrationForm student2 = new RegistrationForm();
            student2.setEmail("juozas@gmail.com");
            student2.setFirstname("Juozas");
            student2.setLastname("Juozaitis");
            student2.setPassword("asd123");
            student2.setPhone("+37069583312");
            student2.setBoardCode("889933");
            student2.setRole("ROLE_STUDENT");
            student2.setCoach("petras@gmail.com");
            student2.setBoardCode("889975");
            User userJuozas = userService.createUser(student2);

            RegistrationForm student3 = new RegistrationForm();
            student3.setEmail("tadas@gmail.com");
            student3.setFirstname("Tadas");
            student3.setLastname("Tadaitis");
            student3.setPassword("asd123");
            student3.setPhone("+37069345912");
            student3.setBoardCode("884448");
            student3.setCoach("treneris@gmail.com");
            student3.setRole("ROLE_STUDENT");
            userService.createUser(student3);

            RegistrationForm student4 = new RegistrationForm();
            student4.setEmail("justas@gmail.com");
            student4.setFirstname("Justas");
            student4.setLastname("Paulauskas");
            student4.setPassword("asd123");
            student4.setPhone("+37069345012");
            student4.setBoardCode("589971");
            student4.setCoach("treneris@gmail.com");
            student4.setRole("ROLE_STUDENT");
            userService.createUser(student4);

            RegistrationForm student5 = new RegistrationForm();
            student5.setEmail("antanas@gmail.com");
            student5.setFirstname("Antanas");
            student5.setLastname("Antanaitis");
            student5.setPassword("asd123");
            student5.setPhone("+37069345002");
            student5.setBoardCode("889971");
            student5.setCoach("treneris@gmail.com");
            student5.setRole("ROLE_STUDENT");
            userService.createUser(student5);

            CreateTask task = new CreateTask();
            task.setPosition(
                    new int[][]{
                            {6, 5, 6, 5, 6, 5, 6, 5},
                            {5, 6, 5, 6, 5, 6, 2, 6},
                            {6, 5, 6, 5, 6, 5, 6, 5},
                            {2, 6, 5, 6, 2, 6, 5, 6},
                            {6, 5, 6, 5, 6, 5, 6, 5},
                            {5, 6, 1, 6, 5, 6, 5, 6},
                            {6, 1, 6, 5, 6, 5, 6, 5},
                            {1, 6, 5, 6, 5, 6, 5, 6}
                    });
            List<String> solution = new ArrayList<>();
            solution.add("c3-b4");
            solution.add("a5:c3");
            solution.add("b2:d4:f6:h8");
            task.setSolution(solution);
            task.setTaskComplexity(TaskComplexity.EASY);
            task.setTaskResult(TaskResult.WHITES_START_AND_WIN);
            task.setTaskType(TaskType.KOMBINACIJA);
            taskService.createTask(task);

            CreateTask task2 = new CreateTask();
            task2.setPosition(
                    new int[][]{
                            {6, 5, 6, 5, 6, 5, 6, 5},
                            {5, 6, 5, 6, 2, 6, 5, 6},
                            {6, 5, 6, 5, 6, 5, 6, 5},
                            {2, 6, 5, 6, 2, 6, 5, 6},
                            {6, 5, 6, 5, 6, 5, 6, 5},
                            {5, 6, 1, 6, 5, 6, 5, 6},
                            {6, 1, 6, 5, 6, 5, 6, 5},
                            {1, 6, 5, 6, 5, 6, 5, 6}
                    });
            List<String> solution2 = new ArrayList<>();
            solution2.add("c3-b4");
            solution2.add("a5:c3");
            solution2.add("b2:d4:f6:f8");
            task2.setSolution(solution2);
            task2.setTaskComplexity(TaskComplexity.EASY);
            task2.setTaskResult(TaskResult.WHITES_START_AND_WIN);
            task2.setTaskType(TaskType.KOMBINACIJA);
            taskService.createTask(task2);

            CreateTask task3 = new CreateTask();
            task3.setPosition(
                    new int[][]{
                            {6, 5, 6, 5, 6, 5, 6, 5},
                            {5, 6, 5, 6, 5, 6, 5, 6},
                            {6, 5, 6, 5, 6, 5, 6, 2},
                            {5, 6, 5, 6, 5, 6, 5, 6},
                            {6, 5, 6, 2, 6, 5, 6, 1},
                            {5, 6, 5, 6, 5, 6, 1, 6},
                            {6, 5, 6, 5, 6, 5, 6, 1},
                            {5, 6, 5, 6, 5, 6, 5, 6}
                    });
            List<String> solution3 = new ArrayList<>();
            solution3.add("h4-g5");
            solution3.add("h6:f4");
            solution3.add("g3:c3");
            task3.setSolution(solution3);
            task3.setTaskComplexity(TaskComplexity.EASY);
            task3.setTaskResult(TaskResult.WHITES_START_AND_WIN);
            task3.setTaskType(TaskType.KOMBINACIJA);
            taskService.createTask(task3);

            CreateTask task4 = new CreateTask();
            task4.setPosition(
                    new int[][]{
                            {6, 5, 6, 5, 6, 5, 6, 3},
                            {5, 6, 5, 6, 5, 6, 5, 6},
                            {6, 5, 6, 5, 6, 5, 6, 2},
                            {2, 6, 5, 6, 5, 6, 5, 6},
                            {6, 5, 6, 5, 6, 5, 6, 5},
                            {5, 6, 5, 6, 5, 6, 5, 6},
                            {6, 5, 6, 5, 6, 5, 6, 5},
                            {5, 6, 5, 6, 5, 6, 5, 6}
                    });
            List<String> solution4 = new ArrayList<>();
            solution4.add("h8-c3");
            solution4.add("h6-g5");
            solution4.add("c3-d2");
            solution4.add("g5-h4");
            solution4.add("d2-e1");
            task4.setSolution(solution4);
            task4.setTaskComplexity(TaskComplexity.MEDIUM);
            task4.setTaskResult(TaskResult.WHITES_START_AND_WIN);
            task4.setTaskType(TaskType.ZIRKLES);
            taskService.createTask(task4);

            CreateTask task5 = new CreateTask();
            task5.setPosition(
                    new int[][]{
                            {6, 5, 6, 5, 6, 5, 6, 2},
                            {5, 6, 5, 6, 5, 6, 2, 6},
                            {6, 5, 6, 5, 6, 5, 6, 2},
                            {5, 6, 5, 6, 5, 6, 5, 6},
                            {6, 5, 6, 5, 6, 1, 6, 1},
                            {5, 6, 5, 6, 5, 6, 5, 6},
                            {6, 5, 6, 5, 6, 5, 6, 5},
                            {5, 6, 5, 6, 5, 6, 5, 6}
                    });
            List<String> solution5 = new ArrayList<>();
            solution5.add("h4-g5");
            task5.setSolution(solution5);
            task5.setTaskComplexity(TaskComplexity.EASY);
            task5.setTaskResult(TaskResult.WHITES_START_AND_WIN);
            task5.setTaskType(TaskType.KITA);
            taskService.createTask(task5);

            CreateTask task6 = new CreateTask();
            task6.setPosition(
                    new int[][]{
                            {6, 5, 6, 5, 6, 5, 6, 5},
                            {5, 6, 5, 6, 5, 6, 5, 6},
                            {6, 5, 6, 1, 6, 5, 6, 5},
                            {5, 6, 5, 6, 5, 6, 2, 6},
                            {6, 5, 6, 5, 6, 5, 6, 5},
                            {5, 6, 5, 6, 5, 6, 5, 6},
                            {6, 5, 6, 5, 6, 5, 6, 2},
                            {5, 6, 5, 6, 5, 6, 1, 6}
                    });
            List<String> solution6 = new ArrayList<>();
            solution6.add("d6-c7");
            solution6.add("g5-f4");
            solution6.add("c7-b8");
            solution6.add("f4-e3");
            solution6.add("g1-f2");
            solution6.add("e3:g1");
            solution6.add("b8-a7");
            task6.setSolution(solution6);
            task6.setTaskComplexity(TaskComplexity.HARD);
            task6.setTaskResult(TaskResult.WHITES_START_AND_WIN);
            task6.setTaskType(TaskType.UŽDARYMAS);
            taskService.createTask(task6);

            CreateTask task7 = new CreateTask();
            task7.setPosition(
                    new int[][]{
                            {6, 3, 6, 5, 6, 5, 6, 5},
                            {5, 6, 5, 6, 5, 6, 5, 6},
                            {6, 5, 6, 5, 6, 5, 6, 1},
                            {5, 6, 5, 6, 5, 6, 5, 6},
                            {6, 5, 6, 5, 6, 5, 6, 5},
                            {5, 6, 2, 6, 5, 6, 5, 6},
                            {6, 5, 6, 5, 6, 5, 6, 5},
                            {5, 6, 5, 6, 5, 6, 5, 6}
                    });
            List<String> solution7 = new ArrayList<>();
            solution7.add("b8-f4");
            solution7.add("c3-b2");
            solution7.add("f4-e5");
            task7.setSolution(solution7);
            task7.setTaskComplexity(TaskComplexity.MEDIUM);
            task7.setTaskResult(TaskResult.WHITES_START_AND_WIN);
            task7.setTaskType(TaskType.ENDSPILIS);
            taskService.createTask(task7);

            CreateTask task8 = new CreateTask();
            task8.setPosition(
                    new int[][]{
                            {6, 5, 6, 5, 6, 5, 6, 5},
                            {5, 6, 5, 6, 2, 6, 5, 6},
                            {6, 5, 6, 5, 6, 5, 6, 5},
                            {5, 6, 5, 6, 5, 6, 5, 6},
                            {6, 5, 6, 5, 6, 1, 6, 5},
                            {5, 6, 5, 6, 5, 6, 5, 6},
                            {6, 5, 6, 5, 6, 5, 6, 5},
                            {5, 6, 5, 6, 5, 6, 5, 6}
                    });
            List<String> solution8 = new ArrayList<>();
            solution8.add("f4-e5");
            task8.setSolution(solution8);
            task8.setTaskComplexity(TaskComplexity.MEDIUM);
            task8.setTaskResult(TaskResult.WHITES_START_AND_WIN);
            task8.setTaskType(TaskType.OPOZICIJA);
            taskService.createTask(task8);

            CreateTask task9 = new CreateTask();
            task9.setPosition(
                    new int[][]{
                            {6, 2, 6, 3, 6, 5, 6, 5},
                            {5, 6, 5, 6, 5, 6, 5, 6},
                            {6, 5, 6, 5, 6, 5, 6, 5},
                            {3, 6, 5, 6, 5, 6, 5, 6},
                            {6, 5, 6, 5, 6, 5, 6, 5},
                            {5, 6, 5, 6, 5, 6, 5, 6},
                            {6, 5, 6, 5, 6, 5, 6, 3},
                            {2, 6, 5, 6, 5, 6, 5, 6}
                    });
            List<String> solution9 = new ArrayList<>();
            solution9.add("h2-d6");
            task9.setSolution(solution9);
            task9.setTaskComplexity(TaskComplexity.MEDIUM);
            task9.setTaskResult(TaskResult.WHITES_START_AND_WIN);
            task9.setTaskType(TaskType.KILPA);
            taskService.createTask(task9);

            DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date date = format.parse("2020-05-24 12:10:23");
            Date date2 = format.parse("2020-05-24 12:15:23");
            List<Date> dates = new ArrayList<>();
            dates.add(format.parse("2020-05-20 12:10:23"));
            dates.add(format.parse("2020-05-20 12:15:23"));
            dates.add(format.parse("2020-05-20 12:10:23"));
            dates.add(format.parse("2020-05-22 8:10:23"));
            dates.add(format.parse("2020-05-22 8:10:23"));
            dates.add(format.parse("2020-05-20 12:10:23"));
            dates.add(format.parse("2020-05-23 7:10:23"));
            dates.add(format.parse("2020-05-23 7:10:23"));
            dates.add(format.parse("2020-05-23 12:10:23"));
            dates.add(format.parse("2020-05-24 10:10:23"));
            dates.add(format.parse("2020-05-25 12:10:23"));
            dates.add(format.parse("2020-05-25 7:10:23"));
            dates.add(format.parse("2020-05-25 7:10:23"));
            dates.add(format.parse("2020-05-25 12:10:23"));
            dates.add(format.parse("2020-05-25 10:10:23"));

            taskRepository.getAll().forEach((taskToSolve) -> {
                TaskForUser taskForUser = new TaskForUser();
                taskForUser.setUser(userRepository.findByEmail(userMilda.getEmail()));
                taskForUser.setTask(taskToSolve);
                taskForUser.setResolvedDate(dates.get(taskToSolve.getId().intValue()));
                taskForUser.setTaskStatus(TaskStatus.RESOLVED);
                taskForUser.setStart(dates.get(taskToSolve.getId().intValue()-1));
                taskForUser.setNumberOfTries(1);
                taskForUserRepository.createTaskForUser(taskForUser);
            });

            taskRepository.getAll()
                    .stream()
                    .filter(item->item.getId()%2==0)
                    .forEach((taskToSolve) -> {
                TaskForUser taskForUser = new TaskForUser();
                taskForUser.setUser(userRepository.findByEmail(userJuozas.getEmail()));
                taskForUser.setTask(taskToSolve);
                taskForUser.setResolvedDate(dates.get(taskToSolve.getId().intValue()));
                taskForUser.setTaskStatus(TaskStatus.RESOLVED);
                taskForUser.setStart(dates.get(taskToSolve.getId().intValue()-1));
                taskForUser.setNumberOfTries(1);
                taskForUserRepository.createTaskForUser(taskForUser);
            });

            Game game = new Game();
            List<String> moves = new ArrayList<>();
            moves.add("e3-f4");
            moves.add("f6-g5");
            moves.add("c3-b4");
            moves.add("g5:e3");
            moves.add("d2:f4");
            moves.add("e7-f6");
            moves.add("f2-e3");
            moves.add("f6-g5");
            moves.add("c1-d2");
            moves.add("g5-h4");
            moves.add("g1-f2");
            moves.add("d8-e7");
            moves.add("b6-a5");
            game.setMoves(moves);
            game.setStartRecording(dates.get(0));
            game.setEndRecording(dates.get(1));
            game.setBoard(boardRepository.getByCode(boardMilda.getCode()));
            game.setPlayer1(userRepository.findByEmail(userJuozas.getEmail()));
            game.setPlayer2(userRepository.findByEmail(userMilda.getEmail()));
            game.setResult("1-1");
            game.setStatus(RecordingStatus.RECORDED);
            gameRepository.createRecording(game);


            Game game2 = new Game();
            List<String> moves2 = new ArrayList<>();
            moves2.add("c3-d4");
            moves2.add("f6-g5");
            moves2.add("b2-c3");
            moves2.add("g7-f6");
            moves2.add("c3-b4");
            moves2.add("b6-a5");
            moves2.add("d4-c5");
            moves2.add("a5:c3");
            moves2.add("d2:b4");
            moves2.add("g5-h4");
            moves2.add("b4-a5");
            moves2.add("d6:b4");
            moves2.add("a5:c3");
            moves2.add("a7-b6");
            moves2.add("c3-d4");
            moves2.add("b6-a5");
            moves2.add("g3-f4");
            game2.setMoves(moves2);
            game2.setStartRecording(dates.get(3));
            game2.setEndRecording(dates.get(4));
            game2.setBoard(boardRepository.getByCode(boardMilda.getCode()));
            game2.setPlayer1(userRepository.findByEmail(userJuozas.getEmail()));
            game2.setPlayer2(userRepository.findByEmail(userMilda.getEmail()));
            game2.setResult("1-1");
            game2.setStatus(RecordingStatus.RECORDED);
            gameRepository.createRecording(game2);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        /*
        CreateRecording createRecording = new CreateRecording();
        createRecording.setBoardCode("889998");
        createRecording.setPlayer1("naudotojas@gmail.com");
        createRecording.setPlayer2("juozas@gmail.com");
        GameDTO gameDTO = gameServices.createRecording("naudotojas@gmail.com", createRecording);
        UpdateRecording updateRecording = new UpdateRecording();
        updateRecording.setBoard(gameDTO.getBoard().getCode());
        updateRecording.setId(gameDTO.getId());
        updateRecording.setPlayer1(gameDTO.getPlayer1().getEmail());
        updateRecording.setPlayer2(gameDTO.getPlayer2().getEmail());
        updateRecording.setResult("1-1");
        List<String> moves = new ArrayList<>();
        moves.add("e3-f4");
        moves.add("f6-g5");
        moves.add("c3-b4");
        moves.add("g5:e3");
        moves.add("d2:f4");
        moves.add("e7-f6");
        moves.add("f2-e3");
        moves.add("f6-g5");
        moves.add("c1-d2");
        moves.add("g5-h4");
        moves.add("g1-f2");
        moves.add("d8-e7");
        moves.add("b6-a5");
        updateRecording.setMoves(moves);
        gameServices.update(updateRecording);
*/
    }
}