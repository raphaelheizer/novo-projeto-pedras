package app.desafio;

/**
 * Application's default User's Email object. Stores {@link #email} and {@link #amountDue}
 * @implNote Email is immutable. It can be deleted only.
 */
public class Email
{
    private final String email;
    private final int amountDue;

    public Email(String email, int amountDue)
    {
        this.email = email;
        this.amountDue = amountDue;
    }

    public Email(String email)
    {
        this.email = email;
        this.amountDue = 0;
    }

    @Override
    public String toString()
    {
        return "Email{" +
                "email='" + email + '\'' +
                ", amountDue=" + amountDue +
                '}';
    }

    public String getEmail()
    {
        return email;
    }

    public int getAmountDue()
    {
        return amountDue;
    }
}
