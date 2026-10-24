import java.util.Scanner;

public class Venditore extends Persona implements Operazioni {

    public String accountName;
    public ListaProdotti<Prodotto> prodotti;
    public int counterVendite;

    public Venditore(String nome, String cf, String accountName) {
        super(nome, cf, TipoAccount.VENDITORE);
        this.prodotti = new ListaProdotti<>();
        this.accountName = accountName;
        counterVendite = 0;
    }

    public String getAccountName() { return accountName; }
    public ListaProdotti<Prodotto> getProdotti() { return prodotti; }
    public int getCounterVendite() { return counterVendite; }
    public int getOggettiInVendita() { return prodotti.size();}

    public void setAccountName(String accountName) { this.accountName = accountName; }
    public void setCounterVendite(int counter) { this.counterVendite = counter; }

    @Override
    public String toString() {
        return "Nome Account: " + accountName + "\n====LISTA PRODOTTI====\n" + prodotti.toString() + "\nCounter vendite: " + counterVendite + "\n";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Venditore other = (Venditore) obj;
        return this.accountName.equals(other.accountName) && this.counterVendite == other.counterVendite && this.prodotti.equals(other.prodotti);
    }

    @Override
    public void aggiungiProdotto(Prodotto prodotto) {
        System.out.println("\nCome preferisci aggiungere il prodotto?\n1. In cima alla lista\n2. In fondo alla lista\n");
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();
        sc.nextLine();
        switch (option) {
            case 1:
                prodotti.addFirst(prodotto);
                System.out.println("\nProdotto aggiunto: " + prodotto.toString() + "\n");
                break;

            case 2:
                prodotti.addLast(prodotto);
                System.out.println("\nProdotto aggiunto: " + prodotto.toString() + "\n");
                break;
        }
    }

    @Override
    public Prodotto rimuoviProdotto() {
        if (prodotti.size() == 0) throw new RuntimeException("\nLa tua lista dei prodotti è vuota\n");
        System.out.println("\nEliminare il primo elemento della lista? (Y/N)\n");
        Scanner sc = new Scanner(System.in);
        char ans = sc.next().charAt(0);
        sc.nextLine();
        if (ans == 'Y') {
            prodotti.removeFirst();
        } else { System.out.println("\nOk\n"); }
        System.out.println("\nEliminare il prodotto infondo alla lista? (Y/N)\n");
        char ans2 = sc.next().charAt(0);
        sc.nextLine();
        if (ans2 == 'Y') {
            prodotti.removeLast();
        } else { System.out.println("\nOk\n"); }
        System.out.println("\nQuale prodotto desideri rimuovere?\n");
        prodotti.toString();
        System.out.println("\nInserire indice:\n");
        int index = sc.nextInt();
        sc.nextLine();
        Prodotto rimosso = prodotti.removeAt(index);
        System.out.println("\nProdotto eliminato: " + rimosso.toString() + "\n");
        return rimosso;
    }

    @Override
    public void menu(ECommerceApp eCommerceApp) {
        while (true) {
            System.out.println("\n======MENU VENDITORE======\n");
            System.out.println("Benvenuto nel menu venditore!\nCosa desideri fare?\n");
            System.out.println("1. Metti in vendita un prodotto");
            System.out.println("2. Rimuovi un prodotto");
            System.out.println("3. Visualizza vendite");
            System.out.println("4. Logout");
            System.out.println("Inserire opzione");
            Scanner sc = new Scanner(System.in);
            int option = sc.nextInt();
            sc.nextLine();
            while (option < 1 || option > 4) {
                System.out.println("\nInserire un'opzione valida\n");
                System.out.println("1. Metti in vendita un prodotto");
                System.out.println("2. Rimuovi un prodotto");
                System.out.println("3. Visualizza vendite");
                System.out.println("4. Logout");
                option = sc.nextInt();
                sc.nextLine();
            }
            switch (option) {
                case 1:
                    System.out.println("\nPerfetto! Inserisci il nome del prodotto: ");
                    String nome = sc.nextLine();
                    System.out.println("\nOk! Ora inserisci il codice del prodotto: ");
                    String codice = sc.nextLine();
                    System.out.println("\nCi siamo quasi! Inserisci il prezzo del prodotto: ");
                    double prezzo = sc.nextDouble();
                    Prodotto prodotto = new Prodotto(nome, codice, prezzo);
                    aggiungiProdotto(prodotto);
                    System.out.println("\nProdotto aggiunto! " + prodotto.toString() + "\n");
                    break;

                case 2:
                    System.out.println("\nCapisco... eliminiamo un prodotto dalla tua lista dei prodotti\n");
                    rimuoviProdotto();
                    break;

                case 3:
                    System.out.println("\nVediamo un po'... hai totalizzato... " + getCounterVendite() + " vendite! Continua così\n");
                    break;

                case 4:
                    System.out.println("\nArrivederci!\n");
                    break;

                default:
                    System.out.println("\nScelta non valida!\n");
                    break;
            }
        }

    }
}
