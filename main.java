package Epidemia;
import java.util.*;

import java.lang.*;

//la popolazione è rappresentata come array dinamico di thread di persone
//ogni presona è un thread che frequenta l'array che è condiviso da tutti gli altri thread
//Ogni volta che una persona incontra un altra, il metodo Contagio si innesca
//La malattia ha un suo decorso che varia da simulazione a simulazione
//I dati di popolazione, risorse etc sono a scelta dell'utilizzatore
//si mettono in input dalla tastiera
//Alla fine di ogni giorno, si conta il numero di risorse guadagnate o perse e
//se la simulazione finisce o continua
//Ogni individuo ha un parametro di tipo int che è uguale a velocità
//e che viene decrementato durante il giorno quando l'individuo incontra altri individui
//e ogni giorno viene resettato
//Ogni individuo ha un parametro boolean che se è true può lavorare e incontrare
//se è false sta fermo e non produce
//il costo delle cure non cambia il metodo Guarigione
//Si possono creare altri array list per creare zone conifnate dove mettere gli
//individui contagiati
//Si fanno i tamponi a tutte le persone che non sono Sintomatiche o morte
//Si continuano a fare i tamponi a un individuo fino a che non mostra un segno
//della malattia o sono passati un periodo di tempo uguale al decorso della malattia stessa

public class main implements Runnable {
	Object chiave=new Object();
	public void run() {
		synchronized(chiave) {
			//codice da mettere che dice come un thread si comporta
		}
	}
	
	public boolean Contagio() {//si attiva quando un verde incontra un giallo o un rosso
		double a=(double)(Math.random(100.0));
		if (a<=Infettività) {
			return true; //significa che l'individuo si è contagiato
		}
		else {
			return false;//scappa l'opportunità ma non guarisce
		}
	}
	public boolean Sintomi() {//si attiva quando un giallo diventa rosso
		double a=(double)(Math.random(100.0));
		if (a<=Sintomaticità) {
			return true;//si ammala
		}
		else {
			return false;//guarisce
		}
	}
	
	public boolean Morte() {//si attiva quando un rosso diventa nero
		double a=(double)(Math.random(100.0));
		if (a<=Letalità) {
			return true; //muore
		}
		else {
			return false; //guarisce
		}
	}
	
	
	public boolean Successo (float a) {
		float b=(float)(Math.random()*100);
		if (a>b) return true;
		else return false;}
	
	public static int Popolazione=100;
	public static int Risorse=3650;
	public static int Tampone=4;
	public double Infettività=4/5;
	public double Sintomaticità=3/5;
	public double Letalità=1/5;
	public int Durata=18;
	public int Manifestazione=6;
	public int Incubazione=3;
	public int Velocità=1;
	
	public static String nomignolo;
	
	public void SetPopolazione(int a) {this.Popolazione=a;}
	public void SetRisorse(int a) { this.Risorse=a;}
	public void SetVelocità(int a) { this.Velocità=a;}
	public void SetTampone(int a) {this.Tampone=a;}
	public void SetInfettività(float a) {this.Infettività=a;}
	public void SetSintomaticità(float a) {this.Sintomaticità=a;}
	public void SetLetalità(float a) {this.Letalità=a;}
	public void SetDurata(int a) {this.Durata=a;}
	public void SetManifestazione(int a) {this.Manifestazione=a;}
	public void SetIncubazione(int a) {this.Incubazione=a;}
	
	public int GetRisorse() {return Risorse;}
	public int GetPopolazione() {return Popolazione;}

