package com.magnabyte.pedidonotifmail.controller;

import java.io.IOException;

import com.magnabyte.pedidonotifmail.service.GenericSendService;
import com.magnabyte.pedidonotifmail.service.pedido.PedidoSendService;
import com.magnabyte.pedidonotifmail.service.pedido.PedidoSendServiceImpl;

public class PedidoNotifMail {
	private GenericSendService genericSendService;
	
	public PedidoNotifMail(int numPedido, String nameFileProperties) throws IOException {
		genericSendService = new PedidoSendServiceImpl(numPedido, nameFileProperties);
		((PedidoSendService)genericSendService).sendMail();
	}

	public static void main(String[] args) throws IOException {
		String nameFileProperties;
		int numPedido;
		
		System.setProperty("file.encoding", "ISO8859_1");
		System.setProperty("java.awt.headless", "true");
		
		switch (args.length) {
		case 2:
			numPedido = Integer.parseInt(args[0]);
			nameFileProperties = args[1];
			
			new PedidoNotifMail(numPedido, nameFileProperties);
			break;
		default:
			System.out.println("Uso incorrecto");
			System.exit(2);
			break;
		}
	}

}
