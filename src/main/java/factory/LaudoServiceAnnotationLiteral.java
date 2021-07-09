package factory;

import dominio.ExameEnum;

import jakarta.enterprise.util.AnnotationLiteral;
import service.TipoExame;

public class LaudoServiceAnnotationLiteral extends AnnotationLiteral<TipoExame> implements TipoExame {

    private final ExameEnum exameEnum;

    public LaudoServiceAnnotationLiteral(ExameEnum exameEnum) {
        this.exameEnum = exameEnum;
    }

    @Override
    public ExameEnum value() {
        return this.exameEnum;
    }

}
