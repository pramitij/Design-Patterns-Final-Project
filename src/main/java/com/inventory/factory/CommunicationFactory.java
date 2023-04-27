package com.inventory.factory;

import com.inventory.api.AbstractFactoryAPI;
import com.inventory.command.Communication;

public class CommunicationFactory extends AbstractFactoryAPI{

	private static Communication comm;
	@Override
	public Communication getObject() {
		
		if(comm == null) {
			comm = new 	Communication();
		}
		return comm;
	}

}
