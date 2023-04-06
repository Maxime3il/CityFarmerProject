package model;

/**
 * La classe Position représente une position dans un espace à deux dimensions.
 * Une position est définie par deux coordonnées, x et y.
 */
public class Position {
	/**
	 * Coordonnée x de la position.
	 */
	private int x;
	
	/**
	 * Coordonnée y de la position.
	 */
    private int y;

    /**
     * Constructeur de la classe Position.
     * @param x La coordonnée x de la position.
     * @param y La coordonnée y de la position.
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Retourne la coordonnée x de la position.
     * @return La coordonnée x.
     */
    public int getX() {
        return x;
    }

    /**
     * Modifie la coordonnée x de la position.
     * @param x La nouvelle coordonnée x.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Retourne la coordonnée y de la position.
     * @return La coordonnée y.
     */
    public int getY() {
        return y;
    }

    /**
     * Modifie la coordonnée y de la position.
     * @param y La nouvelle coordonnée y.
     */
    public void setY(int y) {
        this.y = y;
    }
}
