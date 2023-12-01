package br.com.cefet.dto;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.cefet.model.Categoria;
import br.com.cefet.model.Cliente;
import br.com.cefet.model.Filial;
import br.com.cefet.model.Marca;
import br.com.cefet.model.Paletas;
import br.com.cefet.model.Reserva;
import br.com.cefet.model.Veiculo;
import br.com.cefet.repository.VeiculoRepository;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class requisicaoReserva {
	private Veiculo veiculo;
	@NotNull
	private int veiculoId;
	@NotNull(message = "A data de reserva não pode ser nula.")
	@Future(message = "A data de reserva deve ser no futuro.")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataReserva;
	@NotNull(message = "A data de reserva não pode ser nula.")
	@Future(message = "A data de reserva deve ser no futuro.")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataDevolucao;
	@NotNull
	private float valorPago;
	private String status;
	private Cliente cliente;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Categoria getCategoriaVeiculo() {
		if (veiculo != null) {
			return veiculo.getCategoriaVeiculo();
		} else {

			return null;
		}
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
		if (veiculo != null) {
			veiculo.setCategoriaVeiculo(veiculo.getCategoriaVeiculo());
		}
	}

	public void setModeloVeiculo(Veiculo veiculo) {
		if (veiculo != null) {
			veiculo.setModeloVeiculo(veiculo.getModeloVeiculo());
			;
		}
	}

	public void setMarcaVeiculo(Veiculo veiculo) {
		if (veiculo != null) {
			veiculo.setMarcaVeiculo(veiculo.getMarcaVeiculo());
		}
	}

	public void setPlaca(Veiculo veiculo) {
		if (veiculo != null) {
			veiculo.setPlaca(veiculo.getPlaca());
		}
	}

	public void setCor(Veiculo veiculo) {
		if (veiculo != null) {
			veiculo.setCor(veiculo.getCor());
		}
	}

	public void setAno(Veiculo veiculo) {
		if (veiculo != null) {
			veiculo.setAno(veiculo.getAno());
		}
	}

	public void setBranch(Veiculo veiculo) {
		if (veiculo != null) {
			veiculo.setBranch(veiculo.getBranch());
		}
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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
//		reserva.setVeiculo(this.veiculo);
		reserva.setDataReserva(this.dataReserva);
		reserva.setDataDevolucao(this.dataDevolucao);
		reserva.setStatus(this.status);
//		reserva.setValorPago(this.valorPago);
//		reserva.setCategoriaVeiculo(this.veiculo);
//		reserva.setModeloVeiculo(this.veiculo);
//		reserva.setMarcaVeiculo(this.veiculo);
//		reserva.setPlaca(this.veiculo);
//		reserva.setCor(this.veiculo);
//		reserva.setAno(this.veiculo);
//		reserva.setBranch(this.veiculo);
		return reserva;
	}

//Metodo toVeiculo para update de um veiculo existente
	public Reserva toReserva(Reserva reserva) {
//	reserva.setVeiculo(this.veiculo);
		reserva.setDataReserva(this.dataReserva);
		reserva.setDataDevolucao(this.dataDevolucao);
		reserva.setStatus(this.status);
//	reserva.setValorPago(this.valorPago);
//	reserva.setCategoriaVeiculo(veiculo);
//	reserva.setModeloVeiculo(veiculo);
//	reserva.setMarcaVeiculo(veiculo);
//	reserva.setPlaca(veiculo);
//	reserva.setCor(veiculo);
//	reserva.setAno(veiculo);
//	reserva.setBranch(veiculo);

		return reserva;
	}

	public void fromReserva(Reserva reserva) {
		this.veiculo = reserva.getVeiculo();
		this.dataReserva = reserva.getDataReserva();
		this.dataDevolucao = reserva.getDataDevolucao();
		this.valorPago = reserva.getValorPago();
		this.status = reserva.getStatus();
	}

	@Override
	public String toString() {
		return "requisicaoReserva [veiculo=" + veiculo + ", veiculoId=" + veiculoId + ", dataReserva=" + dataReserva
				+ ", dataDevolucao=" + dataDevolucao + ", valorPago=" + valorPago + ", status=" + status + ", cliente="
				+ cliente + "]";
	}

}
