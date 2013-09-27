package com.magnabyte.pedidonotifmail.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.magnabyte.pedidonotifmail.bean.Cliente;
import com.magnabyte.pedidonotifmail.bean.Corte;
import com.magnabyte.pedidonotifmail.bean.Pedido;
import com.magnabyte.pedidonotifmail.bean.Producto;
import com.magnabyte.pedidonotifmail.connectDB.JDBCDAOFactory;
import com.magnabyte.pedidonotifmail.sql.PedidoSql;

public class PedidoDao {

	public Pedido recuperarPedido(Pedido pedido) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i = 1;
		try {
			con = JDBCDAOFactory.createConnection();
			ps = con.prepareStatement(PedidoSql.GET_PEDIDO);
			ps.clearParameters();
			ps.setInt(i++, pedido.getNumero());
			rs = ps.executeQuery();

			if (rs.next()) {
				Cliente cliente = new Cliente();
				pedido.setFechaPedido(rs.getDate("fecha_pedido"));
				pedido.setFechaEntrega(rs.getDate("fecha_entrega"));
				pedido.setAlmacen(rs.getString("almacen"));
				pedido.setPedidoCliente(rs.getString("ped_clie"));
				pedido.setStatus(rs.getString("status_pedido"));
				cliente.setClave(rs.getString("clie_clave"));
				pedido.setCliente(cliente);
				pedido.setUser(rs.getString("usuario"));
				pedido.setFechaHoraAccion(rs.getDate("fecha_hora"));
			}

			return pedido;

		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw sqle;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (con != null && !con.isClosed())
				con.close();
		}
	}

	public Cliente recuperarClientePedido(Pedido pedido) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i = 1;
		try {
			con = JDBCDAOFactory.createConnection();
			ps = con.prepareStatement(PedidoSql.GET_CLIENTE);
			ps.clearParameters();
			ps.setString(i++, pedido.getCliente().getClave());
			rs = ps.executeQuery();
			Cliente cliente = pedido.getCliente();
			if (rs.next()) {
				cliente.setNombre(rs.getString("nomb_clie"));
			}

			return cliente;

		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw sqle;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (con != null && !con.isClosed())
				con.close();
		}
	}

	public List<Producto> recuperarProductos(Pedido pedido) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i = 1;
		try {
			con = JDBCDAOFactory.createConnection();
			ps = con.prepareStatement(PedidoSql.GET_PEDIDOPROD);
			ps.clearParameters();
			ps.setInt(i++, pedido.getNumero());
			rs = ps.executeQuery();
			List<Producto> productos = new ArrayList<Producto>();
			while (rs.next()) {
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
				productos.add(producto);
			}

			return productos;

		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw sqle;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (con != null && !con.isClosed())
				con.close();
		}
	}

	public List<Corte> recuperarCortesProducto(Producto producto) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i = 1;
		try {
			con = JDBCDAOFactory.createConnection();
			ps = con.prepareStatement(PedidoSql.GET_PEDIDCORTE);
			ps.clearParameters();
			ps.setInt(i++, producto.getPedido().getNumero());
			ps.setInt(i++, producto.getItemNum());
			rs = ps.executeQuery();
			List<Corte> cortes = new ArrayList<Corte>();
			while (rs.next()) {
				Corte corte = new Corte();
				corte.setItem(rs.getInt("item_ped"));
				corte.setNumero(rs.getInt("corte"));
				corte.setCantidad(rs.getInt("num_master"));
				corte.setNumBobinas(rs.getInt("num_present"));
				corte.setAncho(rs.getDouble("ancho"));
				corte.setLargo(rs.getDouble("largo"));
				cortes.add(corte);
			}

			return cortes;

		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw sqle;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (con != null && !con.isClosed())
				con.close();
		}
	}

	public String[] obtenerDestinatarios(Pedido pedido) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i = 1;
		try {
			con = JDBCDAOFactory.createConnection();
			ps = con.prepareStatement(PedidoSql.GET_RECIPIENTS);
			ps.clearParameters();
			ps.setString(i++, pedido.getAlmacen());
			rs = ps.executeQuery();
			List<String> recipients = new ArrayList<String>();
			while (rs.next()) {
				String recipient = rs.getString("email");
				recipients.add(recipient);
			}

			return recipients.toArray(new String[0]);

		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw sqle;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (con != null && !con.isClosed())
				con.close();
		}
	}

	public String recuperarCausa(Pedido pedido) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i = 1;
		try {
			con = JDBCDAOFactory.createConnection();
			ps = con.prepareStatement(PedidoSql.GET_CAUSA);
			ps.clearParameters();
			ps.setInt(i++, pedido.getNumero());
			rs = ps.executeQuery();
			String causa = null;
			if (rs.next()) {
				causa = rs.getString("descripcion");
			}

			return causa;

		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw sqle;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (con != null && !con.isClosed())
				con.close();
		}
	}

	public Date recuperarHoraBaja(Pedido pedido) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i = 1;
		try {
			con = JDBCDAOFactory.createConnection();
			ps = con.prepareStatement(PedidoSql.GET_HORABAJA);
			ps.clearParameters();
			ps.setInt(i++, pedido.getNumero());
			rs = ps.executeQuery();
			Date fecha = null;
			if (rs.next()) {
				fecha = rs.getDate("fecha_hora");
			}

			return fecha;

		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw sqle;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (con != null && !con.isClosed())
				con.close();
		}
	}

}
