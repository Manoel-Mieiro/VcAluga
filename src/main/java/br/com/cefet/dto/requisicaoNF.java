package br.com.cefet.dto;

import br.com.cefet.model.NF;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class requisicaoNF {
	@NotNull
	private int idNF;
	@NotNull
	private int numeroNF;
	
	
	public int getIdNF() {
		return idNF;
	}
	
	public int getNumeroNF() {
		return numeroNF;
	}

	public void setNumeroNF(int numeroNF) {
		this.numeroNF = numeroNF;
	}

public NF toNF() {
		NF nf = new NF();
		nf.setNumeroNF(this.numeroNF);
		
		return nf;
	} 

public NF toNF(NF nf) {
	nf.setNumeroNF(this.numeroNF);
	
	return nf;
} 

public void fromNF(NF nf) {
	this.numeroNF = nf.getNumeroNF();
	
}

@Override
public String toString() {
	return "requisicaoNF [numeroNF=" + numeroNF + "]";
} 
	
	}
