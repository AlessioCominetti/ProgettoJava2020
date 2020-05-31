package Epidemia;
import java.util.*;
import java.lang.*;

public class Individuo extends Thread {
	
	private String Nome;
	public boolean Sano=true;
	public boolean Contagiato=false;
	public boolean Malato=false; //se è malato si applica la cura
	public boolean Morto=false; //se è morto lo sposto nell'arraylist Nero
	public boolean Guarito=false;
	public boolean Movimento=true; //se è in movimento genera guadagni
	public int Dur=18;
	public int Incub=3;
	public int Manif=6;
    private int Velo; //numero di incontri che ogni individuo fa al giorno
	
	public Individuo (String a) {
		Nome=a;
		
	}
	
	public void SetVelo(int a) { Velo=a;}
	public int GetVelo() {return Velo;}
	public void SetNome(String a) {Nome=a;}
	public String GetNome() {return Nome;}
	
	public void run() {
		while (Individuo.Morto==false) {
			//contatori per i giorni in cui esso è malato, contagiato, incubazione etc
			boolean tmp1;
			while (Velo>0 && Individuo.Malato==false && Individuo.Movimento==true) {
			    int a=(int)(Math.random(Popolazione-1-Nero.size())); //le persone morte non si possono incontrare
			    String b=this.Individuo.GetNome(); // l'individuo che fa iniziare il thread
			    char c=b.charAt(7);
			    while (a==c) { //mi serve per evitare che l'individuo incontri se stesso
			    	a=(int)(Math.random(Popolazione-1-Nero.size()));
			    }
			    Individuo d1=this.Individuo;
			    if (a>Verde.size()) {a=a-Verde.size();}
			    else {Individuo d2=Verde.get(a);}
			    if (a>Giallo.size()) {a=a-Giallo.size();}
			    else {Individuo d2=Giallo.get(a);}
			    if (a>Rosso.size()) {a=a-Rosso.size(); Individuo d2=Blu.get(a);}
			    else {Individuo d2=Rosso.get(a);}
			    // ora che ho preso i due individui li faccio incontrare
			    if ((d1.Sano==true && (d2.Contagiato==true || d2.Malato==true))) {
			    	tmp1=Contagio();
			    	if (tmp1==true) {d1.Contagiato==true; d1.Sano==false;
			    	Giallo.add(d1);
			    	Verde.remove(d1);
			    	}
			    }
			    
			    if ((d2.Sano==true && (d1.Contagiato==true || d1.Malato==true))) {
			    	tmp1=Contagio();
			    	if (tmp1==true) {d2.Contagiato==true; d2.Sano==false;
			    	Giallo.add(d2);
			    	Verde.remove(d2);
			    	}
			    	
			    }
			    //controllo sintomi
			    if ((d1.Contagiato==true) && (giorni.hasChanged()==true)) {
			    	d1.Manif--;
			    }
			    if (d1.Manif==0) {
			    	tmp1=Sintomi();
			    	if (tmp1==true) {d1.Contagiato=false; d1.Malato=true;
			    	Rosso.add(d1);
			    	Giallo.remove(d1);
			    	}
			    }
			    //decisione morte/vita
			    if ((d1.Malato==true) && (giorni.hasChanged()==true)) {
			    	d1.Incub--;
			    }
			    if (d1.Incub==0) {
			    	tmp1=Morte();
			    	if (tmp1==true) {d1.Malato=false; d1.Morto=true;
			    	Nero.add(d1);
			    	Rosso.remove(d1);
			    	}
			    }
			    if (((d1.Contagiato==true) || (d1.Malato==true)) && giorni.hasChanged()) {
			    	d1.Dur--;
			    }
			    if (d1.Dur==0) {d1.Contagiato=false; d1.Malato=false; d1.Guarito=true;
			    Blu.add(d1);
			    Giallo.remove(d1);
			    Rosso.remove(d1);
			    }
			
		}
	}

}
