package com.magnabyte.pedidonotifmail.dao;

import java.util.List;

import com.magnabyte.pedidonotifmail.model.Cliente;
import com.magnabyte.pedidonotifmail.model.Corte;
import com.magnabyte.pedidonotifmail.model.Pedido;
import com.magnabyte.pedidonotifmail.model.Producto;

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
