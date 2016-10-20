// 2016 © Yoan Blanc <yoan@dosimple.ch>, HES-SO
//
// Code inspired from:
// * https://xml.apache.org/xalan-j/extensions.html#java-namespace
//
package ch.masrad.xml.xpath;

import java.util.Random;

public class RandomColor {
	public RandomColor() {
		r = new Random();
	}

	public String random() {

		return String.format("%05x", r.nextInt(0xffffff));
	}

	private Random r;
}
