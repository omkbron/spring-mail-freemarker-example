package com.magnabyte.pedidonotifmail.service.pedido.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.magnabyte.pedidonotifmail.dao.PedidoDao;
import com.magnabyte.pedidonotifmail.model.Pedido;
import com.magnabyte.pedidonotifmail.model.Producto;
import com.magnabyte.pedidonotifmail.model.TipoAccion;
import com.magnabyte.pedidonotifmail.service.mail.MailService;
import com.magnabyte.pedidonotifmail.service.pedido.PedidoService;

import freemarker.template.TemplateException;

@Service
public class PedidoServiceImpl implements PedidoService {
	
	@Autowired
	private PedidoDao pedidoDao;
	
	@Autowired
	private MailService mailService;
	
	@Value("${company}")
	private String company;
	
	@Value("${fileHTML}")
	private String templateAlta;
	
	@Value("${fileHTMLCancel}")
	private String templateBaja;
	
	@Override
	public void prepareAndSendMail(TipoAccion accion, Pedido pedido) throws MessagingException, IOException, TemplateException {
		String template = null;
		
		pedido = pedidoDao.recuperarPedido(pedido);
		
		pedido.setCliente(pedidoDao.recuperarClientePedido(pedido));
		
		switch (accion) {
		case ALTA:
			template = templateAlta;
			pedido.setProductos(pedidoDao.recuperarProductos(pedido));
			
			for (Producto producto : pedido.getProductos()) {
				producto.setCortes(pedidoDao.recuperarCortesProducto(producto));
			}
			pedidoDao.recuperarUsuarioHoraAlta(pedido);
			break;
		case BAJA:
			template = templateBaja;
			pedido.setCausa(pedidoDao.recuperarCausa(pedido));
			if (pedido.getCausa() == null || pedido.getCausa().equals("")) {
				pedido.setCausa("NO DEFINIDO");
			}
			pedidoDao.recuperarUsuarioHoraBaja(pedido);
		default:
			break;
		}

		String subject = "GBP Almacén: " + pedido.getAlmacen() + " - " 
				+ pedido.getCliente().getClave() 
				+ " " + pedido.getCliente().getNombre() 
				+" " + accion.getDescripcion() + ": " + pedido.getNumero();
		
		String[] recipients = pedidoDao.obtenerDestinatarios(pedido).toArray(new String[0]);
		if (recipients.length > 0) {
			mailService.sendMailWithEngine("No se puede mostrar el contenido", subject, template, 
					getHtmlBodyProps(pedido), recipients);
		} else {
			System.out.println("El correo no se envia ya que no hay destinatarios configurados.");
		}
	}
	
	private Map<String, Object> getHtmlBodyProps(Pedido pedido) {
		Map<String, Object> htmlBodyProps = new HashMap<String, Object>();
		htmlBodyProps.put("company", company);
		htmlBodyProps.put("pedido", pedido);
		return htmlBodyProps;
	}
}
