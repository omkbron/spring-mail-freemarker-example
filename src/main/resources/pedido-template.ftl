<#ftl encoding='UTF-8'>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
  <table style="height:100%; background-color:#f6f6f6; width:100%;" align="center">
  	<tr style="background-color:#ffffff;">
      <td align="center">
    	  <div style="width: 85%; height: 55px; padding-top: 20px; font-size: 18px; font-family:Helvetica, Arial, sans-serif;">
    	  	<div style="width: 49%; display: inline-block; text-align: left;">
    	  	  <span style="color: #45484E; line-height: 20px; font-weight: bold;">${company}</span>
    	  	</div>
    	  	<div style="width: 49%; display: inline-block; text-align: right;">
    	  	  <span style="color: #272727; font-weight: light; text-align: right; line-height: 20px;">Pedidos</span>
    	  	</div>
    	  </div>
      </td>
  	</tr>
  	<tr style="border-top: 1px solid #ebebeb; border-bottom: 1px solid #ebebeb">
      <td align="center">
    	  <div style="width: 85%; padding: 20px;">
    	  	<div style="width: 100%; height: auto; border: 1px solid #ebebeb; background-color: #ffffff; padding: 5px; text-align: left;">
    	  	  <div style="font-size: 13px; color: #959595; font-weight: normal; text-align: left; font-family: Helvetica, Arial, sans-serif; line-height: 24px;">
              <p style="font-size: 18px; color: #45484E; font-weight: bold; font-family: Helvetica, Arial, sans-serif; line-height: 1;">Apreciable Compañero:</p>
              <p>Le informamos que se gestionó el alta del siguiente pedido: <strong>${pedido.numero?c}</strong></p>
              <p>Fecha Pedido: <strong>${pedido.fechaPedido?string("dd/MM/yyyy")}</strong></p>
              <p>Fecha Entrega: <strong>${pedido.fechaEntrega?string("dd/MM/yyyy")}</strong></p>
              <p>Pedido Cliente: <strong>${pedido.pedidoCliente}</strong></p>
              <p>Almacen: <strong>${pedido.almacen}</strong></p>
              <p>Estatus: <strong>${pedido.status}</strong></p>
              <p>Clave Cliente: <strong>${pedido.cliente.clave}</strong></p>
              <p>Nombre del Cliente:  <strong>${pedido.cliente.nombre}</strong></p>
              <p>Usuario que da de alta el pedido: <strong>${pedido.user}</strong></p>
              <p>Fecha y hora de alta: <strong>${pedido.fechaHoraAccion?string("dd/MM/yyyy HH:mm:ss")}</strong></p>
              <table cellpadding="2" cellspacing="0" style="font-size: 13px; color: #959595; font-weight: normal; text-align: left; font-family: Helvetica, Arial, sans-serif; line-height: 24px;">
                <thead>
                  <tr>
                    <th style="text-align: center; border-bottom: 1px solid">Cantidad</th>
                    <th style="text-align: center; border-bottom: 1px solid">Presentación</th>
                    <th style="text-align: center; border-bottom: 1px solid">Clave</th>
                    <th style="text-align: center; border-bottom: 1px solid">Descripción</th>
                    <th style="text-align: center; border-bottom: 1px solid">Ancho</th>
                    <th style="text-align: center; border-bottom: 1px solid">Largo</th>
                    <th style="text-align: center; border-bottom: 1px solid">Unidad</th>
                  </tr>
                </thead>
                <tbody>
                  <#list pedido.productos as prod>
                    <tr style="font-weight: bold;">
                      <td style="text-align: center;">${prod.cantidad}</td>
                      <td style="text-align: center;">${prod.presentacion}</td>
                      <td style="text-align: center;">${prod.clave}</td>
                      <td style="text-align: center;">${prod.descripcion}</td>
                      <td style="text-align: center;">${prod.ancho?string("0.0000")}</td>
                      <td style="text-align: center;">${prod.largo?string("0.00")}</td>
                      <td style="text-align: center;">${prod.unidad}</td>
                    </tr>
                    <#list prod.cortes as corte>
                      <tr>
                        <td style="text-align: center;"><#if corte.item == 1>${corte.cantidad}</#if></td>
                        <td style="text-align: center;"></td>
                        <td style="text-align: center;"></td>
                        <td style="text-align: center;">Bobinas: ${corte.numBobinas}</td>
                        <td style="text-align: center;">${corte.ancho?string("0.0000")}</td>
                        <td style="text-align: center;">${corte.largo?string("0.00")}</td>
                        <td style="text-align: center;"></td>
                      </tr>
                    </#list>
                  </#list>
                </tbody>
              </table>
            </div>
    	  	</div>
    	  </div>
      </td>
  	</tr>
  	<tr style="background-color:#ffffff;">
      <td align="center">
    	  <div style="width: 85%; padding: 20px;">
          <div style="width: 100%; height: auto; text-align: left;">
            <div style="font-size: 13px; color: #45484E; font-weight: normal; text-align: left; font-family: Helvetica, Arial, sans-serif; line-height: 24px;">
              <p>Este es un correo de carácter informativo.</p>
            </div>
          </div>
    	  </div> 		
      </td>
  	</tr>
  	<tr style="background-color:#313337;  padding: 55px;" align="center">
      <td align="center">
        <div style="width: 85%; padding: 20px;">
    	    <span style="font-weight:400; font-size: 11px; color: #f5f5f5; font-weight: bold; font-family: Helvetica, Arial, sans-serif; line-height: 1; vertical-align: middle;">&reg; 2013 ${company}</span>	
        </div>
      </td>
  	</tr>
  </tr>
</body>
</html>
