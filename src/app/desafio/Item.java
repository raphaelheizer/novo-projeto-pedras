package app.desafio;

/**
 * Application's default Item object. Stores {@link #itemName}, {@link #unitPrice}, {@link #amount}
 * @implNote Item is immutable. It can be deleted only.
 */
public class Item
{
    private final String itemName;
    private final int unitPrice;
    private final int amount;

    final static int MAX_ITEM_ARR_SIZE = 3;

    public Item(String itemName, int unitPrice, int amount)
    {
        this.itemName = itemName;
        this.unitPrice = unitPrice;
        this.amount = amount;
    }

    @Override
    public String toString()
    {
        return "Item{" +
                "itemName='" + itemName + '\'' +
                ", unitPrice=" + unitPrice +
                ", amount=" + amount +
                '}';
    }

    public String getItemName()
    {
        return itemName;
    }

    public int getUnitPrice()
    {
        return unitPrice;
    }

    public int getAmount()
    {
        return amount;
    }
}
