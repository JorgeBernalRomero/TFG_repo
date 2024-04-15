package eu.bigan.fed.edelivery.ops;

import javax.jms.MapMessage;

import eu.bigan.fed.edelivery.message.BiganFedListener;

public class SaveToFiles implements BiganFedListener{

  private MapMessage messageMap;
  private String destinationNode;

  // Public no-argument constructor
  public SaveToFiles() {
      // Optional: Initialize any default values for properties here (if needed)
  }

  public SaveToFiles(MapMessage messageMap, String destinationNode) {
      this.messageMap = messageMap;
      this.destinationNode = destinationNode;
  }

  @Override
  public void handleCallback(MapMessage messageMap) {
      // Implement code for handling the message here
      System.out.println("estoy dentro del handling callback");
  }

}
