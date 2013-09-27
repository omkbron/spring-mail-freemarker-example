package com.magnabyte.pedidonotifmail.controller;

import java.io.IOException;

import com.magnabyte.pedidonotifmail.bean.TipoAccion;
import com.magnabyte.pedidonotifmail.service.GenericSendService;
import com.magnabyte.pedidonotifmail.service.pedido.PedidoSendService;
import com.magnabyte.pedidonotifmail.service.pedido.PedidoSendServiceImpl;

public class PedidoNotifMail {
	private GenericSendService genericSendService;

	public PedidoNotifMail(TipoAccion accion, int numPedido, String nameFileProperties) throws IOException {
		genericSendService = new PedidoSendServiceImpl(accion, numPedido, nameFileProperties);
		((PedidoSendService) genericSendService).sendMail();
	}

	public static void main(String[] args) throws IOException {
		String nameFileProperties;
		int numPedido;

		System.setProperty("file.encoding", "ISO8859_1");
		System.setProperty("java.awt.headless", "true");

		switch (args.length) {
		case 3:
			numPedido = Integer.parseInt(args[0]);
			TipoAccion accion = args[1].equalsIgnoreCase("A") ? TipoAccion.ALTA
					: args[1].equalsIgnoreCase("B") ? TipoAccion.BAJA : null;
			nameFileProperties = args[2];

			new PedidoNotifMail(accion, numPedido, nameFileProperties);
			break;
		default:
			System.out.println("Uso incorrecto");
			System.exit(2);
			break;
		}
	}

}
