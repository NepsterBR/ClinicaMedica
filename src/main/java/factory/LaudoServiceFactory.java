package factory;

import dominio.ExameEnum;

import jakarta.enterprise.inject.Any;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import service.LaudoService;

public class LaudoServiceFactory {

    @Inject
    @Any
    private Instance<LaudoService> laudoServiceInstance;


    public LaudoService realizarExame(ExameEnum exameEnum) {
        LaudoServiceAnnotationLiteral literal = new LaudoServiceAnnotationLiteral(exameEnum);
        return laudoServiceInstance.select(literal).get();

    }
}
