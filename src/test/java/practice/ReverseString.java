package practice;

public class ReverseString {

	public static void main(String[] args) {
		String j="java";
		String reverse="";
		for (int i = 0; i < j.length(); i++) {
			char revers = j.charAt(i);
			reverse=revers+reverse;
		}
		System.out.println(reverse);
	}

}
