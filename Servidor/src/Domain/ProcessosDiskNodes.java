package Domain;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.imageio.ImageIO;

public class ProcessosDiskNodes {
	// Atributos
	private int metadatos;
	private String path;
	private byte[] libros;

	public ProcessosDiskNodes() {
		
	}
	
	public void DistribucionRaid(String nombre) {
		this.path = "/RAID/R"+metadatos+"/"+nombre+"_"+metadatos+".bmp";// /RAID/F#/IMAGEN_#.bmp
		Image image = (Image)toObject(libros);
		BufferedImage imagen = (BufferedImage) image;
		try {
			ImageIO.write(imagen, "bmp", new File(this.path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Byte[] obtenerDatos() {
		return null;
	}
	
	public void informacionControlador() {
		
	}

	public int getMetadatos() {
		return metadatos;
	}

	public void setMetadatos(int metadatos) {
		this.metadatos = metadatos;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public byte[] getLibros() {
		return libros;
	}

	public void setLibros(byte[] libros) {
		this.libros = libros;
	}
	
	public static Object toObject(byte[] bytes){
		Object obj = null;
		ByteArrayInputStream bis = null;
		ObjectInputStream ois = null;
		try {
			bis = new ByteArrayInputStream(bytes);
			ois = new ObjectInputStream(bis);
			obj = ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return obj;
	}
	
}
