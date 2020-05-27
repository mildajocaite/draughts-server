package lt.draughts.controllers;

import lt.draughts.services.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZoneId;
import java.util.Date;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class StatisticController {

    @Autowired
    StatisticService statisticService;

    @GetMapping("/studentStatistics/{email}")
    @PreAuthorize("hasRole('COACH')")
    public ResponseEntity<?> getStudentsStatistics(@PathVariable String email) {
        return ResponseEntity
                .ok()
                .body(statisticService.getStudentsStatistics(email));
    }

    @GetMapping("/studentOwnStatistics")
    public ResponseEntity<?> getStudentsOwnStatistics() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity
                .ok()
                .body(statisticService.getStudentsStatistics(email));
    }

    @GetMapping("/numberOfResolvedTasksPerDay/{start}/{finish}/{email}")
    @PreAuthorize("hasRole('COACH')")
    public ResponseEntity<?> numberOfResolvedTasksPerDay(@PathVariable Date start, @PathVariable Date finish, @PathVariable String email) {
        return ResponseEntity
                .ok()
                .body(statisticService.getNumberOfResolvedTasksPerDay(email,
                        start.toInstant()
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate(),
                        finish.toInstant()
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate()));
    }

    @GetMapping("/numberOfResolvedTasksPerDayForStudent/{start}/{finish}")
    public ResponseEntity<?> numberOfResolvedTasksPerDayForStudent(@PathVariable Date start, @PathVariable Date finish) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity
                .ok()
                .body(statisticService.getNumberOfResolvedTasksPerDay(email,
                        start.toInstant()
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate(),
                        finish.toInstant()
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate()));
    }

    @GetMapping("/usersOrderedByResolvedTasks/{startDate}/{endDate}")
    public ResponseEntity<?> usersOrderedByResolvedTasks(@PathVariable Date startDate, @PathVariable Date endDate) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity
                .ok()
                .body(statisticService.getUsersByResolvedTasks(startDate, endDate, email));
    }

    @GetMapping("/top10MostPopularTasks")
    public ResponseEntity<?> top10MostPopularTasks() {
        return ResponseEntity
                .ok()
                .body(statisticService.getTop10MostPopularTasks());
    }
}