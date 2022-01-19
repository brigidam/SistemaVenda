package com.sistemaweb.model;

import com.sistemaweb.dao.Conexao;
import com.sistemaweb.dao.VendaDAO;

public class Principal {

	public static void main(String[] args) {

		// Conexao.getConexao();
		// ProdutoDAO produtoDAO = new ProdutoDAO();

		// Adicionando produto
		// Produto p = new Produto(0, "Cuscuz", 1.50, 20);
		// int id = produtoDAO.adicionar(p);
		// System.out.println("Id do produto adicionado:" + id);

		// trazendo um produto
		// Produto p = produtoDAO.getProdutoId(5);
		// System.out.println("Nome: " + p.getNome());

		// alterando produto
		// Produto p = produtoDAO.getProdutoId(4);
		// p.setEstoque(2);
		// p.setPreco(4.44);
		// p.setNome("Produto");

		// produtoDAO.editar(p);

		// System.out.println("Nome: " + p.getNome());

		// excluindo
		// Produto p = produtoDAO.getProdutoId(9);
		// produtoDAO.remover(p);

		// Trazendo todos os produtos

		// List<Produto> produtos = produtoDAO.getProdutosTodos();

		// for(Produto produto: produtos) {
		// System.out.println("Id" + produto.getId() + "Nome:" + produto.getNome());
		// }

		// System.out.println("---------------------------------");

		// Produto p = produtoDAO.getProdutoId(4);
		// produtoDAO.remover(p);

		// produtos = produtoDAO.getProdutosTodos();

		// for(Produto produto: produtos) {
		// System.out.println("Id" + produto.getId() + "Nome:" + produto.getNome());
		// }

		Conexao.getConexao();
		VendaDAO vendaDAO = new VendaDAO();

		// adicionar uma venda
		// Venda v = new Venda(0, LocalDate.now(), 0, null);
		// int id = vendaDAO.adicionar(v);

		// System.out.println("Id: " + id);

		// Trazer uma venda
		Venda v = vendaDAO.getVendaId(1);
		System.out.println("Id: " + v.getId() + " Data:" + v.getData().toString());
		System.out.println("Itens Venda:");

		for (ItemVenda item : v.getItens()) {
			System.out.println("Id Item: " + item.getId());
		}

	}
}
