package com.magnabyte.pedidonotifmail.controller;

import java.io.IOException;

import javax.mail.MessagingException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.magnabyte.pedidonotifmail.model.Pedido;
import com.magnabyte.pedidonotifmail.model.TipoAccion;
import com.magnabyte.pedidonotifmail.service.pedido.PedidoService;

import freemarker.template.TemplateException;

public class PedidoNotifMail {

	public static void main(String[] args) throws IOException, MessagingException, TemplateException {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:/applicationContext.xml");
		PedidoService pedidoService = context.getBean(PedidoService.class);
		Pedido pedido = null;
		switch (args.length) {
		case 2:
			pedido = new Pedido(Integer.parseInt(args[0]));
			TipoAccion accion = args[1].equalsIgnoreCase("A") ? TipoAccion.ALTA
					: args[1].equalsIgnoreCase("B") ? TipoAccion.BAJA : null;
			
			pedidoService.prepareAndSendMail(accion, pedido);
			break;
		default:
			System.out.println("Uso incorrecto");
			System.exit(2);
			break;
		}
	}

}
