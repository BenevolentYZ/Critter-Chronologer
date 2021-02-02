package com.udacity.jdnd.course3.critter.controller;

import com.udacity.jdnd.course3.critter.data.ScheduleDTO;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    @Autowired
    ScheduleService scheduleService;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        //throw new UnsupportedOperationException();
        return scheduleService.save(scheduleDTO);
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        //throw new UnsupportedOperationException();
        return scheduleService.getAllSchedules();
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        //throw new UnsupportedOperationException();
        return scheduleService.getScheduleForPet(petId);
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        //throw new UnsupportedOperationException();
        return scheduleService.getScheduleForEmployee(employeeId);
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        //throw new UnsupportedOperationException();
        return scheduleService.getScheduleForCustomer(customerId);
    }
}
