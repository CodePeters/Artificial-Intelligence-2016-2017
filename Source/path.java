import java.util.Vector;

public class path {

    public Vector path_nodes= new Vector();
    public double distSum;
    public double lasth;
    public int last_node;

    path (int newNode,double h,double dist){
        lasth=h;
        last_node=newNode;
        path_nodes.add(newNode);
        distSum=dist;
    }

    path (path original){
        this.path_nodes= new Vector(original.path_nodes);
        this.lasth=original.lasth;
        this.last_node=original.last_node;
        this.distSum=original.distSum;
    }

    public void path_add(int i,double h,double dist){
        lasth=h;
        last_node=i;
        distSum+=dist ;
        path_nodes.add(i);

    }

}
