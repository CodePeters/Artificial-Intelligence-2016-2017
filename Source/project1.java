import java.util.*;
import java.util.Iterator;

public class project1 {

    public static void main( String []args){

        Graph g=new Graph("nodes.csv");

        CSVreader client= new CSVreader("client.csv");

        double x_goal =  Double.parseDouble(client.v.get(0)[0]);
        double y_goal =  Double.parseDouble(client.v.get(0)[1]);

        int  Goal = g.find_closest(x_goal,y_goal);

        x_goal=g.graph_nodes.get(Goal).x;
        y_goal=g.graph_nodes.get(Goal).x;

        CSVreader taxis= new CSVreader("taxis.csv");

        Iterator<Node> it = g.graph_nodes.iterator();

        while(it.hasNext()){
            it.next().seth(x_goal,y_goal);
        }

        Node [] array_nodes = g.graph_nodes.toArray(new Node[g.graph_nodes.size()]);

        Vector<path> taxi_routes=new Vector<>(10);
        int sizetaxi=taxis.v.size();

        // ** A STAR for each TAXI ** \\
        for(int i=0;i<sizetaxi;i++){

            double x_start =  Double.parseDouble(taxis.v.get(i)[0]);
            double y_start =  Double.parseDouble(taxis.v.get(i)[1]);

            int  Start = g.find_closest(x_start,y_start);

            boolean[] visited = new boolean[g.graph_nodes.size()];
            visited[Start]=true;

            x_start=array_nodes[Start].x;
            y_start=array_nodes[Start].y;

            Comparator<path> comparator = new DistComparator();
            PriorityQueue<path> pq = new PriorityQueue<path>(10, comparator);

            int neighboors_size=g.Edges.elementAt(Start).size();

            double start_h=  Math.pow((array_nodes[Goal].x-x_start),2.0)+ Math.pow((array_nodes[Goal].y-y_start),2.0);
            path startPath= new path(Start,start_h,0);

            for(int j=0;j<neighboors_size;j++){
                int neighboor=(int)g.Edges.elementAt(Start).elementAt(j);

                double distance= Math.pow((array_nodes[neighboor].x-x_start),2.0)+ Math.pow((array_nodes[neighboor].y-y_start),2.0);
                distance = Math.sqrt(  distance );

                path pathj = new path(startPath);

                pathj.path_add(neighboor,array_nodes[neighboor].geth(),distance);
                pq.add(pathj);

            }

            while(!pq.isEmpty()){

                  path p1=pq.poll();
                  int current=p1.last_node;
                  visited[p1.last_node]=true;

                  if (current==Goal) {

                      taxi_routes.add(p1);
                      break;
                  }

                  int neighboors_size2=g.Edges.elementAt(current).size();

                  for(int j=0;j<neighboors_size2;j++){
                     int neighboor=(int)g.Edges.elementAt(current).elementAt(j);

                     if(!visited[neighboor]) {

                             double distance = Math.pow((array_nodes[neighboor].x - array_nodes[current].x), 2.0) + Math.pow((array_nodes[neighboor].y - array_nodes[current].y), 2.0);
                        distance = Math.sqrt(distance);

                        path pathj2 = new path(p1);
                        pathj2.path_add(neighboor,array_nodes[neighboor].geth(),distance);

                        pq.add(pathj2);
                     }

                  }
            }

        }

        Iterator<path> it2 = taxi_routes.iterator();
        int min=0;
        int k=0;

        while(it2.hasNext()){

            path element=it2.next();
            if(element.distSum<taxi_routes.get(min).distSum) min=k;
            k++;

        }

        KMLcreator kmltest=new KMLcreator(taxi_routes,min,array_nodes);


    }

}
