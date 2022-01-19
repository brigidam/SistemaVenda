package com.sistemaweb.model;

import java.time.LocalDate;
import java.util.List;

public class Venda {

	private int id;
	private LocalDate data;
	private double total;
	private List<ItemVenda> itens;

	public Venda() {

	}

	public Venda(int id, LocalDate data, double total, List<ItemVenda> itens) {
		super();
		this.id = id;
		this.data = data;
		this.total = total;
		this.itens = itens;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public List<ItemVenda> getItens() {
		return itens;
	}

	public void setItens(List<ItemVenda> itens) {
		this.itens = itens;
	}
}
