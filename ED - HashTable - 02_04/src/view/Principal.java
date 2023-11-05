package view;

import java.io.IOException;

import javax.swing.JOptionPane;

import controller.AndarController;

public class Principal {

	public static void main(String[] args) {
		
		AndarController ac = new AndarController();
		
		int c = 9;
		try {
			ac.readHash();
		} catch (IOException e) {
			System.err.println(e);
		}
		while(c != 0) {
			c = Integer.parseInt(JOptionPane.showInputDialog("Digite 1 para consultar os produtos de um andar.\nDigite 2 para adicionar produtos a um andar.\nDigite 3 para retirar um produto de um andar.\nDigite 4 para salvar.\nDigite 0 para sair."));
			switch (c) {
			case 1:
				int andar = Integer.parseInt(JOptionPane.showInputDialog("Insira o andar que você deseja consultar."));
				ac.consultHash(andar);
				break;
			case 2:
				ac.addHash();
				break;
			case 3:
				ac.removeHash();
				break;
			case 4:
				try {
					ac.saveHash();
				} catch (IOException e) {
					System.err.println(e);
				}
				break;
			case 0:
				System.out.println("Finalizando...");
				break;
			default:
				System.err.println("Opção Inválida.");
				break;
			}
		}

	}

}
