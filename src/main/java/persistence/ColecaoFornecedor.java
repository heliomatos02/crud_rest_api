package persistence;

import java.util.List;

import exception.ColecaoException;
import model.Fornecedor;

public interface ColecaoFornecedor extends Colecao<Fornecedor>{

	public List<Fornecedor> porNome(String nome) throws ColecaoException;
	public Fornecedor porCnpjCpf(String cnpjCpf) throws ColecaoException;
}
