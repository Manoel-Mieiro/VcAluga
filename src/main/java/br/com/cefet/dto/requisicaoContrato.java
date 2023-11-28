package br.com.cefet.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.cefet.model.Categoria;
import br.com.cefet.model.Cliente;
import br.com.cefet.model.Contrato;
import br.com.cefet.model.Filial;
import br.com.cefet.model.Manutencao;
import br.com.cefet.model.Marca;
import br.com.cefet.model.Motorista;
import br.com.cefet.model.Paletas;
import br.com.cefet.model.Reserva;
import br.com.cefet.model.Veiculo;
import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class requisicaoContrato {
	@NotNull
	private int idContrato;
	private Filial filial;
	private int idFilial;
	private Veiculo veiculo; // Mantém uma referência ao objeto Veiculo
	private int idVeiculo;
	private String placa;
	private Marca marcaVeiculo;
	private String modeloVeiculo;
	private Categoria categoriaVeiculo;
	private float quilometragem;
	private Paletas cor;
	private int ano;
	private Reserva reserva;
	private int idReserva;
	@Column(nullable = false, name = "DataReserva")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date dataReserva;
	@Column(nullable = false, name = "DataDevolucao")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date dataDevolucao;
	private float valorPago;
	@Column(nullable = false, name = "DataEmissao")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date dataEmissao;
	@NotBlank
	@NotNull
	private String assinaturaGestor;
	@NotBlank
	@NotNull
	private String assinaturaCliente;
    private Cliente cliente;
    private Motorista motorista;
    private int motoristaId;

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


	public int getIdFilial() {
		return idFilial;
	}

	public void setIdFilial(int idFilial) {
		this.idFilial = idFilial;
	}


	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	
	
	public int getIdVeiculo() {
		return idVeiculo;
	}

	public void setIdVeiculo(int idVeiculo) {
		this.idVeiculo = idVeiculo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Marca getMarcaVeiculo() {
		return marcaVeiculo;
	}

	public void setMarcaVeiculo(Marca marcaVeiculo) {
		this.marcaVeiculo = marcaVeiculo;
	}

	public String getModeloVeiculo() {
		return modeloVeiculo;
	}

	public void setModeloVeiculo(String modeloVeiculo) {
		this.modeloVeiculo = modeloVeiculo;
	}

	public Categoria getCategoriaVeiculo() {
		return categoriaVeiculo;
	}

	public void setCategoriaVeiculo(Categoria categoriaVeiculo) {
		this.categoriaVeiculo = categoriaVeiculo;
	}

	public float getQuilometragem() {
		return quilometragem;
	}

	public void setQuilometragem(float quilometragem) {
		this.quilometragem = quilometragem;
	}

	public Paletas getCor() {
		return cor;
	}

	public void setCor(Paletas cor) {
		this.cor = cor;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public int getIdReserva() {
		return idReserva;
	}

	public void setIdReserva(int idReserva) {
		this.idReserva = idReserva;
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

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
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


	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	

	public Motorista getMotorista() {
		return motorista;
	}

	public void setMotorista(Motorista motorista) {
		this.motorista = motorista;
	}
	
	

	public int getMotoristaId() {
		return motoristaId;
	}

	public void setMotoristaId(int motoristaId) {
		this.motoristaId = motoristaId;
	}

	public Contrato toContrato() {
		Contrato contrato = new Contrato();
//		contrato.setVeiculo(criarVeiculo());
//		contrato.setReserva(criarReserva());
//		contrato.setFilial(criarFilial());
		contrato.setDataEmissao(this.dataEmissao);
		contrato.setAssinaturaCliente(this.assinaturaCliente);
		contrato.setAssinaturaGestor(this.assinaturaGestor);
		return contrato;
	}



	public Contrato toContrato(Contrato contratoExistente) {
//		contratoExistente.setFilial(criarFilial());
//		contratoExistente.setVeiculo(criarVeiculo());
//		contratoExistente.setReserva(criarReserva());
		contratoExistente.setDataEmissao(this.dataEmissao);
		contratoExistente.setAssinaturaCliente(this.assinaturaCliente);
		contratoExistente.setAssinaturaGestor(this.assinaturaGestor);
		return contratoExistente;
	}

	private Veiculo criarVeiculo() {
		Veiculo veiculo = new Veiculo();
	    veiculo.setId(this.idVeiculo);  
		veiculo.setPlaca(this.placa);
		veiculo.setMarcaVeiculo(this.marcaVeiculo);
		veiculo.setModeloVeiculo(this.modeloVeiculo);
		veiculo.setCategoriaVeiculo(this.categoriaVeiculo);
		veiculo.setQuilometragem(this.quilometragem);
		veiculo.setCor(this.cor);
		veiculo.setAno(this.ano);
		return veiculo;
	}

	private Reserva criarReserva() {
		Reserva reserva = new Reserva();
		reserva.setIdReserva(this.idReserva);  
		reserva.setDataReserva(this.dataReserva);
		reserva.setDataDevolucao(this.dataDevolucao);
		reserva.setValorPago(this.valorPago);
		return reserva;
	}
	
	private Filial criarFilial() {
	    Filial filial = new Filial();
	    filial.setId(idFilial);
	    filial.setUf(veiculo.getBranch().getUf());

	    return filial;
	}

	public void fromContrato(Contrato contrato) {
//		this.idContrato = contrato.getIdContrato();
		this.dataReserva = contrato.getReserva().getDataReserva();
		this.dataDevolucao = contrato.getReserva().getDataDevolucao();
		this.valorPago = contrato.getReserva().getValorPago();
		this.filial = contrato.getReserva().getBranch();
		this.idFilial = contrato.getReserva().getBranch().getIdFilial();
		this.veiculo = contrato.getReserva().getVeiculo();
		this.placa = contrato.getReserva().getVeiculo().getPlaca();
		this.marcaVeiculo = contrato.getReserva().getVeiculo().getMarcaVeiculo();
		this.modeloVeiculo = contrato.getReserva().getVeiculo().getModeloVeiculo();
		this.categoriaVeiculo = contrato.getReserva().getVeiculo().getCategoriaVeiculo();
		this.quilometragem = contrato.getReserva().getVeiculo().getQuilometragem();
		this.cor = contrato.getReserva().getVeiculo().getCor();
		this.ano = contrato.getReserva().getVeiculo().getAno();
		this.dataEmissao = contrato.getDataEmissao();
		this.assinaturaCliente = contrato.getAssinaturaCliente();
		this.assinaturaGestor = contrato.getAssinaturaGestor();

		// Ajuste para CNHs, se necessário
	}

	public void preencherComReserva(Reserva reserva) {
		 if (reserva == null || reserva.getVeiculo() == null) {
		        System.out.println("*******************************Reserva ou veículo nulos. Saindo do método.******************************");
		        if (reserva == null) {
		            System.out.println("Reserva é nula.");
		        } else {
		            System.out.println("Veículo na reserva é nulo.");
		        }
		        return;
		    }
		
		Veiculo veiculoReserva = reserva.getVeiculo();

		setPlaca(veiculoReserva.getPlaca());
		setMarcaVeiculo(veiculoReserva.getMarcaVeiculo());
		setModeloVeiculo(veiculoReserva.getModeloVeiculo());
		setCategoriaVeiculo(veiculoReserva.getCategoriaVeiculo());
		setQuilometragem(veiculoReserva.getQuilometragem());
		setCor(veiculoReserva.getCor());
		setAno(veiculoReserva.getAno());
//		setFilial(veiculoReserva.getBranch());
		setDataReserva(reserva.getDataReserva());
		setDataDevolucao(reserva.getDataDevolucao());
		setValorPago(reserva.getValorPago());
	}

	@Override
	public String toString() {
		return "requisicaoContrato [filial=" + filial + ", idFilial=" + idFilial + ", veiculo=" + veiculo
				+ ", idVeiculo=" + idVeiculo + ", placa=" + placa + ", marcaVeiculo=" + marcaVeiculo
				+ ", modeloVeiculo=" + modeloVeiculo + ", categoriaVeiculo=" + categoriaVeiculo + ", quilometragem="
				+ quilometragem + ", cor=" + cor + ", ano=" + ano + ", reserva=" + reserva + ", idReserva=" + idReserva
				+ ", dataReserva=" + dataReserva + ", dataDevolucao=" + dataDevolucao + ", valorPago=" + valorPago
				+ ", dataEmissao=" + dataEmissao + ", assinaturaGestor=" + assinaturaGestor + ", assinaturaCliente="
				+ assinaturaCliente + ", cliente=" + cliente + ", motorista=" + motorista + ", motoristaId="
				+ motoristaId + "]";
	}


}
