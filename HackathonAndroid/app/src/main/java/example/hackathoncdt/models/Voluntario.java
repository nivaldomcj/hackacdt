package example.hackathoncdt.models;

/**
 * Created by Nivaldo Carvalho on 19/02/2017.
 */

public class Voluntario {

    private Integer id;
    private String nome;
    private String credito;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCredito() {
        return credito;
    }

    public void setCredito(String credito) {
        this.credito = credito;
    }
}
