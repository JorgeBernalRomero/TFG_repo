package eu.bigan.fed.edelivery.utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.yaml.snakeyaml.Yaml;

import eu.bigan.fed.poc.Main;

/**
 * Texto sobre lo que hace esta clase.
 * @author jorgebernalromero
 *
 */
public class YamlReader {

	
    /*public List<String[]> read() {
    	
    	final Logger logger = LogManager.getLogger(YamlReader.class);
    	
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
                                String timeout =(String) actionMap.get("timeout");

                                String[] process = new String[]{workerName, actionName, taskContent, workerTask, callback, timeout};
                                processList.add(process);
                            }
                        }
                    }
                }
            }
            
            logger.info("Lectura del yaml correcta.");
            
        } catch (IOException e) {
        	logger.error("Lectura del yaml incorrecta.");
        	
        	e.printStackTrace();
        }

        return processList;
    }*/
	
	   /**
	   * 
	   * @return
	   */
	   public List<String[]> read() {

	      final Logger logger = LogManager.getLogger(YamlReader.class);

	       List<String[]> processList = new ArrayList<>();
	        List<String[]> bashTaskList = new ArrayList<>(); // Lista temporal para tareas "ExeScriptBash"

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
	                               String timeout =(String) actionMap.get("timeout");

	                               String[] process = new String[]{workerName, actionName, taskContent, workerTask, callback, timeout};

                                    if (workerTask.equals("eu.bigan.fed.edelivery.ops.ExeScriptBash")) {
                                        bashTaskList.add(process);
                                    }
                                    else {
                                        processList.add(process);
                                    }
	                            }
	                       }
	                   }
	               }
	           }

	           logger.info("Lectura del yaml correcta.");

	       } catch (IOException e) {
	          logger.error("Lectura del yaml incorrecta.");

	          e.printStackTrace();
	       }

	        processList.addAll(bashTaskList);

	       return processList;
	   }
}




