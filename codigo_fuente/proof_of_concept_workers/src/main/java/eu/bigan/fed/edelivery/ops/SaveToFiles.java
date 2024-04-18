package eu.bigan.fed.edelivery.ops;

import java.io.File;

import javax.jms.MapMessage;
import eu.bigan.fed.edelivery.message.BiganFedListener;
import eu.bigan.fed.edelivery.utils.EnvParameters;
import eu.bigan.fed.edelivery.utils.FileManager;

public class SaveToFiles implements BiganFedListener{

  private MapMessage messageMap;
  private String destNode;

  // Public no-argument constructor
  public SaveToFiles() {
      // Optional: Initialize any default values for properties here (if needed)
  }

  public SaveToFiles(MapMessage messageMap, String destNode) {
      this.messageMap = messageMap;
      this.destNode = destNode;
  }

  @Override
  public void handleCallback(MapMessage messageMap) {
      // Implement code for handling the message here
      System.out.println("estoy dentro del handling callback");
      
      try {
	      MapMessage m = (MapMessage) messageMap;
	      if (messageMap instanceof MapMessage) {
	          System.out.println(messageMap.getJMSMessageID());
	          System.out.println(m.getBytes("payload_1"));
	          System.out.println(new String(m.getBytes("payload_1"),"UTF-8"));
	
	          String fromNodeID = m.getStringProperty("fromPartyId");
	          System.out.println(fromNodeID);
	          
	          String messageId = m.getStringProperty("conversationId");
	
			  String saveDirectory = "";
			  
	
	          switch(fromNodeID) {
	              case "domibus-green":
	                  saveDirectory = "/Users/jorgebernalromero/Documents/INGENIERIA_INFORMATICA/Cuarto_uni/Segundo_cuatri/TFG/communication_files/blueNode/receivedResults/greenResults";
	                  break;
	              case "domibus-pink":
	                  saveDirectory = "/Users/jorgebernalromero/Documents/INGENIERIA_INFORMATICA/Cuarto_uni/Segundo_cuatri/TFG/communication_files/blueNode/receivedResults/pinkResults";    
	                  break;
	          }
	          
	          saveDirectory = saveDirectory + "/" + messageId;
	
	          File saveDir = new File(saveDirectory);
	          if (!saveDir.exists()) {
	              saveDir.mkdirs();
	          }
	          File destinationFile = new File(saveDir, "payload_1");
	          FileManager.writeBytesToFile(destinationFile, m.getBytes("payload_1"));
	          System.out.println("File saved to: " + destinationFile.getAbsolutePath());
	       }
	
	      else{
	          String payload = "No Message Found!";
	          System.out.println(payload);
	      }
      } catch (Exception e) {
          e.printStackTrace();
      }
   }
}