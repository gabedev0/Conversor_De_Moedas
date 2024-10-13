import com.conversorDeMoedas.ImportarAPI;
import com.conversorDeMoedas.ResponseMoeda;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String continuar;

        System.out.println(
                """
                Seja bem vindo ao conversor de moedas!
                *********************************
                Moedas:
                
                Peso argentino (ARS)
                Boliviano boliviano (BOB)
                Real brasileiro (BRL)
                Peso chileno (CLP)
                Pesos Mexicanos (COP)
                Dólar americano (USD)
                
                
                *********************************
                """
        );

        do {
            System.out.print("Digite a moeda de origem: ");
            String moeda1 = input.nextLine().toUpperCase();
            System.out.print("Digite a moeda para conversão: ");
            String moeda2 = input.nextLine().toUpperCase();
            System.out.print("Digite o valor que deseja converter: ");
            double valorConverter = input.nextDouble();
            input.nextLine();

            try {
                ResponseMoeda ResponseMoeda = ImportarAPI.getMoedaResponse(moeda1);
                double taxaConversao = ResponseMoeda.getConversion_rates().getRateFor(moeda2);
                double valorConvertido = valorConverter * taxaConversao;
                System.out.println(
                        "A taxa de conversão de " + moeda1 + " para " + moeda2 + " é: " + taxaConversao +
                                "\nO valor convertido é: " + valorConvertido);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                System.out.println("Erro, tente novamente.");
            } catch (Exception e) {
                System.out.println("Erro ao processar a requisição: " + e.getMessage());
            }

            System.out.println("Deseja realizar outra conversão? Digite 'sim' ou 'nao':");
            continuar = input.nextLine().trim().toLowerCase();

        } while (continuar.equals("sim"));

        System.out.println("Obrigado! Aplicação concluída.");
    }
}
