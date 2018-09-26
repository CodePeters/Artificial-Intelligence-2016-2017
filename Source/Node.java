
public class Node {

    public  double x;
    public  double y;
    public  int code;
    public String name;
    private double h;

     Node(double x1,double y1,int code1, String name1){
        x=x1;
        y=y1;
        code=code1;
        name=name1;

    }

    public double geth (){
        return h;
    }

    public void seth (double x0,double y0){
        h= Math.sqrt(  ( Math.pow((x-x0) , 2.0 ) + Math.pow( (y-y0) , 2.0) ) );
    }

}
