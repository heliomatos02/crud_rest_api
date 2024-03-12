package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.h2.jdbc.JdbcSQLSyntaxErrorException;

import exception.ColecaoException;
import jdk.internal.net.http.common.Log;
import model.Fornecedor;

public class ColecaoFornecedorBdr implements ColecaoFornecedor{
	
	private Connection conexao;

	public ColecaoFornecedorBdr(Connection conexao) {
		this.conexao = conexao;
	}

	@Override
	public List<Fornecedor> todos() throws ColecaoException {
		PreparedStatement ps =null;
		ResultSet rs =null;
		List<Fornecedor> ls= null;
		try {
			ls = new ArrayList<Fornecedor>();
			String sql = "SELECT ID, NOME, CPFCNPJ, RAMOATUACAO FROM FORNECEDOR";
			ps = this.conexao.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				Fornecedor f = new Fornecedor(rs.getInt("ID"),rs.getString("NOME"),rs.getString("CPFCNPJ"),rs.getString("RAMOATUACAO"));
				ls.add(f);
			}
		}catch (SQLException e) {
			throw new ColecaoException("Erro ao recuperar Fornecedor do banco de dados!");
		}finally {
			try {
				ps.close();
				if(rs!=null) {
					rs.close();
				}
			}catch (SQLException e) {
				throw new ColecaoException("Erro ao fechar manipuladores de banco de dados!");
			}
		}
		return ls;
	}

	@Override
	public Fornecedor porId(int id) throws ColecaoException {
		PreparedStatement ps =null;
		ResultSet rs =null;
		Fornecedor fornecedor;
		try {
			fornecedor = new Fornecedor();
			String sql = "SELECT ID, NOME, CPFCNPJ, RAMOATUACAO FROM FORNECEDOR WHERE ID=?";
			ps = this.conexao.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				fornecedor = new Fornecedor(rs.getInt("ID"),rs.getString("NOME"),rs.getString("CPFCNPJ"),rs.getString("RAMOATUACAO"));
			}
		}catch (SQLException e) {
			throw new ColecaoException("Erro ao recuperar Fornecedor do banco de dados!");
		}finally {
			try {
				ps.close();
				if(rs!=null) {
					rs.close();
				}
			}catch (SQLException e) {
				throw new ColecaoException("Erro ao fechar manipuladores de banco de dados!");
			}
		}
		return fornecedor;
	}

	@Override
	public List<Fornecedor> porNome(String nome) throws ColecaoException {
		PreparedStatement ps =null;
		ResultSet rs =null;
		List<Fornecedor> lf;
		try {
			lf = new ArrayList<Fornecedor>();
			String sql = "SELECT ID, NOME, CPFCNPJ, RAMOATUACAO FROM FORNECEDOR WHERE NOME=?";
			ps = this.conexao.prepareStatement(sql);
			ps.setString(1, nome);
			rs = ps.executeQuery();
			while(rs.next()) {
				Fornecedor fornecedor = new Fornecedor(rs.getInt("ID"),rs.getString("NOME"),rs.getString("CPFCNPJ"),rs.getString("RAMOATUACAO"));
				lf.add(fornecedor);
			}
		}catch (SQLException e) {
			throw new ColecaoException("Erro ao recuperar Fornecedor do banco de dados!");
		}finally {
			try {
				ps.close();
				if(rs!=null) {
					rs.close();
				}
			}catch (SQLException e) {
				throw new ColecaoException("Erro ao fechar manipuladores de banco de dados!");
			}
		}
		return lf;
	}

	@Override
	public Fornecedor porCnpjCpf(String cnpjCpf) throws ColecaoException {
		PreparedStatement ps =null;
		ResultSet rs =null;
		Fornecedor fornecedor;
		try {
			fornecedor = new Fornecedor();
			String sql = "SELECT ID, NOME, CPFCNPJ, RAMOATUACAO FROM FORNECEDOR WHERE CPFCNPJ=?";
			ps = this.conexao.prepareStatement(sql);
			ps.setString(1, cnpjCpf);
			rs = ps.executeQuery();
			while(rs.next()) {
				fornecedor = new Fornecedor(rs.getInt("ID"),rs.getString("NOME"),rs.getString("CPFCNPJ"),rs.getString("RAMOATUACAO"));
			}
		}catch (SQLException e) {
			throw new ColecaoException("Erro ao recuperar Fornecedor do banco de dados!");
		}finally {
			try {
				ps.close();
				if(rs!=null) {
					rs.close();
				}
			}catch (SQLException e) {
				throw new ColecaoException("Erro ao fechar manipuladores de banco de dados!");
			}
		}
		return fornecedor;
	}
	
	@Override
	public void inserir(Fornecedor objeto) throws ColecaoException {
		PreparedStatement ps =null;
		ResultSet rs =null;
		try {
			String sql = "INSERT INTO FORNECEDOR (NOME,CPFCNPJ,RAMOATUACAO) VALUES (?,?,?)";
			ps = this.conexao.prepareStatement(sql,	Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, objeto.getNome());
			ps.setString(2, objeto.getCpfCnpj());
			ps.setString(3, objeto.getRamoAtuacao());
			ps.execute();
			rs = ps.getGeneratedKeys();
			while(rs.next()) {
				objeto.setId(rs.getInt(1));
			}
		}catch (SQLException e) {
			throw new ColecaoException("Erro ao recuperar Fornecedor do banco de dados!");
		}finally {
			try {
				ps.close();
				if(rs!=null) {
					rs.close();
				}
			}catch (SQLException e) {
				throw new ColecaoException("Erro ao fechar manipuladores de banco de dados!");
			}
		}
	}

	@Override
	public void alterar(Fornecedor objeto) throws ColecaoException {
		PreparedStatement ps =null;
		try {
			String sql = "UPDATE FORNECEDOR SET NOME=?, CPFCNPJ=?, RAMOATUACAO=? WHERE ID=?";
			ps = this.conexao.prepareStatement(sql);
			ps.setString(1, objeto.getNome());
			ps.setString(2, objeto.getCpfCnpj());
			ps.setString(3, objeto.getRamoAtuacao());
			ps.setInt(4, objeto.getId());
			ps.execute();
		}catch (SQLException e) {
			throw new ColecaoException("Erro ao recuperar Fornecedor do banco de dados!");
		}finally {
			try {
				ps.close();
			}catch (SQLException e) {
				throw new ColecaoException("Erro ao fechar manipuladores de banco de dados!");
			}
		}
	}

	@Override
	public void remover(Fornecedor objeto) throws ColecaoException {
		PreparedStatement ps =null;
		try {
			String sql = "DELETE FROM FORNECEDOR WHERE ID=?";
			ps = this.conexao.prepareStatement(sql);
			ps.setInt(1, objeto.getId());
			ps.execute();
		}catch (SQLException e) {
			throw new ColecaoException("Erro ao recuperar Fornecedor do banco de dados!");
		}finally {
			try {
				ps.close();
			}catch (SQLException e) {
				throw new ColecaoException("Erro ao fechar manipuladores de banco de dados!");
			}
		}
		
	}
	
	public void criarTabela() {
		PreparedStatement ps = null;
		try {
			String query = "create table fornecedor " + "(id INTEGER NOT NULL auto_increment PRIMARY KEY, "
					+ "nome varchar(150), " + "cpfCnpj varchar(50), " + "ramoAtuacao varchar(50))";

			ps = this.conexao.prepareStatement(query);
			ps.execute();
		}catch(JdbcSQLSyntaxErrorException jdbc) {
			System.out.println(jdbc.toString());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/***
	 * se tiver mais de uma tabela, significa que todas as tabelas já foram criadas
	 * @return
	 */
	public boolean verificaTabelas() {
		PreparedStatement ps = null;
		ResultSet rs;
		try {
			String sql = ("show tables");
			ps = this.conexao.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}
	
	}
