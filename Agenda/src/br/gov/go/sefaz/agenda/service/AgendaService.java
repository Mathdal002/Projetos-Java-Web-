package br.gov.go.sefaz.agenda.service;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import br.gov.go.sefaz.agenda.dao.AgendaDao;
import br.gov.go.sefaz.agenda.model.Contato;
import br.gov.go.sefaz.agenda.util.AgendaRelatorioPdf;

public class AgendaService {

	private AgendaDao agendaDao;

	public AgendaService() {
		super();
		agendaDao = new AgendaDao();
	}
	
	public void inserirContato(Contato contato) {
		this.agendaDao.inserirContato(contato);
	}
	
	public List<Contato> listarContatos() {
		return this.agendaDao.listarContatos();
	}
	
	public Contato selecionarContato(String idContato) {
		return this.agendaDao.selecionarContato(idContato);
	}
	
	public void alterarContato(Contato contato) {
		this.agendaDao.alterarContato(contato);
	}
	
	public void deletarContato(Contato contato) {
		this.agendaDao.deletarContato(contato);
	}
	
	// Relatorio dos Contatos em PDF
	public void gerarRelatorio(OutputStream relatorioOutputStream) {
		try {
			ArrayList<Contato> listaContatos = (ArrayList<Contato>) agendaDao.listarContatos();
			String titulo = "Relatório - Lista de Contatos Cadastrados";
			String assunto = "Lista de Contatos Cadastrados";
			String autor = "Matheus Albuquerque";
			AgendaRelatorioPdf relatorioContatosCadastradosPdf = new AgendaRelatorioPdf(titulo, assunto, autor, listaContatos, relatorioOutputStream);
			relatorioContatosCadastradosPdf.gerarPdf();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}