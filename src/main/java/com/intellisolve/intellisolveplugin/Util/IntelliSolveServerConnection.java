package com.intellisolve.intellisolveplugin.Util;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.intellisolve.intellisolveplugin.Model.Task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class IntelliSolveServerConnection {
    private final String address = "http://localhost:8080";

    public List<Task> getAllTasks() throws IOException {
        URL url = new URL(address + "/task/get/all");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while((inputLine = in.readLine()) != null){
            content.append(inputLine);
        }
        in.close();
        JsonMapper jsonMapper = JsonMapper.builder().build();
        return Arrays.asList(jsonMapper.readValue(content.toString(), Task[].class));
    }
}
