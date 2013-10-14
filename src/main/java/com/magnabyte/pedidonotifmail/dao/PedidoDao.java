package com.magnabyte.pedidonotifmail.dao;

import java.util.List;

import com.magnabyte.pedidonotifmail.bean.Cliente;
import com.magnabyte.pedidonotifmail.bean.Corte;
import com.magnabyte.pedidonotifmail.bean.Pedido;
import com.magnabyte.pedidonotifmail.bean.Producto;

public interface PedidoDao {
	Pedido recuperarPedido(Pedido pedido);

	Cliente recuperarClientePedido(Pedido pedido);

	List<Producto> recuperarProductos(Pedido pedido);

	List<Corte> recuperarCortesProducto(Producto producto);

	List<String> obtenerDestinatarios(Pedido pedido);

	String recuperarCausa(Pedido pedido);

	void recuperarUsuarioHoraAlta(Pedido pedido);

	void recuperarUsuarioHoraBaja(Pedido pedido);
}
