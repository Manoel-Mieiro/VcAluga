package br.com.cefet.model;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class Veiculo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String placa;
	@Enumerated(EnumType.STRING)	
	private Marca marcaVeiculo; // enum
	@Column(nullable = false)
	private String modeloVeiculo; // pode vir a se tornar enum
	@Enumerated(EnumType.STRING)	
	private Categoria categoriaVeiculo; // enum
	private float quilometragem;
	@Enumerated(EnumType.STRING)
	private Paletas cor; // enum
	@Column(nullable = false)
	private int ano; // byte foi escolhido porque dificilmente carros duram mais de 127 anos
//	@JsonIgnoreProperties({"marca", "modelo", "filial", "categoria"})
//    @OneToMany(mappedBy = "veiculo")
//    private Reserva reserva;
	@ManyToOne
	@JoinColumn(name = "idFilial")
	private Filial branch;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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

	public Filial getBranch() {
		return branch;
	}
	public void setBranch(Filial branch) {
		this.branch = branch;
	}
	
	public Veiculo() {
		
	}
	public Veiculo(String placa, Marca marcaVeiculo, String modeloVeiculo, Categoria categoriaVeiculo,
			float quilometragem, Paletas cor, int ano, Filial branch) {
		super();
		this.placa = placa;
		this.marcaVeiculo = marcaVeiculo;
		this.modeloVeiculo = modeloVeiculo;
		this.categoriaVeiculo = categoriaVeiculo;
		this.quilometragem = quilometragem;
		this.cor = cor;
		this.ano = ano;
		this.branch = branch;
	}


}
