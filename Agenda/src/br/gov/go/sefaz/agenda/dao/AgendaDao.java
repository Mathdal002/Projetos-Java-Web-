// *** *AgendaDao = Dao ***
package br.gov.go.sefaz.agenda.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.gov.go.sefaz.agenda.model.Contato;

public class AgendaDao {

	// Módulo de conexão
	// Parâmetros de conexão
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3306/estudos?useTimezone=true&serverTimezone=UTC";
	private String user = "root";
	private String password = "root";

	// Método de conexão
	private Connection conectar() {
		Connection connection = null;
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, user, password);
			return connection;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	/** CRUD CREATE **/
	public void inserirContato(Contato contato) {
		String sqlInsertContato = "insert into contatos(nome,fone,email) values(?,?,?)";
		// Objetos de conexão com o banco de dados
		Connection connection = null;
		PreparedStatement preparedstatement = null;
		try {
			// abrir a conexão
			connection = conectar();
			// preparar a query para execução no banco de dados
			preparedstatement = connection.prepareStatement(sqlInsertContato);
			// Substituir os parâmetros(?)pelo conteúdo das variáveis Contato(JavaBeans)
			preparedstatement.setString(1, contato.getNome());
			preparedstatement.setString(2, contato.getFone());
			preparedstatement.setString(3, contato.getEmail());
			// Executa a query
			preparedstatement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				// Fecha o PreparedStatement
				if(preparedstatement != null) {
					preparedstatement.close();
				}
				// Fecha a conexão com o banco de dados
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/** CRUD READ **/
	public List<Contato> listarContatos() {
		ArrayList<Contato> contatos = null;
		String sqlSelectContatos = "select * from contatos order by nome";
		// Objetos de conexão com o banco de dados
		Connection connection = null;
		PreparedStatement preparedstatement = null;
		ResultSet resultset = null;
		// Criando um objeto para acessar a classe Contato(JavaBeans)
		try {
			connection = conectar();
			preparedstatement = connection.prepareStatement(sqlSelectContatos);
			resultset= preparedstatement.executeQuery();
			contatos = new ArrayList<>();
			// o laço abaixo será executado enquanto houver contatos
			while (resultset.next()) {
				// variaveis de apoio que recebem os dados do banco
				String idcon = resultset.getString(1);
				String nome = resultset.getString(2);
				String fone = resultset.getString(3);
				String email = resultset.getString(4);
				// populando o ArrayList
				contatos.add(new Contato(idcon, nome, fone, email));
			}
			return contatos;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		} finally {
			try {
				// Fecha o ResultSet
				if(resultset != null) {
					resultset.close();
				}
				// Fecha o PreparedStatement
				if(preparedstatement != null) {
					preparedstatement.close();
				}
				// Fecha a conexão com o banco de dados
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
	}
	
	/** CRUD UPDATE **/
	// Selecionar o contato
	public Contato selecionarContato(String idContato) {
		String read2 = "select * from contatos where idcon = ?";
		Connection connection = null;
		PreparedStatement preparedstatement = null;
		try {
			connection = conectar();
			preparedstatement = connection.prepareStatement(read2);
			preparedstatement.setString(1, idContato);
			Contato contato = new Contato();
			ResultSet resultSet = preparedstatement.executeQuery();
			while (resultSet.next()) {
				// Setar as variáveis Contato(JavaBeans)
			    contato.setIdcon(resultSet.getString(1));
				contato.setNome(resultSet.getString(2));
				contato.setFone(resultSet.getString(3));
				contato.setEmail(resultSet.getString(4));
			}
			return contato;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		} finally {
			try {
				// Fecha o PreparedStatement
				if(preparedstatement != null) {
					preparedstatement.close();
				}
				// Fecha a conexão com o banco de dados
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
	}
	
	// Editar contato
	public void alterarContato(Contato contato) {
		String create = "update contatos set nome=?,fone=?,email=? where idcon=?";
		Connection connection = null;
		PreparedStatement preparedstatement = null;
		try {
			connection = conectar();
			preparedstatement = connection.prepareStatement(create);
			preparedstatement.setString(1, contato.getNome());
			preparedstatement.setString(2, contato.getFone());
			preparedstatement.setString(3, contato.getEmail());
			preparedstatement.setString(4, contato.getIdcon());
			preparedstatement.executeUpdate();
			connection.close();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				// Fecha o PreparedStatement
				if(preparedstatement != null) {
					preparedstatement.close();
				}
				// Fecha a conexão com o banco de dados
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
	}
	
	/** CRUD DELETE **/

	public void deletarContato(Contato contato) {
        String delete = "delete from contatos where idcon=?";
		Connection connection = null;
		PreparedStatement preparedstatement = null;
		try {
			connection = conectar();
			preparedstatement = connection.prepareStatement(delete);
			preparedstatement.setString(1, contato.getIdcon());
			preparedstatement.executeUpdate();
			connection.close();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				// Fecha o PreparedStatement
				if(preparedstatement != null) {
					preparedstatement.close();
				}
				// Fecha a conexão com o banco de dados
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
	}
}