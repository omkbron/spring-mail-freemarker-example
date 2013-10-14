package com.magnabyte.pedidonotifmail.service.pedido;

import java.io.IOException;

import javax.mail.MessagingException;

import com.magnabyte.pedidonotifmail.bean.Pedido;
import com.magnabyte.pedidonotifmail.bean.TipoAccion;

import freemarker.template.TemplateException;

public interface PedidoService {
	void prepareAndSendMail(TipoAccion accion, Pedido pedido) throws MessagingException, IOException, TemplateException;
}
