package se.ecutb.fullstacktodo.dto;

import se.ecutb.fullstacktodo.entity.AppUser;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

public class CreateTodoForm {

    private int itemId;
    private AppUser username;
    @NotBlank(message = "Title is required")
    private String itemTitle;
    @NotBlank(message = "Description must not be null")
    private String itemDescription;
    @Future(message = "Deadline need to be in the future")
    private LocalDate deadline;
    private boolean doneStatus;
    @DecimalMin(value = "1", message = "minimum value is 1")
    private double reward;

    public CreateTodoForm(int itemId,  AppUser username, String itemTitle,  String itemDescription,LocalDate deadline, boolean doneStatus, double reward) {
        this.itemId = itemId;
        this.username = username;
        this.itemTitle = itemTitle;
        this.itemDescription = itemDescription;
        this.deadline = deadline;
        this.doneStatus = doneStatus;
        this.reward = reward;
    }

    public CreateTodoForm() {
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public AppUser getUsername() {
        return username;
    }

    public void setUsername(AppUser username) {
        this.username = username;
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
}