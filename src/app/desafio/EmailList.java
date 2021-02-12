package app.desafio;

import java.util.ArrayList;
import java.util.Collection;

/**
 * TODO: FAZER A DOCUMENTAÇÃO DESTA CLASSE!
 * Ela é uma ArrayList feita pra retornar um mapa com o e-mail e o valor a ser pago, conforme solicitado no desafio!
 */
public class EmailList extends ArrayList<Email>
{
    public EmailList(int initialCapacity)
    {
        super(initialCapacity);
    }

    public EmailList()
    {
    }

    public EmailList(Collection<? extends Email> c)
    {
        super(c);
    }

    public void printResultingList()
    {
        //TODO: Implementar! Preciso retornar um MAP<String, int> com o e-mail e o valor devido, conforme briefing!
    }
}
