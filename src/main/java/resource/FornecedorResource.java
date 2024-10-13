package resource;

import java.sql.Connection;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;



import exception.ColecaoException;
import exception.ConexaoException;
import model.Fornecedor;
import persistence.ColecaoFornecedor;
import persistence.ColecaoFornecedorBdr;
import persistence.ConexaoSingleton;
import security.Authorize;

@Path("/fornecedor")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FornecedorResource {

	@Authorize
	@GET
	@Path("/")
	public List<Fornecedor> todos() throws ConexaoException, ColecaoException{
		atualizaBanco();
		Connection con = ConexaoSingleton.getConexao();
		ColecaoFornecedor cf = new ColecaoFornecedorBdr(con);
		return cf.todos();
		
	}
	
	@Authorize
	@GET
	@Path("/{id:[0-9]+}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Fornecedor porId(@PathParam("id") int id) throws ConexaoException, ColecaoException{
		atualizaBanco();
		Connection con = ConexaoSingleton.getConexao();
		ColecaoFornecedor cf = new ColecaoFornecedorBdr(con);
		return cf.porId(id);
		
	}
	
	@Authorize
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	public String inserir(Fornecedor fornecedor) throws ConexaoException, ColecaoException {
		atualizaBanco();
		Connection con = ConexaoSingleton.getConexao();
		ColecaoFornecedor cf = new ColecaoFornecedorBdr(con);
		cf.inserir(fornecedor);
		return "Registro inserido com sucesso!";
	}
	
	@Authorize
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	public String alterar(Fornecedor fornecedor) throws ConexaoException, ColecaoException {
		atualizaBanco();
		Connection con = ConexaoSingleton.getConexao();
		ColecaoFornecedor cf = new ColecaoFornecedorBdr(con);
		cf.alterar(fornecedor);
		return "Registro alterado com sucesso!";
	}
	
	@Authorize
	@DELETE
	@Path("/{id:[0-9]+}")
	public String deletar(@PathParam("id") int id) throws ConexaoException, ColecaoException {
		atualizaBanco();
		Connection con = ConexaoSingleton.getConexao();
		Fornecedor f = new Fornecedor();
		f.setId(id);
		ColecaoFornecedor cf = new ColecaoFornecedorBdr(con);
		cf.remover(f);
		return "Registro deletado com sucesso!";
	}
	
	private void atualizaBanco() throws ConexaoException {
		Connection con = ConexaoSingleton.getConexao();
		ColecaoFornecedorBdr cf = new ColecaoFornecedorBdr(con);
		if(cf.verificaTabelas()) {
			cf.criarTabela();
		}
		
	}
}
