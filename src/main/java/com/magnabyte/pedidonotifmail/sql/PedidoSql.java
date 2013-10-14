package com.magnabyte.pedidonotifmail.sql;

public class PedidoSql {
	private static final String EOL = "\n";
	public static final String GET_PEDIDO;
	public static final String GET_CLIENTE;
	public static final String GET_PEDIDOPROD;
	public static final String GET_PEDIDCORTE;
	public static final String GET_RECIPIENTS;
	public static final String GET_CAUSA;
	public static final String GET_BAJADATA;
	public static final String GET_ALTADATA;
	
	static {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT pedido.fecha_pedido, pedido.fecha_entrega, pedido.almacen, TRIM(pedido.ped_clie) AS ped_clie,").append(EOL)
			.append("pedido.status_pedido, pedido.clie_clave, pedido.vendo_clave").append(EOL)
			.append("FROM pedido").append(EOL)
			.append("WHERE pedido.pedido_num = ?").append(EOL);
		GET_PEDIDO = sb.toString();
		
		clearAndReuseStringBuilder(sb);
		
		sb = new StringBuilder();
		sb.append("SELECT TRIM(nomb_clie) AS nomb_clie").append(EOL)
			.append("FROM cliente").append(EOL)
			.append("WHERE clie_clave = ?").append(EOL);
		GET_CLIENTE = sb.toString();
		
		clearAndReuseStringBuilder(sb);
		
		sb = new StringBuilder();
			sb.append("SELECT pedido_prod.item_num, pedido_prod.prod_clave,").append(EOL)
				.append("pedido_prod.pres_prod, pedido_prod.ancho, pedido_prod.largo,").append(EOL)
				.append("pedido_prod.cantidad_ped, pedido_prod.num_present,").append(EOL)
				.append("producto.unid_prod, producto.desc_prod").append(EOL)
				.append("FROM pedido_prod, producto").append(EOL)
				.append("WHERE pedido_prod.pedido_num = ?").append(EOL)
				.append("AND pedido_prod.prod_clave = producto.prod_clave").append(EOL)
				.append("AND pedido_prod.pres_prod  = producto.pres_prod").append(EOL)
				.append("ORDER BY pedido_prod.item_num").append(EOL);
		GET_PEDIDOPROD = sb.toString();
		
		clearAndReuseStringBuilder(sb);
		
		sb = new StringBuilder();
		sb.append("SELECT pedidcorte.numpres_in, pedidcorte.corte,").append(EOL)
        	.append("pedidcorte.num_master, pedidcorte.item_ped,").append(EOL)
        	.append("pedidcorte.t_corte, pedidcorte.largo_c,").append(EOL)
        	.append("pedidcorte.ancho, pedidcorte.largo,").append(EOL)
        	.append("pedidcorte.num_present, pedidcorte.cantidad_ped").append(EOL)
        	.append("FROM pedidcorte").append(EOL)
        	.append("WHERE pedidcorte.pedido_num = ?").append(EOL)
        	.append("AND pedidcorte.item_num = ?").append(EOL)
        	.append("ORDER BY pedidcorte.corte, pedidcorte.item_ped").append(EOL);
		GET_PEDIDCORTE = sb.toString();
		
		clearAndReuseStringBuilder(sb);
		
		sb = new StringBuilder();
		sb.append("SELECT diuser_deta.email").append(EOL)
        	.append("FROM diusermailmov, diuser_deta, dialmamail").append(EOL)
        	.append("WHERE diusermailmov.usuario = diuser_deta.usuario").append(EOL)
        	.append("AND diusermailmov.usuario = dialmamail.usuario").append(EOL)
        	.append("AND diusermailmov.clave_mov = dialmamail.tipo_mov").append(EOL)
        	.append("AND dialmamail.alma_clave = ?").append(EOL)
        	.append("AND diusermailmov.clave_mov = '800'").append(EOL);
		GET_RECIPIENTS = sb.toString();
		
		clearAndReuseStringBuilder(sb);
		
		sb = new StringBuilder();
		sb.append("SELECT TRIM(causa_baja_doc.descripcion) AS descripcion").append(EOL) 
			.append("FROM causa_baja_doc, causa_baja_doc_pedid").append(EOL)
			.append("WHERE causa_baja_doc.clave = causa_baja_doc_pedid.clave").append(EOL)
			.append("AND causa_baja_doc_pedid.pedido_num = ?");
		GET_CAUSA = sb.toString();

		clearAndReuseStringBuilder(sb);
		
		sb = new StringBuilder();

		sb.append("SELECT fecha_hora, usuario FROM traza_pedido").append(EOL)
			.append("WHERE pedido_num = ?").append(EOL)
			.append("AND status_pedido = 'C'").append(EOL);

		GET_BAJADATA = sb.toString();
		
		clearAndReuseStringBuilder(sb);
		
		sb = new StringBuilder();

		sb.append("SELECT fecha_hora, usuario FROM traza_pedido").append(EOL)
			.append("WHERE pedido_num = ?").append(EOL)
			.append("AND status_pedido = 'A'").append(EOL);

		GET_ALTADATA = sb.toString();
	}
	private static StringBuilder clearAndReuseStringBuilder(final StringBuilder qry) {
		qry.delete(0, qry.length());
		return qry;
	}
	
	public static void main(String[] args) {
		System.out.println(GET_BAJADATA);
	}
}
