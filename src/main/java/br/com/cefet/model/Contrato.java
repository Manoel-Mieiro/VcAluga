package br.com.cefet.model;

import java.util.ArrayList;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
@Entity
public class Contrato {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idContrato;
	@OneToOne
	@JoinColumn(name="cnpj")
	private Filial cnpj;
	private Date dataEmissao;
	@ManyToOne
	@JoinColumn(name="veiculoId")
	private Veiculo veiculo;
	@OneToOne
    @JoinColumn(name = "reservaId")
    private Reserva reserva;
//	private String cpfCliente;
//	private String nomeCompleto;
	private String assinaturaGestor;
	private String assinaturaCliente;
	private ArrayList<String> cnhs = new ArrayList<String>(); // coleção de CNHs
	
	public int getIdContrato() {
		return idContrato;
	}

	public void setIdContrato(int idContrato) {
		this.idContrato = idContrato;
	}

	public Filial getCnpj() {
		return cnpj;
	}

	public void setCnpj(Filial cnpj) {
		this.cnpj = cnpj;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}


	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public String getAssinaturaGestor() {
		return assinaturaGestor;
	}

	public void setAssinaturaGestor(String assinaturaGestor) {
		this.assinaturaGestor = assinaturaGestor;
	}

	public String getAssinaturaCliente() {
		return assinaturaCliente;
	}

	public void setAssinaturaCliente(String assinaturaCliente) {
		this.assinaturaCliente = assinaturaCliente;
	}

	public ArrayList<String> getCnhs() {
		return cnhs;
	}

	public void setCnhs(ArrayList<String> cnhs) {
		this.cnhs = cnhs;
	}

	//Método para adicionar CNHs
	 public void adicionarCNH(String numeroCNH) {
	        this.cnhs.add(numeroCNH);
	    }
	 
	 
	
	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public Contrato() {
		super();
	}

	public Contrato(Filial cnpj, Date dataEmissao, Veiculo veiculo, Reserva reserva, String assinaturaGestor,
			String assinaturaCliente, ArrayList<String> cnhs) {
		super();
		this.cnpj = cnpj;
		this.dataEmissao = dataEmissao;
		this.veiculo = veiculo;
		this.reserva = reserva;
		this.assinaturaGestor = assinaturaGestor;
		this.assinaturaCliente = assinaturaCliente;
		this.cnhs = cnhs;
	}

	

	
	
}
