package com.magnabyte.pedidonotifmail.service.pedido;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.magnabyte.pedidonotifmail.bean.Cliente;
import com.magnabyte.pedidonotifmail.bean.Pedido;
import com.magnabyte.pedidonotifmail.bean.Producto;
import com.magnabyte.pedidonotifmail.dao.PedidoDao;
import com.magnabyte.pedidonotifmail.service.GenericSendService;
import com.omkbron.sendemail.model.BeanMail;
import com.omkbron.sendemail.service.SendMail;

public class PedidoSendServiceImpl extends GenericSendService implements
		PedidoSendService {
	private BeanMail beanMail;
	private Pedido pedido;
	private PedidoDao pedidoDao;

	public PedidoSendServiceImpl(int numPedido, String nameFileProperties) throws FileNotFoundException {
		pedidoDao = new PedidoDao();
		pedido = new Pedido(numPedido);
		loadFileProperties(nameFileProperties);
		recuperarPedido();
	}

	private void recuperarPedido() {
		try {
			pedido = pedidoDao.recuperarPedido(pedido);
			
			Cliente cliente = new Cliente();
			cliente = pedidoDao.recuperarClientePedido(pedido);
			pedido.setCliente(cliente);
			
			pedido.setProductos(pedidoDao.recuperarProductos(pedido));
			
			for (Producto producto : pedido.getProductos()) {
				producto.setCortes(pedidoDao.recuperarCortesProducto(producto));
			}
			
			List<Producto> prodtemp = new ArrayList<Producto>();
			prodtemp.add(pedido.getProductos().get(0));
			prodtemp.add(pedido.getProductos().get(0));
			prodtemp.add(pedido.getProductos().get(0));
			
			pedido.setProductos(prodtemp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(pedido);
		constructBeanMail();
	}

	@Override
	public void constructBeanMail() {
		beanMail = new BeanMail();
		beanMail.setMailProps(sendProperties);
		beanMail.setUserName(sendProperties.getProperty("fromMail"));
		beanMail.setPassword(sendProperties.getProperty("pwdMail"));
		beanMail.setFrom(sendProperties.getProperty("from"));
		beanMail.setSubject("GBP Sucursal-Clave, Nombre del cliente Pedido Alta: Folio");
		beanMail.setDirectoryHtmlTemplate(sendProperties.getProperty("pathWork"));
		beanMail.setHtmlTemplate(sendProperties.getProperty("fileHTML"));
		beanMail.setHtmlBodyProps(getHtmlBodyProps());
//		beanMail.setRecipients(dataDocto.getEmails().toArray(new String[0]));
//		beanMail.setRecipients("ovelasco@magnabyte.com.mx", "omvp29@hotmail.com", "omvp29@gmail.com", "pponce@magnabyte.com.mx", "iortega@magnabyte.com.mx");
		beanMail.setRecipients("omvp29@hotmail.com");

	}

	private Map<String, Object> getHtmlBodyProps() {
		Map<String, Object> htmlBodyProps = new HashMap<String, Object>();
		htmlBodyProps.put("company", sendProperties.getProperty("company"));
		htmlBodyProps.put("pedido", pedido);
		return htmlBodyProps;
	}

	@Override
	public void sendMail() {
		try {
			SendMail sendMail = new SendMail();
			sendMail.setupMail(beanMail);
			sendMail.send();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(2);
		}
	}

}
