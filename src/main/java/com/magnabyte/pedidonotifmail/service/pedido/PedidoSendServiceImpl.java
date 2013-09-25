package com.magnabyte.pedidonotifmail.service.pedido;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import com.magnabyte.pedidonotifmail.service.GenericSendService;
import com.omkbron.sendemail.model.BeanMail;
import com.omkbron.sendemail.service.SendMail;

public class PedidoSendServiceImpl extends GenericSendService implements
		PedidoSendService {
	private BeanMail beanMail;

	public PedidoSendServiceImpl(int numPedido, String nameFileProperties) throws FileNotFoundException {
		loadFileProperties(nameFileProperties);
		constructBeanMail();
	}

	@Override
	public void constructBeanMail() {
		beanMail = new BeanMail();
		beanMail.setMailProps(sendProperties);
		beanMail.setUserName(sendProperties.getProperty("fromMail"));
		beanMail.setPassword(sendProperties.getProperty("pwdMail"));
		beanMail.setFrom("from@mail.com");
		beanMail.setSubject("GBP Sucursal-Clave, Nombre del cliente Pedido Alta: Folio");
		beanMail.setDirectoryHtmlTemplate(sendProperties.getProperty("pathWork"));
		beanMail.setHtmlTemplate(sendProperties.getProperty("fileHTML"));
		// beanMail.setRecipientsBcc(!docProperties.getProperty("mailBCC").equals("") ? docProperties.getProperty("mailBCC") : null);
		// beanMail.setCidImages(new CidImage("logo", docProperties.getProperty("pathWork"), "logo.png"));
		beanMail.setHtmlBodyProps(getHtmlBodyProps());

		// beanMail.setRecipients(dataDocto.getEmails().toArray(new String[0]));
		beanMail.setRecipients("ovelasco@magnabyte.com.mx", "omvp29@hotmail.com", "omvp29@gmail.com");
	}

	private static Map<String, Object> getHtmlBodyProps() {
		Map<String, Object> htmlBodyProps = new HashMap<String, Object>();
		htmlBodyProps.put("company", "Green Bay");
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
