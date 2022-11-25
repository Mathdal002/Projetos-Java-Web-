// ***  AgendaController = Controller ***
package br.gov.go.sefaz.agenda.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.gov.go.sefaz.agenda.model.Contato;
import br.gov.go.sefaz.agenda.service.AgendaService;

@WebServlet(urlPatterns = { "/controller", "/main", "/insert", "/select", "/update", "/delete", "/report", "/report-file" })
public class AgendaController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String relatorioArquivoPath = "D:\\";

	private AgendaService agendaService;
	private Contato contato;

	public AgendaController() {
		super();
		this.agendaService = new AgendaService();
		contato = new Contato();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		System.out.println(action);
		if (action.equals("/main")) {
			listarContatos(request, response);
		} else if (action.equals("/insert")) {
			novoContato(request, response);
		} else if (action.equals("/update")) {
			editarContato(request, response);
		} else if (action.equals("/delete")) {
			removerContato(request, response);
		} else if (action.equals("/select")) {
			listarContato(request, response);
		} else if (action.equals("/report")) {
			gerarRelatorio(request, response);
		} else if (action.equals("/report-file")) {
			gerarRelatorioArquivo(request, response);
		} else {
			response.sendRedirect("index.html");
		}
	}

	// Listar contatos
	protected void listarContatos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Criando um objeto que irá receber os dados Contato(JavaBeans)
		ArrayList<Contato> lista = (ArrayList<Contato>) this.agendaService.listarContatos();
		// Encaminhar a lista ao documento agenda.jsp
		request.setAttribute("contatos", lista);
		RequestDispatcher requestdispatcher = request.getRequestDispatcher("agenda.jsp");
		requestdispatcher.forward(request, response);
	}

	// Novo contato
	protected void novoContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// setar as variáveis Contato(javaBeans)
		contato.setNome(request.getParameter("nome"));
		contato.setFone(request.getParameter("fone"));
		contato.setEmail(request.getParameter("email"));
		// invocar o método inserirContatos passando o objeto contato
		this.agendaService.inserirContato(contato);
		// redirecionar para o documento agenda.jsp
		response.sendRedirect("main");
	}

	// Editar Contato
	protected void listarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recebimento do id do contato que sera editado
		String idcon = request.getParameter("idcon");
		// Executar o método selecionarContato (DAO)
		contato = this.agendaService.selecionarContato(idcon);
		// setar os atributos do formulário com o conteúdo Java
		request.setAttribute("idcon", contato.getIdcon());
		request.setAttribute("nome", contato.getNome());
		request.setAttribute("fone", contato.getFone());
		request.setAttribute("email", contato.getEmail());
		// Encaminhar ao documento editar.jsp
		RequestDispatcher requestdispatcher = request.getRequestDispatcher("Editar.jsp");
		requestdispatcher.forward(request, response);
	}

	protected void editarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Setar as variáveis Contato(JavaBeans)
		contato.setIdcon(request.getParameter("idcon"));
		contato.setNome(request.getParameter("nome"));
		contato.setFone(request.getParameter("fone"));
		contato.setEmail(request.getParameter("email"));
		// executar o método alterarContato
		this.agendaService.alterarContato(contato);
		// Redirecionar para o documento agenda.jsp (atualizando as alterações)
		response.sendRedirect("main");
	}
	
	// Remover um Contato
	protected void removerContato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Recebimento do id do contato a ser excluido (validador.js)
		String idcon = request.getParameter("idcon");
		// setar a variavel idcon Contato(JavaBeans)
		contato.setIdcon(idcon);
		// executar o método removerContato
		this.agendaService.deletarContato(contato);
		// Redirecionar para o documento agenda.jsp (atualizando as alterações)
		response.sendRedirect("main");
	}
	
	// Relatorio dos Contatos em PDF
	protected void gerarRelatorio(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			response.addHeader("Content-Disposition", "attachment; filename=" + "relatorio-contatos.pdf");
			response.setContentType("application/pdf");
			this.agendaService.gerarRelatorio(response.getOutputStream());
 			response.sendRedirect("main");
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	protected void gerarRelatorioArquivo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OutputStream relatorioContatosCadastradosPdfFileOutputStream = null;
		try {
			relatorioContatosCadastradosPdfFileOutputStream = new FileOutputStream(relatorioArquivoPath + "relatorio-contatos-cadastrados.pdf");
			this.agendaService.gerarRelatorio(relatorioContatosCadastradosPdfFileOutputStream);
			response.sendRedirect("main");
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if(relatorioContatosCadastradosPdfFileOutputStream != null) {
				relatorioContatosCadastradosPdfFileOutputStream.close();
			}
		}
	}
}