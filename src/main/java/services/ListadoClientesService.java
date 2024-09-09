package main.java.services;

import main.java.daos.ClienteDAO;
import main.java.entities.Cliente;

import java.util.ArrayList;

public class ListadoClientesService {
    private ClienteDAO clienteDAO;

    public ListadoClientesService(ClienteDAO clienteDAO) { this.clienteDAO = clienteDAO; }

    public ArrayList<Cliente> mostFacturedClientsList(){
        return clienteDAO.mostFacturedClients();
    }
}
