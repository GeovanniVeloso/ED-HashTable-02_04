package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

import br.edu.fatec.zl.Lista;
import model.Produto;

public class AndarController {

	Produto p;
	Produto p2;

	Lista<Produto>[] hashTable = new Lista[4];

	public AndarController() {
		int tamanho = hashTable.length;
		for (int i = 0; i < tamanho; i++) {
			hashTable[i] = new Lista<Produto>();
		}
	}

	public void readHash() throws IOException {
		File dir = new File("D:\\3 Semestre\\temp");
		File arq = new File("D:\\3 Semestre\\temp", "Produto_ED_Hash_02_04.csv");

		if (dir.exists() && dir.isDirectory()) {
			if (arq.exists() && arq.isFile()) {
				FileInputStream flux = new FileInputStream(arq);
				InputStreamReader reader = new InputStreamReader(flux);
				BufferedReader buffer = new BufferedReader(reader);
				String linha = buffer.readLine();
				while (linha != null) {
					String[] vet = linha.split(";");
					p = new Produto(Integer.parseInt(vet[0]), vet[1], vet[2]);
					Lista<Produto> lista = hashTable[p.getAndar()];
					if (lista.isEmpty()) {
						lista.addFirst(p);
					} else {
						try {
							lista.addLast(p);
						} catch (Exception e) {
							System.err.println(e);
						}
					}
					linha = buffer.readLine();
				}
				buffer.close();
				reader.close();
				flux.close();
			}
		}
	}

	public void saveHash() throws IOException {
		File dir = new File("D:\\3 Semestre\\temp");
		File arq = new File("D:\\3 Semestre\\temp", "Produto_ED_Hash_02_04.csv");

		if (dir.exists() && dir.isDirectory()) {
			int c = 0;
			StringBuffer buffer = new StringBuffer();
			while (c < 4) {
				Lista<Produto> lista = hashTable[c];
				while (!lista.isEmpty()) {
					try {
						p = lista.get(0);
					} catch (Exception e) {
						System.err.println(e);
					}
					buffer.append(p.getAndar() + ";" + p.getProductName() + ";" + p.getDescricao()+"\r\n");
					try {
						lista.remove(0);
					} catch (Exception e) {
						System.err.println(e);
					}
				}
				c = c +1;
			}
			String thing = buffer.toString();
			FileWriter fileWriter = new FileWriter(arq);
			PrintWriter print = new PrintWriter(fileWriter);
			print.write(thing);
			print.flush();
			print.close();
			fileWriter.close();
		} else {
			System.err.println("Diretório Inválido");
		}
	}

	public void consultHash(int andar) {
		if(andar<4) {
			Lista<Produto>lista = hashTable[andar];
			int size = lista.size();
			int c = 0;
			System.out.println("No andar "+andar+" existem:");
			while(c < size) {
				try {
					p = lista.get(c);
					System.out.println(p.getProductName()+": "+p.getDescricao());
					c = c +1;
				} catch (Exception e) {
					System.err.println(e);
				}	
			}
		}else {
			System.err.println("Andar Inválido");
		}
	}

	public void addHash() {
		String name = JOptionPane.showInputDialog("Insira o número do andar, o produto e a descrição divididos por ;.");
		String []vet = name.split(";");
		int n = Integer.parseInt(vet[0]);
		if(n<4 && n >=0) {
			p = new Produto(n, vet[1], vet[2]);
			Lista<Produto>lista = hashTable[n];
			if(lista.isEmpty()) {
				lista.addFirst(p);
				System.out.println("Produto Adicionado.");
			}else {
				try {
					lista.addLast(p);
				} catch (Exception e) {
					System.err.println(e);
				}
				System.out.println("Produto Adicionado.");
			}
		}else {
			System.err.println("Andar Inválido.");
		}
	}
	
	public void removeHash() {
		String name = JOptionPane.showInputDialog("Insira o número do andar e o nome produto a ser removido divididos por ;.");
		String []vet = name.split(";");
		int n = Integer.parseInt(vet[0]);
		if(n<4 && n >=0) {
			p = new Produto(n, vet[1], null);
			Lista<Produto>lista = hashTable[n];
			if(lista.isEmpty()) {
				System.err.println("Não existem produtos nesse andar.");
			}else {
				int size = lista.size();
				int c = 0;
				while(c < size) {
					try {
						p2 = lista.get(c);
					} catch (Exception e) {
						System.err.println(e);
					}
					String name2 = p.getProductName();
					if(name2.equals(p2.getProductName())) {
						try {
							lista.remove(c);
							c = size;
						} catch (Exception e) {
							System.err.println(e);
						}
					}
					c = c +1;
				}
			}
		}else {
			System.err.println("Andar Inválido.");
		}
	}
	
}
