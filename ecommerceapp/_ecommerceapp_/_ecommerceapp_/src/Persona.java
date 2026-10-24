public class Persona {

    public String nome;
    public String cf;
    public TipoAccount tipoAccount;

    public Persona(String nome, String cf, TipoAccount tipoAccount) {
        this.nome = nome;
        this.cf = cf;
        this.tipoAccount = tipoAccount;
    }

    // N.B. Questo costruttore ci servirà più in là per la sotto-classe Venditore
    public Persona(String nome) {
        this.nome = nome;
    }

    public String getNome() { return nome; }
    public String getCf() { return cf; }
    public TipoAccount getTipoAccount() { return tipoAccount; }

    public void setNome(String nome) { this.nome = nome; }
    public void setCf(String cf) { this.cf = cf; }
    public void setTipoAccount(TipoAccount tipoAccount) { this.tipoAccount = tipoAccount; }

    @Override
    public String toString() {
        return "Nome: " + nome + "\nCF: " + cf + "\nTipo account: " + tipoAccount + "\n";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Persona)) return false;
        Persona other = (Persona) obj;
        return this.nome.equals(other.nome) && this.cf.equals(other.cf) && this.tipoAccount.equals(other.tipoAccount);
    }
}
