package it.unicam.C3.utente;
public abstract class Utente {

	private int id;
    private String nome;
    private String cognome;
    private String email;
    private String password;

    public int getId()
    {
    	return this.id;
    }
    
    public void setId(int id)
    {
    	this.id=id;
    }
    
    /**
     *
     * @return name
     */
    public String getNome() {
        return nome;
    }

    /**
     * set name
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     *
     * @return surname
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * set surname
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    /**
     *
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * set email
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return password
     */
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String toString(){
        return this.nome+" "+this.cognome+" "+this.email;
    }
}
