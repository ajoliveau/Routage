package routage;

public class Routage {

    private static int nbRouteurs;
    private static int nbLiaisons;
    private static int commande;
    private static int[][] matrice;
   
    public static void main(String[] args) {
        lectureEntree();
        switch(commande)
        {
            case 0 :
                comptage();
                break;
            case 1:
                adjacence();
                break;
        }
    }
    
    public static void lectureEntree()
    {
        int liaison1;
        int liaison2;
        nbRouteurs = Pep8.deci();
        nbLiaisons = Pep8.deci();
        matrice = new int [nbRouteurs][nbRouteurs];
        
        for (int i=0;i<nbLiaisons;i++)
        {
            liaison1 = Pep8.deci();
            liaison2 = Pep8.deci();
            matrice[liaison1][liaison2] = 1;
            matrice[liaison2][liaison1] = 1;
        }
        
        commande = Pep8.deci();
    }
    
    public static void comptage()
    {
        Pep8.stro("n=");
        Pep8.deco(nbRouteurs);
        Pep8.stro("\nm=");
        Pep8.deco(nbLiaisons);
        Pep8.charo('\n');
    }
    
    public static void adjacence()
    {
        for (int i=0;i<nbRouteurs;i++)
        {
            for (int j=0;j<nbRouteurs;j++)
            {
                Pep8.deco(matrice[i][j]);
                Pep8.charo(' ');
            }
            Pep8.charo('\n');
        }
    }
    
    public static void distance()
    {
        
    }
}
