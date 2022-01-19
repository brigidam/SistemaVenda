package com.sistemaweb.model;

public class ItemVenda {

	private int id;
	private Produto produto;
	private double valorUnitario;
	private double quantidade;
	private double total;

	public ItemVenda() {
	}

	public ItemVenda(int id, Produto produto, double valorUnitario, double quantidade, double total) {
		super();
		this.id = id;
		this.produto = produto;
		this.valorUnitario = valorUnitario;
		this.quantidade = quantidade;
		this.total = total;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

}
