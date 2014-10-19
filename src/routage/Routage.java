package routage;

public class Routage {

    private static int nbRouteurs;
    private static int nbLiaisons;
    private static int commande;
    private static int[][] matrice;
    private static int[][] 
   
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
            case 2:
                distance();
                break;
        }
    }
    
    public static void lectureEntree() {
        int liaison1;
        int liaison2;
        int 
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
        
        if (commande == 3) {
            while 
        }
    }
    
    public static void comptage() {
        Pep8.stro("n=");
        Pep8.deco(nbRouteurs);
        Pep8.stro("\nm=");
        Pep8.deco(nbLiaisons);
        Pep8.charo('\n');
    }
    
    public static void adjacence() {
        afficherMatrice();
    }
    
    public static void distance() {
        
        formaterMatrice();
        
        for (int k = 0; k < nbRouteurs; k++)
        {
            for (int i = 0; i < nbRouteurs; i++)
            {
                for (int j = 0; j < nbRouteurs; j++)
                {
                    if (matrice[i][k] != Integer.MAX_VALUE/2 && matrice[k][j] != Integer.MAX_VALUE/2)
                    {
                        if (matrice[i][j] > matrice[i][k] + matrice[k][j]) {
                            matrice[i][j] = matrice[i][k] + matrice[k][j];
                        }
                    }
                }
            }
        }
        afficherMatrice();
    }
    
    public static void afficherMatrice() {
        Pep8.charo('\n');
        for (int i=0;i<nbRouteurs;i++)
        {
            for (int j=0;j<nbRouteurs;j++)
            {
                if (matrice[i][j] != Integer.MAX_VALUE/2)
                    Pep8.deco(matrice[i][j]);
                else
                    Pep8.charo('x');
                Pep8.charo(' ');
            }
            Pep8.charo('\n');
        }
    }
    public static void formaterMatrice() {
        for (int i=0;i<nbRouteurs;i++)
        {
            for (int j=0;j<nbRouteurs;j++)
            {
                if (i != j && matrice[i][j] == 0)
                {
                    matrice[i][j] = Integer.MAX_VALUE/2;
                }
            }
        }
    }
 
   
}
