public class Prodotto {

    public String nome;
    public String codice;
    public double prezzo;

    public Prodotto(String nome, String codice, double prezzo) {
        this.nome = nome;
        this.codice = codice;
        this.prezzo = prezzo;
    }

    public String getNome() { return nome; }
    public String getCodice() { return codice; }
    public double getPrezzo() { return prezzo; }

    public void setNome(String nome) { this.nome = nome; }
    public void setCodice(String codice) { this.codice = codice; }
    public void setPrezzo(double prezzo) { this.prezzo = prezzo; }

    @Override
    public String toString() {
        return "[Nome: " + nome + "\nCodice: " + codice + "\nPrezzo: " + prezzo + "]\n";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Prodotto)) return false;
        Prodotto other = (Prodotto) obj;
        return this.nome.equals(other.nome) && this.codice.equals(other.codice) && this.prezzo == other.prezzo;
    }
}
