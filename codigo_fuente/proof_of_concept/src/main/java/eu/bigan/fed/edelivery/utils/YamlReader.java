package eu.bigan.fed.edelivery.utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.yaml.snakeyaml.Yaml;

/**
 * Texto sobre lo que hace esta clase.
 * @author jorgebernalromero
 *
 */
public class YamlReader {
	
	public List<String[]> read() {

		  final Logger logger = LogManager.getLogger(YamlReader.class);

		  List<String[]> processList = new ArrayList<>();
		  String file_yaml_path = EnvParameters.getParameter("blueNodeYamlSource");
		  File yamlFile = new File(file_yaml_path);

		  try (FileReader reader = new FileReader(yamlFile)) {
		    Yaml yaml = new Yaml();
		    Object data = yaml.load(reader);

		    if (data instanceof java.util.Map) {
		      java.util.Map<String, Object> map = (java.util.Map<String, Object>) data;

		      List<Object> tasks = (List<Object>) map.get("tasks");

		      if (tasks != null) {
		        for (Object taskObject : tasks) {
		          if (taskObject instanceof java.util.Map) {
		            java.util.Map<String, Object> taskMap = (java.util.Map<String, Object>) taskObject;

		            String actionName = (String) taskMap.get("actionName");
		            String workerName = (String) taskMap.get("workerName");
		            String taskContent = (String) taskMap.get("taskContent");
		            String workerTask = (String) taskMap.get("worker_task");
		            String callback = (String) taskMap.get("callback");
		            String timeout = (String) taskMap.get("timeout");

		            String[] process = new String[]{workerName, actionName, taskContent, workerTask, callback, timeout};
		            processList.add(process);
		          }
		        }
		      } else {
		        logger.warn("No 'tasks' key found in the YAML file.");
		      }
		    } else {
		      logger.warn("Unexpected root object type in YAML file. Expected Map.");
		    }

		    logger.info("Lectura del yaml correcta.");

		  } catch (IOException e) {
		    logger.error("Lectura del yaml incorrecta.");
		    e.printStackTrace();
		  }

		  return processList;
		}
}
