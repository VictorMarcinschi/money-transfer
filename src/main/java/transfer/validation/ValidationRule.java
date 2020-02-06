package transfer.validation;

public interface ValidationRule<E> {

    int index();

    boolean passes(E entity);

    ValidationError getError();
}
