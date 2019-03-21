import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class test {

 // constantes ------------------------------------------------------------------------------------

    public static final String KEY_DATA  = "Data";
    public static final String PATH_JSON = "C:\\JSON\\";

 // méthodes --------------------------------------------------------------------------------------

 // deserialisation d'un fichier JSON d'un objet unique Personne
    public static Personne deserializePersonne(String fileName)
    {
        Personne personne = null;
        String currentLine;
        String stringToJSON = "";

        try
        {
            FileReader     fichier = new FileReader(fileName);
            BufferedReader reader  = new BufferedReader(fichier);

            while ((currentLine = reader.readLine()) != null)
            {
                stringToJSON += currentLine;
            }

            reader.close();

            personne = new Personne( new JSONObject(stringToJSON) );
        }
        catch (FileNotFoundException io404Error) {
            System.out.println(io404Error.getMessage());
        }
        catch (IOException ioError) {
            System.out.println(ioError.getMessage());
        }

        return personne;
    }

    // deserialisation d'un fichier JSON de plusieurs objets Personne
    public static Personne[] deserializePersonnes(String fileName)
    {
        Personne[] tableau = null;
        String currentLine;
        String stringToJSON = "";

        try
        {
            FileReader fichier = new FileReader(fileName);
            BufferedReader reader = new BufferedReader(fichier);

            while ( (currentLine = reader.readLine() ) != null)
            {
                stringToJSON += currentLine;
            }

            reader.close();

            JSONArray objectData = ( new JSONObject(stringToJSON) ).getJSONArray(KEY_DATA);

            tableau = new Personne[objectData.length()];

            for ( int i = 0 ; i < tableau.length ; i++ )
                tableau[i] = new Personne( objectData.getJSONObject(i) );
        }
        catch (FileNotFoundException io404Error)
        {
            System.out.println(io404Error.getMessage());
        }
        catch (IOException ioError)
        {
            System.out.println(ioError.getMessage());
        }

        return tableau;
    }

    // serialisation d'un objet Personne dans un fichier fileName
    public static void serializePersonne(Personne personne, String fileName)
    {
        try (FileWriter fichier = new FileWriter(fileName))
        {
            fichier.write( personne.toString() );
            fichier.flush();
        }
        catch (IOException ioError)
        {
            System.out.println(ioError.getMessage());
        }
    }

    // serialisation d'un objet Personne dans un fichier fileName
    public static void serializePersonne(Personne[] personnes, String fileName)
    {
        JSONArray tableau = new JSONArray();
        JSONObject object = new JSONObject();

        for ( int i = 0 ; i < personnes.length ; i++ )
            tableau.put( new JSONObject( personnes[i].toString() ) );

        object.put(KEY_DATA, tableau);

        try (FileWriter fichier = new FileWriter(fileName))
        {
            fichier.write( object.toString() );
            fichier.flush();
        }
        catch (IOException ioError)
        {
            System.out.println(ioError.getMessage());
        }
    }

    public static void main(String[] args)
    {
        Personne[] tableau = new Personne[4];
        tableau[0] = new Personne("Marcelina", "00.387461.59.92", "+336.18.94.00.60");
        tableau[1] = new Personne("Josy", "00.3339846592", "+336.18.94.00.60");
        tableau[2] = new Personne("Hector", "00.38465.59.92", "+336.18.94.00.60");
        tableau[3] = new Personne("Prosper", "00.321.753.59.92", "+336.18.94.00.60");

        /*
        serializePersonne( tableau , PATH_JSON + "test07.json" );
        serializePersonne( tableau[ 2 ] , PATH_JSON + "test05.json" );
        */

        Personne test = deserializePersonne( PATH_JSON + "test05.json" );
        System.out.println(test.getTelephoneFixe());

        Personne[] blaireau = deserializePersonnes( PATH_JSON + "test07.json");

        for (int i = 0; i < blaireau.length; i++)
            System.out.println(blaireau[i].toString());
    }
}