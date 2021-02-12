package app.desafio;

/**
 * Stores the current user action and sets the insertion mode on the console. <br>
 * Insertion mode allows data addition by its category.
 *
 * <br><br>
 * <p>
 * {@link #EMAIL} Enters email insertion mode
 * <br>
 * {@link #ITEM} enters item insertion mode
 * <br>
 * {@link #NONE} exits insertion mode
 */
public enum InputType
{
    EMAIL, ITEM, NONE
}
