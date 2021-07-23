package Domain;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ControllerNode {
	// Atributos
	private ArrayList<ProcessosDiskNodes> nodes;

	public ControllerNode() {
		nodes = new ArrayList<ProcessosDiskNodes>();
	}

	public void almacenaDatos(byte[] libros) {
		ProcessosDiskNodes tempNode = new ProcessosDiskNodes();
		tempNode.setMetadatos(nodes.size() + 1);
		tempNode.setLibros(libros);
		nodes.add(tempNode);
	}

	// Registra el nombre de todos los archivos que son guardados en el RAID
	public void registraInformacion(String nombre) {
		try {
			File file = new File("../Servidor/src/RAID/archivos.txt");
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file, true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(nombre + "\n");
			bw.close();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public ArrayList<String> listaInformacion() {
		ArrayList<String> lista = new ArrayList<String>();
		try {
			File file = new File("../Servidor/src/RAID/archivos.txt");
			if (!file.exists()) {
				file.createNewFile();
			}
			FileReader fw = new FileReader(file);
			BufferedReader bw = new BufferedReader(fw);
			String info = "";
			while ((info = bw.readLine()) != null) {
				if (info != "") {
					lista.add(info);
				}
			}
			bw.close();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(lista.isEmpty()) {
			return null;
		}
		return lista;
	}

	public void distribuyeInfoNodos(String nombre) {
		if(!nodes.isEmpty()) {
			for(int i=0;i<nodes.size();i++) {
				nodes.get(i).DistribucionRaid(nombre);
			}
		}
	}

	public void recuperaInfoNodos(String nombre) {

	}
}
