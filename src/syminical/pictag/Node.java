package syminical.pictag;

public class Node<T> {

	private T Data;
	private Node<T> Next, Last;

	public Node( T Container, Node<T> Container2, Node<T> Container3 ) {

		Data = Container;
		Next = Container2;
		Last = Container3;

	}

	public void Next( Node<T> Container ) { Next = Container; }

	public void Last( Node<T> Container ) { Last = Container; }

	public T Data() { return Data; }

	public Node<T> Next() { return Next; }

	public Node<T> Last() { return Last; }

}

