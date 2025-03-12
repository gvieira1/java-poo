package br.uff.ic.agenda.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import br.uff.ic.agenda.controller.ControleAdicionar;
import br.uff.ic.agenda.controller.ControleCarregar;
import br.uff.ic.agenda.controller.ControlePersistencia;
import br.uff.ic.agenda.controller.ControleRemover;
import br.uff.ic.agenda.controller.ControleSalvar;
import br.uff.ic.agenda.model.Contato;

public class Agenda extends JFrame {
    
    private static final String ICONE_ADICIONA = "/toolbarButtonGraphics/general/Add16.gif";
    private static final String ICONE_REMOVE = "/toolbarButtonGraphics/general/Delete16.gif";
    
    private final DefaultListModel<Contato> contatos = new DefaultListModel<>();
    private final JList<Contato> listaContatos = new JList<>(contatos);
    private final JTextField campoNome = new JTextField();
    private final JTextField campoTelefone = new JTextField();
    private final JTextArea campoDetalhes = new JTextArea();
    private final JLabel rotuloTotalContatos = new JLabel("Total de contatos: 0");
    private final JTextField campoBusca = new JTextField();
    private final JButton botaoOrdenarAZ = new JButton("Ordenar (A-Z)");
    
    public Agenda() {
        super("Agenda");        
        montaJanela();
    }
    
    private void montaJanela() {        
    	JPanel painelLista = new JPanel(new BorderLayout());
    	painelLista.setBorder(BorderFactory.createTitledBorder("Contatos"));
    	listaContatos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    	JScrollPane scrollPane = new JScrollPane(listaContatos);
    	painelLista.add(scrollPane, BorderLayout.CENTER);

    	JPanel painelOrdenacao = new JPanel(new BorderLayout());
    	painelOrdenacao.add(botaoOrdenarAZ, BorderLayout.CENTER);

    	JPanel painelBusca = new JPanel(new BorderLayout());
    	painelBusca.add(new JLabel("Buscar: "), BorderLayout.WEST);
    	painelBusca.add(campoBusca, BorderLayout.CENTER);

    	JPanel painelBuscaContagem = new JPanel(new BorderLayout());
    	painelBuscaContagem.add(rotuloTotalContatos, BorderLayout.NORTH);
    	painelBuscaContagem.add(painelBusca, BorderLayout.CENTER);  
    	painelBuscaContagem.add(painelOrdenacao, BorderLayout.SOUTH);

    	painelLista.add(painelBuscaContagem, BorderLayout.NORTH);
    	painelLista.add(scrollPane, BorderLayout.CENTER);

        botaoOrdenarAZ.addActionListener(e -> ordenarContatosAZ());
        
        campoBusca.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                filtrarContatos(campoBusca.getText());
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                filtrarContatos(campoBusca.getText());
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                filtrarContatos(campoBusca.getText());
            }
        });

        JButton botaoAdicionar = new JButton(new ImageIcon(getClass().getResource(ICONE_ADICIONA)));
        JButton botaoRemover = new JButton(new ImageIcon(getClass().getResource(ICONE_REMOVE)));
        JPanel painelBotoes = new JPanel(new GridLayout(1, 2));
        painelBotoes.add(botaoAdicionar);
        painelBotoes.add(botaoRemover);
        painelLista.add(painelBotoes, BorderLayout.SOUTH);
                        
        JPanel painelNome = new JPanel(new BorderLayout());
        painelNome.add(new JLabel("Nome:"), BorderLayout.WEST); 
        campoNome.setEnabled(false);
        painelNome.add(campoNome, BorderLayout.CENTER);
        
        JPanel painelTelefone = new JPanel(new BorderLayout());
        painelTelefone.add(new JLabel("Telefone:"), BorderLayout.WEST);
        campoTelefone.setEnabled(false);
        painelTelefone.add(campoTelefone, BorderLayout.CENTER);
        
        JPanel painelCampos = new JPanel(new GridLayout(2, 1));
        painelCampos.add(painelNome);
        painelCampos.add(painelTelefone);
        
        JPanel painelDetalhes = new JPanel(new BorderLayout());
        painelDetalhes.setBorder(BorderFactory.createTitledBorder("Detalhes"));
        campoDetalhes.setEnabled(false);
        painelDetalhes.add(new JScrollPane(campoDetalhes), BorderLayout.CENTER);

        JPanel painelCentral = new JPanel(new BorderLayout());
        painelCentral.add(painelCampos, BorderLayout.NORTH);
        painelCentral.add(painelDetalhes, BorderLayout.CENTER);
        
        JSplitPane painelPrincipal = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, painelLista, painelCentral);
        painelPrincipal.setDividerLocation(200);
        this.setContentPane(painelPrincipal);

        listaContatos.addListSelectionListener(new ControleCarregar(listaContatos, campoNome, campoTelefone, campoDetalhes));
        botaoAdicionar.addActionListener(e -> {
            new ControleAdicionar(contatos).actionPerformed(e);
            atualizarTotalContatos();
        });
 
        botaoRemover.addActionListener(e -> {
            new ControleRemover(listaContatos, contatos).actionPerformed(e);
            atualizarTotalContatos();
        });
        ControleSalvar salvar = new ControleSalvar(listaContatos, campoNome, campoTelefone, campoDetalhes);
        
        campoNome.addKeyListener(salvar);
        campoTelefone.addKeyListener(salvar);
        campoDetalhes.addKeyListener(salvar);
        this.addWindowListener(new ControlePersistencia(contatos) {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                super.windowClosing(e);
                atualizarTotalContatos();
            }
        });

        this.pack();
        this.setSize(468, 374);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
    }
    
    private void atualizarTotalContatos() {
        rotuloTotalContatos.setText("Total de contatos: " + contatos.getSize());
    }

    private void filtrarContatos(String termo) {
        DefaultListModel<Contato> contatosFiltrados = new DefaultListModel<>();

        for (int i = 0; i < contatos.size(); i++) {
            Contato contato = contatos.getElementAt(i);
            if (contato.getNome().toLowerCase().contains(termo.toLowerCase())) {
                contatosFiltrados.addElement(contato);
            }
        }

        listaContatos.setModel(contatosFiltrados);
    }

    private void ordenarContatosAZ() {
        List<Contato> listaOrdenada = Collections.list(contatos.elements());
        listaOrdenada.sort(Comparator.comparing(Contato::getNome));
        atualizarListaContatos(listaOrdenada);
    }

    private void atualizarListaContatos(List<Contato> listaOrdenada) {
        contatos.clear();
        for (Contato contato : listaOrdenada) {
            contatos.addElement(contato);
        }
    }

            
    public static void main(String[] args) {
        Agenda agenda = new Agenda();
        agenda.setVisible(true);
        
    }
}
