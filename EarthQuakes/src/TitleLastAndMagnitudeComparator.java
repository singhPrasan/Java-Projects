import java.util.*;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {

	@Override
	public int compare(QuakeEntry q1, QuakeEntry q2) {
		String title1 = q1.getInfo();
		int spaceIndex1 = title1.lastIndexOf(" ");
		String last1 = title1.substring(spaceIndex1);
		String title2 = q2.getInfo();
		int spaceIndex2 = title2.lastIndexOf(" ");
		String last2 = title2.substring(spaceIndex2);
		
		int diff = last1.compareTo(last2);
		if(diff == 0){
			diff = Double.compare(q1.getMagnitude(), q2.getMagnitude());
		}
		return diff;
	}

}
