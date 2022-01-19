package com.sistemaweb.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import com.sistemaweb.model.ItemVenda;
import com.sistemaweb.model.Produto;
import com.sistemaweb.model.Venda;

public class VendaDAO {

	public int adicionar(Venda v) {
		String sql = "insert into venda (data, total) values (?,?)";
		var conn = Conexao.getConexao();
		int idVenda = 0;

		PreparedStatement ps;

		try {
			ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setDate(1, Date.valueOf(v.getData()));
			ps.setDouble(2, v.getTotal());

			ps.executeUpdate();
			var rs = ps.getGeneratedKeys();

			if (rs.next()) {
				idVenda = rs.getInt(1);
			}

			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return idVenda;
	}
	
	

	public boolean addItemVenda(int idVenda, ItemVenda item) {
		return false;
	}

	public Venda getVendaId(int id) {
		String sql = "select * from venda where id = ?";
		var conn = Conexao.getConexao();
		Venda v = null;

		PreparedStatement ps;

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);

			var rs = ps.executeQuery();

			if (rs.next()) {

				// Obtendo os itens da venda
				List<ItemVenda> itens = new ArrayList<ItemVenda>();
				String sqlItens = "select * from itensvenda where id_venda = ?";
				PreparedStatement psItens;

				psItens = conn.prepareStatement(sqlItens);
				psItens.setInt(1, id);
				var rsItens = psItens.executeQuery();

				while (rsItens.next()) {
					Produto p = new Produto(rsItens.getInt(1), "", 0, 0);
					ItemVenda item = new ItemVenda(rsItens.getInt(1), p, rsItens.getDouble(4), rsItens.getDouble(5),
							rsItens.getDouble(6));
					itens.add(item);
				}

				v = new Venda(rs.getInt(1), rs.getDate(2).toLocalDate(), rs.getDouble(3), itens);
			}

			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return v;
	}
	
	public List<Venda> getVendasTotal() {

		String sql = "select * from venda order by id";
		var conn = Conexao.getConexao();
		List<Venda> vendas = new ArrayList<Venda>();

		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);

			var rs = ps.executeQuery();

			while (rs.next()) {
				
				/*
				 * java.sql.Date date1 = rs.getDate(2);
				 * 
				 * long timeInMilliSeconds = date1.getTime();
				 * 
				 * Date data = new Date(timeInMilliSeconds); LocalDate localDate =
				 * data.toInstant().atZone( ZoneId.systemDefault() ).toLocalDate();
				 */
		        
				Venda v = new Venda(rs.getInt(1), LocalDate.now() , rs.getDouble(3), new ArrayList<ItemVenda>());
				vendas.add(v);
			}

			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return vendas;
	}

}
