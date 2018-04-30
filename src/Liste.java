import java.util.*;

public class Liste<T>
{
	protected Link<T> anfang;
	protected Link<T> ende;

	// Vorgänger von AktuellerZeiger
	protected Link<T> vorgaengerAktuellerZeiger;

	public Liste()
	{
		// Leere Liste: alle Zeiger sind null (Standardwerte)
	}

	public T naechstesElement()
	{
		if (vorgaengerAktuellerZeiger == ende)
		{
			return null;
		}
		else
			if (vorgaengerAktuellerZeiger == null)
			{
				vorgaengerAktuellerZeiger = anfang;
			}
			else
			{
				vorgaengerAktuellerZeiger = vorgaengerAktuellerZeiger.naechster;
			}

		return vorgaengerAktuellerZeiger.getDaten();
	}

	// Zurücksetzen des aktuellen Zeigers auf den Listenanfang
	public void setzeAktuellerZeigerZurueck()
	{
		vorgaengerAktuellerZeiger = null;
	}

	// Einfügen vor die aktuelle Zeigerposition
	public void einfuegenElement(T neuesElement)
	{
		// Wenn die Liste leer ist, entspricht dies einem Anfügen
		if (istLeer())
		{
			anfuegenElement(neuesElement);

			return;
		}

		Link<T> neu = new Link<T>(neuesElement, aktuellerZeiger());

		// Wenn die Liste nur ein Element hat, muss anfang gesetzt werden
		if (vorgaengerAktuellerZeiger == null)
		{
			anfang = vorgaengerAktuellerZeiger = neu;

			return;
		}

		// Vorgänger-Element von neu zeigt jetzt auf neu
		vorgaengerAktuellerZeiger = vorgaengerAktuellerZeiger.naechster = neu;
	}

	// Anfügen am Ende der Liste
	public void anfuegenElement(T neuesElement)
	{
		// Neuen Link anlegen
		Link<T> neu = new Link<T>(neuesElement, null);

		// Wenn die Liste leer ist, müssen anfang und ende gesetzt werden
		if (istLeer())
		{
			// Neuen Link als anfang der Liste
			anfang = neu;
		}
		else
		{
			// Anfügen des Elements an das bisherige Ende
			ende.naechster = neu;	// Ende-Element zeigt jetzt auf neu
		}

		// Neues Ende
		ende = neu;
	}

	public T entfernenElement()
	{
		// Liste leer?
		if (istLeer())
			return null;

		Link<T> opfer = aktuellerZeiger();

		// Ist der aktuelle Zeiger über das Listenende hinausgewandert?
		if (opfer == null)
			return null;

		// Zeiger am Anfang der Liste
		if (vorgaengerAktuellerZeiger == null)
		{
			anfang = anfang.naechster;
		}
		else
		{
			vorgaengerAktuellerZeiger.naechster = opfer.naechster;
		}

		// Zeiger am Ende der Liste
		if (opfer == ende)
		{
			ende = vorgaengerAktuellerZeiger;
			vorgaengerAktuellerZeiger = null;
		}

		return opfer.getDaten();
	}

	// Liefert true, wenn Element in der Liste vorkommt, sonst false.
	// Wenn true zurückgegeben wurde, kann mit naechstesElement() auf Element
	// zugegriffen werden, wenn nicht steht aktuellerZeiger am Ende der Liste.
	public boolean suchenElement(T element)
	{
		Link<T> zeiger = anfang;
		Link<T> backupZeiger = null;

		// Zeiger auf Listenanfang setzen
		vorgaengerAktuellerZeiger = null;

		// Liste leer?
		if (zeiger == null)
			return false;

		// Abfrage auf Gleichheit ist möglich mit equals (Operation von der Klasse Object)
		while ((zeiger != null) && !zeiger.getDaten().equals(element))
		{
			backupZeiger = vorgaengerAktuellerZeiger;
			vorgaengerAktuellerZeiger = zeiger;

			zeiger = zeiger.naechster;
		}

		// Abfrage auf Ungleichheit
		if (zeiger == null)
		{
			// Zeiger eins zurücksetzen
			vorgaengerAktuellerZeiger = backupZeiger;

			return false;
		}

		return true;
	}

	public ListeIterator<T> iterator()
	{
		return new ListeIterator<T>(anfang, ende);
	}

	// Aktuellen Zeiger aus dem gespeicherten vorgaengerAkuellerZeiger ableiten
	public Link<T> aktuellerZeiger()
	{
		return istLeer() ? null : (vorgaengerAktuellerZeiger == null) ? anfang : vorgaengerAktuellerZeiger.naechster;
		// Sonderfallbehandlung; effiziente Kurzform für:
		// if (istLeer()) return null;
		// if (vorgaengerAktuellerZeiger == null) return anfang;
		// return vorgaengerAktuellerZeiger.naechster;
	}

	// true, wenn AktuellerZeiger nicht am Ende der Liste ist
	public boolean weitereElemente()
	{
		return istLeer() ? false : aktuellerZeiger() != ende;
		// Effiziente Kurzform für:
		// if (istLeer()) { return false; } else { return aktuellerZeiger() != ende; }
	}

	public Link<T> getAnfang()
	{
		return anfang;
	}

	public Link<T> getEnde()
	{
		return ende;
	}

	// Prüfen, ob Liste leer ist
	public boolean istLeer()
	{
		// Diese Methode wird im Praktikum implementiert
		// TODO
return anfang==null;	}

	public void verketten(Liste<T> zweiteListe)
	{
		ende.naechster = zweiteListe.anfang;
		ende= zweiteListe.ende;

	}

	public int loescheWerte(T victim)
	{
		int anzGeloeschte = 0;

		Link<T> zeiger = anfang;
		Link<T> backupZeiger = null;

		// Zeiger auf Listenanfang setzen
		vorgaengerAktuellerZeiger = null;


		// Abfrage auf Gleichheit ist möglich mit equals (Operation von der Klasse Object)
		while ((zeiger != null))
		{
			if(zeiger.getDaten().equals(victim)) {
				anzGeloeschte++;
				if (backupZeiger == null)
				{
					anfang = anfang.naechster;
				}
				else
				{
					backupZeiger.naechster = zeiger.naechster;
				}

				// Zeiger am Ende der Liste
				if (zeiger == ende)
				{
					ende = backupZeiger;
					backupZeiger = null;
				}


			}
			backupZeiger = zeiger;
			zeiger = zeiger.naechster;
			}
			
		return anzGeloeschte;
	}
}