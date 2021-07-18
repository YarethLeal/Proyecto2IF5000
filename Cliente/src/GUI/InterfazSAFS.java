package GUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class InterfazSAFS extends JFrame implements ActionListener {
	// InitComponents
	private JMenu jmArchivo;
	private JMenuItem jmiEnviar, jmiRecibir, jmiCerrar;
	private JMenuBar jmbArchivo;
	private JLabel jlbServidor, jlbPort;
	private JTextField jtfServidor, jtfPort;
	private JButton jbtnEnviar, jbtnImagen, jbtnPDF, jbtnLista, jbtnPedir;
	private JPanel jpPanel;
	private JFileChooser jfcBuscaImagen, jfcBuscaPDF;
	private BufferedImage biImagen;
	private FileNameExtensionFilter filter, filter2;
	private ImageIcon icon;
	private JLabel labelArchivo;
	private String rutaArchivo;
	private JList<String> jlLista;

	public InterfazSAFS() {
		setTitle("Cliente");
		setLayout(null);
		setSize(800, 800);
		initComponents();
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private void initComponents() {
		this.jmiEnviar = new JMenuItem("Enviar Archivo");
		this.jmiEnviar.addActionListener(this);
		this.jmiRecibir = new JMenuItem("Recibir Archivo");
		this.jmiRecibir.addActionListener(this);
		this.jmiCerrar = new JMenuItem("Salir");
		this.jmiCerrar.addActionListener(this);
		this.jmArchivo = new JMenu("Archivo");
		this.jmbArchivo = new JMenuBar();
		this.jmArchivo.add(this.jmiEnviar);
		this.jmArchivo.add(this.jmiRecibir);
		this.jmArchivo.add(this.jmiCerrar);
		this.jmbArchivo.add(this.jmArchivo);
		setJMenuBar(this.jmbArchivo);
		this.jpPanel = new JPanel();
		this.jpPanel.setBounds(0, 0, getWidth(), getHeight());
		this.jpPanel.setLayout(null);
		add(this.jpPanel);

	}

	private void enviarLayout() {
		this.jpPanel.removeAll();
		Font font1 = new Font("SansSerif", Font.BOLD, 16);
		// label
		this.jlbServidor = new JLabel("Servidor:");
		this.jlbServidor.setFont(font1);
		this.jlbPort = new JLabel("Puerto:");
		this.jlbPort.setFont(font1);
		// textfield
		this.jtfServidor = new JTextField();
		this.jtfServidor.setFont(font1);
		this.jtfPort = new JTextField("69");
		this.jtfPort.setFont(font1);
		// Botones
		this.jbtnImagen = new JButton("Selecciona Imagen");
		this.jbtnImagen.setFont(font1);
		this.jbtnImagen.addActionListener(this);
		this.jbtnPDF = new JButton("Selecciona PDF");
		this.jbtnPDF.setFont(font1);
		this.jbtnPDF.addActionListener(this);
		this.jbtnEnviar = new JButton("Enviar al Servidor");
		this.jbtnEnviar.setFont(font1);
		this.jbtnEnviar.addActionListener(this);
		// bounds
		this.jlbServidor.setBounds(10, 30, 100, 20);
		this.jtfServidor.setBounds(90, 30, 100, 25);// 125
		this.jlbPort.setBounds(220, 30, 120, 20);
		this.jtfPort.setBounds(290, 30, 120, 25);
		this.jbtnImagen.setBounds(10, 100, 180, 25);
		this.jbtnPDF.setBounds(200, 100, 180, 25);
		this.jbtnEnviar.setBounds(390, 100, 180, 25);
		this.jpPanel.add(this.jlbServidor);
		this.jpPanel.add(this.jtfServidor);
		this.jpPanel.add(this.jlbPort);
		this.jpPanel.add(this.jtfPort);
		this.jpPanel.add(this.jbtnImagen);
		this.jpPanel.add(this.jbtnPDF);
		this.jpPanel.add(this.jbtnEnviar);
		// filechosser
		this.jfcBuscaImagen = new JFileChooser();
		this.filter = new FileNameExtensionFilter("PNG,JPG,GIF", "png", "jpg", "bmp");
		this.jfcBuscaImagen.setFileFilter(this.filter);
		this.jfcBuscaPDF = new JFileChooser();
		this.filter2 = new FileNameExtensionFilter("PDF", "pdf");
		this.jfcBuscaPDF.setFileFilter(this.filter2);
		repaint();
	}

	private void pedirLayout() {
		this.jpPanel.removeAll();
		Font font1 = new Font("SansSerif", Font.BOLD, 16);
		// label
		this.jlbServidor = new JLabel("Servidor:");
		this.jlbServidor.setFont(font1);
		this.jlbPort = new JLabel("Puerto:");
		this.jlbPort.setFont(font1);
		// textfield
		this.jtfServidor = new JTextField();
		this.jtfServidor.setFont(font1);
		this.jtfPort = new JTextField("69");
		this.jtfPort.setFont(font1);
		// Botones
		this.jbtnLista = new JButton("Lista");
		this.jbtnLista.setFont(font1);
		this.jbtnLista.addActionListener(this);
		this.jbtnPedir = new JButton("Pedir del Servidor");
		this.jbtnPedir.setFont(font1);
		this.jbtnPedir.addActionListener(this);
		// Lista
		this.jlLista = new JList<String>();
		// bounds
		this.jlbServidor.setBounds(10, 30, 100, 20);
		this.jtfServidor.setBounds(90, 30, 100, 25);// 125
		this.jlbPort.setBounds(220, 30, 120, 20);
		this.jtfPort.setBounds(290, 30, 120, 25);
		this.jbtnLista.setBounds(10, 100, 180, 25);
		this.jbtnPedir.setBounds(200, 100, 180, 25);
		this.jlLista.setBounds(10, 150, 180, 250);
		this.jpPanel.add(this.jlbServidor);
		this.jpPanel.add(this.jtfServidor);
		this.jpPanel.add(this.jlbPort);
		this.jpPanel.add(this.jtfPort);
		this.jpPanel.add(this.jbtnLista);
		this.jpPanel.add(this.jbtnPedir);
		this.jpPanel.add(this.jlLista);
		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(this.jmiEnviar)) {
			enviarLayout();
		} else if (e.getSource().equals(this.jmiRecibir)) {
			pedirLayout();
		} else if (e.getSource().equals(this.jmiCerrar)) {
			dispose();
		} else if (e.getSource().equals(this.jbtnImagen)) {
			int seleccion = this.jfcBuscaImagen.showSaveDialog(this);
			if (seleccion == JFileChooser.APPROVE_OPTION) {
				File fichero = this.jfcBuscaImagen.getSelectedFile();
				this.rutaArchivo = fichero.getPath();
				try {
					this.biImagen = ImageIO.read(fichero);
					this.icon = new ImageIcon(this.biImagen);
					if (this.labelArchivo != null) {
						this.jpPanel.remove(this.labelArchivo);
					}
					this.labelArchivo = new JLabel(icon);
					this.labelArchivo.setBounds(30, 150, 500, 500);
					this.jpPanel.add(this.labelArchivo);
					repaint();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		} else if (e.getSource().equals(this.jbtnPDF)) {
			int seleccion = this.jfcBuscaPDF.showSaveDialog(this);
			if (seleccion == JFileChooser.APPROVE_OPTION) {
				File fichero = this.jfcBuscaPDF.getSelectedFile();
				this.rutaArchivo = fichero.getPath();
				if (this.labelArchivo != null) {
					this.jpPanel.remove(this.labelArchivo);
				}
				this.labelArchivo = new JLabel(fichero.getName());
				this.labelArchivo.setBounds(30, 150, 500, 500);
				this.jpPanel.add(this.labelArchivo);
				repaint();
			}
		} else if (e.getSource().equals(this.jbtnEnviar)) {

		} else if (e.getSource().equals(this.jbtnLista)) {

		} else if (e.getSource().equals(this.jbtnPedir)) {

		}
	}
}
