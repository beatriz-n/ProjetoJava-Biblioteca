package View;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import Model.Cliente;
import Model.ModeloTabela;
import dao.DAO;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class JPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldBusca;
	private JTable table;
	private ArrayList<Cliente> clientes;
	private JPrincipal jPrincipal;
	private TableRowSorter<ModeloTabela> rowSorter;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JPrincipal frame = new JPrincipal();
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
	public JPrincipal() {
		this.jPrincipal = this;
		DAO dao = new DAO();
		try {
			clientes = dao.listarCliente();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("erro ao listar o cliente");
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 599, 439);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		textFieldBusca = new JTextField();
		textFieldBusca.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				filtrar();
			}
		});
		textFieldBusca.setBounds(186, 28, 331, 19);
		contentPane.add(textFieldBusca);
		textFieldBusca.setColumns(10);

		JButton btnNewButton = new JButton("Cadastrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JCadastro jCadastro = new JCadastro(null, jPrincipal);
				jCadastro.setLocationRelativeTo(jCadastro);
				jCadastro.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				jCadastro.setVisible(true);
			}
		});
		btnNewButton.setBounds(69, 27, 107, 21);
		contentPane.add(btnNewButton);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(34, 73, 522, 302);
		contentPane.add(scrollPane);
		ModeloTabela modeloTabela = new ModeloTabela(clientes);
		table = new JTable();
		table.setModel(modeloTabela);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == 1) {
					try {
						Cliente clienteSelecionado = dao
								.consultarCliente(modeloTabela.getValueAt(table.getSelectedRow(), 0).toString());
						JCadastro jCadastro = new JCadastro(clienteSelecionado, jPrincipal);
						jCadastro.setLocationRelativeTo(jCadastro);
						jCadastro.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
						jCadastro.setVisible(true);
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Erro ao selecionar cliente ", "",
								JOptionPane.WARNING_MESSAGE);
						e1.printStackTrace();
					}
				}
			}
		});
		rowSorter = new TableRowSorter<>(modeloTabela);
		table.setRowSorter(rowSorter);
		scrollPane.setViewportView(table);

	}

	private void filtrar() {
		String busca = textFieldBusca.getText().trim();

		if (busca.length() == 0) {
			rowSorter.setRowFilter(null);
		} else {
			rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + busca));
		}
	}
}
