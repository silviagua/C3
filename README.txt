PRIMA ITERAZIONE:
----------------
Individuati i senguenti attori:
- Amministrazione Comunale
- Commerciante
- Commesso
- Cliente
- Corriere

Individuati e sviluppati i seguenti casi d'uso:
- ElaboraVendita
- GeneraPacco
- SelezionaCorriere

SECONDA ITERAZIONE
------------------
Aggiunti i seguenti attori:
- Utente anonimo
- 'Utente C3' generalizzazione degli utenti precedentemenete individuati

Aggiunti i seguenti casi d'uso:
- Iscrizione
- Login

Descritti nel dettaglio i seguenti casi d'uso:
- Affida Pacco (Commesso)
- Scarica Pacco (Corriere)
- Ritira Pacco (Cliente)

Aggiunto database e classe per la gestione del database, in questa iterazione il database è utilizzato solo per caricare 
la configurazione iniziale.

TERZA ITERAZIONE
----------------
Descritti nel dettaglio i seguenti casi d'uso:
- Iscrizione
- Login
- Logout

Descritti nel dettaglio i seguenti casi d'uso:
- Iscrizione (Utente anonimo)
- Login (Utente anonimo)
- Logout (Utente C3)
- Visualizza Vendite (Cliente)
- Consegna Pacchi (Utente C3)

Create classi separate per la gestione delle diverse aree del database. 
Create diverse classi per l'interazione con gli utenti.
Aggiunto il caso d'uso 'Ritira pacchi' anche per l'utente anonimo, in quanto un qualsiasi utente in 
possesso di un id_pacco valido può ritirare un pacco dal locker, senza dover fare il login.
