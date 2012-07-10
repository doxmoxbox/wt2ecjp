package web2.ls5.de.pages;

import org.apache.tapestry5.annotations.Property;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: merten
 * Date: 04.05.12
 * Time: 12:58
 * To change this template use File | Settings | File Templates.
 */
public class Loop {

	@Property
	private Integer value;


	public List<Integer> getCollection() {
		List<Integer> bla = new ArrayList<Integer>();

		for(int i = 0; i < 9; ++i) {
			bla.add(i);
		}

		return bla;
	}

	public int getQuadrat() {
		return value * value;
	}


}
