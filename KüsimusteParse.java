import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Andreas on 03/03/2017.
 */
public class KüsimusteParse {

   static ArrayList<String> failiread = new ArrayList<>();

    public static void main(String[] args) throws Exception {

        radioButtons();
        //button();
        failiRead();
    }

    public static void failiRead(){

        for (int i = 0; i <failiread.size() ; i++) {
            System.out.println(failiread.get(i));
        }

    }

    public static void radioButtons(){// küsimused kus on kasutatud radioButtoneid



        String url = "http://www.e-uni.ee/e-kursused/eucip/arendus/kordamisksimused28.html"; // Link kust võtab radiobuttoniga küsimused

        Document doc = null; // muutuja kuhu laetakse html kood
        try {
            doc = Jsoup.connect(url).get(); // võtab url-i järgi html koodi
        } catch (IOException e) {
            e.printStackTrace();
        }

        Elements küsimused = doc.select("div.question"); // valib div elemendi question mis sisaldab kõiki küsimusi ja vastuseid

        ArrayList<String> vastused = new ArrayList<>(); //Array mis sisaldab vastuseid
        ArrayList<String> kontroll = new ArrayList<>(); //Array mis sisaldab vastuse tüüpi(õige, vale)

        int count = 3;


        for (int a = 0; a < küsimused.size(); a++) { 
            Elements e = küsimused.get(a).children().select("div"); //sisaldab küsimust, vastusevarjante, õigeid vastuseid
            System.out.println(a);
            String küsimus = küsimused.get(a).child(0).text(); // võtab küsimuse div-ist
            System.out.println(küsimus); //väljastab küsimuse


            for (int i = 0; i < e.size(); i++) {

                if (i > e.size() / 2) {

                    kontroll.add(e.get(i).text()); // lisab arraysse "kontroll" vastuse tüübi
                    System.out.println(e.get(i).text());
                }else {

                    if (i != 0) {
                        vastused.add(e.get(i).text()); // lisab arraysse "vastused"
                        System.out.println(e.get(i).text());
                    }

                }
            }

            for (int i = 0; i < kontroll.size(); i++) {
                //System.out.println(vastused.get(i) + " - " + kontroll.get(i)); //väljastab küsimuse koos vastuse tüübiga(õige,vale)
            }

            String vastuseQuery = küsimus + ";";

            for (int i = 0; i <kontroll.size(); i++) {
                if(i < kontroll.size()-1){
                    vastuseQuery = vastuseQuery + vastused.get(i) + ",";
                }else{
                    vastuseQuery = vastuseQuery + vastused.get(i) + ";";
                }


                if(kontroll.get(i).equals("Õige")){
                    //System.out.println(vastused.get(i) + " - Õige"); // väljastab vastuse koos vastuse tüübiga
                }else{
                    //System.out.println(vastused.get(i) + " - Vale"); // väljastab vastuse koos vastuse tüübiga
                }

            }

            for (int i = 0; i <kontroll.size(); i++) {
                if(kontroll.get(i).equals("Õige")){
                    if(i < kontroll.size()-1){

                        if(i == 0){
                            vastuseQuery = vastuseQuery + "Õige";
                        }else{
                            vastuseQuery = vastuseQuery + ",Õige";
                        }
                    }else{
                        vastuseQuery = vastuseQuery + ",Õige;";
                    }
                }else{
                    if(i < kontroll.size()-1){
                        if(i == 0){
                            vastuseQuery = vastuseQuery + "Vale";
                        }else{
                            vastuseQuery = vastuseQuery + ",Vale";
                        }
                    }else{
                        vastuseQuery = vastuseQuery + ",Vale;";
                    }
                }

            }

            System.out.println(vastuseQuery);
            failiread.add(vastuseQuery);


            vastused.clear(); //teeb vastuse array tühjaks et saaks järgmised sisestada
            kontroll.clear(); //teeb vastuse tüübi array tühjaks et saaks järgmised sisestada

            System.out.println(" "); //teeb outputi arusaadavaks(eraldab küsimused)
        }
    }

