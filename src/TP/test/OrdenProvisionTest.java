package TP.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import TP.datos.*;

class OrdenProvisionTest {

	@Test
	void testBajaStockExiste() {
		
		
		Sucursal sucursal1 = new Sucursal("prueba", 12.00, 15.00, true, 10);
		Producto producto1 = new Producto("Horno", "DESC", 100.00, 100.00, 100);
		Stock stock2 = new Stock(producto1, 10);
		
		OrdenProvision orden1 = new OrdenProvision("hoy", sucursal1, 10000);
		orden1.getProductos().add(stock2);
		orden1.bajaStock(producto1);
		
		OrdenProvision orden2 = new OrdenProvision("hoy", sucursal1, 10000);
		orden2.getProductos().add(stock2);
		orden2.getProductos().remove(stock2);
	
		assertEquals(orden1.getProductos(),orden2.getProductos());
		
	}
	
	@Test
	void testBajaStockNoExiste() {
		
		Sucursal sucursal1 = new Sucursal("prueba", 12.00, 15.00, true, 10);
		Producto producto1 = new Producto("Horno", "DESC", 100.00, 100.00, 100);
		Producto producto2 = new Producto("Heladera", "DESC", 150.00, 200.00, 101);
		
		OrdenProvision orden1 = new OrdenProvision("hoy", sucursal1, 10000);
		orden1.bajaStock(producto2);
		
		OrdenProvision orden2 = new OrdenProvision("hoy", sucursal1, 10000);
	
		assertEquals(orden1.getProductos(),orden2.getProductos());
		
	}
	
	@Test
	void testConfirmarOrdenConfirmada() {
		
		//en este caso probamos que se confirma la orden exitosamente
		
		Sucursal sucursal1 = new Sucursal("prueba", 12.00, 15.00, true, 10);
		Producto producto1 = new Producto("Horno", "DESC", 100.00, 100.00, 100);
		Stock stock2 = new Stock(producto1, 10);
		
		OrdenProvision orden1 = new OrdenProvision("hoy", sucursal1, 10000);
		orden1.getProductos().add(stock2);
		orden1.confirmarOrden();
		
		OrdenProvision orden2 = new OrdenProvision("hoy", sucursal1, 10000);
		orden2.getProductos().add(stock2);
		orden2.setEstado(Estado_Orden.PENDIENTE);
		
		assertEquals(orden1.getEstado(),orden2.getEstado());
		
	}
	
	@Test
	void testConfirmarOrdenNoConfirmada() {
		
		//en este caso probamos que se confirma la orden exitosamente
		
		Sucursal sucursal1 = new Sucursal("prueba", 12.00, 15.00, true, 10);
		
		OrdenProvision orden1 = new OrdenProvision("hoy", sucursal1, 10000);
		orden1.confirmarOrden();
		
		OrdenProvision orden2 = new OrdenProvision("hoy", sucursal1, 10000);
		
		assertEquals(orden1.getEstado(),orden2.getEstado());
		
	}
	
	@Test
	void testAgregarStockExiste() {
		
		Sucursal sucursal1 = new Sucursal("prueba", 12.00, 15.00, true, 10);
		Producto producto1 = new Producto("Horno", "DESC", 100.00, 100.00, 100);
		Producto producto2 = new Producto("Heladera", "DESC", 150.00, 200.00, 101);
		Stock stock1 = new Stock(producto1, 10);
		Stock stock2 = new Stock(producto2, 5);
		
		OrdenProvision orden1 = new OrdenProvision("hoy", sucursal1, 10000);
		orden1.getProductos().add(stock1);
		orden1.getProductos().add(stock2);
		orden1.agregarStock(producto2, 5);
		
		OrdenProvision orden2 = new OrdenProvision("hoy", sucursal1, 10000);
		orden2.getProductos().add(stock1);
		orden2.getProductos().add(stock2);
		stock2.setCantidad(10);
	
		assertEquals(orden1.getProductos(),orden2.getProductos());
		
	}
	
	@Test
	void testAgregarStockNoExiste() {
		
		Sucursal sucursal1 = new Sucursal("prueba", 12.00, 15.00, true, 10);
		Producto producto1 = new Producto("Horno", "DESC", 100.00, 100.00, 100);
		Producto producto2 = new Producto("Heladera", "DESC", 150.00, 200.00, 101);
		Stock stock1 = new Stock(producto1, 10);
		Stock stock2 = new Stock(producto2, 5);
		
		OrdenProvision orden1 = new OrdenProvision("hoy", sucursal1, 10000);
		orden1.getProductos().add(stock1);
		orden1.agregarStock(producto2, 5);
		
		OrdenProvision orden2 = new OrdenProvision("hoy", sucursal1, 10000);
		orden2.getProductos().add(stock1);
		orden2.getProductos().add(stock2);
	
		assertEquals(orden1.getProductos(),orden2.getProductos());
		
	}

}
