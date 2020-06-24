package se.ecutb.fullstacktodo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import se.ecutb.fullstacktodo.dto.CreateTodoForm;
import se.ecutb.fullstacktodo.entity.AppUser;
import se.ecutb.fullstacktodo.entity.TodoItem;
import se.ecutb.fullstacktodo.service.AppUserService;
import se.ecutb.fullstacktodo.service.TodoService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class TodoController{
    private TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping("/users/items/create")
    public String createItem(@Valid @ModelAttribute("item") CreateTodoForm createTodoForm, BindingResult bindingResult, @AuthenticationPrincipal UserDetails principal){

        if(bindingResult.hasErrors()){
            return "item-create";
        }
        if(principal.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ADMIN"))) {
            TodoItem newTodo = new TodoItem(createTodoForm.getItemTitle(), createTodoForm.getItemDescription(), createTodoForm.getDeadline(), createTodoForm.isDoneStatus(), createTodoForm.getReward());
            TodoItem todoItem = todoService.create(newTodo,principal.getUsername());

            return "redirect:/users/items?type=id&value="+todoItem.getItemId();       //return "redirect:/users/items?type=id&value=" + todoItem.getItemId();
        }
        return "item-create";
    }

    @GetMapping("users/items")
    public String findItem(@RequestParam (name="type", defaultValue="all") String type, @RequestParam(name="value", defaultValue = "") String value, Model model){
        if(type.equals("all")){
            model.addAttribute("itemList", todoService.findAll());
            return "item-info";
        }
        else if(type.equals("id")){
            int id=Integer.parseInt(value);
            TodoItem todoItem = todoService.findByItemId(id);
            model.addAttribute("items", todoItem);
            return "items";
        }
        else{
            throw new IllegalArgumentException("Todo not found");
        }
    }

    @GetMapping("/users/items/create")
    public String getForm(Model model) {
        model.addAttribute("item", new CreateTodoForm());
        return "item-create";
    }

    @GetMapping("/users/items/details")
    public String getItems(@ModelAttribute("itemList") Model model){

        List<TodoItem> itemList = todoService.findAll();
        if (itemList.get(0).getItemId() != 0){
            throw new IllegalArgumentException("Not found");
        }
        model.addAttribute("itemList", itemList);
        return "item-info";
    }

    @GetMapping("/users/items/{id}/update")
    public String getUpdate(@PathVariable("id") int id,Model model){
        TodoItem todo1 = todoService.findByItemId(id);
        CreateTodoForm todoItemForm= new CreateTodoForm(todo1.getItemId(), todo1.getUsername(), todo1.getItemTitle(),todo1.getItemDescription(),todo1.getDeadline(),todo1.isDoneStatus(),todo1.getReward());
        model.addAttribute("form",todoItemForm);
        return "item-update";

    }
    @PostMapping("/users/items/{id}/update")
    public String update(@PathVariable("id") int id,@ModelAttribute("form") CreateTodoForm item){
        todoService.updateItem(item);
        return "redirect:/users/items";
    }
}