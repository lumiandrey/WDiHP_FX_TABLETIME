package by.bsuir.vdishp.model.exception;

import java.io.Serializable;

/**
 * Created by andrey on 14.05.2016.
 */
public class DAOException extends Exception implements Serializable{
    private static final long serialVersionUID = 5L;

    public DAOException(){}
    public DAOException(String message){
        super(message);
    }
    public DAOException(String message, Throwable cause){
        super(message,cause);
    }
    public DAOException(Throwable cause){
        super(cause);
    }
}
