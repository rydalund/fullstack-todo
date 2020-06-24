package se.ecutb.fullstacktodo.data;

import org.springframework.data.repository.CrudRepository;
import se.ecutb.fullstacktodo.entity.TodoItem;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TodoItemRepository extends CrudRepository<TodoItem, Integer> {
    List<TodoItem> findAll();
    TodoItem findByItemId(int itemId);
    Optional<TodoItem> findByItemTitle(String itemTitle);
    List<TodoItem> findByDeadline(LocalDate deadline);
    List<TodoItem> findByDoneStatus(boolean status);
}

