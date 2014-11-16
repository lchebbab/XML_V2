
public class Client {
private String nom;
private String prenom;
private String adresse_postal;
private String adresse_mel;
private String mdp;

public Client(String nom,String prenom,String adresse_postal,String adresse_mel,String mdp){
	this.nom=nom;
	this.prenom=prenom;
	this.adresse_postal=adresse_postal;
	this.adresse_mel=adresse_mel;
	this.mdp=mdp;
}

public Client(){
	
}

public String getNom() {
	return nom;
}

public void setNom(String nom) {
	this.nom = nom;
}

public String getPrenom() {
	return prenom;
}

public void setPrenom(String prenom) {
	this.prenom = prenom;
}

public String getAdresse_postal() {
	return adresse_postal;
}

public void setAdresse_postal(String adresse_postal) {
	this.adresse_postal = adresse_postal;
}

public String getAdresse_mel() {
	return adresse_mel;
}

public void setAdresse_mel(String adresse_mel) {
	this.adresse_mel = adresse_mel;
}

public String getMdp() {
	return mdp;
}

public void setMdp(String mdp) {
	this.mdp = mdp;
}




}
