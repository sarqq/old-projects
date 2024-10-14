/* 
* Lausekielinen ohjelmointi II S2017, harjoitustyö 1
* Ohjelma lukee käyttäjän antaman syötteen, analysoi sitä ja kertoo siitä erilaisia tietoja.
*
* - Sara Baba
*/
public class StringStats {
    public static void merkinPoistaja(String s){
        //muuttujina syötteestä poistettavat erikoismerkit
        char pilkku = ',';
        char piste = '.';
        char puolip = ';';
        char kaksoisp = ':';
        char kysymys = '?';
        char huuto = '!';
        char jako = '/';
        char ykslainaus = '\'';
        char lainaus = '\"';
        char sulkua = '(';
        char sulkuk = ')';
        
        //muuttujat syötteen pituudelle ja uudelle merkittömälle jonolle
        int syotePituus = s.length();
        String merkiton = "";
        int i;
        
        //luodaan uusi merkitön jono lisäämällä siihen syötteestä kaikki merkit, paitsi edellämainitut erikoismerkit
        for(i = 0; i<syotePituus; i++) {
            //luetaan nykyinen merkki merkkijonosta
            char nyk = s.charAt(i);
            //mikäli nykyinen merkki ei ole mikään erikoismerkeistä, lisätään se uuteen merkittömään jonoon 
            if((nyk != pilkku) && (nyk != piste) && (nyk != puolip) && (nyk != kaksoisp) && (nyk != kysymys) &&
            (nyk != huuto) && (nyk != jako) && (nyk != ykslainaus) && (nyk != lainaus) && (nyk != sulkua) &&
            (nyk != sulkuk)){
                merkiton = merkiton + nyk;
            }
        }
        
        //tulostetaan uusi merkitön jono lainausmerkeissä
        System.out.println("\"" + merkiton + "\"");
        //lähetetään jono käsiteltäväksi
        analysoija(merkiton);
    }
    public static void analysoija(String paaJono){
        //muuttujat (nimet mielestäni tarpeeksi kuvaavia)
        final char EROTIN = ' ';
        int jononPituus = paaJono.length();
        int osienMaara = 0;
        String summaJono = "";
        
        //alustetaan muuttujat että ohjelma suostuu kääntämään
        int lyhinPituus = 0;
        int tokaLyhinPituus = 0;
        int pisinPituus = 0;
        int tokaPisinPituus = 0;
    
        String apuSana = "";
        int apuSananPituus = 0;
        int erotinLkm = 0;
    
        //lippumuuttuja ensimmäiselle kierrokselle
        boolean ekaKierros = true;
        for(int idx1 = 0; idx1<jononPituus; idx1++){
            char apumerkki1 = paaJono.charAt(idx1);
            //rakennetaan silmukan nykyisestä merkistä jonoja
            if(apumerkki1 != EROTIN){
                summaJono = summaJono + apumerkki1;
                apuSana = apuSana + apumerkki1;
                apuSananPituus = apuSana.length();
            }
            //jos nykyinen merkki on erotin (välilyönti), sana on valmis ja sitä ryhdytään vertaamaan
            if((apumerkki1 == EROTIN) || (idx1 == jononPituus-1)){
                //jos kyseessä on ensimmäinen kierros, annetaan muuttujille alkuarvoiksi vastakkaiset ääriarvot
                if(ekaKierros){
                    lyhinPituus = jononPituus; 
                    pisinPituus = 0;
                    tokaLyhinPituus = jononPituus;
                    tokaPisinPituus = 0;
                    //päivitetään lippumuuttuja merkitsemään, että ensimmäinen kierros on ohi
                    ekaKierros = false;
                }
                //seuraavilla kierroksilla rakennettuja sanoja verrataan muihin tunnettuihin merkkijonoihin
                //mikäli jokin arvo syrjäyttää nykyisen, päivitetään se uudeksi arvoksi
                if(!ekaKierros){
                    if(apuSananPituus < lyhinPituus){
                        tokaLyhinPituus = lyhinPituus;
                        lyhinPituus = apuSananPituus;
                    }
                    else if((apuSananPituus < tokaLyhinPituus) && (apuSananPituus > lyhinPituus)){
                        tokaLyhinPituus = apuSananPituus;
                    }
                    if(apuSananPituus > pisinPituus){
                        tokaPisinPituus = pisinPituus;
                        pisinPituus = apuSananPituus;
                    }
                    else if((apuSananPituus > tokaPisinPituus) && (apuSananPituus < pisinPituus)){
                        tokaPisinPituus = apuSananPituus;
                    }
                }
                //erottimen tullessa vastaan, päivitetään laskuria sanojen lukumäärälle
                osienMaara++;
                //aloitetaan uusi sana
                apuSana = "";
            }
        }
        
        //tarkistetaan erikoistapaukset, jossa osat ovat yhtä pitkiä
        //jos ääriarvot ovat yhtä pitkät, on muidenkin osien pakko olla
        if(lyhinPituus == pisinPituus){
            tokaLyhinPituus = pisinPituus;
            tokaPisinPituus = lyhinPituus;
        }
        
        //lasketaan osien keskimääräinen pituus
        int osienSumma = summaJono.length();
        double keskiarvo = ((double)(osienSumma))/((double)(osienMaara));
        int osienKeski = (int) Math.round(keskiarvo);
                
        //tulostetaan analyysin tulokset
        System.out.println("- The number of parts is " + osienMaara + ".");
        System.out.println("- The sum of part lengths is " + osienSumma + ".");
        System.out.println("- The average length of parts is " + osienKeski + ".");
        System.out.println("- The length of the shortest part is " + lyhinPituus + ".");
        System.out.println("- The length of the second shortest part is " + tokaLyhinPituus + ".");
        System.out.println("- The length of the second longest part is " + tokaPisinPituus + ".");
        System.out.println("- The length of the longest part is " + pisinPituus + ".");
    }
    public static void main(String[] args){
        //esitellään ohjelma käyttäjälle
        System.out.println("Hello! I calculate some string statistics.");
        //lippumuuttuja ohjelman jatkumiselle
        boolean jatkuuko;
        do{
            //luetaan syöte käyttäjältä
            System.out.println("Please, enter a string:");
            String syote = In.readString();
            //lähetetään syöte käsiteltäväksi
            merkinPoistaja(syote);
            
            //kysytään käyttäjältä, haluaako tämä ohjelman jatkuvan
            //kyllä = 'y', ei = 'n'
            char vastaus;
            System.out.println("Continue (y/n)?");
            vastaus = In.readChar();
                
                if(vastaus == 'y') {
                    //jatketaan ohjelmaa
                    jatkuuko = true;
                }
                if((vastaus != 'n') && (vastaus != 'y')) {
                    while((vastaus != 'y') && (vastaus != 'n')){
                        //mikäli syöte on virheellinen, jatketaan kyselyä kunnes syöte on oikein
                        System.out.println("Error!");
                        System.out.println("Continue (y/n)?");
                        vastaus = In.readChar();
                    }
                }
                if(vastaus == 'n') {
                    //lopetetaan ohjelma
                    jatkuuko = false;
                    System.out.println("See you soon.");
                    return;
                }
        }
        while(jatkuuko = true);
    }
}