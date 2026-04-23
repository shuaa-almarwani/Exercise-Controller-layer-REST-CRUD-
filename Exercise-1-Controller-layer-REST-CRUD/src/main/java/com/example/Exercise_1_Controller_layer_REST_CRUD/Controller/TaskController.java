package com.example.Exercise_1_Controller_layer_REST_CRUD.Controller;


import com.example.Exercise_1_Controller_layer_REST_CRUD.ApiResponse.ApiResponse;
import com.example.Exercise_1_Controller_layer_REST_CRUD.Model.Task;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/task")
public class TaskController {


    ArrayList<Task> tasks = new ArrayList<>();

    @GetMapping("/get")
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    @GetMapping("/get/{title}")
    public Task searchTaskByTilte(@PathVariable String title) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getTitle().equalsIgnoreCase(title)) {
                return tasks.get(i);
            }
        }
        return null;
    }

    @PostMapping("/add")
    public ApiResponse addTask(@RequestBody Task task) {
        tasks.add(task);
        return new ApiResponse(task.getTitle() + " Task added successfully ");

    }


    @PatchMapping("/update/{id}/status")
    public Task changeTaskStatus(@PathVariable int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                task.setStatus(!task.isStatus());
                return task;
            }
        }
        return null;
    }
    @PutMapping("/update/{id}")
    public ApiResponse updateTask(@PathVariable int id, @RequestBody Task task) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId() == id) {
                tasks.set(i, task);
                return new ApiResponse("Task updated successfully");
            }
        }
        return new ApiResponse("Task not found");
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse updateTask(@PathVariable int id) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId() == id) {
                tasks.remove(i);
                return new ApiResponse("Task deleted successfully");
            }
        }
        return new ApiResponse("Task not found");
    }
}
