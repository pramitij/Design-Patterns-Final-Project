package com.inventory.command;

import java.io.IOException;

import com.inventory.api.CommandAPI;


public class Client implements Runnable, CommandAPI{

	static String msg;
	
	public Client() {
	}

	public Client(String msg) {
		super();
		Client.msg = msg;
	}

	@Override
	public void run() {
		try {
			new ServerClient().Client(Client.msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void execute() {
		Thread t = new Thread(new Client(Client.msg));
		t.start();
		
	}

}
