
public class Tile {
	
	private int getIndexForFit(NumberTile tile)
	{
	if ((this.board.size() == 0) ||
	(tile.getRight() == this.board.get(0).getLeft()))
	return 0;
	for (int i = 1; i < this.board.size(); i++)
	{
	if (tile.getLeft() == this.board.get(i-1).getRight() &&
	tile.getRight() == this.board.get(i).getLeft())
	return i;
	}
	if (tile.getLeft() == this.board.get(this.board.size() - 1).getRight())
	return this.board.size();
	return -1;
	}
	
	public boolean insertTile(NumberTile tile)
	{
	int index = getIndexForFit(tile);
	int test = 1;
	while (index == -1 && test < 4)
	{
	tile.rotate();
	index = getIndexForFit(tile);
	test++;
	}
	if (index != -1)
	this.board.add(index, tile);
	return (index != -1);
	} 

}
