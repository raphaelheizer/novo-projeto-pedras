package app.desafio;

import app.desafio.enums.InputType;
import app.desafio.models.Email;
import app.desafio.models.EmailList;
import app.desafio.models.Item;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main
{

    // Manages the user input state. User can input any commands if true
    static boolean keepListening = true;

    // flags if user wishes to add ITEM or EMAIL
    static InputType inputType = InputType.NONE;

    // Item list
    static List<Item> itemArrayList = new ArrayList<>();

    // Using the application EmailList class instead of a List<T> due to its special characteristics
    static EmailList emailList = new EmailList();

    public static void main(String[] args)
    {
        // Greeting message and help command hint
        System.out.println(
                "\n" +
                        "Bem vindo à aplicação candidata ao Desafio Stone - Elixir. v.1.0.0 by Raphael Heizer\n" +
                        "Você pode acessar a documentação ou o código-fonte desta aplicação através do link " +
                        "https://github.com/raphaelheizer/desafio-elixir-stone-java/\n" +
                        "Muito obrigado por dispor do seu tempo!\n\n" +

                        "Caso precise de ajuda, use o comando help ou ajuda.\n"
        );

        // Initializes the console so user can interact
        startConsole();

        // Resets Input State to NONE so we'll never initialize a Null object. Nobody likes NullPointerExceptions.
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
                // Starts command line as a buffered reader and stores in local local variable
                InputStreamReader streamReader = new InputStreamReader(System.in);
                BufferedReader bufferedReader = new BufferedReader(streamReader);

                String command = bufferedReader.readLine();

                // Checks if current user input is any applicable command
                executeCommand(command);

                // Hints the user of the current insertion mode, if it's not set as NONE
                outputInputType();

            } catch (IOException e)
            {
                // The application will crash since it cannot work without the current try block. Warning user.
                System.out.println("Não foi possível inicializar o programa." +
                        "Neste instante, houve uma tentativa de inicializar um StreamReader e um BufferedReader.\n" +
                        "Logo, há grandes chances de uma instalação ruim do JVM estar causando este erro.");
                e.printStackTrace();
            }
        }

        // Application close message
        System.out.println("Encerrando a aplicação...");
    }

    /**
     * Hints the user of the current insertion mode if it's not set as NONE
     */
    static void outputInputType()
    {
        // Better be safe
        if (inputType == null)
            return;

        // Outputs the user current Input Mode at the beginning of the caret
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
            case "exit", "sair" -> keepListening = false;

            // Adding new data
            case "add item", "add items" -> {
                System.out.println("Entrando em modo de inserção de itens");
                inputType = InputType.ITEM;
            }

            case "add email", "add emails" -> {
                System.out.println("Entrando em modo de inserção de email");
                inputType = InputType.EMAIL;
            }

            // Exit addition mode
            case "add none" -> {
                System.out.println("Saindo do modo de inserção");
                inputType = InputType.NONE;
            }

            // Splitting amounts between e-mails
            case "split" -> {
                System.out
                        .println("\nDividindo valores entre os participantes. Valores com restos serão distribuídos\n" +
                                "de maneira igualitária, onde o resto será dividido unitariamente entre os participantes.\n" +
                                "Exemplo.: Se o resto for 5 centavos," +
                                " será adicionado 1 centavo para os primeiros cinco emails." + "\n\n" +
                                "Resultado: \n");

                DueValueCalculator dueValueCalculator = new DueValueCalculator(emailList, itemArrayList);

                Map<String, Integer> resultingValuesMap = dueValueCalculator.divideValueByEmails();

                System.out.println(resultingValuesMap);
            }

            // Listing current mem stored data
            case "list item", "list items" -> System.out
                    .printf("Listando todos os itens:\n%s\n", itemArrayList.toString());
            case "list email", "list emails" -> System.out
                    .printf("Listando todos os e-mails:\n%s\n", emailList.toString());

            case "clear email", "clear emails" -> emailList.clear();

            case "clear item", "clear items" -> itemArrayList.clear();

            case "clear all" -> {
                emailList.clear();
                itemArrayList.clear();
            }

            // Lists App info and available commands
            case "help", "ajuda" -> System.out.println(
                    "\n" +
                            "Desafio Stone - Elixir by Raphael Heizer, v.1.00\n" +
                            "O prompt aceita os seguintes comandos, todos em caixa baixa:\n\n" +

                            "exit (ou) sair: Encerra a aplicação.\n\n" +

                            "add item: Entra no modo de inserção de objetos do tipo Item.\n" +
                            "Ou seja, você só poderá digitar os itens" +
                            " após entrar no modo de inserção.\n" +
                            "O cursor mudará para ITEM e todas as inserções " +
                            "deverão ter o formato [ITEM, PREÇO, QUANTIDADE].\n" +
                            "As unidades trabalhadas sempre serão centavos e gramas.\n" +
                            "Ex.: \"banana, 6500, 500\", sem as aspas.\n\n" +

                            "add email: Adiciona um novo objeto do tipo E-mail à memória.\n\n" +

                            "add none: Sai do modo de inserção de objetos.\n\n" +

                            "split: Divide o valor total dos itens pela quantidade de e-mails.\n" +
                            "Se a divisão possuir resto, este será dividido unitariamente pela quantidade " +
                            "de e-mails.\n" +
                            "Exemplo: Se o resto da divisão por 5, distribuiremos um centavo " +
                            "aos 5 primeiros e-mails.\n\n" +

                            "clear email (ou) clear emails: limpa a lista de e-mails\n\n" +

                            "clear items (ou) clear items: limpa a lista de items\n\n" +

                            "clear all: limpa dos as listas\n\n" +

                            "list item (ou) list items: Lista todos os itens adicionados em memória.\n\n" +

                            "list email (ou) list emails: Lista todos os e-mails adicionados em memória.\n");

            /*
                If it doesn't match any command line case, try split, trim and parse the String as a subcommand.
                If parsing still doesn't work, tells user there are no valid commands to execute.
                Outputs an Array.
             */
            default -> {
                // Trim, try to split and store as Array
                String[] commArray = comm.replace(" ", "")
                                         .split(",");

                // If the input is not a known command so far, check if it's a subcommand of add item or add email
                if (!parseSubCommand(commArray))
                {
                    System.out.printf("'%s' não é um comando válido.\n", comm);
                }
            }
        }
    }

    /**
     * Parses an array of String and tries to execute a subcommand.
     *
     * @param commandArray Array to be parsed as command
     * @apiNote Subcommands are actions derived from standard commands such as add item or add email.
     */
    static boolean parseSubCommand(String[] commandArray)
    {
        /*
         * If user input matches any of the below conditions, we're adding items or emails if the input is valid.
         * Email validation rules are shown in isValidEmail() function
         */
        switch (inputType)
        {
            case ITEM -> {
                for (String c : commandArray)
                {
                    // if input mode is ITEM, is not blank char or differs from item array size, the command is invalid
                    if (c.isBlank() || commandArray.length != Item.MAX_ITEM_ARR_SIZE)
                    {
                        System.out.println("Um item não pode ter um campo vazio.");
                    } else
                    {
                        /* If the command throws input errors,
                         * tells user he must only input numbers in price and amount fields
                         * Warning: only NumberFormatException errors are expected. */
                        try
                        {
                            itemArrayList.add(new Item(commandArray[0], Integer.parseInt(commandArray[1]), Integer
                                    .parseInt(commandArray[2])));
                        } catch (NumberFormatException nfe)
                        {
                            System.out.println("ERRO: Input inválido! " +
                                    "Certifique que inseriu apenas números nos campos de valor e quantidade.");
                        }
                    }
                    return true;
                }
            }

            case EMAIL -> {
                String c = commandArray[0];

                // Field must not be blank and must be valid email. We're validating fields HERE!
                if (c.isBlank() || !isValidEmail(c))
                {
                    System.out.println("O e-mail precisa ter um formato válido!");
                } else
                {
                    emailList.add(new Email(c));
                }
                return true;
            }
        }

        return false;
    }

    /**
     * Checks if a given e-mail is valid and non-null
     *
     * @param email {@link String} to be tested
     * @return true if matches validation requirements
     */
    static private boolean isValidEmail(String email)
    {
        return email.contains("@") && email.contains(".");
    }
}
