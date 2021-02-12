package app.desafio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main
{
    // Manages the user input state. We can input any values if true
    static boolean keepListening = true;

    // Stores the input type state. Here we know if you're adding a new e-mail or new item
    static InputType inputType;

    // Our item list
    static List<Item> itemArrayList = new ArrayList<>();

    // Using the application EmailList class instead of a List<T> due to its special characteristics
    static EmailList emailList = new EmailList();

    public static void main(String[] args)
    {
        // Greeting message and tips
        System.out.println(
                "Processo inicializado com sucesso. \n" +
                        "Placeholder!"
        );

        startConsole();
        inputType = InputType.NONE;

    }

    /**
     * Starts console and begins listening input if {@link #keepListening} is true.
     * Closes application with a message once {@link #keepListening} is false
     */
    static void startConsole()
    {
        while (keepListening)
        {
            try
            {
                // Starts command line as a buffered reader and stores in local a local variable
                InputStreamReader streamReader = new InputStreamReader(System.in);
                BufferedReader bufferedReader = new BufferedReader(streamReader);

                String command = bufferedReader.readLine();

                executeCommand(command);
                // Hints the user of the current insertion mode
                outputInputType();

            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        // Application close message
        System.out.println("Encerrando a aplicação...");
    }

    /**
     * Hints the user of the current insertion mode
     */
    static void outputInputType()
    {
        if (inputType == null)
            return;

        if (inputType != InputType.NONE)
            System.out.printf("%s: ", inputType);
    }

    /**
     * parses a given {@link String} and tries to execute it as a command line function
     *
     * @param comm {@link String} to be parsed
     */
    static void executeCommand(String comm)
    {
        switch (comm)
        {
            case "exit" -> keepListening = false;

            // Adding new data
            case "add item" -> {
                System.out.println("Entrando em modo de inserção de itens");
                inputType = InputType.ITEM;
            }

            case "add email" -> {
                System.out.println("Entrando em modo de inserção de email");
                inputType = InputType.EMAIL;
            }

            case "add none" -> {
                System.out.println("Saindo do modo de inserção");
                inputType = InputType.NONE;
            }

            // Listing current data
            case "list item" -> System.out.println("Implement me");

            case "list email" -> System.out.println("Implement me");

            // TODO: checar se é possível fazer um parse no comando de acordo com o tipo de inserção através de método
            default -> System.out.printf("'%s' não é um comando válido.\n", comm);
        }
    }
}
