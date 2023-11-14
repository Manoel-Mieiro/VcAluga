package br.com.cefet.dto;

import java.util.ArrayList;
import java.util.Date;

import br.com.cefet.model.Categoria;
import br.com.cefet.model.Contrato;
import br.com.cefet.model.Filial;
import br.com.cefet.model.Marca;
import br.com.cefet.model.Paletas;
import br.com.cefet.model.Reserva;
import br.com.cefet.model.Veiculo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class requisicaoContrato {
	@NotNull
	private int idContrato;
	@NotNull
	private Filial cnpj;
	@NotNull
	private Veiculo veiculo;  // Mantém uma referência ao objeto Veiculo
	private String placa;
	private Marca marcaVeiculo;
	private String modeloVeiculo;
	private Categoria categoriaVeiculo;
	private float quilometragem;
	private Paletas cor;
	private int ano;
	private Reserva reserva;
	private int idReserva;
	private Date dataReserva;
	private Date dataDevolucao;
	private float valorPago;
	@NotNull
	private Date dataEmissao;
	@NotBlank
	@NotNull
	private String assinaturaGestor;
	@NotBlank
	@NotNull
	private String assinaturaCliente;
	@NotBlank
	@NotNull
	private ArrayList<String> cnhs;

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

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
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

	public ArrayList<String> getCnhs() {
		return cnhs;
	}

	public void setCnhs(ArrayList<String> cnhs) {
		this.cnhs = cnhs;
	}

	public Contrato toContratoNovo() {
        Contrato contrato = new Contrato();
        Veiculo veiculo = new Veiculo();
        veiculo.setPlaca(this.placa);
        veiculo.setMarcaVeiculo(this.marcaVeiculo);
        veiculo.setModeloVeiculo(this.modeloVeiculo);
        veiculo.setCategoriaVeiculo(this.categoriaVeiculo);
        veiculo.setQuilometragem(this.quilometragem);
        veiculo.setCor(this.cor);
        veiculo.setAno(this.ano);

        contrato.setVeiculo(veiculo);
        
        Reserva reserva = new Reserva();
        reserva.setDataReserva(this.dataReserva);
        reserva.setDataDevolucao(this.dataDevolucao);
        reserva.setValorPago(this.valorPago);

        contrato.setReserva(reserva);

        contrato.setDataEmissao(this.dataEmissao);
        contrato.setAssinaturaCliente(this.assinaturaCliente);
        contrato.setAssinaturaGestor(this.assinaturaGestor);

        // Ajuste para CNHs, se necessário
        contrato.setCnhs(this.cnhs);

        return contrato;
    }

    public Contrato toContratoExistente(Contrato contrato) {
        Veiculo veiculo = new Veiculo();
        veiculo.setPlaca(this.placa);
        veiculo.setMarcaVeiculo(this.marcaVeiculo);
        veiculo.setModeloVeiculo(this.modeloVeiculo);
        veiculo.setCategoriaVeiculo(this.categoriaVeiculo);
        veiculo.setQuilometragem(this.quilometragem);
        veiculo.setCor(this.cor);
        veiculo.setAno(this.ano);

        contrato.setVeiculo(veiculo);
        
        Reserva reserva = contrato.getReserva();
        reserva.setDataReserva(this.dataReserva);
        reserva.setDataDevolucao(this.dataDevolucao);
        reserva.setValorPago(this.valorPago);

        contrato.setReserva(reserva);

        contrato.setCnpj(this.cnpj);
        contrato.setDataEmissao(this.dataEmissao);
        contrato.setAssinaturaCliente(this.assinaturaCliente);
        contrato.setAssinaturaGestor(this.assinaturaGestor);

        // Ajuste para CNHs, se necessário
        contrato.setCnhs(this.cnhs);

        return contrato;
    }

    public void fromContrato(Contrato contrato) {
        this.idContrato = contrato.getIdContrato();
        this.cnpj = contrato.getCnpj();
        this.dataReserva = contrato.getReserva().getDataReserva();
        this.dataDevolucao = contrato.getReserva().getDataDevolucao();
        this.valorPago = contrato.getReserva().getValorPago();
        this.veiculo = contrato.getVeiculo();
        this.placa = contrato.getVeiculo().getPlaca();
        this.marcaVeiculo = contrato.getVeiculo().getMarcaVeiculo();
        this.modeloVeiculo = contrato.getVeiculo().getModeloVeiculo();
        this.categoriaVeiculo = contrato.getVeiculo().getCategoriaVeiculo();
        this.quilometragem = contrato.getVeiculo().getQuilometragem();
        this.cor = contrato.getVeiculo().getCor();
        this.ano = contrato.getVeiculo().getAno();
        this.dataEmissao = contrato.getDataEmissao();
        this.assinaturaCliente = contrato.getAssinaturaCliente();
        this.assinaturaGestor = contrato.getAssinaturaGestor();

        // Ajuste para CNHs, se necessário
        this.cnhs = contrato.getCnhs();
    }


	@Override
	public String toString() {
		return "requisicaoContrato [cnpj=" + cnpj + ", veiculo=" + veiculo + ", placa=" + placa + ", marcaVeiculo="
				+ marcaVeiculo + ", modeloVeiculo=" + modeloVeiculo + ", categoriaVeiculo=" + categoriaVeiculo
				+ ", quilometragem=" + quilometragem + ", cor=" + cor + ", ano=" + ano + ", reserva=" + reserva
				+ ", dataReserva=" + dataReserva + ", dataDevolucao=" + dataDevolucao + ", valorPago=" + valorPago
				+ ", dataEmissao=" + dataEmissao + ", assinaturaGestor=" + assinaturaGestor + ", assinaturaCliente="
				+ assinaturaCliente + ", cnhs=" + cnhs + "]";
	}
	
    
}
