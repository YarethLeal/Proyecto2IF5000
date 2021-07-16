import java.io.IOException;

import javax.swing.JFrame;

import Domain.Servidor;


public class Main {

	public static void main(String[] args) {

		Servidor servidor;
		try {
			servidor = new Servidor();
			servidor.setVisible(true);
			servidor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			servidor.setLocationRelativeTo(null);
			servidor.setResizable(false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
