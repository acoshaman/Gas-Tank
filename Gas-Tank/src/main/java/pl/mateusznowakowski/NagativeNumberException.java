package pl.mateusznowakowski;

import java.io.*;
class NegativeNumberException extends IOException
{
    public NegativeNumberException(double irritant)
    {
        super("non-negative number expected, not " + irritant);
    }

    public NegativeNumberException()
    {
        super("non-negative number expected");
    }
}