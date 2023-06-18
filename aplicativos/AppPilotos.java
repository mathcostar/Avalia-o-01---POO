package aplicativos;

import java.io.IOException;
import java.util.Scanner;

import classes.Aeronave;
import classes.Piloto;

public class AppPilotos {
    private static int MAX_PILOTOS = 5;
    private static final int MAX_AERONAVES = 10;

    private Piloto[] pilotos;
    private Aeronave[] aeronaves;
    private int qtdCadastrados;
    private int qtdAeronaves;

    public AppPilotos() {
        pilotos = new Piloto[MAX_PILOTOS];
        aeronaves = new Aeronave[MAX_AERONAVES];
        qtdCadastrados = 0;
        qtdAeronaves = 0;
    }

    public void run() throws InterruptedException, IOException {
        Scanner scan = new Scanner(System.in);

        int opcao;

        do {
            limparTela();
            exibirMenu();

            opcao = scan.nextInt();
            scan.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarPiloto(scan);
                    break;
                case 2:
                    listarPilotosCadastrados();
                    break;
                case 3:
                    localizarPilotoPorBreve(scan);
                    break;
                case 4:
                    cadastrarAeronave(scan);
                    break;
                case 5:
                    listarAeronavesCadastradas();
                    break;
                case 6:
                    aumentarArmazenamento(scan);
                    break;
                case 0:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida! Verifique.");
                    break;
            }

            voltarMenu(scan);
        } while (opcao != 0);

        scan.close();
    }

    private void exibirMenu() {
        System.out.println("\n=====================================");
        System.out.println("                MENU");
        System.out.println("=====================================\n");
        System.out.println("1 - Cadastrar piloto");
        System.out.println("2 - Listar pilotos cadastrados");
        System.out.println("3 - Localizar piloto pelo brevê");
        System.out.println("4 - Cadastrar aeronave");
        System.out.println("5 - Listar aeronaves cadastradas");
        System.out.println("6 - Aumentar espaço de armazenamento (pilotos)");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");
    }

    private void cadastrarPiloto(Scanner scan) {
        if (qtdCadastrados == MAX_PILOTOS) {
            System.out.println("\nNão há espaço para um novo cadastro de piloto. Retorne ao Menu e digite a opção '4' para aumentar o armazenamento.");
            return;
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
    }

    private void listarPilotosCadastrados() {
        if (qtdCadastrados == 0) {
            System.out.println("\nNão há pilotos cadastrados para exibir. Retorne ao menu para cadastrar.");
            return;
        }

        for (int i = 0; i < qtdCadastrados; i++) {
            System.out.println("\nNome do Piloto: " + pilotos[i].getNome());
            System.out.println("CPF: " + pilotos[i].getCpf());
            System.out.println("Brevê: " + pilotos[i].getBreve());
        }
    }

    private void localizarPilotoPorBreve(Scanner scan) {
        System.out.print("Insira o brevê do piloto: ");
        String breve = scan.nextLine();
        boolean encontrado = false;

        for (int i = 0; i < qtdCadastrados; i++) {
            if (breve.equals(pilotos[i].getBreve())) {
                encontrado = true;
                System.out.println("Piloto encontrado na base: " + pilotos[i].getNome() + "\nBrevê: " + pilotos[i].getBreve());
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Não há nenhum piloto com o brevê informado! Por favor, retorne ao menu e tente novamente.");
        }
    }

    private void cadastrarAeronave(Scanner scan) {
        if (qtdCadastrados == 0) {
            System.out.println("Não há pilotos cadastrados! Antes de cadastrar uma aeronave, retorne ao menu e cadastre um piloto.");
            return;
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
                return;
            }

            Piloto pilotoSelecionado = pilotos[numeroPiloto - 1];
            aeronave.setPilotoId(pilotoSelecionado);
        }

        aeronaves[qtdAeronaves] = aeronave;
        qtdAeronaves++;

        System.out.println("\nAeronave cadastrada com sucesso!");
    }

    private void listarAeronavesCadastradas() {
        if (qtdAeronaves == 0) {
            System.out.println("\nNão há aeronaves cadastradas para exibir. Retorne ao menu para cadastrar (necessário ao menos um piloto cadastrado).");
            return;
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
    }

    private void aumentarArmazenamento(Scanner scan) {
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
            System.out.println("Espaço de armazenamento alterado com sucesso! Voltando ao menu principal...");
        } else {
            System.out.println("Retornando ao menu principal...");
        }
    }

    private void voltarMenu(Scanner scan) throws InterruptedException {
        System.out.print("\nPressione Enter para voltar ao menu...");
        scan.nextLine();
    }

    private void limparTela() throws InterruptedException, IOException {
        if (System.getProperty("os.name").contains("Windows")) {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } else {
            Runtime.getRuntime().exec("clear");
        }
    }

    private boolean validarCPF(String cpf) {
        // Lógica de validação do CPF aqui
        return true; // Retorna verdadeiro para simplificar o exemplo
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        AppPilotos app = new AppPilotos();
        app.run();
    }
}
