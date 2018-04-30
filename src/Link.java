public class Link<T>
{
	private T daten;
	Link<T> naechster;

	public Link(T daten, Link<T> naechster)
	{
		this.daten = daten;
		this.naechster = naechster;
	}

	public T getDaten()
	{
		return daten;
	}

	public void setDaten(T daten)
	{
		this.daten = daten;
	}

	public String toString()
	{
		return daten.toString();
	}
}