    public static void button(){ // küsimused kus on kasutatud tavalisi nuppe
        String url = "http://www.e-uni.ee/e-kursused/eucip/arendus/kordamisksimused6.html"; // Link kust võtab buttoniga küsimused

        Document doc = null; // muutuja kuhu laetakse html kood
        try {
            doc = Jsoup.connect(url).get(); // võtab url-i järgi html koodi
        } catch (IOException e) {
            e.printStackTrace();
        }

        Elements küsimused = doc.select("div.question"); // valib div elemendi question mis sisaldab kõiki küsimusi ja vastuseid

        ArrayList<String> vastused = new ArrayList<>(); //Array mis sisaldab vastuseid
        ArrayList<String> kontroll = new ArrayList<>(); //Array mis sisaldab vastuse tüüpi(õige, vale)

        Elements küsimus = küsimused.select("table");

        System.out.println(küsimused.size());
        for (int i = 0; i <küsimused.size(); i++) {
            //küsimused.get(i);
            //System.out.println(küsimused.get(i).text());

            String question = küsimused.get(i).child(0).text(); // võtab küsimuse
            String answer = küsimused.get(i).child(1).text(); // võtab küsimuse
            System.out.println(i + "  -  " + question + " - " + answer); // väljastab küsimuse
            for (int j = 0; j <1 ; j++)  {
                //System.out.println( "* "+küsimus.get(i).select("td").get(i).text());
            }

        }

        int kysNr = 2;
        Elements tableRow  = küsimused.get(kysNr).child(1).select("tbody").select("tr").select("td");
        //System.out.println(tableRow);
        int size = küsimused.get(kysNr).child(1).select("tbody").select("tr").select("td").size();
        System.out.println(size);

        ArrayList<String> vastusedList = new ArrayList<>();
        ArrayList<String> vastuseVarjandid = new ArrayList<>();

        for (int i = 0; i <size ; i++) {


                //System.out.println(tableRow.get(i).text());



            if(tableRow.get(i).html().contains("input")){

                //System.out.println("Value: " + tableRow.get(i).child(0).attr("value"));
                if(tableRow.get(i).child(0).attr("value").equals("True")){
                    //System.out.println("Õige");
                    vastusedList.add("Õige");
                }else{
                    //System.out.println("Vale");
                    vastusedList.add("Vale");
                }

            }else{

                if(tableRow.get(i).html().contains("div")) {

                        System.out.println(i + tableRow.get(i).text());
                        vastuseVarjandid.add(tableRow.get(i).text());


                }

            }


        }

        String varjantideRida = "";

        for (int i = 0; i <vastuseVarjandid.size() ; i++) {
            if(i% 2 == 0){
                System.out.println(vastuseVarjandid.get(i));
                varjantideRida += vastuseVarjandid.get(i) + ",";

            }


        }
        varjantideRida = varjantideRida.substring(0,varjantideRida.length()-1);
        System.out.println(" ");
        varjantideRida += ";";
        for (int i = 0; i <vastusedList.size() ; i++) {

            System.out.println(vastusedList.get(i));
            varjantideRida += vastusedList.get(i) + ",";
        }
        varjantideRida = varjantideRida.substring(0,varjantideRida.length()-1) + ";";

        String questionn = küsimused.get(kysNr).child(0).text();

        System.out.println(questionn + ";"+varjantideRida);

        System.out.println(" ");
        
        int id = 2;

        for (int b = 0; b <=id ; b++) {


            String question = küsimused.get(b).child(0).text(); // võtab küsimuse
            System.out.println(question); // väljastab küsimuse

            
            
            for (int i = 1; i <=30 ; i+=1) {

                //System.out.println(küsimus.get(b).select("td").get(i).text());
                //vastused.add(küsimus.get(b).select("td").get(i).text()); // isab arraysse "vastused" vastuse
            }

            for (int a = 0; a < 4; a++) {

                //System.out.println(küsimus.get(b).select("td").select("input").get(a).attr("value").toString());
                //kontroll.add(küsimus.get(b).select("td").select("input").get(a).attr("value").toString()); // isab arraysse "kontroll" vastuse tüübi


            }

            String vastuseQuery = question + ";";

            for (int i = 0; i <kontroll.size(); i++) {
                if(i < kontroll.size()-1){
                    vastuseQuery = vastuseQuery + vastused.get(i) + ",";
                }else{
                    vastuseQuery = vastuseQuery + vastused.get(i) + ";";
                }


                if(kontroll.get(i).equals("True")){
                    System.out.println(vastused.get(i) + " - Õige"); // väljastab vastuse koos vastuse tüübiga
                }else{
                    System.out.println(vastused.get(i) + " - Vale"); // väljastab vastuse koos vastuse tüübiga
                }

            }

            for (int i = 0; i <kontroll.size(); i++) {
                if(kontroll.get(i).equals("True")){
                    if(i < kontroll.size()-1){

                        if(i == 0){
                            vastuseQuery = vastuseQuery + "Õige";
                        }else{
                            vastuseQuery = vastuseQuery + ",Õige";
                        }
                    }else{
                        vastuseQuery = vastuseQuery + ",Õige;";
                    }
                }else{
                    if(i < kontroll.size()-1){
                        if(i == 0){
                            vastuseQuery = vastuseQuery + "Vale";
                        }else{
                            vastuseQuery = vastuseQuery + ",Vale";
                        }
                    }else{
                        vastuseQuery = vastuseQuery + ",Vale;";
                    }
                }

            }

            System.out.println(vastuseQuery);
            failiread.add(vastuseQuery);

            kontroll.clear(); //tühjendab  array
            vastused.clear(); // tühjendab array
            System.out.println(" ");
        }


    }

}
