package mate.academy.rickandmorty.exception;

public class FetchingDataException extends RuntimeException {
    public FetchingDataException(Throwable exception, String exceptionDescription) {
        super(exceptionDescription, exception);
    }
}
