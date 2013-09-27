package com.magnabyte.pedidonotifmail.service.pedido;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import com.magnabyte.pedidonotifmail.bean.Cliente;
import com.magnabyte.pedidonotifmail.bean.Pedido;
import com.magnabyte.pedidonotifmail.bean.Producto;
import com.magnabyte.pedidonotifmail.bean.TipoAccion;
import com.magnabyte.pedidonotifmail.dao.PedidoDao;
import com.magnabyte.pedidonotifmail.service.GenericSendService;
import com.omkbron.sendemail.model.BeanMail;
import com.omkbron.sendemail.service.SendMail;

public class PedidoSendServiceImpl extends GenericSendService implements
		PedidoSendService {
	private BeanMail beanMail;
	private Pedido pedido;
	private PedidoDao pedidoDao;
	private TipoAccion accion;

	public PedidoSendServiceImpl(TipoAccion accion, int numPedido, String nameFileProperties) throws FileNotFoundException {
		this.accion = accion;
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
			
			switch (accion) {
			case ALTA:
				pedido.setProductos(pedidoDao.recuperarProductos(pedido));
				
				for (Producto producto : pedido.getProductos()) {
					producto.setCortes(pedidoDao.recuperarCortesProducto(producto));
				}
				break;
			case BAJA:
				pedido.setCausa(pedidoDao.recuperarCausa(pedido));
				if (pedido.getCausa() == null || pedido.getCausa().equals("")) {
					pedido.setCausa("NO DEFINIDO");
				}
				pedido.setFechaHoraAccion(pedidoDao.recuperarHoraBaja(pedido));
			default:
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		constructBeanMail();
	}

	@Override
	public void constructBeanMail() {
		beanMail = new BeanMail();
		beanMail.setMailProps(sendProperties);
		beanMail.setUserName(sendProperties.getProperty("fromMail"));
		beanMail.setPassword(sendProperties.getProperty("pwdMail"));
		beanMail.setFrom(sendProperties.getProperty("from"));
		beanMail.setSubject("GBP Almacén: " + pedido.getAlmacen() + " - " 
				+ pedido.getCliente().getClave() 
				+ " " + pedido.getCliente().getNombre() 
				+" " + accion.getDescripcion() + ": " + pedido.getNumero());
		beanMail.setDirectoryHtmlTemplate(sendProperties.getProperty("pathWork"));
		String template = null;
		switch (accion) {
		case ALTA:
			template = sendProperties.getProperty("fileHTML");
			break;
		case BAJA:
			template = sendProperties.getProperty("fileHTMLCancel");
			break;
		default:
			break;
		}
		beanMail.setHtmlTemplate(template);
		beanMail.setHtmlBodyProps(getHtmlBodyProps());
		try {
			beanMail.setRecipients(pedidoDao.obtenerDestinatarios(pedido));
		} catch (Exception e) {
			e.printStackTrace();
		}
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
			if (beanMail.getRecipients().length > 0) {
				SendMail sendMail = new SendMail();
				sendMail.setupMail(beanMail);
				sendMail.send();
			} else {
				System.out.println("El correo no se envia ya que no hay destinatarios configurados.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(2);
		}
	}

}
