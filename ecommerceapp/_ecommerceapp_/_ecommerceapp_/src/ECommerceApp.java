import java.util.ArrayList;
import java.util.Scanner;

public class ECommerceApp {
    public ArrayList<Persona> utenti;

    public ECommerceApp() {
        utenti = new ArrayList<>();
    }

    public ArrayList<Persona> getUtenti() { return utenti; }

    public String raccoltaProdotti() {
        String prodotti = "";
        for (int i = 0; i < utenti.size(); i++)
            if (utenti.get(i).getTipoAccount().equals(TipoAccount.VENDITORE)) {
                Venditore venditore = (Venditore) utenti.get(i);
                if (!venditore.getProdotti().isEmpty()) {
                    for (int j = 0; j < venditore.getProdotti().size(); j++) {
                        prodotti += venditore.getProdotti().getAt(j).toString() + "\n";
                    }
                }
            }
        return prodotti;
    }

    public Prodotto trovaProdotto(String code) {
        for (int i = 0; i < utenti.size(); i++) {
            if (utenti.get(i).getTipoAccount().equals(TipoAccount.VENDITORE)) {
                Venditore venditore = (Venditore) utenti.get(i);
                if (!venditore.getProdotti().isEmpty()) {
                    for (int j = 0; j < venditore.getProdotti().size(); j++) {
                        if (venditore.getProdotti().getAt(j).getCodice().equals(code)) {
                            return venditore.getProdotti().getAt(j);
                        }
                    }
                }
            }
        }
        System.out.println("\nNessun prodotto corrispondente alla ricerca\n");
        return null;
    }

    public void venditaProdotto(Prodotto prodotto) {
        if (prodotto == null) {
            System.out.println("\nAbbiamo riscontrato un problema\n");
        }
        for (int i = 0; i < utenti.size(); i++) {
            if (utenti.get(i).getTipoAccount().equals(TipoAccount.VENDITORE)) {
                Venditore venditore = (Venditore) utenti.get(i);
                if (!venditore.getProdotti().isEmpty()) {
                    for (int j = 0; j < venditore.getProdotti().size(); j++) {
                        if (venditore.getProdotti().getAt(j).equals(prodotto)) {
                            venditore.getProdotti().removeAt(j);
                            venditore.setCounterVendite(venditore.getCounterVendite() + 1);
                            break;
                        }
                    }
                }
            }
        }
    }

    public Persona trovaUtente(String accountName) {
        for (Persona utente : utenti) {
            if (utente.getTipoAccount().equals(TipoAccount.VENDITORE)) {
                Venditore v = (Venditore) utente;
                if (v.getAccountName().equals(accountName)) {
                    return utente;
                }
            }
            if (utente.getTipoAccount().equals(TipoAccount.CLIENTE)) {
                Cliente c = (Cliente) utente;
                if (c.getAccountName().equals(accountName)) {
                    return utente;
                }
            }
        }
        throw new UtenteNonTrovatoException("\nUtente non trovato\n");
    }

    public void menuApp() {
        while (true) {
            System.out.println("====MENU INIZIALE====");
            System.out.println("1. Login");
            System.out.println("2. Logout");
            System.out.println("Inseire opzione");
            Scanner sc = new Scanner(System.in);
            int option = sc.nextInt();
            sc.nextLine();
            while (option < 1 || option > 2) {
                System.out.println("\nInserire un'opzione valida\n");
                System.out.println("1. Login");
                System.out.println("2. Logout");
                System.out.println("Inseire opzione");
                option = sc.nextInt();
                sc.nextLine();
            }
            switch (option) {
                case 1:
                    System.out.println("Inserire nome utente\n");
                    String nomeUtente = sc.nextLine();
                    try {
                        Persona utente = trovaUtente(nomeUtente);
                        if (utente.getTipoAccount().equals(TipoAccount.VENDITORE)) {
                            Venditore v = (Venditore) utente;
                            v.menu(this);
                        }
                        if (utente.getTipoAccount().equals(TipoAccount.CLIENTE)) {
                            Cliente c = (Cliente) utente;
                            c.menu(this);
                        }
                    } catch (UtenteNonTrovatoException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 2:
                    System.out.println("\nArrivederci!\n");
                    return;
            }
        }
    }
}
