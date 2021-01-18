package sample.Exceptions;

public class DeleteMovieException extends Exception {

    public String message = "Check if the data is correct, while You are deleting movie";

    public DeleteMovieException()
    {
        super();
    }

    public DeleteMovieException(String message)
    {

        super(message);
    }

    public DeleteMovieException(String message, Throwable cause)
    {

        super(message, cause);
    }

    public DeleteMovieException(String message, Exception ex)
    {
        super(message, ex);
    }
}
