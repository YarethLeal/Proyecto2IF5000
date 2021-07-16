package Domain;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class Servidor extends JFrame implements Runnable {

	private int PORT = 5000;
	private JLabel jlIp;
	private JLabel jlPort;
	private Thread hilo;

	public Servidor() throws IOException {
		super("Server");
		this.setLayout(null);
		this.setSize(300, 300);

		this.jlIp = new JLabel();
		this.jlIp.setBounds(50, 50, 500, 100);
		this.add(this.jlIp);
		
		this.jlPort = new JLabel("Puerto: "+PORT);
		this.jlPort.setBounds(50, 50, 500, 150);
		this.add(this.jlPort);

		this.hilo = new Thread(this);
		this.hilo.start();
	}

	@Override
	public void run() {

		byte[] buffer = new byte[1024];
		try {
			DatagramSocket serverSocket = new DatagramSocket(this.PORT);
			InetAddress address = InetAddress.getLocalHost();
			System.out.println("Servidor ejecutado");
			this.jlIp.setText(String.valueOf(address));
			
			//Esperar siempre solicitudes
			while(true) {
				//Preparo espacio de peticion
				DatagramPacket solicitud = new DatagramPacket(buffer, buffer.length);
				
				//Recibe Datagrama
				serverSocket.receive(solicitud);
				System.out.println("Informacion de cliente recibida");
				
				//Obtener mensaje
				String mensaje = new String(solicitud.getData());
				System.out.println(mensaje);
				
				if(mensaje.contains("Inicio")) {
					System.out.println("Entra If");
					while(!mensaje.contains("Fin")) {
						//Recibe Datagrama
						buffer = new byte[1024];
						solicitud = new DatagramPacket(buffer, buffer.length);
						serverSocket.receive(solicitud);
						System.out.println("Informacion:");
						//Obtener mensaje
						mensaje = new String(solicitud.getData());
						System.out.println(mensaje);
					}
				}
				
				//Devolver respuesta
				int puertoC = solicitud.getPort();
				InetAddress direccionC = solicitud.getAddress();
				
				mensaje = "Datos recibidos";
				System.out.println("Respuesta: "+mensaje);
				buffer = new byte[1024];
				buffer = mensaje.getBytes();
				
				//Datagrama de envio
				DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length,direccionC,puertoC);
				
				//Envia respuesta
				System.out.println("Envio la informacion del cliente");
                serverSocket.send(respuesta);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
