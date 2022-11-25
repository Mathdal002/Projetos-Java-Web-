<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="br.gov.go.sefaz.agenda.model.Contato"%>
<%@ page import="java.util.ArrayList"%>
<% 
    ArrayList<Contato> lista = (ArrayList<Contato>) 
request.getAttribute("contatos");

 %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="utf-8">
<title>Agenda de contatos</title>
<link rel="icon" href="imagens/favicon.png">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<h1>Agenda de Contatos:</h1>
	<a href="novo.html" class="Botao1">Adicionar</a>
	<a href="report" class="Botao2"> Relatório</a>
	<a href="report-file" class="Botao3"> Relatório em Arquivo</a>
    <table id="tabela">
        <thead>
             <tr>
                 <th>ID</th>
                 <th>Nome</th>
                 <th>Fone</th>
                 <th>E-mail</th>
                 <th>opções</th>             
             </tr>
         </thead>
         <tbody>
             <%for (int i = 0; i < lista.size(); i++) { %>
                <tr>
                    <td><%=lista.get(i).getIdcon()%></td>
                    <td><%=lista.get(i).getNome()%></td>
                    <td><%=lista.get(i).getFone()%></td>
                    <td><%=lista.get(i).getEmail()%></td>
                    <td><a href="select?idcon=<%=lista.get(i).getIdcon() %>" class="Botao1">Editar</a></td>
                    <td><a href="javascript: confirmar(<%=lista.get(i).getIdcon() %>)" class="Botao2">Excluir</a></td>
                </tr>
             <%} %>
         </tbody>    
    </table>
    <script src="scripts/confirmador.js"></script>
</body>
</html>