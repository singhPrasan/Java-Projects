
import java.util.*;
public class TitleAndDepthComparator implements Comparator<QuakeEntry> {

	@Override
	public int compare(QuakeEntry q1, QuakeEntry q2) {
		String title1 = q1.getInfo();
		String title2 = q2.getInfo();
		int diff = title1.compareTo(title2);
		if(diff == 0 ){
			diff = Double.compare(q1.getDepth(), q2.getDepth());
		}
		return diff;
	}

}
