import java.util.Iterator;
import java.util.Vector;
import java.io.FileWriter;
import java.io.IOException;

public class KMLcreator {

    String kmlstart =  "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"+
                       "\t<kml xmlns=\"http://earth.google.com/kml/2.1\">\n"+
                       "\t<Document>\n"+
                       "\t\t<name>Taxi Routes</name>\n"+
                       "\t\t<Style id=\"green\">\n"+
                       "\t\t\t<LineStyle>\n"+
                       "\t\t\t\t<color>ff009900</color>\n"+
                       "\t\t\t\t<width>4</width>\n"+
                       "\t\t\t</LineStyle>\n"+
                       "\t\t</Style>\n"+
                       "\t\t<Style id=\"red\">\n"+
                       "\t\t\t<LineStyle>\n"+
                       "\t\t\t\t<color>ff0000ff</color>\n"+
                       "\t\t\t\t<width>4</width>\n"+
                       "\t\t</LineStyle>\n"+
                       "\t\t</Style>\n";

    String kmlend = "\t</Document>\n"+"</kml>";

    String kmlelement ="\t\t<Placemark>\n";

    String  kmlgreen = "\t\t\t<styleUrl>#green</styleUrl>\n";

    String  kmlred = "\t\t\t<styleUrl>#red</styleUrl>\n";

    String kmlelement1= "\t\t\t<LineString>\n"+
                       "\t\t\t\t<altitudeMode>relative</altitudeMode>\n"+
                       "\t\t\t\t<coordinates>\n";

    String kmlelement2 = "\t\t\t\t</coordinates>\n"+
                        "\t\t\t</LineString>\n"+
                        "\t\t</Placemark>\n";


  KMLcreator(Vector<path> v,int min,Node array[]){

      try {

        FileWriter  fwriter = new FileWriter("finalM.kml");


          Iterator<path> it2 =v.iterator();

          int k=0;
          fwriter.write(kmlstart);

          while(it2.hasNext()){


              String output;
              String option=kmlred;
              if(k==min) option=kmlgreen;
              int taxi_number=k+1;
              output=kmlelement+"\t\t\t<name>Taxi"+taxi_number+"</name>\n"+option+kmlelement1;

              path element=it2.next();

              Iterator newit  =element.path_nodes.iterator();

              while(newit.hasNext()){

                  Node current=  array[(int)newit.next()];
                  output=output+"\t\t\t\t"+current.x+","+current.y+","+0+"\n";

              }
              output=output+ kmlelement2;
              fwriter.write(output);
              k++;

          }

          fwriter.write(kmlend);
          fwriter.flush();
          fwriter.close();
      }catch (IOException e1) {
          e1.printStackTrace();
      }

  }

}
