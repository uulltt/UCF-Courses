
public class NumberTile {
	public int left, right, up, down;
    private String name;
    public NumberTile(int l, int u, int r, int d, String n)
    {
     left = l;
     right = r;
     up = u;
     down = d;
     name = n;
    }
    public String getName()
    {
     return name;
    }
    /** Rotates the tile 90 degrees clockwise */
    public void rotate()
    {
     int[] tmp = {left, up, right, down};
     left = tmp[3];
     up = tmp[0];
     right = tmp[1];
     down = tmp[2];
    }

    /** @return value at left edge of tile */
    public int getLeft()
    {
     return left;
    }

    /** @return value at right edge of tile */
    public int getRight()
    {
     return right;
    }
   
    public int[] getAll()
    {
     int[] a = {left, up, right, down };
     return a;
    }
   }


