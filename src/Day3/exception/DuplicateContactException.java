package Day3.exception;

public class DuplicateContactException extends RuntimeException{
    public DuplicateContactException(String mess){
        super(mess);
    }
}
