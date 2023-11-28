package br.com.cefet.model;


import java.util.Date;


import org.springframework.format.annotation.DateTimeFormat;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Contrato {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idContrato;
	@OneToOne
	@JoinColumn(name="idFilial")
	private Filial filial;
	@Column(nullable = false, name = "DataEmissao")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date dataEmissao;
	@ManyToOne
	@JoinColumn(name="veiculoId")
	private Veiculo veiculo;
	@OneToOne
    @JoinColumn(name = "reservaId")
    private Reserva reserva;
	private String assinaturaGestor;
	private String assinaturaCliente;

	
	@ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;
	
	 @ManyToOne
	    @JoinColumn(name = "id_motorista")
	    private Motorista motorista;
	
	 
	 
	public Motorista getMotorista() {
		return motorista;
	}

	public void setMotorista(Motorista motorista) {
		this.motorista = motorista;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public int getIdContrato() {
		return idContrato;
	}

	public void setIdContrato(int idContrato) {
		this.idContrato = idContrato;
	}

	public Filial getFilial() {
		return filial;
	}

	public void setFilial(Filial filial) {
		this.filial = filial;
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

	
	 

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public Contrato() {
		super();
	}

	public Contrato(Filial filial, Date dataEmissao, Veiculo veiculo, Reserva reserva, String assinaturaGestor,
			String assinaturaCliente, Cliente cliente, Motorista motorista) {
		super();
		this.filial = filial;
		this.dataEmissao = dataEmissao;
		this.veiculo = veiculo;
		this.reserva = reserva;
		this.assinaturaGestor = assinaturaGestor;
		this.assinaturaCliente = assinaturaCliente;
		this.cliente = cliente;
		this.motorista = motorista;
	}

	
	



	
	
}
