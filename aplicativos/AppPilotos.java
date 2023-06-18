package aplicativos;

import java.io.IOException;
import java.util.Scanner;

import classes.Aeronave;
import classes.Piloto;

public class AppPilotos {
    public static void main(String[] args) throws InterruptedException, IOException {

        Scanner scan = new Scanner(System.in);

        int MAX_PILOTOS = 5; // Aqui, estarei colocando 5 cadastros por padrão...
        int MAX_AERONAVES = 10; // Aqui, estarei colocando 10 cadastros por padrão, já que podemos ter mais aeronaves que pilotos...
        int opcao, qtdCadastrados = 0;
        int qtdAeronaves = 0;
        Piloto[] pilotos = new Piloto[MAX_PILOTOS];
        Aeronave[] aeronaves = new Aeronave[MAX_AERONAVES];

        // Começando o programa...

        do {
            limparTela();
            System.out.println("\n=====================================\n                MENU\n=====================================\n");
            System.out.println("1 - Cadastrar piloto");
            System.out.println("2 - Listar pilotos cadastrados");
            System.out.println("3 - Localizar piloto pelo brevê");
            System.out.println("4 - Cadastrar aeronave");
            System.out.println("5 - Listar aeronaves cadastradas");            
            System.out.println("6 - Aumentar espaço de armazenamento (pilotos)");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scan.nextInt();
            scan.nextLine();

            if (opcao == 1) { 
                if (qtdCadastrados == MAX_PILOTOS) {
                    System.out.println("\nNão há espaço para um novo cadastro de piloto. Retorne ao Menu e digite a opção '4' para aumentar o armazenamento.");
                    voltarMenu(scan);
                    continue;
                }

                Piloto piloto = new Piloto();

                System.out.print("Digite o nome do Piloto: ");
                piloto.setNome(scan.nextLine());

                boolean cpfValido;
                do {
                System.out.print("Agora, seu CPF (somente números, com tamanho de 11 dígitos!): ");
                piloto.setCpf(scan.nextLine());

                if (piloto.getCpf().length() != 11) {
                System.out.println("CPF inválido! Verifique.");
                cpfValido = false;
                } else {
                cpfValido = validarCPF(piloto.getCpf());
                if (!cpfValido) {
                System.out.println("CPF inválido! Verifique.");
                    }
                  }
                } while (!cpfValido);

                System.out.print("Informe seu brevê: ");
                piloto.setBreve(scan.nextLine());

                pilotos[qtdCadastrados] = piloto;
                qtdCadastrados++;

                System.out.println("\nPiloto cadastrado com sucesso!");
                voltarMenu(scan);

            } else if (opcao == 2) {
                if (qtdCadastrados == 0) {
                    System.out.println("\nNão há pilotos cadastrados para exibir. Retorne ao menu para cadastrar.");
                    voltarMenu(scan);
                    continue;
                }

                for (int i = 0; i < qtdCadastrados; i++) {
                    System.out.println("\nNome do Piloto: " + pilotos[i].getNome());
                    System.out.println("CPF: " + pilotos[i].getCpf());
                    System.out.println("Brevê: " + pilotos[i].getBreve());
                }
                voltarMenu(scan);

            } else if (opcao == 3 ) {
                System.out.print("Insira o brevê do piloto: ");
                String breve = scan.nextLine();
                boolean existe = false;

                for (int i = 0; i < qtdCadastrados && !existe; i++) {
                    if (breve.equals(pilotos[i].getBreve())) {
                        existe = true;
                        System.out.println("Piloto encontrado na base: " + pilotos[i].getNome() + "\nBrevê: " + pilotos[i].getBreve());
                    }
                }
                if (!existe) {
                    System.out.println("Não há nenhum piloto com o brevê informado! Por favor, retorne ao menu e tente novamente.");
                }
                voltarMenu(scan);

            } else if (opcao == 4) {
            if (qtdCadastrados == 0) {
                System.out.println("Não há pilotos cadastrados! Antes de cadastrar uma aeronave, retorne ao menu e cadastre um piloto.");
                voltarMenu(scan);
                continue;
            }
            
            System.out.print("Informe o modelo da aeronave: ");
            String modelo = scan.nextLine();

            System.out.print("Agora, o número de série: ");
            String numeroSerie = scan.nextLine();

            Aeronave aeronave = new Aeronave();
            aeronave.setModelo(modelo);
            aeronave.setNumeroSerie(numeroSerie);

            System.out.println("Deseja informar o piloto desta aeronave?\n1 - Sim\n2 - Não");
            int opcaoAeronave = scan.nextInt();
            scan.nextLine();

            if (opcaoAeronave == 1) {
                System.out.println("\nPilotos cadastrados:");
                for (int i = 0; i < qtdCadastrados; i++) {
                    System.out.println((i + 1) + " - Nome: " + pilotos[i].getNome() + ", brevê: " + pilotos[i].getBreve());
                }

                System.out.print("Escolha o número do brevê do piloto que vincular à aeronave: ");
                int numeroPiloto = scan.nextInt();
                scan.nextLine();

                if (numeroPiloto < 1 || numeroPiloto > qtdCadastrados) {
                    System.out.println("Número inválido. Retornando ao menu principal...");
                    voltarMenu(scan);
                    continue;
                }

                Piloto pilotoSelecionado = pilotos[numeroPiloto - 1];
                aeronave.setPilotoId(pilotoSelecionado);
            }

            aeronaves[qtdAeronaves] = aeronave;
            qtdAeronaves++;

            System.out.println("\nAeronave cadastrada com sucesso!");
            voltarMenu(scan);
        
            }else if (opcao == 5) {
                 if (qtdAeronaves == 0) {
                    System.out.println("\nNão há aeronaves cadastradas para exibir. Retorne ao menu para cadastrar (necessário ao menos um piloto cadastrado).");
                    voltarMenu(scan);
                    continue;
                }

                for (int i = 0; i < qtdAeronaves; i++) {
                    System.out.println("\nModelo da aeronave: " + aeronaves[i].getModelo());
                    System.out.println("Número de série: " + aeronaves[i].getNumeroSerie());

                    Piloto pilotoId = aeronaves[i].getPilotoId();
                    if (pilotoId != null) {
                    System.out.println("Piloto vinculado: " + aeronaves[i].getPilotoId().getNome());
                    } else {
                        System.out.println("Nenhum piloto vinculado.");
                    }
                }
                voltarMenu(scan);

                } else if (opcao == 6) {
                System.out.print("Por padrão, o sistema permite até 5 cadastros na base.\nDeseja realmente alterar a quantidade máxima? Digite 1 para sim ou 2 para retornar ao menu. ");
                int opc = scan.nextInt();
                if (opc == 1) {
                    System.out.print("Digite o tamanho do novo armazenamento: ");
                    int tamanho = scan.nextInt();
                    Piloto[] aux = pilotos;
                    pilotos = new Piloto[tamanho];
                    MAX_PILOTOS = tamanho;
    
                    for (int i = 0; i < qtdCadastrados; i++) {
                        pilotos[i] = aux[i];
                    }
                    System.out.println("Espaço de armazenamento alterado com sucesso!");
                    voltarMenu(scan);
                
            } else voltarMenu(scan);

            }  

         }  while (opcao != 0);
    }

    private static boolean validarCPF(String cpf) {
        return cpf.length() == 11;
    }

    private static void voltarMenu(Scanner in) throws InterruptedException, IOException {
        System.out.print("\nPressione ENTER para voltar ao menu.");
        in.nextLine();
    }

    public static void limparTela() {
    try {
        if (System.getProperty("os.name").contains("Windows"))
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        else
            System.out.print("\033[H\033[2J");
    } catch(Exception e) {}
 }
}