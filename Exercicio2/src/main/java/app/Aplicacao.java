package app;

import java.util.List;
import java.util.*;

import dao.UsuarioDAO;
import model.Usuario;

public class Aplicacao {
	private static UsuarioDAO usuarioDAO;
	public static void main(String[] args) throws Exception {
		
		usuarioDAO = new UsuarioDAO();
		Scanner scanner = new Scanner(System.in);
		int escolha = 0;
		do {
			System.out.println("Escolha uma opção: ");
			System.out.println("1-Listar\n2-Inserir\n3-Excluir\n4-Atualizar\n5-Sair");
			escolha = scanner.nextInt();
			switch(escolha) {
			case 1: listar();
					break;
			case 2: inserir(scanner);
					break;
			case 3: excluir(scanner);
					break;
			case 4: atualizar(scanner);
					break;
			case 5: System.out.println("Fim do programa");
					break;
			default: System.out.println("Escolha invalida");
			}
		}while(escolha != 5);
		usuarioDAO.close();
		scanner.close();
	}
	
	private static void listar() {
		List<Usuario> usuarios = usuarioDAO.getOrderByCodigo();
		for(Usuario u : usuarios) {
			System.out.println(u.toString());
		}
	}
	private static void inserir(Scanner scanner) {
		System.out.println("Digite o codigo do  novo usuario: ");
		int codigo = scanner.nextInt();
		System.out.println("Digite o login do novo usuario: ");
		scanner.nextLine();
		String login = scanner.nextLine();
		System.out.println("Digite a senha do novo usuario: ");
		String senha = scanner.nextLine();
		System.out.println("Digite o sexo do novo usuario: ");
		char sexo = scanner.nextLine().charAt(0);
		Usuario novoUsuario = new Usuario(codigo, login, senha, sexo);
		usuarioDAO.insert(novoUsuario);
	}
	private static void excluir(Scanner scanner) {
		System.out.println("Digite o codigo do usuario a ser removido: ");
		int codigo = scanner.nextInt();
		usuarioDAO.delete(codigo);
	}
	private static void atualizar(Scanner scanner) {
		System.out.println("Digite o codigo do usuario a ser atualizado: ");
		int codigo = scanner.nextInt();
		Usuario usuarioAtualizado = usuarioDAO.get(codigo);
		if(usuarioAtualizado != null) {
			System.out.println("Digite o novo login do usuario: ");
			scanner.nextLine();
			String login = scanner.nextLine();
			System.out.println("Digite a nova senha do usuario: ");
			String senha = scanner.nextLine();
			System.out.println("Digite o novo sexo usuario: ");
			char sexo = scanner.nextLine().charAt(0);
			usuarioAtualizado.setLogin(login);
			usuarioAtualizado.setSenha(senha);
			usuarioAtualizado.setSexo(sexo);
			usuarioDAO.update(usuarioAtualizado);
		}
		else {
			System.out.println("Nao existe usuario com esse codigo");
		}
	}
}