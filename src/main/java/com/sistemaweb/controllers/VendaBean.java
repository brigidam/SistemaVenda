package com.sistemaweb.controllers;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.sistemaweb.dao.ProdutoDAO;
import com.sistemaweb.dao.VendaDAO;
import com.sistemaweb.model.ItemVenda;
import com.sistemaweb.model.Produto;
import com.sistemaweb.model.Venda;

@Named
@SessionScoped
public class VendaBean implements Serializable {

	private static final long serialVersionUID = 3556111288049721586L;
	
	private List<ItemVenda> itens;  
	private List<Produto> produtos;
	private ItemVenda item;
	private Venda venda;
	private List<Venda> vendas;

	public VendaBean() {
		this.item = new ItemVenda();
		getItens();
		
	}

	public ItemVenda getItem() {
		return item;
	}

	public void setItem(ItemVenda item) {
		this.item = item;
	}

	public List<Produto> getProdutos() {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtos = produtoDAO.getProdutosTodos();
		
		return produtos;
	}
	
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	
	public List<Venda> getVendas(){
		if(vendas == null) {
			VendaDAO vendaDAO = new VendaDAO();
			vendas = vendaDAO.getVendasTotal();
		}
		
		return vendas;
	}
	
	public List<ItemVenda> getItens() {
		itens = new ArrayList<ItemVenda>();
		getProdutos();
		ItemVenda itemVenda = new ItemVenda();
		for (Produto produto : produtos) {
			itemVenda.setProduto(produto);
			itens.add(itemVenda);
			itemVenda = new ItemVenda();
		}
		return itens;	
	}
	
	
	public void setItens(List<ItemVenda> itens) {
		this.itens = itens;
	}
	
	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}


	public void getTotal() {
		double totalVenda = 0;
		for (ItemVenda itemVenda : itens) {
			itemVenda.setTotal(itemVenda.getQuantidade() * itemVenda.getProduto().getPreco());
			totalVenda += itemVenda.getTotal();
		}
		setItens(itens);
		venda.setTotal(totalVenda);
	}
	
	public String efetuarVenda() {
		venda = new Venda();
		ProdutoDAO produtoDao = new ProdutoDAO();
		VendaDAO vendaDAO = new VendaDAO();
		
		List<ItemVenda> itensSelecionados = new ArrayList<ItemVenda>();
		for(ItemVenda item: itens) {
			if(item.getQuantidade() > 0) {
				itensSelecionados.add(item);
				item.getProduto().setEstoque(item.getProduto().getEstoque() - item.getQuantidade());
				produtoDao.editar(item.getProduto());
			}
		}
		venda.setItens(itensSelecionados);
		double totalVenda = 0;
		for (ItemVenda itemVenda : itensSelecionados) {
			itemVenda.setTotal(itemVenda.getQuantidade() * itemVenda.getProduto().getPreco());
			totalVenda += itemVenda.getTotal();
		}
		venda.setData(LocalDate.now());
		venda.setTotal(totalVenda);
		vendaDAO.adicionar(venda);
		
		return "vendacomprar?faces-redirect=true";
	}
	
}
