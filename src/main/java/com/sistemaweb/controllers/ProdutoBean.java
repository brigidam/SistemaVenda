
package com.sistemaweb.controllers;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import com.sistemaweb.dao.ProdutoDAO;
import com.sistemaweb.model.Produto;

@Named
@SessionScoped
public class ProdutoBean implements Serializable {

	private static final long serialVersionUID = -4477484702948584826L;
	
	private Produto produto;
	private List<Produto> produtos;

	public ProdutoBean() {
		this.produto = new Produto();
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Produto> getProdutos() {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtos = produtoDAO.getProdutosTodos();
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public String salvar() {

		ProdutoDAO produtoDAO = new ProdutoDAO();
		if (produto.getId() == 0) {
			produtoDAO.adicionar(produto);
		} else {
			produtoDAO.editar(produto);
		}
		this.produtos = produtoDAO.getProdutosTodos();
		return "produtos?faces-redirect=true";
	}

	public String excluir(Produto p) {
		ProdutoDAO produtoDAO = new ProdutoDAO();
		produtoDAO.remover(p);

		this.produtos = produtoDAO.getProdutosTodos();
		return "produtos?faces-redirect=true";
	}

	public String editar() {
		ProdutoDAO produtoDAO = new ProdutoDAO();
		produtoDAO.editar(produto);
		return "produtos?faces-redirect=true";
	}

	public String selecionarParaEditar(Produto produto) {
		this.produto = produto;
		return "produtoeditar?faces-redirect=true";

	}
	
	public String cadastrarProduto() {
		this.produto = new Produto();
		return "produtocadastrar?faces-redirect=true";
	}

}
