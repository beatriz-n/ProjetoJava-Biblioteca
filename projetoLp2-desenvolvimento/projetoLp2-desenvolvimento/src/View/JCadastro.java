package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

import Model.Cliente;
import dao.DAO;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JCadastro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textNome;
	private JTextField textCPF;
	private JTextField textTelefone;
	private JTextField textEmail;
	private JTextArea  textAreaEndereco;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JCadastro frame = new JCadastro(null, null);
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
	public JCadastro(Cliente clienteSelecionado, JPrincipal jPrincipal) {
		DAO dao = new DAO();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 320);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nome");
		lblNewLabel.setBounds(37, 23, 45, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("CPF");
		lblNewLabel_1.setBounds(37, 81, 45, 13);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Telefone");
		lblNewLabel_2.setBounds(204, 81, 45, 13);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("E-Mail");
		lblNewLabel_3.setBounds(37, 128, 45, 13);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Endereço");
		lblNewLabel_4.setBounds(37, 175, 45, 13);
		contentPane.add(lblNewLabel_4);
		
		textAreaEndereco = new JTextArea();
		textAreaEndereco.setBorder(new LineBorder(new Color(0, 0, 0)));
		textAreaEndereco.setBounds(29, 198, 367, 37);
		contentPane.add(textAreaEndereco);
		
		textNome = new JTextField();
		textNome.setBounds(29, 41, 367, 30);
		contentPane.add(textNome);
		textNome.setColumns(10);
		
		textCPF = new JTextField();
		textCPF.setBounds(29, 99, 165, 19);
		contentPane.add(textCPF);
		textCPF.setColumns(10);
		
		textTelefone = new JTextField();
		textTelefone.setBounds(204, 99, 192, 19);
		contentPane.add(textTelefone);
		textTelefone.setColumns(10);
		
		textEmail = new JTextField();
		textEmail.setBounds(29, 146, 367, 19);
		contentPane.add(textEmail);
		textEmail.setColumns(10);
		
		JButton btnNewButton = new JButton(clienteSelecionado == null?"Incluir":"Alterar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Cliente cliente = new Cliente(null, textNome.getText(), textCPF.getText(), textEmail.getText(), textTelefone.getText(), textAreaEndereco.getText());
				if(clienteSelecionado == null) {
					if(!"".equalsIgnoreCase(textNome.getText()) && !"".equalsIgnoreCase(textCPF.getText())) {
						dao.cadastrarCliente(cliente);
						abrirTelaPrincipal(jPrincipal);
					}else {
						JOptionPane.showMessageDialog(null, "Confira o nome e CPF");
					}


				}else {
					if(!"".equalsIgnoreCase(textNome.getText()) && !"".equalsIgnoreCase(textCPF.getText())) {
						dao.alterarCliente(clienteSelecionado.getID(), cliente);
						abrirTelaPrincipal(jPrincipal);
					}else {
						JOptionPane.showMessageDialog(null, "Confira o nome e CPF");
					}
				}
				
			}
		});
		btnNewButton.setBounds(311, 252, 85, 21);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Excluir");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dao.excluirCliente(clienteSelecionado.getID());
				abrirTelaPrincipal(jPrincipal);
			}
		});
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setBackground(new Color(255, 0, 0));
		btnNewButton_1.setBounds(29, 251, 89, 23);
		btnNewButton_1.setVisible(false);
		contentPane.add(btnNewButton_1);
		
		if(clienteSelecionado != null) {
			preencherCampos(clienteSelecionado);
			btnNewButton_1.setVisible(true);
		}
	}
	
	private void preencherCampos(Cliente clienteSelecionado) {
		textNome.setText(clienteSelecionado.getNome());
		textCPF.setText(clienteSelecionado.getCPF());
		textEmail.setText(clienteSelecionado.getTelefone());
		textTelefone.setText(clienteSelecionado.getTelefone());
		textAreaEndereco.setText(clienteSelecionado.getEndereco());
	}
	
	private void abrirTelaPrincipal(JPrincipal jPrincipal) {
		jPrincipal.dispose();
		dispose();
		jPrincipal = new JPrincipal();
		jPrincipal.setLocationRelativeTo(jPrincipal);
		jPrincipal.setVisible(true);
	}
}

