package app;

import model.Biblioteca;
import model.Livro;

import java.util.Scanner;

public class SistemaBiblioteca {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Biblioteca biblioteca = new Biblioteca();
        boolean executando = true;

        while (executando) {
            System.out.println("\n===== MENU PRINCIPAL =====");
            System.out.println("1. Gestão de Livro");
            System.out.println("2. Gestão de Empréstimos");
            System.out.println("3. Relatórios e Consultas");
            System.out.println("4. Administração do Sistema");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    menuGestaoLivro(scanner, biblioteca);
                    break;
                case 2:
                    menuEmprestimos(scanner, biblioteca);
                    break;
                case 3:
                    menuRelatorios(scanner, biblioteca);
                    break;
                case 4:
                    menuAdministracao(biblioteca);
                    break;
                case 5:
                    System.out.println("Encerrando o sistema...");
                    executando = false;
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }

    private static void menuGestaoLivro(Scanner scanner, Biblioteca biblioteca) {
        System.out.println("\n--- Gestão de Livro ---");
        System.out.println("1. Cadastrar novo livro");
        System.out.println("2. Editar informações de livro");
        System.out.println("3. Remover livro do acervo");
        System.out.println("4. Listar todos os livros");
        System.out.println("5. Buscar livro por ISBN");
        System.out.println("6. Voltar ao menu principal");

        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1:
                System.out.print("ISBN: ");
                String isbn1 = scanner.nextLine();
                System.out.print("Título: ");
                String titulo = scanner.nextLine();
                System.out.print("Autor: ");
                String autor = scanner.nextLine();
                Livro livro = new Livro(isbn1, titulo, autor);
                biblioteca.adicionarLivro(livro);
                System.out.println("Livro cadastrado!");
                break;

            case 2:
                System.out.print("ISBN do livro a editar: ");
                String isbn2 = scanner.nextLine();
                System.out.print("Novo título: ");
                String novoTitulo = scanner.nextLine();
                System.out.print("Novo autor: ");
                String novoAutor = scanner.nextLine();
                boolean sucesso = biblioteca.editarLivro(isbn2, novoTitulo, novoAutor);
                System.out.println(sucesso ? "Livro atualizado!" : "Livro não encontrado.");
                break;

            case 3:
                System.out.print("ISBN do livro para remover: ");
                String isbn3 = scanner.nextLine();
                boolean removido = biblioteca.removerLivro(isbn3);
                System.out.println(removido ? "Livro removido!" : "Livro não encontrado.");
                break;

            case 4:
                biblioteca.listarTodosOsLivros();
                break;

            case 5:
                System.out.print("ISBN: ");
                String isbn5 = scanner.nextLine();
                Livro encontrado = biblioteca.buscarPorIsbn(isbn5);
                System.out.println(encontrado != null ? encontrado.gerarDescricao() : "Livro não encontrado.");
                break;

            case 6:
                System.out.println("Retornando ao menu principal...");
                break;

            default:
                System.out.println("Opção inválida.");
                break;
        }
    }

    private static void menuEmprestimos(Scanner scanner, Biblioteca biblioteca) {
        System.out.println("\n--- Gestão de Empréstimos ---");
        System.out.println("1. Registrar empréstimo");
        System.out.println("2. Registrar devolução");
        System.out.println("3. Listar livros emprestados");
        System.out.println("4. Listar livros disponíveis");
        System.out.println("5. Voltar ao menu principal");

        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1:
                System.out.print("ISBN para empréstimo: ");
                String isbnEmprestimo = scanner.nextLine();
                boolean emprestado = biblioteca.emprestarLivro(isbnEmprestimo);
                System.out.println(emprestado ? "Livro emprestado!" : "Livro indisponível ou não encontrado.");
                break;

            case 2:
                System.out.print("ISBN para devolução: ");
                String isbnDevolucao = scanner.nextLine();
                boolean devolvido = biblioteca.devolverLivro(isbnDevolucao);
                System.out.println(devolvido ? "Livro devolvido!" : "Livro não está emprestado ou não encontrado.");
                break;

            case 3:
                biblioteca.listarEmprestados().forEach(livro ->
                        System.out.println(livro.gerarDescricao()));
                break;

            case 4:
                biblioteca.listarDisponiveis().forEach(livro ->
                        System.out.println(livro.gerarDescricao()));
                break;

            case 5:
                System.out.println("Retornando ao menu principal...");
                break;

            default:
                System.out.println("Opção inválida.");
                break;
        }
    }

    private static void menuRelatorios(Scanner scanner, Biblioteca biblioteca) {
        System.out.println("\n--- Relatórios e Consultas ---");
        System.out.println("1. Relatório completo do acervo");
        System.out.println("2. Buscar livros por autores");
        System.out.println("3. Estatísticas de empréstimos");
        System.out.println("4. Voltar ao menu principal");

        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1:
                biblioteca.exibirRelatorio();
                break;

            case 2:
                System.out.print("Autor (digite o nome): ");
                String autorBusca = scanner.nextLine();
                biblioteca.buscaPorAutor(autorBusca).forEach(livro ->
                        System.out.println(livro.gerarDescricao()));
                break;

            case 3:
                biblioteca.exibirEstatisticasEmprestimos();
                break;

            case 4:
                System.out.println("Retornando ao menu principal...");
                break;

            default:
                System.out.println("Opção inválida.");
                break;
        }
    }

    private static void menuAdministracao(Biblioteca biblioteca) {
        System.out.println("\n--- Administração do Sistema ---");
        System.out.println("Total de livros cadastrados: " + Livro.getTotalLivros());
        System.out.println("Total de bibliotecas: " + Biblioteca.getTotalBibliotecas());
    }
}
