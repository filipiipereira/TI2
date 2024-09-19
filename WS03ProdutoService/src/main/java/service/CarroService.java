package service;

import java.util.Scanner;
import java.io.File;
import java.util.List;
import dao.CarroDAO;
import model.Carro;
import spark.Request;
import spark.Response;


public class CarroService {

	private CarroDAO carroDAO = new CarroDAO();
	private String form;
	private final int FORM_INSERT = 1;
	private final int FORM_DETAIL = 2;
	private final int FORM_UPDATE = 3;
	private final int FORM_ORDERBY_ID = 1;
	private final int FORM_ORDERBY_MODELO = 2;
	private final int FORM_ORDERBY_PRECO = 3;
	
	
	public CarroService() {
		makeForm();
	}

	
	public void makeForm() {
		makeForm(FORM_INSERT, new Carro(), FORM_ORDERBY_MODELO);
	}

	
	public void makeForm(int orderBy) {
		makeForm(FORM_INSERT, new Carro(), orderBy);
	}

	
	public void makeForm(int tipo, Carro carro, int orderBy) {
		String nomeArquivo = "form.html";
		form = "";
		try{
			Scanner entrada = new Scanner(new File(nomeArquivo));
		    while(entrada.hasNext()){
		    	form += (entrada.nextLine() + "\n");
		    }
		    entrada.close();
		}  catch (Exception e) { System.out.println(e.getMessage()); }
		
		String umCarro = "";
		if(tipo != FORM_INSERT) {
			umCarro += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umCarro += "\t\t<tr>";
			umCarro += "\t\t\t<td align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;<a href=\"/carro/list/1\">Novo Carro</a></b></font></td>";
			umCarro += "\t\t</tr>";
			umCarro += "\t</table>";
			umCarro += "\t<br>";			
		}
		
		if(tipo == FORM_INSERT || tipo == FORM_UPDATE) {
			String action = "/carro/";
			String name, modelo, buttonLabel;
			if (tipo == FORM_INSERT){
				action += "insert";
				name = "Inserir Carro";
				modelo = "sandero, onix, ...";
				buttonLabel = "Inserir";
			} else {
				action += "update/" + carro.getID();
				name = "Atualizar Carro (ID " + carro.getID() + ")";
				modelo = carro.getModelo();
				buttonLabel = "Atualizar";
			}
			umCarro += "\t<form class=\"form--register\" action=\"" + action + "\" method=\"post\" id=\"form-add\">";
			umCarro += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umCarro += "\t\t<tr>";
			umCarro += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;" + name + "</b></font></td>";
			umCarro += "\t\t</tr>";
			umCarro += "\t\t<tr>";
			umCarro += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
			umCarro += "\t\t</tr>";
			umCarro += "\t\t<tr>";
			umCarro += "\t\t\t<td>&nbsp;Modelo: <input class=\"input--register\" type=\"text\" name=\"modelo\" value=\""+ modelo +"\"></td>";
			umCarro += "\t\t\t<td>Cor: <input class=\"input--register\" type=\"text\" name=\"cor\" value=\""+ carro.getCor() +"\"></td>";
			umCarro += "\t\t\t<td>Marca: <input class=\"input--register\" type=\"text\" name=\"marca\" value=\""+ carro.getMarca() +"\"></td>";
			umCarro += "\t\t\t<td>Preco: <input class=\"input--register\" type=\"text\" name=\"preco\" value=\""+ carro.getPreco() +"\"></td>";
			umCarro += "\t\t\t<td>Ano: <input class=\"input--register\" type=\"text\" name=\"ano\" value=\""+ carro.getAno() +"\"></td>";
			umCarro += "\t\t</tr>";
			umCarro += "\t\t<tr>";
			umCarro += "\t\t\t<td align=\"center\"><input type=\"submit\" value=\""+ buttonLabel +"\" class=\"input--main__style input--button\"></td>";
			umCarro += "\t\t</tr>";
			umCarro += "\t</table>";
			umCarro += "\t</form>";		
		} else if (tipo == FORM_DETAIL){
			umCarro += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umCarro += "\t\t<tr>";
			umCarro += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Detalhar Carro (ID " + carro.getID() + ")</b></font></td>";
			umCarro += "\t\t</tr>";
			umCarro += "\t\t<tr>";
			umCarro += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
			umCarro += "\t\t</tr>";
			umCarro += "\t\t<tr>";
			umCarro += "\t\t\t<td>&nbsp;Cor: "+ carro.getCor() +"</td>";
			umCarro += "\t\t\t<td>&nbsp;Modelo: "+ carro.getModelo() +"</td>";
			umCarro += "\t\t\t<td>&nbsp;Marca: "+ carro.getMarca() +"</td>";
			umCarro += "\t\t\t<td>Preco: "+ carro.getPreco() +"</td>";
			umCarro += "\t\t\t<td>Ano: "+ carro.getAno() +"</td>";
			umCarro += "\t\t</tr>";
			umCarro += "\t</table>";		
		} else {
			System.out.println("ERRO! Tipo não identificado " + tipo);
		}
		form = form.replaceFirst("<UM-CARRO>", umCarro);
		
		String list = new String("<table width=\"80%\" align=\"center\" bgcolor=\"#f3f3f3\">");
		list += "\n<tr><td colspan=\"6\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Relação de Carros</b></font></td></tr>\n" +
				"\n<tr><td colspan=\"6\">&nbsp;</td></tr>\n" +
    			"\n<tr>\n" + 
        		"\t<td><a href=\"/carro/list/" + FORM_ORDERBY_ID + "\"><b>ID</b></a></td>\n" +
        		"\t<td><a href=\"/carro/list/" + FORM_ORDERBY_MODELO + "\"><b>Modelo</b></a></td>\n" +
        		"\t<td><a href=\"/carro/list/" + FORM_ORDERBY_PRECO + "\"><b>Preço</b></a></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Detalhar</b></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Atualizar</b></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Excluir</b></td>\n" +
        		"</tr>\n";
		
		List<Carro> carros;
		if (orderBy == FORM_ORDERBY_ID) {                 	carros = carroDAO.getOrderByID();
		} else if (orderBy == FORM_ORDERBY_MODELO) {		carros = carroDAO.getOrderByModelo();
		} else if (orderBy == FORM_ORDERBY_PRECO) {			carros = carroDAO.getOrderByPreco();
		} else {											carros = carroDAO.get();
		}

		int i = 0;
		String bgcolor = "";
		for (Carro p : carros) {
			bgcolor = (i++ % 2 == 0) ? "#fff5dd" : "#dddddd";
			list += "\n<tr bgcolor=\""+ bgcolor +"\">\n" + 
            		  "\t<td>" + p.getID() + "</td>\n" +
            		  "\t<td>" + p.getModelo() + "</td>\n" +
            		  "\t<td>" + p.getPreco() + "</td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"/carro/" + p.getID() + "\"><img src=\"/image/detail.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"/carro/update/" + p.getID() + "\"><img src=\"/image/update.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"javascript:confirmarDeleteCarro('" + p.getID() + "', '" + p.getModelo() + "', '" + p.getPreco() + "');\"><img src=\"/image/delete.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "</tr>\n";
		}
		list += "</table>";		
		form = form.replaceFirst("<LISTAR-CARRO>", list);				
	}
	
	
	public Object insert(Request request, Response response) {
		String cor = request.queryParams("cor");
		String modelo = request.queryParams("modelo");
		String marca = request.queryParams("marca");
		float preco = Float.parseFloat(request.queryParams("preco"));
		int ano = Integer.parseInt(request.queryParams("ano"));

		
		String resp = "";
		
		Carro carro = new Carro(-1, cor, modelo, marca, preco, ano);
		
		if(carroDAO.insert(carro) == true) {
            resp = "Carro (" + modelo + ") inserido!";
            response.status(201); // 201 Created
		} else {
			resp = "Carro (" + modelo + ") não inserido!";
			response.status(404); // 404 Not found
		}
			
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}

	
	public Object get(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));		
		Carro carro = (Carro) carroDAO.get(id);
		
		if (carro != null) {
			response.status(200); // success
			makeForm(FORM_DETAIL, carro, FORM_ORDERBY_MODELO);
        } else {
            response.status(404); // 404 Not found
            String resp = "Carro " + id + " não encontrado.";
    		makeForm();
    		form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");     
        }

		return form;
	}

	
	public Object getToUpdate(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));		
		Carro carro = (Carro) carroDAO.get(id);
		
		if (carro != null) {
			response.status(200); // success
			makeForm(FORM_UPDATE, carro, FORM_ORDERBY_MODELO);
        } else {
            response.status(404); // 404 Not found
            String resp = "Carro " + id + " não encontrado.";
    		makeForm();
    		form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");     
        }

		return form;
	}
	
	
	public Object getAll(Request request, Response response) {
		int orderBy = Integer.parseInt(request.params(":orderby"));
		makeForm(orderBy);
	    response.header("Content-Type", "text/html");
	    response.header("Content-Encoding", "UTF-8");
		return form;
	}			
	
	public Object update(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
		Carro carro = carroDAO.get(id);
        String resp = "";       

        if (carro != null) {
        	carro.setCor(request.queryParams("cor"));
        	carro.setModelo(request.queryParams("modelo"));
        	carro.setMarca(request.queryParams("marca"));
        	carro.setPreco(Float.parseFloat(request.queryParams("preco")));
        	carro.setAno(Integer.parseInt(request.queryParams("ano")));
        	carroDAO.update(carro);
        	response.status(200); // success
            resp = "Carro (ID " + carro.getID() + ") atualizado!";
        } else {
            response.status(404); // 404 Not found
            resp = "Carro (ID \" + carro.getId() + \") não encontrado!";
        }
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}

	
	public Object delete(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        Carro carro = carroDAO.get(id);
        String resp = "";       

        if (carro != null) {
            carroDAO.delete(id);
            response.status(200); // success
            resp = "Carro (" + id + ") excluído!";
        } else {
            response.status(404); // 404 Not found
            resp = "Carro (" + id + ") não encontrado!";
        }
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}
}