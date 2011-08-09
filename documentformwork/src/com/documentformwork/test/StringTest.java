package com.documentformwork.test;

public class StringTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String name="vsg.root.module.2.icon";
		String name1="vsg.root.module.2.icon";
		System.out.println(name1.split("\\.").length);
		System.out.println(name.indexOf("vsg.root.module."));
		System.out.println(name1.substring("vsg.root.module.".length(),name1.lastIndexOf(".")));
		
		String key=name1.substring("vsg.root.module.".length(),name1.lastIndexOf("."));
		System.out.println(name1.substring(4));
		System.out.println(name1.substring("vsg.root.module.".length()+(key+".").length()));

	}

}
