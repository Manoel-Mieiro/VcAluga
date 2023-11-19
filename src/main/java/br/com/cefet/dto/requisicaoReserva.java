package br.com.cefet.dto;

import java.time.LocalDate;
import java.util.Date;

import br.com.cefet.model.Categoria;
import br.com.cefet.model.Filial;
import br.com.cefet.model.Marca;
import br.com.cefet.model.Paletas;
import br.com.cefet.model.Reserva;
import br.com.cefet.model.Veiculo;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class requisicaoReserva {
	private Veiculo veiculo;
    @NotNull
    private int veiculoId; 
	@NotNull
	//adicionar notação para D+1
	private Date dataReserva;
	@NotNull
	//limitar x dias
	private Date dataDevolucao;
	@NotNull
	private float valorPago;

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
	
	
public int getVeiculoId() {
		return veiculoId;
	}

	public void setVeiculoId(int veiculoId) {
		this.veiculoId = veiculoId;
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

public Reserva toReserva() {
		Reserva reserva = new Reserva();
		reserva.setVeiculo(this.veiculo);
		reserva.setDataReserva(this.dataReserva);
		reserva.setDataDevolucao(this.dataDevolucao);
		reserva.setValorPago(this.valorPago);
		reserva.setCategoriaVeiculo(this.veiculo);
		reserva.setModeloVeiculo(this.veiculo);
		reserva.setMarcaVeiculo(this.veiculo);
		reserva.setPlaca(this.veiculo);
		reserva.setCor(this.veiculo);
		reserva.setAno(this.veiculo);
		reserva.setBranch(this.veiculo);
		return reserva;
	} 

//Metodo toVeiculo para update de um veiculo existente
public Reserva toReserva(Reserva reserva) {
	reserva.setVeiculo(this.veiculo);
	reserva.setDataReserva(this.dataReserva);
	reserva.setDataDevolucao(this.dataDevolucao);
	reserva.setValorPago(this.valorPago);
	reserva.setCategoriaVeiculo(veiculo);
	reserva.setModeloVeiculo(veiculo);
	reserva.setMarcaVeiculo(veiculo);
	reserva.setPlaca(veiculo);
	reserva.setCor(veiculo);
	reserva.setAno(veiculo);
	reserva.setBranch(veiculo);
	
	return reserva;
} 

public void fromReserva(Reserva reserva) {
	this.veiculo = reserva.getVeiculo();
	this.dataReserva = reserva.getDataReserva();
	this.dataDevolucao = reserva.getDataDevolucao();
	this.valorPago = reserva.getValorPago();
}
@Override
public String toString() {
	return "requisicaoReserva [veiculo=" + veiculo + ", veiculoId=" + veiculoId + ", dataReserva=" + dataReserva
			+ ", dataDevolucao=" + dataDevolucao + ", valorPago=" + valorPago + "]";
}


	
	
	
	
	
	}
