package joao.programgenerator.custom;

/**
 * Created by Joao on 01-08-2015.
 */
public class Musica {

    private int numero;
    private String nome;

    public Musica(int numero, String nome){

        this.numero = numero;
        this.nome = nome;
    }

    public void setNumero(int numero){
        this.numero = numero;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public int getNumero(){

        return this.numero;
    }

    public String getNome(){
        return this.nome;
    }
}
