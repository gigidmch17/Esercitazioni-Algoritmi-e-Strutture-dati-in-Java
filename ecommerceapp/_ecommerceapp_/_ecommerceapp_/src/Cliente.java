import java.util.Scanner;
import java.util.ArrayList;

public class Cliente extends Persona implements Operazioni{

    public String accountName;
    public double saldo;
    public ArrayList<Prodotto> carrello;

    public Cliente(String nome, String cf, String accountName, double saldo) {
        super(nome, cf, TipoAccount.CLIENTE);
        this.accountName = accountName;
        this.saldo = saldo;
        this.carrello = new ArrayList<>();
    }

    public String getAccountName() { return accountName; }
    public double getSaldo() { return saldo; }
    public ArrayList<Prodotto> getCarrello() { return carrello; }

    public void setAccountName(String accountName) { this.accountName = accountName; }
    public void setSaldo(double saldo) { this.saldo = saldo; }

    @Override
    public String toString() {
        String tmp = "[ ";
        for (Prodotto p : carrello) {
            tmp += p.toString() + ", ";
        }
        tmp += " ]";
        return "Nome Account: " + accountName + "\n====CARRELLO====\n" + tmp + "\nSaldo: " + saldo + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;
        Cliente other = (Cliente) o;
        return accountName.equals(other.accountName) && saldo == other.saldo && carrello.equals(other.carrello);
    }

    @Override
    public void aggiungiProdotto(Prodotto prodotto) {
        carrello.add(prodotto);
    }

    @Override
    public Prodotto rimuoviProdotto() {
        if (carrello.size() == 0) {
            System.out.println("\nIl carrello è vuoto\n");
            return null;
        }
        System.out.println("\nQuale prodotto desideri rimuovere dal carrello?\n");
        for (int i = 0; i < carrello.size(); i++) {
            System.out.println((i + 1) + carrello.get(i).getNome() + "\n");
        }
        Scanner sc = new Scanner(System.in);
        int index = sc.nextInt();
        sc.nextLine();
        Prodotto rimosso = carrello.get(index - 1);
        carrello.remove(index - 1);
        System.out.println("\nProdotto " + rimosso.toString() + " rimosso\n");
        return rimosso;
    }

    public void aggiornaSaldo() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nSaldo attuale: " + saldo + "\n");
        System.out.println("Inserire valore da aggiungere al saldo:\n");
        double delta = sc.nextDouble();
        sc.nextLine();
        System.out.println("Denaro caricato sul saldo con successo!\n");
        saldo += delta;
        System.out.println("Saldo attuale: " + saldo + "\n");
    }

    public void acquistaProdotti(ECommerceApp eCommerceApp) throws CarrelloVuotoException, SaldoInsufficienteException {
        if (carrello.isEmpty()) throw new CarrelloVuotoException("Il carrello al momento è vuoto\n");
        double costo = 0.0;
        for (Prodotto prodotto : carrello) {
            costo += prodotto.getPrezzo();
        }
        if (costo > saldo) throw new SaldoInsufficienteException("Saldo insufficiente\n");
        saldo -= costo;
        setSaldo(saldo);
        carrello.clear();
        System.out.println("Acquisto effettuato con successo!\n!");
    }

    public void visualizzaProdotti(ECommerceApp eCommerceApp) {
        System.out.println("\n==PRODOTTI DISPONIBILI==\n");
        System.out.println(eCommerceApp.raccoltaProdotti());
    }

    @Override
    public void menu(ECommerceApp eCommerceApp) {
        while (true) {
            System.out.println("======MENU CLIENTE======\n");
            System.out.println("1. Aggiorna saldo");
            System.out.println("2. Visualizza prodotti");
            System.out.println("3. Aggiungi al carrello");
            System.out.println("4. Esegui acquisto");
            System.out.println("5. Logout");
            System.out.println("Inserire opzione");
            Scanner sc = new Scanner(System.in);
            int option = sc.nextInt();
            sc.nextLine();
            while (option < 1 || option > 5) {
                System.out.println("\nInserire un'opzione valida\n");
                System.out.println("======MENU CLIENTE======\n");
                System.out.println("1. Aggiorna saldo");
                System.out.println("2. Visualizza prodotti");
                System.out.println("3. Aggiungi al carrello");
                System.out.println("4. Esegui acquisto");
                System.out.println("5. Logout");
                System.out.println("Inserire opzione");
                option = sc.nextInt();
                sc.nextLine();
            }
            switch (option) {
                case 1:
                    aggiornaSaldo();
                    break;

                case 2:
                    visualizzaProdotti(eCommerceApp);
                    break;

                case 3:
                    System.out.println("Inserire codice prodotto per aggiungerlo al carrello");
                    String codiceProdotto = sc.nextLine();
                    sc.nextLine();
                    aggiungiProdotto(eCommerceApp.trovaProdotto(codiceProdotto));
                    break;

                case 4:
                    acquistaProdotti(eCommerceApp);
                    break;

                case 5:
                    System.out.println("Arrivederci!");
                    return;

                default:
                    System.out.println("\nScelta non valida!\n");
                    break;
            }
        }
    }


}
