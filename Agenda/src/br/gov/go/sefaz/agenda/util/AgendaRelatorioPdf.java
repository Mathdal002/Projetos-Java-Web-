package br.gov.go.sefaz.agenda.util;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import br.gov.go.sefaz.agenda.model.Contato;

public class AgendaRelatorioPdf {

    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
    
    private Document relatorioPdfDocument;
    private OutputStream relatorioPdfOutputStream;
    
    private String titulo;
    private String assunto;
    private String autor;
    
    private ArrayList<Contato> listaContatos;
    
    public AgendaRelatorioPdf(String titulo, String assunto, String autor, ArrayList<Contato> listaContatos, OutputStream relatorioPdfOutputStream) {
    	this.titulo = titulo;
    	this.assunto = assunto;
    	this.autor = autor;
    	this.listaContatos = listaContatos;
    	this.relatorioPdfOutputStream = relatorioPdfOutputStream;
	}
    
    public void gerarPdf() throws DocumentException {
    	createDocument();
    	addMetaData();
    	addContent();
    	closeDocument();
    }

    private void createDocument() throws DocumentException {
    	this.relatorioPdfDocument = new Document();
    	PdfWriter.getInstance(this.relatorioPdfDocument, this.relatorioPdfOutputStream);
    	this.relatorioPdfDocument.open();
    }
    
    private void addMetaData() {
        relatorioPdfDocument.addTitle(titulo);
        relatorioPdfDocument.addSubject(assunto);
        relatorioPdfDocument.addKeywords(assunto);
        relatorioPdfDocument.addAuthor(autor);
        relatorioPdfDocument.addCreator(autor);
    }

    private void addContent() throws DocumentException {
    	Paragraph footer = new Paragraph("Relatório gerado por: " + this.autor + ", " + new Date(), smallBold);
        Anchor anchor = new Anchor(this.titulo, catFont);
        anchor.setName(this.titulo);
        Chapter chapter = new Chapter(new Paragraph(anchor), 1);
        Paragraph subjectParagraph = new Paragraph(this.assunto, subFont);
        Section subjectParagraphSection = chapter.addSection(subjectParagraph);
        addEmptyLine(subjectParagraph, 1);
        subjectParagraphSection.add(createTable());
        subjectParagraphSection.add(footer);
        this.relatorioPdfDocument.add(chapter);
    }

    private PdfPTable createTable() {
        PdfPTable table = new PdfPTable(3);
        // t.setBorderColor(BaseColor.GRAY);
        // t.setPadding(4);
        // t.setSpacing(4);
        // t.setBorderWidth(1);
        PdfPCell c1 = new PdfPCell(new Phrase("NOME"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        c1 = new PdfPCell(new Phrase("FONE"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        c1 = new PdfPCell(new Phrase("E-MAIL"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        table.setHeaderRows(1);
		for (int i = 0; i < this.listaContatos.size(); i++) {
			table.addCell(this.listaContatos.get(i).getNome());
			table.addCell(this.listaContatos.get(i).getFone());
			table.addCell(this.listaContatos.get(i).getEmail());
		}
		return table;
    }

    private void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
    
    private void closeDocument() {
    	this.relatorioPdfDocument.close();
    }
}