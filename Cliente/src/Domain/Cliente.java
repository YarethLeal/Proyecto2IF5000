package Domain;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

public class Cliente {
	  final int PUERTO_SERVIDOR = 5000;
	  private InetAddress direccionServidor;
	public Cliente() throws UnknownHostException {
	
        direccionServidor = InetAddress.getByName("localhost");
     

	}
	
	public void envio(String urlImagen, String nombre) {
		try {
			 //puerto del servidor
	      
	        //buffer donde se almacenara los mensajes
	        byte[] buffer = new byte[1024];
	        
	        	//Creo el socket de UDP
	            DatagramSocket socketUDP = new DatagramSocket();

	            String mensaje = "Registra Imagen";

	            //Convierto el mensaje a bytes
	            buffer = mensaje.getBytes();

	            //Creo un datagrama
	            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, direccionServidor, PUERTO_SERVIDOR);

	            //Lo envio con send
	            System.out.println("Envio el datagrama");
	            socketUDP.send(packet);
	            buffer = new byte[1024];
	            mensaje = nombre;

	            buffer = mensaje.getBytes();
	            packet = new DatagramPacket(buffer, buffer.length, direccionServidor, PUERTO_SERVIDOR);
	            System.out.println(mensaje);
	            socketUDP.send(packet);
	            
		
			// Envio de Paquete de datos Imagen
	        MosaicoImagen mosaico;
			mosaico = new MosaicoImagen(urlImagen,2, 2);
			ArrayList<PiezaImagen> piezasAux = mosaico.getImagenPartes();
			int i=0;
			while (!piezasAux.isEmpty()) {
				 
				Thread.sleep(1000);
				buffer = new byte[1024];
				PiezaImagen pieza = piezasAux.remove(i);
				buffer = toByteArray((Object)pieza);
				System.out.println("LLega");
				packet = new DatagramPacket(buffer, buffer.length, direccionServidor, PUERTO_SERVIDOR);
				socketUDP.send(packet);
				i++;
			}
			
								
			// Recibir respuesta del servidor
			byte[] bufer = new byte[1024];
			DatagramPacket respuesta = new DatagramPacket(bufer, bufer.length);
			socketUDP.receive(respuesta);
			// Enviamos la respuesta del servidor a la salida estandar
			System.out.println("Respuesta: " + new String(respuesta.getData()));

			// Cerrar Socket
			socketUDP.close();
			System.out.println("Hecho");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public ArrayList<String> listaObjetos() {
		try {
		
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	

	public static byte[] toByteArray(Object obj) throws IOException {
		byte[] bytes = null;
		ByteArrayOutputStream bos = null;
		ObjectOutputStream oos = null;
		try {
			bos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(bos);
			oos.writeObject(obj);
			oos.flush();
			bytes = bos.toByteArray();
		} finally {
			if (oos != null) {
				oos.close();
			}
			if (bos != null) {
				bos.close();
			}
		}
		return bytes;
	}

	public static Object toObject(byte[] bytes) throws IOException, ClassNotFoundException {
		Object obj = null;
		ByteArrayInputStream bis = null;
		ObjectInputStream ois = null;
		try {
			bis = new ByteArrayInputStream(bytes);
			ois = new ObjectInputStream(bis);
			obj = ois.readObject();
		} finally {
			if (bis != null) {
				bis.close();
			}
			if (ois != null) {
				ois.close();
			}
		}
		return obj;
	}

}
