package TP.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.*;
import TP.datos.GestorRutas;
import TP.datos.GestorSucursales;

public class PanelPageRank extends JPanel {
	
	private JList<String> resultadosList;
    private DefaultListModel<String> listModel;
    
    public void inicializar() {
    	
    	
    	listModel = new DefaultListModel<>();
	    resultadosList = new JList<>(listModel);
	    JScrollPane resultadosScrollPane = new JScrollPane(resultadosList);
	    
	    this.add(resultadosScrollPane);
	    
		GestorRutas.getInstancia().pageRank(GestorSucursales.getInstancia().getCentro());
		List<String> aux = GestorRutas.getInstancia().getPageRanks().stream()
								.sorted((p1, p2) -> -1* (p1.getValor().compareTo(p2.getValor())))
								.map(s -> " id: " + s.getSucursal().getId() + " nombre: " + s.getSucursal().getNombre() + " pagerank: " + s.getValor())
								.collect(Collectors.toList());
		mostrarResultados(aux);
	    
    	
    }
    
	private void mostrarResultados(List<String> aux) {
        listModel.clear();

        for (String sucursal : aux) {
            listModel.addElement(sucursal);
        }
    }
	

}
