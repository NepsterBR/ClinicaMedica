package factory;

import dominio.ExameEnum;

import jakarta.enterprise.inject.Any;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import service.LaudoService;
import view.ExameView;

public class ExameViewFactory {

    @Inject
    @Any
    private Instance<ExameView> exameViewInstance;


    public ExameView realizarExame(ExameEnum exameEnum) {
        final ExameViewAnnotationLiteral literal = new ExameViewAnnotationLiteral(exameEnum);
        return this.exameViewInstance.select(literal).get();

    }
}