	public static void main(String[] args) {
		//Faccio scegliere i parametri all'utente
		Scanner scan=new Scanner(System.in);
		System.out.println("Inserisci dati popolazione");
		Popolazione=scan.nextInt();
		System.out.println("Inserisci dati risorse");
		Risorse=scan.nextInt();
		System.out.println("Inserisci dati costo tampone");
		Tampone=scan.nextInt();
		
		System.out.println("Inserisci dato tasso infettività");
		Infettività=scan.nextDouble();
		System.out.println("Inserici dato tasso Sintomaticità");
		Sintomaticità=scan.nextDouble();
		System.out.println("Inserisci dato tasso letalità");
		Letalità=scan.nextDouble();
				
		System.out.println("Inserisci dato della durata complessiva della malattia");
		Durata=scan.nextInt();
		System.out.println("Inserisci dato della durata della manifestazione");
		Manifestazione=scan.nextInt();
		System.out.println("Inserisci dato della durata dell'incubazione");
		Incubazione=scan.nextInt();
				
		System.out.println("Inserisci dato della velocità degli incontri");
		Velocità=scan.nextInt();
		
		System.out.println("Vuoi dare un nome alla malattia? Opzionale, lascia vuoto se vuoi");
		nomignolo=scan.nextLine();
		scan.close();
		
		//Creo i membi della città
		//Creo una matrice di individui dove le colonne sono il numero di individui
		//per ogni riga e le righe sono le aree in quarantena, una per ogni colore
		ArrayList<ArrayList<Individuo>> Città=new ArrayList<ArrayList<Individuo>>();
		ArrayList<Individuo> Verde=new ArrayList<Individuo>(); //persone sane
		ArrayList<Individuo> Giallo=new ArrayList<Individuo>(); //persone contagiate
		ArrayList<Individuo> Rosso=new ArrayList<Individuo>(); //persone malate
		ArrayList<Individuo> Blu=new ArrayList<Individuo>(); //persone guarite
		ArrayList<Individuo> Nero=new ArrayList<Individuo>(); //persone morte
		Individuo Persus=new Individuo("Persona1");
		Persus.SetVelo(Velocità); //paziente zero
		Persus.Manif=Manifestazione;
		Persus.Incub=Incubazione;
		Persus.Dur=Durata;
		Giallo.add(Persus);
		for (int i=1;i<Popolazione;i++) {
			String b="Persona"+(i+1);
			Individuo Perso=new Individuo(b);
			Perso.SetVelo(Velocità);
			Perso.Manif=Manifestazione;
			Perso.Incub=Incubazione;
			Perso.Dur=Durata;
			Verde.add(Perso);	
		}
		Città.add(Verde);
		Città.add(Giallo);
		Città.add(Rosso);
		Città.add(Blu);
		Città.add(Nero);
		
		//Faccio scegliere i parametri all'utente
		System.out.println("Inserisci dati popolazione");
		System.out.println("Inserisci dati risorse");
		System.out.println("Inserisci dati costo tampone");
		
		System.out.println("Inserisci dato tasso infettività");
		System.out.println("Inserici dato tasso Sintomaticità");
		System.out.println("Inserisci dato tasso letalità");
		
		System.out.println("Inserisci dato della durata complessiva della malattia");
		System.out.println("Inserisci dato della durata della manifestazione");
		System.out.println("Inserisci dato della durata dell'incubazione");
		
		System.out.println("Inserisci dato della velocità degli incontri");
		
		
		//iniziano le danze
		int giorni=1; // indica il numero di giorni
		Persus.start();
	    for (int i=0;i<Verde.size();i++) {
	    	Verde.get(i).start();
	    }			
		while (true) {
						    			    	   
			// fine della giornata
			// si perde una risorsa per ogni individuo fermo
			//per ogni individuo malato si spendono più risorse per la cura
			for (int i=0;i<Verde.size();i++) {
				Individuo a1=Verde.get(i);
				if (a1.Movimento==false) {
					Risorse=Risorse-1;
				}
			}
            for (int i=0;i<Giallo.size();i++) {
				Individuo a1=Giallo.get(i);
				if (a1.Movimento==false) {
					Risorse=Risorse-1;
				}
			}
            for (int i=0;i<Rosso.size();i++) {
            	Risorse=Risorse-3*Tampone;
            }	
				giorni++;
			
			// controllo vittoria malattia debellata
			if (Giallo.size()==0 || Rosso.size()==0) {
				System.out.println("La malattia "+nomignolo+" è stata debellata!");
				break;
			}
			// controllo vittoria malattia stermina tutti
			if (Nero.size()==Popolazione) {
				System.out.println("La malattia "+nomignolo+" ha sterminato il pianeta!");
				break;
			}
			// controllo vittoria collasso
			if (Risorse==0) {
				System.out.println("Il sistema è collassato!");
				break;
			}
			
			
		}

	}

}
