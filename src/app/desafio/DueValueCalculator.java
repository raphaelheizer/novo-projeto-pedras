package app.desafio;

import app.desafio.models.Email;
import app.desafio.models.EmailList;
import app.desafio.models.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DueValueCalculator
{
    private int totalDueValue;

    private List<Item> itemList;

    private final EmailList emailList;

    public DueValueCalculator(EmailList emailList, List<Item> itemList)
    {
        this.emailList = emailList;
        this.itemList = itemList;
    }

    public DueValueCalculator(EmailList emailList, int totalDueValue)
    {
        this.totalDueValue = totalDueValue;
        this.emailList = emailList;
    }

    public int getTotalDueValue()
    {
        return totalDueValue;
    }

    public void setTotalDueValue(int value)
    {
        this.totalDueValue = value;
    }

    public EmailList getEmailList()
    {
        return emailList;
    }

    public List<Item> getItemList()
    {
        return itemList;
    }

    public void setItemList(ArrayList<Item> itemList)
    {
        this.itemList = itemList;
    }

    // PS.: Returns the required Map<String, Integer>

    /**
     * Divides the total amount by the email count in a given {@link EmailList}.
     * <br>
     * If division has module, it will be split by each email until the module is equal to zero
     *
     * @return {@link Map}
     */
    public Map<String, Integer> divideValueByEmails()
    {
        int emailCount = this.emailList.size();

        // Calculating resulting price value
        totalDueValue = calculateTotalAmountValues();

        int dueValueModule = 0;
        int dueValueDivResult = 0;

        // Cannot divide by zero!
        try
        {
            dueValueModule = totalDueValue % emailCount;
            dueValueDivResult = totalDueValue / emailCount;

        } catch (ArithmeticException ae)
        {
            System.out.println("Não é possível dividir por zero! Certifique-se que a lista de e-mails tem ao menos" +
                    "um e-mail");
        }

        for (Email email : this.emailList)
        {
            /* Since the division module is always smaller than the divisor,
             we can distribute +1 whenever there is a module. */
            if (dueValueModule > 0)
            {
                email.setAmountDue(dueValueDivResult + 1);
                dueValueModule--;
            } else
            {
                email.setAmountDue(dueValueDivResult);
            }
        }
        return emailList.toMap();
    }

    /**
     * Calculates amount times item price for all items
     * returns {@link Integer}
     */
    private int calculateTotalAmountValues()
    {
        int result = 0;

        for (Item item : itemList)
        {
            result = item.getAmount() * item.getUnitPrice();
        }

        return result;
    }

}
