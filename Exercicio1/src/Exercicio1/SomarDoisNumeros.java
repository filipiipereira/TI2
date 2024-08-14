package Exercicio1;

import java.util.*;

class SomarDoisNumeros {
	public static Scanner sc = new Scanner(System.in);
	public static void main(String args[]) {
		//declaracao de variaveis
		int x1, x2, soma;
		//leitura dos dois numeros
		System.out.println("Digite o primeiro numero: ");
		x1 = sc.nextInt();
		System.out.println("Digite o segundo numero: ");
		x2 = sc.nextInt();
		//somar
		soma = x1 + x2;
		//imprimir na tela
		System.out.println("Soma = " + soma);
	}
}
