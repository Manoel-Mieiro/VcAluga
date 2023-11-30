package br.com.cefet.model;

import java.time.temporal.ChronoUnit;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Random;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class NF {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idNF;
	@Column(nullable = false)
	private int numeroNF;
	@Column(nullable = false)
	private float valorTotal;
	@Column(nullable = false)
	private float valorSemImposto;
	@OneToOne
	@JoinColumn(name = "contrato_id")
	private Contrato contrato;
	@Column(nullable = false, name = "data_emissao")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private LocalDate dataEmissao;
//	@Column(nullable = false)
//	private Date dataVencimento;
	@Column(nullable = false)
	private static final float aliquota = 0.09f;
	@Column(nullable = false)
	private float valorDoImposto;

	public int getIdNF() {
		return idNF;
	}

	public void setIdNF(int idNF) {
		this.idNF = idNF;
	}

	public int getNumeroNF() {
		return numeroNF;
	}

	public void setNumeroNF(int numeroNF) {
		this.numeroNF = numeroNF;
	}
	

	public float getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(float valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

	public LocalDate getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(LocalDate today) {
		this.dataEmissao = today;
	}

//	public Date getDataVencimento() {
//		return dataVencimento;
//	}
//
//	public void setDataVencimento(Date dataVencimento) {
//		this.dataVencimento = dataVencimento;
//	}

	public float getValorDoImposto() {
		return valorDoImposto;
	}

	public void setValorDoImposto(float valorDoImposto) {
		this.valorDoImposto = valorDoImposto;
	}

	public static float getAliquota() {
		return aliquota;
	}

	public float getValorSemImposto() {
		return valorSemImposto;
	}

	public void setValorSemImposto(float valorSemImposto) {
		this.valorSemImposto = valorSemImposto;
	}

	public NF() {
		super();
	}



	public NF(int numeroNF, float valorTotal, float valorSemImposto, Contrato contrato, LocalDate dataEmissao,
			float valorDoImposto) {
		super();
		this.numeroNF = gerarNumeroNF();
		this.valorTotal = valorTotal;
		this.valorSemImposto = valorSemImposto;
		this.contrato = contrato;
		this.dataEmissao = dataEmissao;
		this.valorDoImposto = valorDoImposto;
	}


	public Float calcularValorSemImposto(LocalDate inicio, LocalDate fim) {
	    if (contrato != null && contrato.getReserva() != null && inicio != null && fim != null) {
	        long diferencaDias = ChronoUnit.DAYS.between(inicio, fim) + 1;
	        float valorDiaria = contrato.getReserva().getValorPago();

	        return valorDiaria * diferencaDias;
	    } else {
	        return null;
	    }
	}



	
	public Float calcularValorImposto(LocalDate inicio, LocalDate fim) {
        if (contrato != null && contrato.getReserva() != null && inicio != null && fim != null) {
            return calcularValorSemImposto(inicio, fim) * aliquota;
        } else {
            return null;
        }
    }
	

	public Float calcularTotal(LocalDate inicio, LocalDate fim) {
        if (contrato != null && contrato.getReserva() != null && inicio != null && fim != null) {
            return calcularValorSemImposto(inicio, fim) * (1 - aliquota);
        } else {
            return null;
        }
    }
	
	  public int gerarNumeroNF() {
	        Random random = new Random();
	        return 100_000 + random.nextInt(900_000); // Gera um número aleatório entre 100.000 e 999.999
	    }
	
}
