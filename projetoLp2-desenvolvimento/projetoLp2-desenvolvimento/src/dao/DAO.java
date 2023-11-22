package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Controller.Conexao;
import Model.Cliente;
import Model.Usuario;

public class DAO {
	private static PreparedStatement preparedStatement = null;
	private static ResultSet resultSet = null;

	private static String CADASTRAR_CLIENTE = " INSERT INTO CLIENTE  "
			+ " (ID, NOME, CPF, EMAIL, TELEFONE, ENDERECO) " + " VALUES (NULL, ?, ?, ?, ?, ?) ";

	private static String CONSULTAR_CLIENTE = " SELECT * FROM CLIENTE  " + " WHERE ID = ? ";

	private static String ALTERAR_CLIENTE = " UPDATE CLIENTE  SET "
			+ " NOME = ?, CPF = ?, EMAIL = ?, TELEFONE = ?, ENDERECO = ? " + " WHERE ID = ? ";

	private static String EXCLUIR_CLIENTE = " DELETE FROM CLIENTE  " + " WHERE ID = ? ";

	private static String LISTAR_CLIENTES = " SELECT * FROM CLIENTE  " + " WHERE 1=1 ";

	private static String CONSULTAR_USUARIO = " SELECT USUARIO, SENHA  " + " FROM USUARIO " + " WHERE USUARIO = ? "
			+ " AND SENHA = ? ";

	public DAO() {

	}

	public Cliente consultarCliente(String ID) throws Exception {
		Connection connection = Conexao.getInstancia().abrirConexao();
		Cliente cliente  = null;
		String query = CONSULTAR_CLIENTE;
		
		try {
		preparedStatement = connection.prepareStatement(query);
		
		int i = 1;
		preparedStatement.setString(i++, ID);
		
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()){
	
			cliente = new Cliente(resultSet.getString("ID"),
					resultSet.getString("nome"),
					resultSet.getString("CPF"),
					resultSet.getString("EMAIL"),
					resultSet.getString("TELEFONE"),
					resultSet.getString("ENDERECO"));
		}
		
	}catch(SQLException e) {
		e.printStackTrace();
	}finally {
		fecharConexao();
	}
		if (cliente == null) {
			JOptionPane.showMessageDialog(null, "Não foi possível localizar o cliente selecionado ", "", JOptionPane.WARNING_MESSAGE);
			throw new Exception("Não foi possível localizar o cliente selecionado");
		}
		return cliente;
	}
	public void cadastrarCliente(Cliente cliente)  {
			Connection connection = Conexao.getInstancia().abrirConexao();
			
			String query = CADASTRAR_CLIENTE;
			
			try {
			preparedStatement = connection.prepareStatement(query);
			
			int i = 1;
			preparedStatement.setString(i++, cliente.getNome());
			preparedStatement.setString(i++, cliente.getCPF());
			preparedStatement.setString(i++, cliente.geteMail());
			preparedStatement.setString(i++, cliente.getTelefone());
			preparedStatement.setString(i++, cliente.getEndereco());
			
			preparedStatement.execute();
			//connection.commit();
			
			JOptionPane.showMessageDialog(null, "Cliente incluído com sucesso");
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			fecharConexao();
		}}

	public void alterarCliente(String ID, Cliente cliente) {
		Connection connection = Conexao.getInstancia().abrirConexao();
		
		String query = ALTERAR_CLIENTE;
		
		try {
		preparedStatement = connection.prepareStatement(query);
		
		int i = 1;
		preparedStatement.setString(i++, cliente.getNome());
		preparedStatement.setString(i++, cliente.getCPF());
		preparedStatement.setString(i++, cliente.geteMail());
		preparedStatement.setString(i++, cliente.getTelefone());
		preparedStatement.setString(i++, cliente.getEndereco());
		preparedStatement.setString(i++, ID);
		
		preparedStatement.execute();
		//connection.commit();
		
		JOptionPane.showMessageDialog(null, "Cliente alterado com sucesso");
	}catch(SQLException e) {
		e.printStackTrace();
	}finally {
		fecharConexao();
	}}

	public void excluirCliente(String ID)  {
		Connection connection = Conexao.getInstancia().abrirConexao();
		
		String query = EXCLUIR_CLIENTE;
		
		try {
		preparedStatement = connection.prepareStatement(query);
		
		int i = 1;
		
		preparedStatement.setString(i++, ID);
		
		preparedStatement.execute();
		//connection.commit();
		
		JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso");
	}catch(SQLException e) {
		e.printStackTrace();
	}finally {
		fecharConexao();
	}}

	private void fecharConexao() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			Conexao.getInstancia().fecharConexao();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public ArrayList<Cliente> listarCliente() throws Exception {
		Connection connection = Conexao.getInstancia().abrirConexao();
		ArrayList<Cliente> clientes = new ArrayList<>();
		
		
		String query = LISTAR_CLIENTES;
		
		try {
		preparedStatement = connection.prepareStatement(query);
		
		
		
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()){
	
			clientes.add( new Cliente(resultSet.getString("ID"),
					resultSet.getString("nome"),
					resultSet.getString("CPF"),
					resultSet.getString("EMAIL"),
					resultSet.getString("TELEFONE"),
					resultSet.getString("ENDERECO")));
		}
		
	}catch(SQLException e) {
		e.printStackTrace();
	}finally {
		fecharConexao();
	}
		if (clientes.size() < 0) {
			JOptionPane.showMessageDialog(null, "Não há clientes cadastrados ", "", JOptionPane.WARNING_MESSAGE);
			throw new Exception("Não há clientes cadastrados");
		}
		return clientes;
	}

	
	public Usuario consultarUsuario(String nomeUsuario, String senhaCriptografada) throws Exception {
	Connection connection = Conexao.getInstancia().abrirConexao();
	Usuario usuario  = null;
	String query = CONSULTAR_USUARIO;
	
	try {
	preparedStatement = connection.prepareStatement(query);
	
	int i = 1;
	preparedStatement.setString(i++, nomeUsuario);
	preparedStatement.setString(i++, senhaCriptografada);
	
	resultSet = preparedStatement.executeQuery();
	while (resultSet.next()){

		usuario = new Usuario(resultSet.getInt("ID"),
				resultSet.getString("USUARIO"),
				resultSet.getString("SENHA"));
				
	}
	
}catch(SQLException e) {
	e.printStackTrace();
}finally {
	fecharConexao();
}
	if (usuario == null) {
		JOptionPane.showMessageDialog(null, "Não foi possível localizar o usuario selecionado ", "", JOptionPane.WARNING_MESSAGE);
		throw new Exception("Não foi possível localizar o usuario selecionado");
	}
	return usuario;
}
}

