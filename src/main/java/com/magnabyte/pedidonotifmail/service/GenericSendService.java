package com.magnabyte.pedidonotifmail.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.magnabyte.pedidonotifmail.controller.PedidoNotifMail;

public abstract class GenericSendService {
	public static Properties sendProperties;
	
	public void loadFileProperties(String nameFileProperties)
			throws FileNotFoundException {
		Properties props = new Properties();
		InputStream stream = PedidoNotifMail.class.getClassLoader().getResourceAsStream(nameFileProperties);

		if (stream != null) {
			try {
				props.load(stream);
				sendProperties = props;
			} catch (IOException e) {
				System.out.println("Error al cargar el archivo de propiedades");
				e.printStackTrace();
				System.exit(2);
			}
		} else {
			throw new FileNotFoundException("No se encontro el archivo de propiedades");
		}
	}
	
	public abstract void constructBeanMail();
}
