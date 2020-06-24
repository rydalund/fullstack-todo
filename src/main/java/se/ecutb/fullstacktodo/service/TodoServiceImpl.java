package se.ecutb.fullstacktodo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.ecutb.fullstacktodo.data.AppUserRepository;
import se.ecutb.fullstacktodo.data.TodoItemRepository;
import se.ecutb.fullstacktodo.dto.CreateTodoForm;
import se.ecutb.fullstacktodo.entity.AppUser;
import se.ecutb.fullstacktodo.entity.TodoItem;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TodoServiceImpl implements TodoService{
    private AppUserRepository appUserRepository;
    private TodoItemRepository todoItemRepository;

    @Autowired
    public TodoServiceImpl(AppUserRepository appUserRepository, TodoItemRepository todoItemRepository) {
        this.appUserRepository = appUserRepository;
        this.todoItemRepository = todoItemRepository;
    }

    @Override
    public List<TodoItem> findAll(){
        return todoItemRepository.findAll();
    }

    @Override
    public List<TodoItem> findAllUnAssigned(){
        return todoItemRepository.findAll()
                .stream()
                .filter(todoItem -> todoItem.getUsername().equals(null))
                .collect(Collectors.toList());
    }

    @Override
    public TodoItem findByItemId(int itemId){
        return todoItemRepository.findByItemId(itemId);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public TodoItem create(TodoItem item, String username){
        AppUser user = appUserRepository.findByUsername(username).get();
        TodoItem todoItem = new TodoItem(item.getItemTitle(),item.getItemDescription(),item.getDeadline(),item.isDoneStatus(),item.getReward());
        todoItem.setUsername(user);
        return todoItemRepository.save(todoItem);
    }

    @Override
    public TodoItem save(TodoItem todoItem){
        if(todoItem.getItemId() == 0){
            throw new IllegalArgumentException("Todo item is not yet persisted");
        }
        return todoItemRepository.save(todoItem);
    }

    @Override
    public boolean delete(int itemId){
        todoItemRepository.deleteById(itemId);
        return todoItemRepository.existsById(itemId);
    }

    @Override
    public TodoItem findByTitle(String title){
        if(title == null){
            throw new IllegalArgumentException("Title not found");
        }
        Optional<TodoItem> todoItem = todoItemRepository.findByItemTitle(title);
        return todoItem.get();
    }

    @Override
    public TodoItem updateItem(CreateTodoForm createTodoForm){
        if(createTodoForm.getItemId() == 0){
            throw new IllegalArgumentException("Todo item not found");
        }
        TodoItem newItem = findByItemId(createTodoForm.getItemId());
        newItem.setUsername(createTodoForm.getUsername());
        newItem.setItemDescription(createTodoForm.getItemDescription());
        newItem.setDeadline(createTodoForm.getDeadline());
        newItem.setDoneStatus(createTodoForm.isDoneStatus());
        newItem.setReward(createTodoForm.getReward());

        return todoItemRepository.save(newItem);
    }
}