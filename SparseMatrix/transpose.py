rows = 3
cols = 4


def pos_to_coords(position):
	return (position//cols,position%cols)

def coords_to_pos(coords):
	return (coords[1]*rows)+coords[0]

for i in range(12):
	print(f"{i}: {coords_to_pos(pos_to_coords(i))}")