package se.ecutb.fullstacktodo.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class TodoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int itemId;
    @Column(unique = true)
    private String itemTitle;
    private String itemDescription;
    private LocalDate deadline;
    private boolean doneStatus;
    private double reward;

    @ManyToOne(fetch = FetchType.LAZY)
    private AppUser username;

    public TodoItem(String itemTitle, String itemDescription, LocalDate deadline, boolean doneStatus, double reward) {
        this.itemTitle = itemTitle;
        this.itemDescription = itemDescription;
        this.deadline = deadline;
        this.doneStatus = doneStatus;
        this.reward = reward;
    }

    public TodoItem() {}

    public int getItemId() {
        return itemId;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public boolean isDoneStatus() {
        return doneStatus;
    }

    public void setDoneStatus(boolean doneStatus) {
        this.doneStatus = doneStatus;
    }

    public double getReward() {
        return reward;
    }

    public void setReward(double reward) {
        this.reward = reward;
    }

    public AppUser getUsername() {
        return username;
    }

    public void setUsername(AppUser userName) {
        this.username = userName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TodoItem todoItem = (TodoItem) o;
        return itemId == todoItem.itemId &&
                doneStatus == todoItem.doneStatus &&
                Double.compare(todoItem.reward, reward) == 0 &&
                Objects.equals(itemTitle, todoItem.itemTitle) &&
                Objects.equals(itemDescription, todoItem.itemDescription) &&
                Objects.equals(deadline, todoItem.deadline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, itemTitle, itemDescription, deadline, doneStatus, reward);
    }

    @Override
    public String toString() {
        return "TodoItem{" +
                "itemId=" + itemId +
                ", itemTitle='" + itemTitle + '\'' +
                ", itemDescription='" + itemDescription + '\'' +
                ", deadline=" + deadline +
                ", doneStatus=" + doneStatus +
                ", reward=" + reward +
                '}';
    }
}
