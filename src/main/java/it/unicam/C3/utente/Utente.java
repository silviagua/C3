package it.unicam.C3.utente;
public  class Utente {

	private int id;
    private String nome;
    private String cognome;
    private String email;
    private String password;
    private String username;

    public int getId()
    {
    	return this.id;
    }
    
    public void setId(int id)
    {
    	this.id=id;
    }
    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setUserName(String userName) {
    	this.username= userName;
    }
    
    public String getUserName() {
    	return username;
    }

    public String toString(){
        return this.nome+" "+this.cognome+" "+this.email;
    }
    
    public Utente(int id, String nome, String cognome, String email, String password, String userName) {
    	this.setId(id);
        this.setNome(nome);
        this.setCognome(cognome);
        this.setEmail(email);
        this.setPassword(password);
        this.setUserName(userName);
    }    
}
