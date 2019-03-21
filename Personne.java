public class Personne {

 // constantes ------------------------------------------------------------------------------------

    public static final String KEY_NOM       = "Nom";
    public static final String KEY_FIXE      = "Fixe";
    public static final String KEY_PHONES    = "Telephone";
    public static final String KEY_PORTABLE  = "Portable";


 // variables privées -----------------------------------------------------------------------------
    private String nom;
    private String telephoneFixe;
    private String telephonePortable;

 // constructeurs ---------------------------------------------------------------------------------

    Personne( String nom , String telFixe , String telPortable )
    {
        setNom( nom );
        setTelephoneFixe( telFixe );
        setTelephonePortable( telPortable );
    }

    Personne( JSONObject object )
    {
        this( object.getString(KEY_NOM) ,
              object.getJSONObject(KEY_PHONES).getString(KEY_FIXE),
              object.getJSONObject(KEY_PHONES).getString(KEY_PORTABLE));
    }

 // méthodes --------------------------------------------------------------------------------------

    public String getNom()
    {
        return nom;
    }

    public String getTelephoneFixe()
    {
        return telephoneFixe;
    }

    public String getTelephonePortable()
    {
        return telephonePortable;
    }

    public void setNom( String nom )
    {
        this.nom = nom;
    }

    public void setTelephoneFixe( String telephoneFixe )
    {
        this.telephoneFixe = telephoneFixe;
    }

    public void setTelephonePortable( String telephonePortable )
    {
        this.telephonePortable = telephonePortable;
    }

    public  JSONObject toJSONObject()
    {
        JSONObject objectPhones = new JSONObject();
        objectPhones.put( KEY_FIXE , getTelephoneFixe() );
        objectPhones.put( KEY_PORTABLE , getTelephonePortable() );

        JSONObject object  = new JSONObject();
        object.put( KEY_NOM , getNom() );
        object.put( KEY_PHONES , objectPhones );

        return object;
    }

    // toString() au format JSON
    public String toString()
    {
        return toJSONObject().toString();
    }
}