package eu.bigan.fed.edelivery.ops;

import java.io.File;
import javax.jms.MapMessage;
import eu.bigan.fed.edelivery.message.BiganFedListener;
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
  public void handleCallback(MapMessage messageMap) {
	  
	  final Logger logger = LogManager.getLogger(SaveToFiles.class);
	 
      //System.out.println("estoy dentro del handling callback");
	  logger.info("Estoy dentro de la función de callback asignada 'saveToFiles'");
      
      try {
	      MapMessage m = (MapMessage) messageMap;
	      if (messageMap instanceof MapMessage) {
	          //System.out.println(messageMap.getJMSMessageID());
	          //System.out.println(m.getBytes("payload_1"));
	          //System.out.println(new String(m.getBytes("payload_1"),"UTF-8"));
	
	          String fromNodeID = m.getStringProperty("fromPartyId");
	          //System.out.println(fromNodeID);
	          
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
	          //System.out.println("File saved to: " + destinationFile.getAbsolutePath());
	          logger.info("Archivo guardado correctamente.");
	          
	          
	          //ahora mismo tengo en payload_1 el archivo .json que he recibido
	          //tengo que leer el .json, extraer los dos campos y guardar en un results.txt el contenido de la variable results, fijarme tb en outputCode para ver qué hacer
	          
	          /*ReaderJsonContent reader = new ReaderJsonContent();
              reader.readJsonContentFromFile(destinationFile.getAbsolutePath());

              String results = reader.getResults();
              String outputCode = reader.getOutputCode();*/
	          
       		}
	
	      else{
	          //System.out.println("No Message Found!");
	    	  logger.error("No se ha conseguido guardar el archivo.");
	      }
      } catch (Exception e) {
    	  logger.error("Ha habido un error en la gestión del archivo.");
          e.printStackTrace();
      }
   }
  
}