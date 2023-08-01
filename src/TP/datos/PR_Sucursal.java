package TP.datos;

public class PR_Sucursal {
	
	//esta clase sirve para almacenar los valores de pagerank para cada sucursal
	
	private Sucursal sucursal;
	private Double valor;
	
	public PR_Sucursal(Sucursal s, Double val) {
		sucursal = s;
		valor = val;
	}
	
	
	
	public Sucursal getSucursal() {
		return sucursal;
	}



	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}



	public Double getValor() {
		return valor;
	}



	public void setValor(double valor) {
		this.valor = (Double) valor;
	}



	@Override
	public boolean equals(Object o) {
		if(o instanceof PR_Sucursal) {
			PR_Sucursal aux = (PR_Sucursal) o;
			if (aux.getSucursal().getId() == this.getSucursal().getId()) {
				return true;
			}
		}
		return false;
	}
	
	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + sucursal.getId();
        result = (int) (prime * result + valor);
        return result;
    }

}
