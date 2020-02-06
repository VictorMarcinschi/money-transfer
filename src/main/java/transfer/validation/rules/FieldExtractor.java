package transfer.validation.rules;

public interface FieldExtractor<E, F> {

    F extract(E entity);
}
