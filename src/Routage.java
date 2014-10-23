/**
 * Programme Routage
 * 
 * A partir de la description d'un réseau comportant une liste de routeurs et 
 * les connexions entre eux, le programme permet différentes opérations de 
 * routage dépendant d'une commande entrée par l'utilisateur :
 * commande 0 : affiche le nombre de routeurs et le nombre de réseau
 * commande 1 : affiche la matrice d'adjacence du réseau
 * commande 2 : affiche la matrice des distances minimales du réseau
 * commande 3 : affiche les chemins les plus courts pour des paires de routeurs
 * 
 * Paramètres (tous entiers) :
 * - Le nombre de routeurs entre 1 et 16
 * - Le nombre de liaisons, positif
 * - Pour chaque liaison, une paire de numéros de routeurs, positifs
 * - Le numéro de la commande entre 0 et 3
 * - Si la commande est 3, une liste de paires de routeurs, terminée par -1
 * 
 * 
 * 
 * Programme par Arthur Joliveau-Breney
 * JOLA11049104
 */

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
                afficherMatrice(); // la matrice est déja initialisée dans lectureEntree()
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
            default:
                Pep8.stro("Erreur de commande");
        }
    }
    
    
    
    /**
     * Lit la topologie en entrée jusqu'a la commande et cree la matrice d'adjacence.
    */
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
            if (liaison1 != liaison2) { // ignore les liaisons reliant un routeur a lui-meme
                matrice[liaison1][liaison2] = 1;
                matrice[liaison2][liaison1] = 1;
            }
        }
        
        commande = Pep8.deci();
    }
    /**
     * Affiche le resultat attendu pour la première commande
     */
    public static void comptage() {
        Pep8.stro("n=");
        Pep8.deco(nbRouteurs);
        Pep8.stro("\nm=");
        Pep8.deco(nbLiaisons);
        Pep8.charo('\n');
    }
    
    /**
     * Crèe la matrice des distances en utilisant l'algorithme de Floyd-Warshall
     * 
     * Boucle dans chaque routeur k et, pour chacun d'entre eux, boucle dans
     * tous les chemins possibles entre deux routeurs i et j. 
     * Si il est possible
     * et plus rapide de passer par k pour aller de i à j, met a jour la matrice.
     */
    public static void creerMatriceDistances() {
        
        formaterMatrice();  //nécessaire pour différencier les 0 dans la diagonale des autres
        
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
                            if (commande == 3) // La matrice predecesseurs n'est necessaire que pour obtenir les chemins
                                predecesseurs[i][j] = predecesseurs[k][j];
                        }
                    }
                }
            }
        }
        
       
        
    }
    /**
     * Pour la commande 3, lis les paires de routeurs en entrée du programme et affiche
     * le chemin le plus court pour aller d'un routeur a l'autre de chacune des paires.
     * 
     * Utilise la matrice des prédecesseurs construite dans creerMatriceDistances()
     * pour retrouver chaque prédecesseur du routeur de sortie lié au routeur d'entrée. 
     * La fonction stocke ces predecesseurs dans un tableau, puis les affiche dans l'ordre.
     */
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
                i++;    // sert a savoir l'indice maximal de chemin[] utilisé pour pouvoir le 
                        // parcourir à l'envers lors de l'affichage du chemin
            }
            if (predecesseurs[cheminEntree][cheminSortie] == -1) // s'il n'y a pas de chemin
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

                for (int j = i; j>0; j--) // on parcours chemin[] a l'envers
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
    /**
     * Crée la matrice des predecesseurs initiale
     */
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
    
    /**
     * Affiche la matrice (d'adjacence ou de distances selon la commande)
     * 
     * Prends en compte le formatage potentiel où les 0 qui ne sont pas sur la
     * diagonale sont transformés en ∞ : dans ce cas on affiche un x a la place
     */
    
    public static void afficherMatrice() {
        for (int i=0;i<nbRouteurs;i++)
        {
            for (int j=0;j<nbRouteurs;j++)
            {
                if (j != 0) {
                    Pep8.charo(' ');
                }
                if (matrice[i][j] != Integer.MAX_VALUE) {
                    Pep8.deco(matrice[i][j]);
                } 
                else {
                    Pep8.charo('x');
                }
            }
            Pep8.charo('\n');
        }
    }
    /**
     * Formate la matrice pour la rendre compatible avec l'algorithme de Floyd-Warshall
     * 
     * Fais la distinction entre les 0 sur la diagonale et les 0 en dehors. Ces derniers sont
     * remplacés par l'équivalent de l'infini (la plus haute valeur possible pour un int)
     */
    
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
