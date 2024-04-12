package eu.bigan.fed.edelivery.ops;

import javax.jms.MapMessage;

import eu.bigan.fed.edelivery.message.BiganFedListener;

public class SaveToFiles implements BiganFedListener{
	
	private MapMessage messageMap;
	private String destinationNode;
	
	public SaveToFiles(MapMessage messageMap, String destinationNode) {
		this.messageMap = messageMap;
        this.destinationNode = destinationNode;
	}

	@Override
	public void onMessage(MapMessage messageMap) {
		//implementar aquí el código
	}
	
}