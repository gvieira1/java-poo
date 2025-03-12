package br.uff.ic.agenda.controller;

import br.uff.ic.agenda.model.Contato;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;

public class ControlePersistencia extends WindowAdapter {

    private static final String NOME_ARQUIVO = "C:\\Users\\eclipse-workspace\\api-e-microsservicos\\Lista_1\\Agenda\\agenda\\agenda.dat";

    private final DefaultListModel<Contato> contatos;

    public ControlePersistencia(DefaultListModel<Contato> contatos) {
        this.contatos = contatos;
    }

    @Override
    public void windowOpened(WindowEvent e) {
        new Thread(() -> {
            try (FileInputStream fileIn = new FileInputStream(NOME_ARQUIVO);
                 ObjectInputStream in = new ObjectInputStream(fileIn)) {            
                Object[] objetos = (Object[]) in.readObject();
                for (Object objeto : objetos) {
                    contatos.addElement((Contato) objeto);
                }
                System.out.println("Contatos carregados com sucesso!");
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(ControlePersistencia.class.getName()).log(Level.SEVERE, "Erro ao carregar contatos", ex);
            }
        }).start();
    }

    @Override
    public void windowClosing(WindowEvent e) {
        new Thread(() -> {
            try (FileOutputStream fileOut = new FileOutputStream(NOME_ARQUIVO);
                 ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
                out.writeObject(contatos.toArray());
                System.out.println("Contatos salvos com sucesso!");
            } catch (IOException ex) {
                Logger.getLogger(ControlePersistencia.class.getName()).log(Level.SEVERE, "Erro ao salvar contatos", ex);
            }
        }).start();
    }
}
