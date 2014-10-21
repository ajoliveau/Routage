public class Routage {

    private static int nbRouteurs;
    private static int nbLiaisons;
    private static int commande;
    private static int[][] matrice;
    private static int[][] predecesseurs;
   
    public static void main(String[] args) {
        lectureEntree();
        switch(commande)
        {
            case 0 :
                comptage();
                break;
            case 1:
                afficherMatrice();
                break;
            case 2:
                creerMatriceDistances();
                afficherMatrice();
                break;
            case 3:
                creerMatricePredecesseurs();
                creerMatriceDistances();
                afficherChemins();
                break;
        }
    }
    
    public static void lectureEntree() {
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
    
    public static void creerMatriceDistances() {
        
        formaterMatrice();
        
        for (int k = 0; k < nbRouteurs; k++)
        {
            for (int i = 0; i < nbRouteurs; i++)
            {
                for (int j = 0; j < nbRouteurs; j++)
                {
                    if (matrice[i][k] != Integer.MAX_VALUE && matrice[k][j] != Integer.MAX_VALUE)
                    {
                        if (matrice[i][j] > matrice[i][k] + matrice[k][j]) {
                            matrice[i][j] = matrice[i][k] + matrice[k][j];
                            if (commande == 3)
                                predecesseurs[i][j] = predecesseurs[k][j];
                        }
                    }
                }
            }
        }
        
       
        
    }
    
    public static void afficherChemins()
    {
        int cheminEntree = Pep8.deci();
        int cheminSortie;
        int[] chemin = new int[nbRouteurs];
        int i;
        
        while (cheminEntree != -1)
        {
            i = 0;
            cheminSortie = Pep8.deci();
            while(cheminEntree != cheminSortie && predecesseurs[cheminEntree][cheminSortie] != -1)
            {
                chemin[i] =  cheminSortie;
                cheminSortie = predecesseurs[cheminEntree][cheminSortie];
                i++;
            }
            if (predecesseurs[cheminEntree][cheminSortie] == -1)
            {
                Pep8.deco(cheminEntree);
                Pep8.charo('-');
                Pep8.charo('X');
                Pep8.charo('-');
                Pep8.charo('>');
                Pep8.deco(cheminSortie);
                Pep8.charo('\n');
            }
            else {
                chemin[i] = cheminEntree;

                for (int j = i; j>0; j--)
                {
                    Pep8.deco(chemin[j]);
                    Pep8.charo('-');
                    Pep8.charo('>');
                }
                Pep8.deco(chemin[0]);
                Pep8.charo('\n');
            }
            
            cheminEntree = Pep8.deci();
            
        }
    }
    
    public static void creerMatricePredecesseurs()
    {
        predecesseurs = new int[nbRouteurs][nbRouteurs];
        for (int i = 0; i < nbRouteurs; i++) {
            for (int j = 0; j < nbRouteurs; j++) {
                if (matrice[i][j] != 0)
                    predecesseurs[i][j] = i;
                else
                    if (i == j)
                        predecesseurs[i][j] = 0;
                    else        
                        predecesseurs[i][j] = -1;
            }
            
        }
    }
    
    public static void afficherMatrice() {
        for (int i=0;i<nbRouteurs;i++)
        {
            for (int j=0;j<nbRouteurs;j++)
            {
                if (j != 0)
                    Pep8.charo(' ');
                if (matrice[i][j] != Integer.MAX_VALUE)
                    Pep8.deco(matrice[i][j]);
                else
                    Pep8.charo('x');
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
                    matrice[i][j] = Integer.MAX_VALUE;
                }
            }
        }
    }
 
   
}
