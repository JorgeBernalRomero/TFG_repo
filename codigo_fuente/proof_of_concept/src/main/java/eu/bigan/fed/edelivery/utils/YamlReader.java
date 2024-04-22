package eu.bigan.fed.edelivery.utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.yaml.snakeyaml.Yaml;


public class YamlReader {

    public List<String[]> yamlReading() {
        List<String[]> processList = new ArrayList<>();

        String file_yaml_path = EnvParameters.getParameter("blueNodeYamlSource");
        File yamlFile = new File(file_yaml_path);

        try (FileReader reader = new FileReader(yamlFile)) {
            Yaml yaml = new Yaml();
            Object data = yaml.load(reader);

            if (data instanceof java.util.Map) {
                java.util.Map<String, Object> map = (java.util.Map<String, Object>) data;
                java.util.List<Object> workers = (java.util.List<Object>) map.get("workers");

                for (Object worker : workers) {
                    if (worker instanceof java.util.Map) {
                        java.util.Map<String, Object> workerMap = (java.util.Map<String, Object>) worker;
                        String workerName = (String) workerMap.get("name");
                        java.util.List<Object> actions = (java.util.List<Object>) workerMap.get("actions");

                        for (Object action : actions) {
                            if (action instanceof java.util.Map) {
                                java.util.Map<String, Object> actionMap = (java.util.Map<String, Object>) action;
                                String actionName = (String) actionMap.get("name");
                                String taskContent = (String) actionMap.get("taskContent");
                                String workerTask = (String) actionMap.get("worker_task");
                                String callback = (String) actionMap.get("callback");

                                // Create a String array for each process and add to processList
                                String[] process = new String[]{workerName, actionName, taskContent, workerTask, callback};
                                processList.add(process);
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading YAML file: " + e.getMessage());
        }

        return processList;
    }
}