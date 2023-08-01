package TP.ui;

import TP.datos.Ruta;
import TP.datos.Sucursal;
import TP.datos.GestorSucursales;
import TP.datos.GestorRutas;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

import javax.swing.JFrame;
import javax.swing.JPanel;
import TP.datos.GestorRutas;
import TP.datos.GestorSucursales;

public class PanelGrafo extends JPanel {
	
	private ArrayList<Ruta> aristas;
	private ArrayList<Sucursal> vertices;
	Random random;
	
	public PanelGrafo() {
		
		aristas = (ArrayList<Ruta>) GestorRutas.getInstancia().getRutas().stream().filter(r -> r.isEstado()).collect(Collectors.toList());
		vertices = (ArrayList<Sucursal>) GestorSucursales.getInstancia().getSucursales().stream().filter(s -> s.getEstado()).collect(Collectors.toList());
		random = new Random();
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for (Sucursal vertice : vertices) {
			setearCoordenadas(vertice);
            g.setColor(Color.BLUE);
            g.fillOval(vertice.getCoord_x(), vertice.getCoord_y(), 30, 30);
            g.setColor(Color.BLACK);
            g.drawString(vertice.getNombre(), vertice.getCoord_x() + 10, vertice.getCoord_y() + 20);
        }

        // Dibuja las aristas
        for (Ruta arista : aristas) {
        	Sucursal verticeOrigen = arista.getOrigen();
        	Sucursal verticeDestino = arista.getDestino();
            g.setColor(Color.RED);
            g.drawLine(verticeOrigen.getCoord_x() + 15, verticeOrigen.getCoord_y() + 15,
            		verticeDestino.getCoord_x() + 15, verticeDestino.getCoord_y() + 15);
        }
	}
	
	private void setearCoordenadas(Sucursal suc) {
		
		if(suc.isPuerto()) {
			suc.setCoord_x(10);
			suc.setCoord_y(10);
		}else if (suc.isCentro()) {
			suc.setCoord_x(500);
			suc.setCoord_y(500);
		}else {
			suc.setCoord_x(random.nextInt(100,400));
			suc.setCoord_y(random.nextInt(100,400));
		}
		
	}
	
	
	

}
