package com.main.restfulhomework;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class HomeworkController {

    private Homework homeworkA2 = loadJSON("homeworkA2.json");
    private Homework homeworkB1 = loadJSON("homeworkB1.json");

    @RequestMapping("/gethw")
    public Homework getHomework(@RequestParam(value = "grp") String group) {
        if (group.equals("A2")) {
            homeworkA2.getCount++;
            saveJSON(homeworkA2, "homeworkA2.json");
            return homeworkA2;
        } else {
            homeworkB1.getCount++;
            saveJSON(homeworkB1, "homeworkB1.json");
            return homeworkB1;
        }
    }

    @RequestMapping("/sethw")
    public String setHomework(@RequestParam(value = "grp") String group, @RequestParam(value = "txt") String text) {
        if (group.equals("A2")) {
            homeworkA2.date = new SimpleDateFormat("dd.MM.yyyy HH:mm").format(new Date());
            homeworkA2.task = text;
            homeworkA2.setCount++;
            saveJSON(homeworkA2, "homeworkA2.json");
            return ("Для группы: " + group + " успешно добавлено задание: \"" + text + "\" в " + homeworkA2.date + ".");
        } else {
            homeworkB1.date = new SimpleDateFormat("dd.MM.yyyy HH:mm").format(new Date());
            homeworkB1.task = text;
            homeworkB1.setCount++;
            saveJSON(homeworkB1, "homeworkB1.json");
            return ("Для группы: " + group + " успешно добавлено задание: \"" + text + "\" в " + homeworkB1.date + ".");
        }
    }

    private Homework loadJSON(String filepath) {
        Homework homework;
        try {
            String jsonString = new String(Files.readAllBytes(Paths.get(filepath)));
            JSONObject jsonObject = new JSONObject(jsonString);
            String date = jsonObject.getString("date");
            String task = jsonObject.getString("task");
            int getCount = jsonObject.getInt("getCount");
            int setCount = jsonObject.getInt("setCount");
            homework = new Homework(date, task, getCount, setCount);
        } catch (IOException | JSONException e) {
            homework = new Homework("", "", 0, 0);
        }
        return homework;
    }

    private void saveJSON(Homework homework, String filepath) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("date", homework.date);
            jsonObject.put("task", homework.task);
            jsonObject.put("getCount", homework.getCount);
            jsonObject.put("setCount", homework.setCount);
            FileWriter fileWriter = new FileWriter(filepath);
            fileWriter.write(jsonObject.toString());
            fileWriter.flush();
            fileWriter.close();
        } catch (JSONException | IOException ignored) {
        }
    }
}