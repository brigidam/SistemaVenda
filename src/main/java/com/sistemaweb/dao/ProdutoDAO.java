package com.sistemaweb.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sistemaweb.model.Produto;

public class ProdutoDAO {

	public int adicionar(Produto p) {

		String sql = "insert into produtos (nome, preco, estoque) values (?,?,?)";
		var conn = Conexao.getConexao();
		int idProduto = 0;

		PreparedStatement ps;

		try {
			ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, p.getNome());
			ps.setDouble(2, p.getPreco());
			ps.setDouble(3, p.getEstoque());

			ps.executeUpdate();
			var rs = ps.getGeneratedKeys();

			if (rs.next()) {
				idProduto = rs.getInt(1);
			}

			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return idProduto;
	}

	public boolean remover(Produto p) {

		String sql = "delete from produtos where id = ?";
		var conn = Conexao.getConexao();
		boolean resultado = false;

		PreparedStatement ps;

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, p.getId());

			int retorno = ps.executeUpdate();

			if (retorno > 0) {
				resultado = true;
			}

			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return resultado;
	}

	public boolean editar(Produto p) {

		String sql = "update produtos set nome = ?, preco = ?, estoque = ? where id = ?";
		var conn = Conexao.getConexao();
		boolean resultado = false;

		PreparedStatement ps;

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, p.getNome());
			ps.setDouble(2, p.getPreco());
			ps.setDouble(3, p.getEstoque());
			ps.setInt(4, p.getId());

			int retorno = ps.executeUpdate();

			if (retorno > 0) {
				resultado = true;
			}

			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return resultado;
	}

	public Produto getProdutoId(int id) {

		String sql = "select * from produtos where id = ?";
		var conn = Conexao.getConexao();
		Produto p = null;

		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);

			var rs = ps.executeQuery();

			if (rs.next()) {
				p = new Produto(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getDouble(4));

			}

			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return p;
	}

	public List<Produto> getProdutosTodos() {

		String sql = "select * from produtos order by id";
		var conn = Conexao.getConexao();
		List<Produto> produtos = new ArrayList<Produto>();

		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);

			var rs = ps.executeQuery();

			while (rs.next()) {
				Produto p = new Produto(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getDouble(4));
				produtos.add(p);
			}

			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return produtos;
	}

}
