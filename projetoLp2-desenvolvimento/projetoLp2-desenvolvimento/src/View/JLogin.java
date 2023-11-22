package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.Criptografia;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldUsuario;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JLogin frame = new JLogin();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 0, 64));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(61, 20, 312, 214);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Senha");
		lblNewLabel.setBounds(62, 104, 45, 13);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Usuário");
		lblNewLabel_1.setBounds(62, 28, 45, 13);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		panel.add(lblNewLabel_1);
		
		textFieldUsuario = new JTextField();
		textFieldUsuario.setBounds(62, 51, 195, 19);
		panel.add(textFieldUsuario);
		textFieldUsuario.setColumns(10);
		
		JButton btnNewButton = new JButton("Entrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Criptografia criptografia = new Criptografia(passwordField.getText() , Criptografia.SHA256);
				System.out.println(criptografia.criptografar());
				if ((textFieldUsuario.getText() != null)&&
						(!textFieldUsuario.getText().isEmpty())
						&&(passwordField.getText() != null) && 
						(!passwordField.getText().isEmpty())) {
					if (criptografia.criptografar().equals("8D969EEF6ECAD3C29A3A629280E686CF0C3F5D5A86AFF3CA12020C923ADC6C92"
							)) {
					JOptionPane.showMessageDialog(btnNewButton, "Informações Válidas");
					dispose();
					JPrincipal jPrincipal = new JPrincipal();
					jPrincipal.setLocationRelativeTo(jPrincipal);
					jPrincipal.setVisible(true);
					
					}
				}else {
					JOptionPane.showMessageDialog(btnNewButton, "Verifique as informações", "Aviso", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnNewButton.setBounds(97, 158, 85, 21);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("Bem-vindo");
		lblNewLabel_2.setBounds(97, 10, 109, 13);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel.add(lblNewLabel_2);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(62, 127, 195, 19);
		panel.add(passwordField);
	}
}
