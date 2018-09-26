import java.util.Vector;

public class Graph {

    public Vector <Node> graph_nodes = new Vector <Node>(10,100);
    public Vector<Vector> Edges = new Vector<Vector>();

    Graph( String name1) {

        CSVreader nodes= new CSVreader(name1);

        for (String[] element : nodes.v) {

           String name;
           if (element.length>3) name = element[3];
           else name =" ";

            double x1 =  Double.parseDouble(element[0]);
            double x2 =  Double.parseDouble(element[1]);
            int code = Integer.parseInt(element[2]);

            graph_nodes.add(new Node(x1,x2,code,name));
        }

       Node [] array=graph_nodes.toArray(new Node[graph_nodes.size()]);

        int len=graph_nodes.size();

        for(int i=0;i<len;i++){

            Vector v1 =new Vector();
            if (i<len-1 && array[i].code==array[i+1].code ) v1.add(i+1);
            if ( i>0 && array[i-1].code==array[i].code) v1.add(i-1);

           for(int j=0;j<len;j++){

                if (j!=i && array[i].x==array[j].x && array[i].y==array[j].y) {v1.add(j); }
           }
            Edges.add(v1);
        }

    }

    public int find_closest(double x0,double y0){

        int closest=0;
        double distance=  Math.sqrt(Math.pow(1000*(graph_nodes.get(0).x-x0),2.0)+ Math.pow(1000*(graph_nodes.get(0).y-y0),2.0) );

        Node [] array=graph_nodes.toArray(new Node[graph_nodes.size()]);

        int len=graph_nodes.size();

        for(int i=1;i<len;i++){

            double distance2= Math.sqrt( Math.pow(1000*(array[i].x-x0),2.0)+ Math.pow(1000*(array[i].y-y0),2.0)) ;

            int retval = Double.compare(distance, distance2);
            if (retval>0) {distance=distance2; closest=i; }
        }

        return closest;
    }
}
