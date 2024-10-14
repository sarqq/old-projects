/**
 * Lausekielinen ohjelmointi 2 2017, harjoitustyö 2
 * - Sara Baba
 */

import java.util.*;
import java.io.*;

public class ASCIIArt{
    public static char[][] taulukoija(File ASCIITiedosto, int rivLkm, int sarLkm){
        char[][] taulu = null;
        //jos vaaditut tiedot kunnossa, luodaan taulukko main-operaatiosta saaduilla arvoilla
        if((ASCIITiedosto != null) && (rivLkm > 0) && (sarLkm > 0)){
            taulu = new char[rivLkm][sarLkm];
            try{
                //avataan lukija
                Scanner riviLukija = new Scanner(ASCIITiedosto);
                //luetaan tiedoston rivejä ja täytetään luotu taulukko niillä
                while(riviLukija.hasNextLine()){
                    for(int rivIdx = 0; rivIdx<rivLkm; rivIdx++){
                        String rivi = riviLukija.nextLine();
                        for(int sarIdx = 0; sarIdx<sarLkm; sarIdx++){
                        taulu[rivIdx][sarIdx] = rivi.charAt(sarIdx);
                        }
                    }
                }
                //suljetaan lukija
                riviLukija.close();
            }
            //käsitellään mahdollinen poikkeus
            catch(FileNotFoundException e){
                System.out.println("Invalid command-line argument!");
                System.out.println("Bye, see you soon.");
            }
        }
        //palautetaan luotu taulukko
        return taulu;
    }
    public static void printteri(char[][] kuvaTaulukkona){
        //varmistetaan että taulukolle on varattu muistia
        if(kuvaTaulukkona != null){
            //tulostetaan taulu
            for(int i = 0; i<kuvaTaulukkona.length; i++){
                for(int j = 0; j<kuvaTaulukkona[0].length; j++){
                    System.out.print(kuvaTaulukkona[i][j]);
                }
                System.out.print("\n");
            }
        }
    }
    public static void infoaja(char[][] kuvaTaulukkona, int korkeus, int leveys){
        //varmistetaan, että taulukolle on varattu muistia, ja että parametrit ovat oikeellisia
        if((kuvaTaulukkona != null) && (korkeus > 0 ) && (leveys > 0)){
            //luodaan taulukot erikoismerkeille ja niiden lukumäärille
            int[] esiintymiset = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
            char[] merkit = new char[]{'#','@','&','$','%','x','*','o','|','!',';',':','\'',',','.',' '};
        
            /* Käydään läpi merkkejä yksitellen. Kun kohdataan joku erikoismerkeistä, lisätään sitä vastaavaa
            * arvoa lukumäärille tarkoitetussa taulussa yhdellä. Tämän olisi varmaan voinut toteuttaa paremmin.
            */
            for(int rivIdx = 0; rivIdx<korkeus; rivIdx++){
                for(int sarIdx = 0; sarIdx<leveys; sarIdx++){
                    char nykMerkki = kuvaTaulukkona[rivIdx][sarIdx];
                    if(nykMerkki == merkit[0]){
                            esiintymiset[0] = esiintymiset[0] + 1;
                    }
                    if(nykMerkki == merkit[1]){
                        esiintymiset[1] = esiintymiset[1] + 1;
                    }
                    if(nykMerkki == merkit[2]){
                        esiintymiset[2] = esiintymiset[2] + 1;
                    }
                    if(nykMerkki == merkit[3]){
                        esiintymiset[3] = esiintymiset[3] + 1;
                    }
                    if(nykMerkki == merkit[4]){
                        esiintymiset[4] = esiintymiset[4] + 1;
                    }
                    if(nykMerkki == merkit[5]){
                        esiintymiset[5] = esiintymiset [5] + 1;
                    }
                    if(nykMerkki == merkit[6]){
                        esiintymiset[6] = esiintymiset[6] + 1;
                    }
                    if(nykMerkki == merkit[7]){
                        esiintymiset[7] = esiintymiset[7] + 1;
                    }
                    if(nykMerkki == merkit[8]){
                        esiintymiset[8] = esiintymiset[8] + 1;
                    }
                    if(nykMerkki == merkit[9]){
                        esiintymiset[9] = esiintymiset[9] + 1;
                    }
                    if(nykMerkki == merkit[10]){
                        esiintymiset[10] = esiintymiset[10] + 1;
                    }
                    if(nykMerkki == merkit[11]){
                        esiintymiset[11] = esiintymiset[11] + 1;
                    }
                    if(nykMerkki == merkit[12]){
                        esiintymiset[12] = esiintymiset[12] + 1;
                    }
                    if(nykMerkki == merkit[13]){
                        esiintymiset[13] = esiintymiset[13] + 1;
                    }
                    if(nykMerkki == merkit[14]){
                        esiintymiset[14] = esiintymiset[14] + 1;
                    }
                    if(nykMerkki == merkit[15]){
                        esiintymiset[15] = esiintymiset[15] + 1;
                    }
                }
            }
            //tulostetaan pyydetyt tiedot
            System.out.println("" + korkeus + " x " + leveys);
            for(int x = 0; x<merkit.length; x++){
                System.out.println(merkit[x] + " " + esiintymiset[x]);
            }
        }
    }
    public static char[][] varjaaja(char[][] kuvaTaulukkona, char vanhaVari, char uusiVari){
        //varmistetaan, että taulukolle on varattu muistia
        if(kuvaTaulukkona != null){
            //käydään läpi taulukon merkkejä
            //kun kohdataan uudelleenvärjättävä arvo, vaihdetaan se annetuksi uudeksi merkiksi
            for(int rivIdx = 0; rivIdx<kuvaTaulukkona.length; rivIdx++){
                for(int sarIdx = 0; sarIdx<kuvaTaulukkona[0].length; sarIdx++){
                    char nykMerkki = kuvaTaulukkona[rivIdx][sarIdx];
                    if(nykMerkki == vanhaVari){
                        kuvaTaulukkona[rivIdx][sarIdx] = uusiVari;
                    }
                }
            }
        }
        //palautetaan käsitelty taulukko
        return kuvaTaulukkona;
    }
    //aliohjelma kiertää taulukkoa 90 astetta oikealle
    public static char[][] oikeallePyorittaja(char[][] kuvaTaulukkona, int korkeus, int leveys){
        //luodaan uusi taulukko käsitellyille merkeille
        char[][] pyoritetty = null;
        if(kuvaTaulukkona != null){
            //koska taulukkoa käännetään, sen x ja y vaihtavat paikkaa
            int uusiLeveys = korkeus;
            int uusiKorkeus = leveys;
            pyoritetty = new char[uusiKorkeus][uusiLeveys];
            //siirretään merkkejä
            for(int i = 0; i<pyoritetty.length; i++){
                for(int j = 0; j<pyoritetty[i].length; j++){
                    pyoritetty[i][j] = kuvaTaulukkona[((pyoritetty[i].length)-1)-j][i];
                }
            }
        }
        //palautetaan käsitelty taulukko
        return pyoritetty;
    }
    public static char[][] vasemmallePyorittaja(char[][] kuvaTaulukkona, int korkeus, int leveys){
        //luodaan uusi taulukko käsitellyille merkeille
        char[][] pyoritetty = null;
        if(kuvaTaulukkona != null){
            //koska taulukkoa käännetään, sen x ja y vaihtavat paikkaa
            int uusiLeveys = korkeus;
            int uusiKorkeus = leveys;
            pyoritetty = new char[uusiKorkeus][uusiLeveys];
            for(int i = 0; i<pyoritetty.length; i++){
                for(int j = 0; j<pyoritetty[i].length; j++){
                    pyoritetty[i][j] = kuvaTaulukkona[j][(pyoritetty.length-1)-i];
                }
            }
        }
        //palautetaan käsitelty taulukko
        return pyoritetty;
    }
    public static void main(String[] args){
        //tulostetaan ohjelman nimi käyttäjälle
        System.out.println("-------------------");
        System.out.println("| A S C I I A r t |");
        System.out.println("-------------------");
        
        //luodaan tiedosto-olio komentoriviparametrina saadusta tiedoston nimestä
        String tiedostoNimi = args[0];
        File ASCIITiedosto = new File(tiedostoNimi);
        try{
            //avataan lukija
            Scanner kuvaLukija = new Scanner(ASCIITiedosto);
            //muuttujat tiedoston ulottuvuuksille, alkuarvoiksi 0
            int rivPituus = 0;
            int rivLkm = 0;
            
            while(kuvaLukija.hasNextLine()){
                //luetaan tiedoston rivejä
                String rivi = kuvaLukija.nextLine();
                rivPituus = rivi.length();
                //rivin vaihtuessa lisätään rivien lukumäärää yhdellä
                rivLkm++;
            }
            //suljetaan lukija
            kuvaLukija.close();
            
            //koska ASCII-tiedoston kaikki rivit ovat yhtä pitkiä, otetaan taulukon leveydeksi rivin pituus
            //taulukon korkeudeksi annetaan tiedoston rivien lukumäärä
            int kuvaLeveys = rivPituus;
            int kuvaKorkeus = rivLkm;
            //muunnetaan tiedosto taulukoksi
            char[][] kuvaTaulukkona = taulukoija(ASCIITiedosto, kuvaKorkeus, kuvaLeveys);
            kuvaKorkeus = kuvaTaulukkona.length;
            kuvaLeveys = kuvaTaulukkona[0].length;
            
            //vakioidaan kaikki komennot paitsi 'recolour'
            final String PRINTTAA = "print";
            final String INFOA = "info";
            final String PYORITAO = "rotater";
            final String PYORITAV = "rotatel";
            final String RESETOI = "reset";
            final String LOPETA = "quit";
            
            //lippumuuttuja ohjelman jatkumiselle
            boolean jatketaanko = true;
            do{
                //luetaan käyttäjältä komento
                System.out.println("Please, enter a command:");
                String komento = In.readString();
                if(komento.equals(PRINTTAA) == true){
                    printteri(kuvaTaulukkona);
                }
                if(komento.equals(INFOA) == true){
                    infoaja(kuvaTaulukkona, kuvaKorkeus, kuvaLeveys);
                }
                //koska komento "recolour x y" on 12:n pituinen, muuttujat x ja y ovat indeksipaikoilla 9 ja 11.
                if(komento.startsWith("recolour") == true){
                    char varjattava = komento.charAt(9);
                    char uusiVari = komento.charAt(11);
                    kuvaTaulukkona = varjaaja(kuvaTaulukkona, varjattava, uusiVari);
                }
                if(komento.equals(PYORITAO) == true){
                    kuvaTaulukkona = oikeallePyorittaja(kuvaTaulukkona, kuvaKorkeus, kuvaLeveys);
                    kuvaKorkeus = kuvaTaulukkona.length;
                    kuvaLeveys = kuvaTaulukkona[0].length;
                }
                if(komento.equals(PYORITAV) == true){
                    kuvaTaulukkona = vasemmallePyorittaja(kuvaTaulukkona, kuvaKorkeus, kuvaLeveys);
                    kuvaKorkeus = kuvaTaulukkona.length;
                    kuvaLeveys = kuvaTaulukkona[0].length;
                }
                if(komento.equals(RESETOI) == true){
                    //luodaan alkuperäisestä kuvatiedostosta taas taulukko
                    kuvaLeveys = rivPituus;
                    kuvaKorkeus = rivLkm;
                    kuvaTaulukkona = taulukoija(ASCIITiedosto, kuvaKorkeus, kuvaLeveys);
                }
                if(komento.equals(LOPETA) == true){
                    jatketaanko = false;
                    System.out.println("Bye, see you soon.");
                }
            }
            //jatketaan ohjelman suoritusta kunnes jatketaanko = false;
            while(jatketaanko);
        }
        //käsitellään mahdollinen poikkeus
        catch(FileNotFoundException e){
            System.out.println("Invalid command-line argument!");
            System.out.println("Bye, see you soon.");
        }
    }
}