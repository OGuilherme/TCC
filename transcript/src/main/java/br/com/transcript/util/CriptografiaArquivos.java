package br.com.transcript.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CriptografiaArquivos {

	public int[] RamdomKey() { // retorna um vetor randomico...
		int[] ret = new int[32];
		int valor;
		int cont = 0;
		for (int ct = 0; ct < 32; ct++) {
			ret[ct] = 0;
		}
		while (true) {
			valor = (int) (Math.random() * 256);
			ret[cont % 32] = (ret[cont % 32] + valor) % 256;
			cont++;
			if (cont == 3200) {
				break;
			}
		}
		return ret;
	}

	public void Cript(String caminho, String destino, String Chave, char Operacao) {
		try {
			File fileOrigin = new File(caminho);
			File fileDestiny = new File(destino);
			FileInputStream i = new FileInputStream(fileOrigin);
			FileOutputStream o = new FileOutputStream(fileDestiny);
			BufferedInputStream in = new BufferedInputStream(i);
			BufferedOutputStream out = new BufferedOutputStream(o);
			int[] ch = new int[32];
			ch = Transform(stringHexa(gerarHash(Chave, "SHA-256")));
			int cont = 0;
			String tmp = "";
			int op = 1;
			if (Operacao == 'C' || Operacao == 'c') {
				op = 1;
			} else {
				op = -1;
			}
			int x;
			int[] valores = new int[32];
			String xValores = "";
			if (op == 1) { // CIFRAGEM
				valores = RamdomKey();
				xValores = ConverteParaString(valores);
				for (int pag = 0; pag < 32; pag++) {
					x = (ch[pag] + valores[pag]);
					if (x > 255) {
						x = x - 256;
					}
					if (x < 0) {
						x = x + 256;
					}
					out.write(x);
				}
			} else { // DECIFRAGEM
				for (int pag = 0; pag < 32; pag++) {
					x = in.read();
					x = x - ch[pag];
					if (x > 255) {
						x = x - 256;
					}
					if (x < 0) {
						x = x + 256;
					}
					valores[pag] = x;
				}
				xValores = ConverteParaString(valores);
			}
			// Calcular nova sequencia da Chave!!!
			cont = 0;
			tmp = Chave;
			Chave = ConverteParaString(ch);
			ch = Transform(stringHexa(gerarHash(Chave + tmp, "SHA-256")));
			tmp = xValores;
			xValores = ConverteParaString(valores);
			valores = Transform(stringHexa(gerarHash(xValores + tmp, "SHA-256")));
			if (op == 1) {
				// Cifragem do arquivo...
				while ((x = in.read()) != -1) {
					x = (x + ch[cont] + valores[cont]) % 256;
					++cont;
					out.write(x);
					// Calcular nova sequencia da Chave!!!
					if (cont > 31) {
						cont = 0;
						tmp = Chave;
						Chave = ConverteParaString(ch);
						ch = Transform(stringHexa(gerarHash(Chave + tmp, "SHA-256")));
						tmp = xValores;
						xValores = ConverteParaString(valores);
						valores = Transform(stringHexa(gerarHash(xValores + tmp, "SHA-256")));
					}
				}
			} else {
				// Decifragem do arquivo...
				while ((x = in.read()) != -1) {
					x = x - ((ch[cont] + valores[cont]) % 256);
					++cont;
					if (x < 0) {
						x = x + 256;
					}
					out.write(x);
					// Calcular nova sequencia da Chave!!!
					if (cont > 31) {
						cont = 0;
						tmp = Chave;
						Chave = ConverteParaString(ch);
						ch = Transform(stringHexa(gerarHash(Chave + tmp, "SHA-256")));
						tmp = xValores;
						xValores = ConverteParaString(valores);
						valores = Transform(stringHexa(gerarHash(xValores + tmp, "SHA-256")));
					}
				}
			}
			in.close();
			out.close();
		} catch (Exception e) {
			System.out.println("Erro = " + e);
		}
	}

	public byte[] gerarHash(String frase, String algoritmo) {
		try {
			MessageDigest md = MessageDigest.getInstance(algoritmo);
			md.update(frase.getBytes());
			return md.digest();
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}

	private String stringHexa(byte[] bytes) {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			int parteAlta = ((bytes[i] >> 4) & 0xf) << 4;
			int parteBaixa = bytes[i] & 0xf;
			if (parteAlta == 0) {
				s.append('0');
			}
			s.append(Integer.toHexString(parteAlta | parteBaixa));
		}
		if (s.toString().length() != 64) {
			System.out.println("erro com SHA-256");
			System.exit(0);
		}
		return s.toString();
	}

	public int[] Transform(String entrada) {
		int[] ret = new int[32];
		int tam = entrada.length() - 1;
		int cont = 0;
		for (int ct = 0; ct < tam; ct = ct + 2) {
			int valor = 0, valor2 = 0;
			char r;
			int num, ct2;
			char[] car = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
			r = entrada.charAt(ct);
			num = 0;
			ct2 = 0;
			while (ct2 < 16) {
				num = (r == car[ct2]) ? ct2 * 16 : 0;
				valor = valor + num;
				++ct2;
			}
			r = entrada.charAt(ct + 1);
			num = 0;
			ct2 = 0;
			while (ct2 < 16) {
				num = (r == car[ct2]) ? ct2 : 0;
				valor2 = valor2 + num;
				++ct2;
			}
			ret[cont] = valor + valor2;
			++cont;
		}
		return ret;
	}

	public String ConverteParaString(int[] entrada) {
		String ret = "";
		int tam = entrada.length;
		for (int ct = 0; ct < tam; ct++) {
			ret = ret + (char) entrada[ct];
		}
		if (ret.length() != 32) {
			System.out.println("erro");
			System.exit(0);
		}
		return ret;
	}
}