package com.intellisolve.intellisolveplugin.Util;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.intellisolve.intellisolveplugin.Model.CodeAnalysisResults;
import com.intellisolve.intellisolveplugin.Model.Task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

public class IntelliSolveServerConnection {
    private static IntelliSolveServerConnection instance;
    private final String address = "http://localhost:8080";

    private IntelliSolveServerConnection(){

    }
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

    public CodeAnalysisResults runTaskSolution(String taskId, String code) throws IOException {
        URL url = new URL(address + "/task/run/" + taskId);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-type", "text/plain");

        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(code.getBytes(StandardCharsets.UTF_8));
        outputStream.close();

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while((inputLine = in.readLine()) != null){
            content.append(inputLine);
        }
        in.close();
        JsonMapper jsonMapper = JsonMapper.builder().build();
        return jsonMapper.readValue(content.toString(), CodeAnalysisResults.class);
    }

    public static IntelliSolveServerConnection getInstance(){
        if (instance == null){
            instance = new IntelliSolveServerConnection();
        }
        return instance;
    }
}
