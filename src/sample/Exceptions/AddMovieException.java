package sample.Exceptions;

public class AddMovieException extends Exception {

    public String message = "Check if the data is correct, while You are adding movie";

    public AddMovieException()
    {
        super();
    }

    public AddMovieException(String message)
    {

        super(message);
    }

    public AddMovieException(String message, Throwable cause)
    {

        super(message, cause);
    }

    public AddMovieException(String message, Exception ex)
    {
        super(message, ex);
    }
}
