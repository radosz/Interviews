public class Start {

	public static void main(String[] args) {
		Keranov keranov = new Keranov();
		QvkataDLG qvkata = new QvkataDLG();
		System.out.println("qvakata.songs() != keran.songs() "
				+ !qvkata.songs().equals(keranov.songs()));
		System.out.println(qvkata);
		System.out.println(keranov);
	}

}
