package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import exception.ConexaoException;

public class ConexaoSingleton {

	private static Connection conexao;
	
	private static Connection novaConexao() throws ConexaoException {
		Connection con = null;
			try {
				Class.forName("org.h2.Driver"); 
				con = DriverManager.getConnection("jdbc:h2:~/test");
			} catch (SQLException e) {
				throw new ConexaoException("Não foi possível conectar com banco de dados");
			}catch (ClassNotFoundException e) {
				throw new ConexaoException("Erro drive banco de dados.");
			}
			return con;
		
	}
	
	public static Connection getConexao() throws ConexaoException {
		if(conexao == null) {
			conexao = novaConexao();
		}
		return conexao;
	}
	
	public static void encerraConexao() throws ConexaoException {
		try {
			conexao.close();
		}catch (SQLException e) {
			throw new ConexaoException("Erro ao fechar banco de dados.");
		}
	}
	
	
}
