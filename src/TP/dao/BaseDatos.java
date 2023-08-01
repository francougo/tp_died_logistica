package TP.dao;

import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import TP.datos.*;

public class BaseDatos{
    
    private Connection coneccion;
    private String url;
    private String user;
    private String password;
    private static BaseDatos instancia;

    public static BaseDatos getInstancia(){
        
        if (instancia == null){
            instancia = new BaseDatos();
        }
        return instancia;
    }

    private BaseDatos(){
        this.coneccion = null;
    }

    public Connection getConeccion(){
    	//System.out.println(this.url + " " + this.user + " " + this.password);
        if (coneccion == null){
            try{
                coneccion = DriverManager.getConnection(this.url, this.user, this.password);
            } catch (Exception e) { e.printStackTrace(); }
        }
        return coneccion;
    }

    public void cerrarConeccion(){
        if(coneccion != null) try { coneccion.close(); }
        catch (SQLException e) { e.printStackTrace(); }
    }

    public void setNombre(String nombre){
        this.url = "jdbc:postgresql://localhost/" + nombre;
    }

    public void setUser(String user){
        this.user = user;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void nuevaSucursal(Sucursal suc){
        try{
            PreparedStatement ps = getConeccion().prepareStatement("insert into sucursal (nombre, hs_apertura, hs_cierre, estado, id_sucursal) values (?,?,?,?,?)");
            ps.setString(1, suc.getNombre());
            ps.setDouble(2, suc.getHs_apertura());
            ps.setDouble(3, suc.getHs_cierre());
            ps.setBoolean(4, suc.getEstado());
            ps.setInt(5, suc.getId());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void nuevaRuta(Ruta r){
        try{
            PreparedStatement ps = getConeccion().prepareStatement("insert into ruta (id_origen, id_destino, capacidad, duracion_min, estado, id_ruta) values (?,?,?,?,?,?)");
            ps.setInt(1, r.getOrigen().getId());
            ps.setInt(2, r.getDestino().getId());
            ps.setDouble(3, r.getCapacidad());
            ps.setDouble(4, r.getDuracion_min());
            ps.setBoolean(5, r.isEstado());
            ps.setInt(6, r.getId());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void nuevaOrden(OrdenProvision o){
        try{
            PreparedStatement ps = getConeccion().prepareStatement("insert into orden (id_orden, fecha, id_destino, tiempo_maximo, estado) values (?,?,?,?,?)");
            ps.setInt(1, o.getId());
            ps.setString(2, o.getFecha());
            ps.setInt(3, o.getDestino().getId());
            ps.setDouble(4, o.getTiempo_maximo());
            ps.setString(5, o.getEstado());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void nuevoProducto(Producto p){
        try{
            PreparedStatement ps = getConeccion().prepareStatement("insert into producto (nombre, descripcion, precio_unitario, peso_kg, id_producto) values (?,?,?,?,?)");
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getDesc());
            ps.setDouble(3, p.getPrecio_unitario());
            ps.setDouble(4, p.getPeso_kg());
            ps.setInt(5, p.getId());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void nuevoStockOrden(Stock s, int id_orden){
        try{
            PreparedStatement ps = getConeccion().prepareStatement("insert into stock_orden (id_producto, cantidad, id_orden) values (?,?,?)");
            ps.setInt(1, s.getProd().getId());
            ps.setInt(2, s.getCantidad());
            ps.setInt(3, id_orden);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void nuevoStockSucursal(Stock s, int id_sucursal){
        try{
            PreparedStatement ps = getConeccion().prepareStatement("insert into stock_sucursal (id_producto, cantidad, id_sucursal) values (?,?,?)");
            ps.setInt(1, s.getProd().getId());
            ps.setInt(2, s.getCantidad());
            ps.setInt(3, id_sucursal);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void modificarSucursal(Sucursal s){
        try{
            PreparedStatement ps = getConeccion().prepareStatement("update sucursal set nombre=?, hs_apertura=?, hs_cierre=?, estado=? where id_sucursal=?");
            ps.setString(1, s.getNombre());
            ps.setDouble(2, s.getHs_apertura());
            ps.setDouble(3, s.getHs_cierre());
            ps.setBoolean(4, s.getEstado());
            ps.setInt(5, s.getId());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void modificarRuta(Ruta r){
        try{
            PreparedStatement ps = getConeccion().prepareStatement("update ruta set id_origen=?, id_destino=?, capacidad=?, duracion_min=?, estado=? where id_ruta=?");
            ps.setInt(1, r.getOrigen().getId());
            ps.setInt(2, r.getDestino().getId());
            ps.setDouble(3, r.getCapacidad());
            ps.setDouble(4, r.getDuracion_min());
            ps.setBoolean(5, r.isEstado());
            ps.setInt(6, r.getId());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void modificarOrden(OrdenProvision o){
        try{
            PreparedStatement ps = getConeccion().prepareStatement("update orden set fecha=?, id_destino=?, tiempo_maximo=?, estado=? where id_orden=?");
            ps.setString(1, o.getFecha());
            ps.setInt(2, o.getDestino().getId());
            ps.setDouble(3, o.getTiempo_maximo());
            ps.setString(4, o.getEstado());
            ps.setInt(5, o.getId());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void modificarProducto(Producto p){
        try{
            PreparedStatement ps = getConeccion().prepareStatement("update producto set nombre=?, descripcion=?, precio_unitario=?, peso_kg=? where id_producto=?");
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getDesc());
            ps.setDouble(3, p.getPrecio_unitario());
            ps.setDouble(4, p.getPeso_kg());
            ps.setInt(5, p.getId());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void modificarStockOrden(Stock s, int id_orden){
        try{
            PreparedStatement ps = getConeccion().prepareStatement("update stock_orden set cantidad=? where id_producto=? and id_orden=?");
            ps.setInt(1, s.getCantidad());
            ps.setInt(2, s.getProd().getId());
            ps.setInt(3, id_orden);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void modificarStockSucursal(Stock s, int id_sucursal){
        try{
            PreparedStatement ps = getConeccion().prepareStatement("update stock_sucursal set cantidad=? where id_producto=? and id_sucursal=?");
            ps.setInt(1, s.getCantidad());
            ps.setInt(2, s.getProd().getId());
            ps.setInt(3, id_sucursal);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void eliminarSucursal(Sucursal s){
        try{
            PreparedStatement ps = getConeccion().prepareStatement("delete from sucursal where id_sucursal=?");
            ps.setInt(1, s.getId());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void eliminarRuta(Ruta r){
        try{
            PreparedStatement ps = getConeccion().prepareStatement("delete from ruta where id_ruta=?");
            ps.setInt(1, r.getId());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void eliminarOrden(OrdenProvision o){
        try{
            PreparedStatement ps = getConeccion().prepareStatement("delete from orden where id_orden=?");
            ps.setInt(1, o.getId());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void eliminarProducto(Producto p){
        try{
            PreparedStatement ps = getConeccion().prepareStatement("delete from producto where id_producto=?");
            ps.setInt(1, p.getId());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void eliminarStockOrden(Stock s, int id_orden){
        try{
            PreparedStatement ps = getConeccion().prepareStatement("delete from stock_orden where id_producto=? and id_orden=?");
            ps.setInt(1, s.getProd().getId());
            ps.setInt(2, id_orden);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void eliminarStockSucursal(Stock s, int id_sucursal){
        try{
            PreparedStatement ps = getConeccion().prepareStatement("delete from stock_sucursal where id_producto=? and id_sucursal=?");
            ps.setInt(1, s.getProd().getId());
            ps.setInt(2, id_sucursal);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void cargarBaseDatos(){
        ArrayList<Sucursal> sucursales = new ArrayList<>();
        ArrayList<Ruta> rutas = new ArrayList<>();
        ArrayList<Producto> productos = new ArrayList<>();
        try{
        PreparedStatement ps = getConeccion().prepareStatement("select * from sucursal");

        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            Sucursal s = new Sucursal(rs.getString(1), rs.getDouble(2), rs.getDouble(3), rs.getBoolean(4), rs.getInt(5));
            sucursales.add(s);
        }
        GestorSucursales.getInstancia().setSucursales(sucursales);
        ps.close();
        ps = getConeccion().prepareStatement("select max(id_sucursal) from sucursal");
        rs = ps.executeQuery();
        rs.next();
        GestorSucursales.getInstancia().setIdMinima(rs.getInt(1) + 1);

        

        ps.close();
        ps = getConeccion().prepareStatement("select * from producto");
        rs = ps.executeQuery();
        while (rs.next()){
            Producto p = new Producto(rs.getString(1), rs.getString(2), rs.getDouble(3), rs.getDouble(4), rs.getInt(5));
            productos.add(p);
        }
        GestorProductos.getInstancia().setProductos(productos);
        ps.close();
        ps = getConeccion().prepareStatement("select max(id_producto) from producto");
        rs = ps.executeQuery();
        rs.next();
        GestorProductos.getInstancia().setIdMinima(rs.getInt(1) + 1);

        ps.close();
        ps = getConeccion().prepareStatement("select * from ruta where id_origen = ?");
        
        for (Sucursal s : sucursales) {
        	ps.setInt(1, s.getId());
        	rs = ps.executeQuery();
        	while (rs.next()){
        		int id_aux = rs.getInt(2);
        		Sucursal destino = GestorSucursales.getInstancia().busqueda(suc -> suc.getId() == id_aux).get(0);
                Ruta r = new Ruta(s, destino, rs.getDouble(3), rs.getDouble(4), rs.getBoolean(5), rs.getInt(6));
                rutas.add(r);
            }
        }
        
        
        GestorRutas.getInstancia().setRutas(rutas);
        ps.close();
        ps = getConeccion().prepareStatement("select max(id_ruta) from ruta");
        rs = ps.executeQuery();
        rs.next();
        GestorRutas.getInstancia().setIdMinima(rs.getInt(1) + 1);
        
        ps.close();
        PreparedStatement ps2 = getConeccion().prepareStatement("select id_orden, fecha, id_destino, tiempo_maximo, estado from orden where id_destino = ?");

        for (Sucursal s : sucursales){
            ArrayList<OrdenProvision> ordenes = new ArrayList<>();
            ps2.setInt(1, s.getId());
            rs = ps2.executeQuery();
            //System.out.println(s.getId() + " " + rs.getFetchSize());
            while (rs.next()){
            	//System.out.println(rs.getInt(1));
                OrdenProvision o = new OrdenProvision(rs.getString("fecha"), s, rs.getInt("id_orden"));
                o.setTiempo_maximo(rs.getInt(4));
                o.setEstado(Estado_Orden.valueOf(rs.getString(5).toUpperCase()));	//prestar atencion.
                ordenes.add(o);
            }
            s.setOrdenes(ordenes);
            
            ps.close();
            ps = getConeccion().prepareStatement("select max(id_orden) from orden where id_destino = ?");
            ps.setInt(1, s.getId());
            rs = ps.executeQuery();
            rs.next();
            if (rs.getInt(1) != 0) {
            	s.setIdOrdenMinima(rs.getInt(1) - 1000*s.getId() + 1);
            }
        }

        ps2.close();
        ps.close();
        ps = getConeccion().prepareStatement("select * from stock_sucursal where id_sucursal = ? and id_producto = ?");
        

        for (Sucursal s : sucursales){
            ArrayList<Stock> stocks = new ArrayList<>();
            ps.setInt(1, s.getId());
            for (Producto p : productos) {
            	ps.setInt(2, p.getId());
            	rs = ps.executeQuery();
            	while (rs.next()){
                    Stock st = new Stock(p, rs.getInt(2));
                    stocks.add(st);
                }
            }
            s.setStock(stocks);
        }

        ps.close();
        ps = getConeccion().prepareStatement("select * from stock_orden where id_orden = ? and id_producto = ?");

        for (Sucursal s : sucursales){
            for (OrdenProvision o : s.getOrdenes()){
                ArrayList<Stock> stocks = new ArrayList<>();
                ps.setInt(1, o.getId());
                for (Producto p : productos) {
                	ps.setInt(2, p.getId());
                	rs = ps.executeQuery();
                	while (rs.next()){
                		Stock st = new Stock(p, rs.getInt(2));
                		stocks.add(st);
                	}
                }
                o.setStock(stocks);
            }
        }

        ps.close();
        
        }catch (SQLException e) { e.printStackTrace(); }
    }     
}