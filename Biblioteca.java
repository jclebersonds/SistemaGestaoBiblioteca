package model;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private final List<Livro> acervo;
    private static int totalBibliotecas = 0;

    public Biblioteca(){
        this.acervo = new ArrayList<>();
        totalBibliotecas++;
    }

    public void adicionarLivro(Livro livro){
        acervo.add(livro);
    }

    public boolean editarLivro(String isbn, String novoTitulo, String novoAutor){
        for(Livro livro : acervo){
            if(livro.getIsbn().equals(isbn)){
                if(novoTitulo != null) livro.setTitulo(novoTitulo);
                if(novoAutor != null) livro.setAutor(novoAutor);
                return true;
            }
        }
        return false;
    }

    public boolean removerLivro(String isbn){
        return acervo.removeIf(livro -> livro.getIsbn().equals(isbn));
    }

    public Livro buscarPorIsbn(String isbn){
        for(Livro livro : acervo){
            if(livro.getIsbn().equals(isbn)){
                return livro;
            }
        }
        return null;
    }

    public void listarTodosOsLivros(){
        for(Livro livro : acervo){
            System.out.println(livro.gerarDescricao());
        }
    }

    public boolean emprestarLivro(String isbn){
        for(Livro livro : acervo){
            if(livro.getIsbn().equals(isbn) && !livro.isEmprestado()){
                livro.emprestar();
                return true;
            }
        }
        return false;
    }

    public boolean devolverLivro(String isbn){
        for(Livro livro : acervo){
            if(livro.getIsbn().equals(isbn) && livro.isEmprestado()){
                livro.devolver();
                return true;
            }
        }
        return false;
    }

    public List<Livro> listarEmprestados(){
        return acervo.stream().filter(Livro::isEmprestado).toList();
    }

    public List<Livro> listarDisponiveis(){
        return acervo.stream().filter(livro -> !livro.isEmprestado()).toList();
    }

    public List<Livro> buscaPorAutor(String... autores){
        List<Livro> resultado = new ArrayList<>();
        for(Livro livro : acervo){
            for(String autor : autores){
                if(livro.getAutor().equalsIgnoreCase(autor)){
                    resultado.add(livro);
                    break;
                }
            }
        }
        return resultado;
    }

    public void exibirRelatorio(){
        System.out.println("\n---- Relatório da Biblioteca ----");
        System.out.println("Total de livros no acervo: " + acervo.size());

        System.out.println("Livros disponíveis:");
        listarDisponiveis().forEach(livro ->
                System.out.println("- " + livro.getTitulo() + " (" + livro.getAutor() + ")")
        );

        System.out.println("Livros emprestados:");
        listarEmprestados().forEach(livro ->
                System.out.println("- " + livro.getTitulo() + " (" + livro.getAutor() + ")")
        );
    }

    public void exibirEstatisticasEmprestimos(){
        long emprestados = acervo.stream().filter(Livro::isEmprestado).count();
        long disponiveis = acervo.size() - emprestados;

        System.out.println("\n---- Estatísticas de Empréstimos ----");
        System.out.printf("Livros emprestados: %d\n", emprestados);
        System.out.printf("Livros disponíveis: %d\n", disponiveis);
    }

    public static int getTotalBibliotecas(){
        return totalBibliotecas;
    }
}
