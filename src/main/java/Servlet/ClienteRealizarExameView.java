//package Servlet;
//
//import dominio.Cliente;
//import dominio.Exame;
//import dominio.ExameEnum;
//import dominio.Laudo;
//import factory.ExameViewFactory;;
//import jakarta.inject.Inject;
//import service.LaudoService;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.logging.Logger;
//
//public class ClienteRealizarExameView {
//
//    @Inject
//    private ExameViewFactory exameViewFactory;
//
//
//    private LaudoService laudoService;
//
//
//    private Logger logger;
//
//    public void realizarExame(ExameEnum exameEnum) {
//        Cliente cliente = new Cliente();
//        ArrayList<Exame> exames = new ArrayList<Exame>();
//        Exame exame = this.exameViewFactory.criar(exameEnum).criar();
//        Laudo laudo = new Laudo(cliente, exames);
//        System.out.println(Arrays.toString(this.logger.getHandlers()));
//        this.laudoService.realizarExame(cpf);
//    }
//
//}
