package br.com.cefet.model;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name="reserva")
public class Reserva {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idReserva;

	@ManyToOne

	@JoinColumn(name = "veiculoId")
	private Veiculo veiculo;
	
	@Column(nullable = false, name = "DataReserva")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date dataReserva;

	@Column(nullable = false, name = "DataDevolucao")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date dataDevolucao;

	@Column(nullable = false, name = "ValorPago")
	private float valorPago;
	
	 @ManyToOne
	    @JoinColumn(name = "id_cliente")
	    private Cliente cliente;
	
	@Column(nullable = false)
	private String status;

	public Reserva() {
		super();
	}


	
	public Cliente getCliente() {
		return cliente;
	}



	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}



	public Categoria getCategoriaVeiculo() {
		return veiculo.getCategoriaVeiculo();
	}
	public String getModeloVeiculo() {
		return veiculo.getModeloVeiculo();
	}
	
	public Marca getMarcaVeiculo() {
		return veiculo.getMarcaVeiculo();
	}
	
	public String getPlaca() {
		return veiculo.getPlaca();
	}
	
	public Paletas getCor() {
		return veiculo.getCor();
	}
	
	public int getAno() {
		return veiculo.getAno();
	}
	
	public Filial getBranch() {
		return veiculo.getBranch();
	}
	
	
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setCategoriaVeiculo(Veiculo veiculo) {
		if(veiculo != null) {
			veiculo.setCategoriaVeiculo(getCategoriaVeiculo());
		}
	}
	
	public void setModeloVeiculo(Veiculo veiculo) {
		if(veiculo != null) {
			veiculo.setModeloVeiculo(getModeloVeiculo());;
		}
	}
	
	public void setMarcaVeiculo(Veiculo veiculo) {
		if(veiculo != null) {
			veiculo.setMarcaVeiculo(getMarcaVeiculo());
		}
	}
	
	public void setPlaca(Veiculo veiculo) {
		if(veiculo != null) {
			veiculo.setPlaca(getPlaca());
		}
	}
	
	public void setCor(Veiculo veiculo) {
		if(veiculo != null) {
			veiculo.setCor(getCor());
		}
	}
	
	
	public void setAno(Veiculo veiculo) {
		if(veiculo != null) {
			veiculo.setAno(getAno());
		}
	}
	
	public void setBranch(Veiculo veiculo) {
		if(veiculo != null) {
			veiculo.setBranch(getBranch());
		}
	}
	

	public int getIdReserva() {
		return idReserva;
	}


	public void setIdReserva(int idReserva) {
		this.idReserva = idReserva;
	}



	public Veiculo getVeiculo() {
		return veiculo;
	}



	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}



	public Date getDataReserva() {
		return dataReserva;
	}



	public void setDataReserva(Date dataReserva) {
		this.dataReserva = dataReserva;
	}



	public Date getDataDevolucao() {
		return dataDevolucao;
	}



	public void setDataDevolucao(Date dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}



	public float getValorPago() {
		return valorPago;
	}



	public void setValorPago(float valorPago) {
		this.valorPago = valorPago;
	}



	public Reserva(Veiculo veiculo, Date dataReserva, Date dataDevolucao, float valorPago, Cliente cliente,
			String status) {
		super();
		this.veiculo = veiculo;
		this.dataReserva = dataReserva;
		this.dataDevolucao = dataDevolucao;
		this.valorPago = valorPago;
		this.cliente = cliente;
		this.status = status;
	}




	 



}
