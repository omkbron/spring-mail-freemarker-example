package com.magnabyte.pedidonotifmail.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.magnabyte.pedidonotifmail.dao.GenericJdbcDao;
import com.magnabyte.pedidonotifmail.dao.PedidoDao;
import com.magnabyte.pedidonotifmail.model.Cliente;
import com.magnabyte.pedidonotifmail.model.Corte;
import com.magnabyte.pedidonotifmail.model.Pedido;
import com.magnabyte.pedidonotifmail.model.Producto;
import com.magnabyte.pedidonotifmail.sql.PedidoSql;

@Repository
public class PedidoDaoImpl extends GenericJdbcDao implements PedidoDao {

	public Pedido recuperarPedido(final Pedido pedido) {

		return getJdbcTemplate().queryForObject(PedidoSql.GET_PEDIDO,
				new RowMapper<Pedido>() {
					@Override
					public Pedido mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Cliente cliente = new Cliente();
						pedido.setFechaPedido(rs.getDate("fecha_pedido"));
						pedido.setFechaEntrega(rs.getDate("fecha_entrega"));
						pedido.setAlmacen(rs.getString("almacen"));
						pedido.setPedidoCliente(rs.getString("ped_clie"));
						pedido.setStatus(rs.getString("status_pedido"));
						cliente.setClave(rs.getString("clie_clave"));
						pedido.setCliente(cliente);
						return pedido;
					}
				}, pedido.getNumero());

	}

	public Cliente recuperarClientePedido(final Pedido pedido) {

		return getJdbcTemplate().queryForObject(PedidoSql.GET_CLIENTE,
				new RowMapper<Cliente>() {
					@Override
					public Cliente mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Cliente cliente = new Cliente();
						cliente.setClave(pedido.getCliente().getClave());
						cliente.setNombre(rs.getString("nomb_clie"));
						return cliente;
					}
				}, pedido.getCliente().getClave());

	}

	public List<Producto> recuperarProductos(final Pedido pedido) {

		return getJdbcTemplate().query(PedidoSql.GET_PEDIDOPROD,
				new RowMapper<Producto>() {
					@Override
					public Producto mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Producto producto = new Producto();
						producto.setItemNum(rs.getInt("item_num"));
						producto.setClave(rs.getString("prod_clave"));
						producto.setPresentacion(rs.getString("pres_prod"));
						producto.setDescripcion(rs.getString("desc_prod"));
						producto.setAncho(rs.getDouble("ancho"));
						producto.setLargo(rs.getDouble("largo"));
						producto.setCantidad(rs.getInt("num_present"));
						producto.setUnidad(rs.getString("unid_prod"));
						producto.setPedido(pedido);
						return producto;
					}
				}, pedido.getNumero());

	}

	public List<Corte> recuperarCortesProducto(Producto producto) {
		
		return getJdbcTemplate().query(PedidoSql.GET_PEDIDCORTE, new RowMapper<Corte>() {
			@Override
			public Corte mapRow(ResultSet rs, int rowNum) throws SQLException {
				Corte corte = new Corte();
				corte.setItem(rs.getInt("item_ped"));
				corte.setNumero(rs.getInt("corte"));
				corte.setCantidad(rs.getInt("num_master"));
				corte.setNumBobinas(rs.getInt("num_present"));
				corte.setAncho(rs.getDouble("ancho"));
				corte.setLargo(rs.getDouble("largo"));
				return corte;
			}
		}, producto.getPedido().getNumero(), producto.getItemNum());
		
	}

	public List<String> obtenerDestinatarios(Pedido pedido) {
		
		return getJdbcTemplate().query(PedidoSql.GET_RECIPIENTS, new RowMapper<String>() {
			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString("email");
			}
		}, pedido.getAlmacen());
		
	}

	public String recuperarCausa(Pedido pedido) {
		try {
			return getJdbcTemplate().queryForObject(PedidoSql.GET_CAUSA, String.class, pedido.getNumero());
		} catch (EmptyResultDataAccessException ex) {
			return null;
		}
	}

	@Override
	public void recuperarUsuarioHoraAlta(final Pedido pedido) {
		getJdbcTemplate().queryForObject(PedidoSql.GET_ALTADATA, new RowMapper<Pedido>() {
			@Override
			public Pedido mapRow(ResultSet rs, int rowNum) throws SQLException {
				pedido.setUser(rs.getString("usuario"));
				pedido.setFechaHoraAccion(rs.getDate("fecha_hora"));
				return pedido;
			}
		}, pedido.getNumero());
	}

	@Override
	public void recuperarUsuarioHoraBaja(final Pedido pedido) {
		getJdbcTemplate().queryForObject(PedidoSql.GET_BAJADATA, new RowMapper<Pedido>() {
			@Override
			public Pedido mapRow(ResultSet rs, int rowNum) throws SQLException {
				pedido.setUser(rs.getString("usuario"));
				pedido.setFechaHoraAccion(rs.getDate("fecha_hora"));
				return pedido;
			}
		}, pedido.getNumero());
	}

}
