import java.util.Comparator;

public class DistComparator implements Comparator<path> {
        @Override
        public int compare(path x, path y)
        {
            if( Math.abs( (y.distSum + y.lasth) - (x.distSum+ x.lasth) ) < 0.00000001)
            {
                return 0;
            }
            if ( ( x.distSum + x.lasth )  > ( y.distSum + y.lasth) )
            {
                return 1;
            }
            return -1;
        }
}
