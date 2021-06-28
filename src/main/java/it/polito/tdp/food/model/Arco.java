package it.polito.tdp.food.model;

public class Arco {

	String type1;
	String type2;
	int peso;
	public Arco(String type1, String type2, int peso) {
		super();
		this.type1 = type1;
		this.type2 = type2;
		this.peso = peso;
	}
	public String getType1() {
		return type1;
	}
	public void setType1(String type1) {
		this.type1 = type1;
	}
	public String getType2() {
		return type2;
	}
	public void setType2(String type2) {
		this.type2 = type2;
	}
	public int getPeso() {
		return peso;
	}
	public void setPeso(int peso) {
		this.peso = peso;
	}
	@Override
	public String toString() {
		return "Arco [type1=" + type1 + ", type2=" + type2 + ", peso=" + peso + "]";
	}
	
	
}
