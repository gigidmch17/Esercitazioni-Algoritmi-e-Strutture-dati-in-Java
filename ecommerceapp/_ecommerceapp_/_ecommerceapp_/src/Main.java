public class Main {
    public static void main(String[] args) {
        System.out.println("===== TEST PROGRAMMA ECOMMERCE =====\n");

        // --- TEST PRODOTTO ---
        Prodotto p1 = new Prodotto("Laptop", "P001", 1000.0);
        Prodotto p2 = new Prodotto("Mouse", "P002", 20.0);
        Prodotto p3 = new Prodotto("Monitor", "P003", 200.0);

        System.out.println("Prodotto p1: " + p1);
        System.out.println("Prodotto p2 uguale a p1? " + p2.equals(p1));
        p2.setPrezzo(25.0);
        System.out.println("p2 modificato: " + p2);

        // --- TEST LISTAPRODOTTI ---
        ListaProdotti<Prodotto> lista = new ListaProdotti<>();
        lista.addFirst(p1);
        lista.addLast(p2);
        lista.addLast(p3);
        System.out.println("\nLista prodotti: \n" + lista);
        System.out.println("Primo elemento: " + lista.first());
        System.out.println("Ultimo elemento: " + lista.last());
        System.out.println("Elemento in posizione 1: " + lista.getAt(1));

        Prodotto rimosso = lista.removeAt(1);
        System.out.println("Rimosso: " + rimosso);
        System.out.println("Lista aggiornata: " + lista);

        // --- TEST PERSONA ---
        Persona persona1 = new Persona("Mario Rossi", "RSSMRA80A01F205X", TipoAccount.CLIENTE);
        Persona persona2 = new Persona("Mario Rossi", "RSSMRA80A01F205X", TipoAccount.CLIENTE);
        System.out.println("\nPersona1: \n" + persona1);
        System.out.println("Persona1 uguale a Persona2? " + persona1.equals(persona2));
        persona1.setNome("Luigi Verdi");
        System.out.println("Persona1 modificata: " + persona1);

        // --- TEST VENDITORE ---
        Venditore venditore = new Venditore("Mario Rossi", "RSSMRA80A01F205X", "venditore1");
        venditore.getProdotti().addLast(new Prodotto("Tastiera", "P004", 50.0));
        venditore.getProdotti().addLast(new Prodotto("Stampante", "P005", 150.0));
        System.out.println("\nVenditore creato:\n" + venditore);
        System.out.println("Numero oggetti in vendita: " + venditore.getOggettiInVendita());

        // --- TEST CLIENTE ---
        Cliente cliente = new Cliente("Giuseppe Bianchi", "BNCGPP90C10H501F", "cliente1", 2000.0);
        cliente.aggiungiProdotto(p1);
        cliente.aggiungiProdotto(p2);
        System.out.println("\nCliente creato:\n" + cliente);

        cliente.rimuoviProdotto(); // ⚠️ attenzione: nel tuo codice originale usa Scanner → qui sarebbe da modificare

        // --- TEST ECOMMERCEAPP ---
        ECommerceApp app = new ECommerceApp();
        app.getUtenti().add(venditore);
        app.getUtenti().add(cliente);

        System.out.println("\n=== Prodotti disponibili nell'app ===");
        System.out.println(app.raccoltaProdotti());

        System.out.println("Trova prodotto con codice P001:");
        Prodotto trovato = app.trovaProdotto("P001");
        System.out.println(trovato);

        System.out.println("Trova utente 'cliente1':");
        Persona trovatoUtente = app.trovaUtente("cliente1");
        System.out.println("Trovato: " + trovatoUtente.getNome());

        // --- TEST ECCEZIONI ---
        try {
            app.trovaUtente("inesistente");
        } catch (UtenteNonTrovatoException e) {
            System.out.println("Eccezione catturata: " + e.getMessage());
        }

        try {
            Cliente c2 = new Cliente("Test", "XXXX", "cliente2", 10.0);
            c2.acquistaProdotti(app); // carrello vuoto
        } catch (CarrelloVuotoException e) {
            System.out.println("Eccezione catturata: " + e.getMessage());
        } catch (SaldoInsufficienteException e) {
            System.out.println("Saldo insufficiente: " + e.getMessage());
        }

        System.out.println("\n===== FINE TEST =====");
    }
}
