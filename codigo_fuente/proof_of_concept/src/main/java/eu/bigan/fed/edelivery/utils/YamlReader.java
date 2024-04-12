package eu.bigan.fed.edelivery.utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.yaml.snakeyaml.Yaml;

public class YamlReader {
	
	public void yamlReading() {
		
		String file_yaml_path = EnvParameters.getParameter("blueNodeYamlSource");
		
		File yamlFile = new File(file_yaml_path);

	    try (FileReader reader = new FileReader(yamlFile)) {
	      Yaml yaml = new Yaml();
	      Object data = yaml.load(reader);

	      // Print the complete data structure (for debugging purposes)
	      // System.out.println(data);

	      // Accessing specific elements
	      if (data instanceof java.util.Map) {
	        java.util.Map<String, Object> map = (java.util.Map<String, Object>) data;
	        
	        double version = (double) map.get("version"); // Cast to double
	        System.out.println("Version: " + version);


	        java.util.List<Object> workers = (java.util.List<Object>) map.get("workers");
	        for (Object worker : workers) {
	          if (worker instanceof java.util.Map) {
	            java.util.Map<String, Object> workerMap = (java.util.Map<String, Object>) worker;
	            String name = (String) workerMap.get("name");
	            System.out.println("Worker Name: " + name);

	            java.util.List<Object> actions = (java.util.List<Object>) workerMap.get("actions");
	            for (Object action : actions) {
	              if (action instanceof java.util.Map) {
	                java.util.Map<String, Object> actionMap = (java.util.Map<String, Object>) action;
	                String actionName = (String) actionMap.get("name");
	                String script = (String) actionMap.get("script");
	                String callback = (String) actionMap.get("callback");
	                System.out.println("\tAction Name: " + actionName);
	                System.out.println("\tScript: " + script);
	                System.out.println("\tCallback: " + callback);
	              }
	            }
	          }
	        }
	      }

	    } catch (IOException e) {
	      System.out.println("Error reading YAML file: " + e.getMessage());
	    }
	}

}
