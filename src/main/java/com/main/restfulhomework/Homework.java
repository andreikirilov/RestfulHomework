package com.main.restfulhomework;

@SuppressWarnings("unused")
class Homework {

    String date;
    String task;
    int getCount;
    int setCount;

    Homework(String date, String task, int getCount, int setCount) {
        this.date = date;
        this.task = task;
        this.getCount = getCount;
        this.setCount = setCount;
    }

    public String getDate() {
        return date;
    }

    public String getTask() {
        return task;
    }

    public int getRqCount() {
        return getCount;
    }

    public int getRsCount() {
        return setCount;
    }
}