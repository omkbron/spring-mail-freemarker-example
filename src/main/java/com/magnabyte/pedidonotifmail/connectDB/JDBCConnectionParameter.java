package com.magnabyte.pedidonotifmail.connectDB;

import com.magnabyte.pedidonotifmail.service.pedido.PedidoSendServiceImpl;



/**
 * 
 * @author Jose_Leon
 * 
 */
public interface JDBCConnectionParameter {
	static final String USERDB = PedidoSendServiceImpl.sendProperties.getProperty("userDB").trim();
	static final String PWDDB = PedidoSendServiceImpl.sendProperties.getProperty("pwdDB").trim();
	static final String URLDB = PedidoSendServiceImpl.sendProperties.getProperty("urlDB").trim();
	static final String PORTDB = PedidoSendServiceImpl.sendProperties.getProperty("portDB").trim();
	static final String SID = PedidoSendServiceImpl.sendProperties.getProperty("sid").trim();
	static final String INFORMIXSERVER = PedidoSendServiceImpl.sendProperties.getProperty("informixserver").trim();
	static final String DRIVERDB = PedidoSendServiceImpl.sendProperties.getProperty("driverDB").trim();
	static final String SERVERHOST = PedidoSendServiceImpl.sendProperties.getProperty("serverHostDB").trim();
}
