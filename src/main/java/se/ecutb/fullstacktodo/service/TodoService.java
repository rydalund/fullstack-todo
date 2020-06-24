package se.ecutb.fullstacktodo.service;

import org.springframework.transaction.annotation.Transactional;
import se.ecutb.fullstacktodo.dto.CreateTodoForm;
import se.ecutb.fullstacktodo.entity.TodoItem;

import java.util.List;
import java.util.Optional;

public interface TodoService {
    List<TodoItem> findAll();
    List<TodoItem> findAllUnAssigned();
    TodoItem findByItemId(int itemId);
    @Transactional(rollbackFor = RuntimeException.class)
    TodoItem create(TodoItem item, String username);
    TodoItem save(TodoItem todoItem);
    boolean delete(int itemId);
    TodoItem findByTitle(String title);
    TodoItem updateItem(CreateTodoForm createTodoForm);
}