package edu.currency.converter;

import edu.currency.converter.controllers.CurrencyController;
import edu.currency.converter.models.entities.Currency;

import java.text.DecimalFormat;
import java.util.Scanner;

public class CurrencyConverterApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CurrencyController controller = new CurrencyController();

        String opt = "";
        while (!opt.equals("0")) {
            opt = MainMenu(scanner);

            if(opt.equalsIgnoreCase("0")){
                System.out.println("Finalizando a aplicação");
                break;
            }

            String BASE = "https://v6.exchangerate-api.com/v6/4a9d82f90f4563c653dabe6b/pair/";
            String endpoint = "";

            switch (opt){
                case "1":
                    endpoint = BASE + "USD/ARS";
                    break;
                case "2":
                    endpoint = BASE + "ARS/USD";
                    break;
                case "3":
                    endpoint = BASE + "USD/BRL";
                    break;
                case "4":
                    endpoint = BASE + "BRL/USD";
                    break;
                case "5":
                    endpoint = BASE + "USD/BOB";
                    break;
                case "6":
                    endpoint = BASE + "BOB/USD";
                    break;
                default:
                    break;
            }

            try {
                System.out.println("Informe o valor a ser convertido");
                String ins = scanner.nextLine();
                ins = ins.replace(',', '.');
                double value = Double.parseDouble(ins);

                Currency data = controller.getCurrenciesRate(endpoint);
                double rate = Double.parseDouble(data.conversion_rate());

                String baseCode = data.base_code();
                String targetCode = data.target_code();
                DecimalFormat df = new DecimalFormat("#0.00");
                String result = df.format(value * rate);
                System.out.println("Valor " + df.format(value) + "[" + baseCode + "] equivale a "
                        + result + "[" + targetCode + "]");
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
                System.out.println("Finalizando a aplicação");
                opt = "0";
            }
        }
    }

    public static String MainMenu(Scanner scanner) {
        System.out.println("*******************************************************");
        System.out.println("Seja bem vindo ao conversor de moedas");
        System.out.println("Selecione uma opção no menu abaixo:");
        System.out.println();
        String message = """
                1 - Dólar --> Peso Argentino
                2 - Peso Argentino --> Dólar
                3 - Dólar --> Real Brasileiro
                4 - Real Brasileiro --> Dólar
                5 - Dólar --> Peso Colombiano
                6 - Peso Colombiano --> Dólar
                0 - Encerrar
                """;
        System.out.println(message);
        return scanner.nextLine();
    }
}