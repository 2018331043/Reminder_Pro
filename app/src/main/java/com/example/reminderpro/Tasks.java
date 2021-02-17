package com.example.reminderpro;

public class Tasks {
    private String actualTask;
    private Boolean done;

    public Tasks(String actualTask, Boolean done) {
        this.actualTask = actualTask;
        this.done = done;
    }

    public String getActualTask() {
        return actualTask;
    }

    public void setActualTask(String actualTask) {
        this.actualTask = actualTask;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }
}
