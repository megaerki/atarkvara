package ee.ut.math.tvt.salessystem.domain.exception;

public class SalesSystemException extends Exception {
	 private static final long serialVersionUID = 1L;
	 
	 public SalesSystemException() {}

     public SalesSystemException(String message)
     {
        super(message);
     }
}
