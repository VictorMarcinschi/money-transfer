package transfer.validation;

public interface ValidationRule<E> {

    boolean passes(E entity);

    ValidationError getError();
}
