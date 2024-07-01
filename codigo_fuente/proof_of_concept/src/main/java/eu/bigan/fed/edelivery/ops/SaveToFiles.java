package eu.bigan.fed.edelivery.ops;

import java.io.File;
import javax.jms.MapMessage;
import eu.bigan.fed.edelivery.message.BiganFedListener;
import eu.bigan.fed.edelivery.message.MessageMetadata;
import eu.bigan.fed.edelivery.utils.EnvParameters;
import eu.bigan.fed.edelivery.utils.FileManager;
import eu.bigan.fed.edelivery.utils.ReaderJsonContent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Texto sobre lo que hace esta clase.
 * @author jorgebernalromero
 *
 */
public class SaveToFiles implements BiganFedListener{

  @Override
  /**
   * 
   */
  public void handleCallback(MapMessage messageMap, MessageMetadata message) {
	  
	  final Logger logger = LogManager.getLogger(SaveToFiles.class);
	 
	  logger.info("Estoy dentro de la función de callback asignada 'saveToFiles'");
      
      try {
	      MapMessage m = (MapMessage) messageMap;
	      if (messageMap instanceof MapMessage) {
	
	          String fromNodeID = m.getStringProperty("fromPartyId");

	          String messageId = m.getStringProperty("conversationId");
	
			  String blueDirRecFromGreen=EnvParameters.getParameter("blueDirRecFromGreen");
			  String blueDirRecFromPink=EnvParameters.getParameter("blueDirRecFromPink");
			  
			  String saveDirectory = "";
	
	          switch(fromNodeID) {
	              case "domibus-green":
	                  saveDirectory = blueDirRecFromGreen;
	                  break;
	              case "domibus-pink":
	                  saveDirectory = blueDirRecFromPink;    
	                  break;
	          }
	          
	          saveDirectory = saveDirectory + "/" + messageId;
	
	          File saveDir = new File(saveDirectory);
	          if (!saveDir.exists()) {
	              saveDir.mkdirs();
	          }
	          
	          File destinationFile = new File(saveDir, "payload_1");
	          FileManager.writeBytesToFile(destinationFile, m.getBytes("payload_1"));

	          logger.info("Archivo 'payload_1' guardado correctamente.");
	          
	          ReaderJsonContent reader = new ReaderJsonContent();
              reader.readJsonContentFromFile(destinationFile.getAbsolutePath());

              String results = reader.getResults();
              String outputCode = reader.getOutputCode();
              int outputCodeAsInt = Integer.parseInt(outputCode);
              
              if (outputCodeAsInt == 0) {
                  String filePath = saveDir + "/" + "outputs.txt";
                  byte[] contentBytes = results.getBytes();
                  File file = new File(filePath);
                  try{
                      FileManager.writeBytesToFile(file, contentBytes);
                  } catch (Exception e) {
                      e.printStackTrace();
                  }
                  logger.info("Se ha guardado el contenido del campo 'results' del 'payload_1' en el archivo 'outputs.txt'.");
                  message.setStatus(2);
              }   
              else {
            	  logger.error("Ha habido un problema con la tarea" + messageId + "en el nodo worker. El campo 'results' del 'payload_1' está vacío.");
              }
	          
       		}
	
	      else{
	    	  logger.error("No se ha conseguido guardar el archivo.");
	      }
      } catch (Exception e) {
    	  logger.error("Ha habido un error en la gestión del archivo.");
          e.printStackTrace();
      }
   }
}
