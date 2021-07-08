package view;

import dominio.Cliente;

import java.io.IOException;
import java.util.Scanner;

public interface ClienteView {
    void criarCliente(Scanner input) throws IOException;
}
